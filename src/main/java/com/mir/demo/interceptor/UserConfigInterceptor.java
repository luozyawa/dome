package com.mir.demo.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.mir.demo.auth.JwtTokenUtil;
import com.mir.demo.dao.T_userRepository;
import com.mir.demo.entity.T_user;
import com.mir.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class UserConfigInterceptor implements HandlerInterceptor{
    @Autowired
    private T_userRepository userDao;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        /*if (!(handler instanceof HandlerMethod)) {
            return true;
        }*/

        System.out.println("-----------------------自定义拦截器------------------------------");
        String client_token = request.getHeader("token");
        if(StringUtils.isEmpty(client_token)){
            client_token = request.getParameter("token");
        }

        if(!StringUtils.isEmpty(client_token)){
            //验证token
            Map<String, Claim> claims =  JwtTokenUtil.verifyToken(client_token);
            Claim userName_claim = claims.get("userName");
            System.out.println("======================"+userName_claim.asString()+"===================");
            String redis_token = (String) redisTemplate.opsForValue().get(userName_claim.asString());
            if(!StringUtils.isEmpty(redis_token)){
                return true;
            }else {
                PrintWriter out = response.getWriter();
                out.write("token out time or token error!!");
                return false;
            }
        }else{
            PrintWriter out = response.getWriter();
            if("/auth".equals(request.getRequestURI())){
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if(!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(password)){
                    List<T_user> tus = userDao.queryUser(username,password);
                    if(tus!=null&&tus.size()>0){
                        //数据库验证用户名密码
                        List<T_user> users = userDao.queryUser(username,password);
                        if(users.size()>0){
                            //生成token
                            String create_token = JwtTokenUtil.createToken(username);
                            //保存token到redis
                            redisTemplate.opsForValue().set(username,create_token,60, TimeUnit.MINUTES);
                            out.write(create_token);
                        }else{
                            out.write("username or password error");
                        }
                    }else{
                        out.write("username or password error");
                    }
                }else{
                    out.write("not find username or password");
                }
            }else{
                out.write("request path erre!!!");
            }
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
