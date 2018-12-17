package com.mir.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.mir.demo.entity.T_user;
import com.mir.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@RestController
public class UserContoroller {
    @Autowired
    private UserService service;
    @RequestMapping(value = "/simple/{id}",method = RequestMethod.GET)
    public Optional<T_user> findAll(@PathVariable String id){
        return this.service.findUserById(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Object saveUser(T_user user){
        System.out.println("-----------------"+user.toString());
        /*T_user user = new T_user();
        user.setCreatee_time(new Date());
        user.setId(UUID.randomUUID().toString());
        user.setMobile("6666666");
        user.setPassword("6666666");
        user.setUserName("6666666666");*/
        //return  service.save(user);
        
        return "55555";
    }
    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.GET,produces = "text/html")
    public Object test(){
        return "<html>12312<h1>11111</h1><button type=\"button\">Click Me!</button></html>";
    }

    public JSONObject getJSONParam(HttpServletRequest request) {
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
            // 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }
}
