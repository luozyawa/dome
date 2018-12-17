package com.mir.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @RequestMapping(value = "/addString/{key}/{value}")
    public  String  addString(@PathVariable String key, @PathVariable String value){
        stringRedisTemplate.opsForValue().set(key+"",value+"");
        return "ok";
    }

    @RequestMapping(value = "/getString/{key}")
    public String getString(@PathVariable String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    @RequestMapping(value = "/setMap",method = RequestMethod.POST)
    public Map addMap(String name){
        Map map = new HashMap();
        map.put("a","aaaa");
        map.put("b","bbbbb");
        stringRedisTemplate.opsForHash().putAll(name,map);
        return map;
    }

    @RequestMapping(value = "/getMap",method = RequestMethod.POST)
    public Object getMap(String name){
        return stringRedisTemplate.opsForHash().get(name,"a");

    }

    @RequestMapping(value = "/setList",method = RequestMethod.POST)
    public Object setList(String name){
        List list  = new ArrayList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        list.add("ff");
        stringRedisTemplate.opsForList().leftPushAll(name,list);
        return "ok";
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    public Object getList(String name){
        return stringRedisTemplate.opsForList().range(name,0,-1);
    }

    @RequestMapping(value = "/setSet",method = RequestMethod.POST)
    public Object setSet(String key,String value){
        String[] strings = new String[]{value};
        stringRedisTemplate.opsForSet().add(key,strings);
        return "ok";
    }

    @RequestMapping(value = "/getSet",method = RequestMethod.POST)
    public Object getSet(String key){
      return stringRedisTemplate.opsForSet().members(key);
    }

    @RequestMapping(value = "/setStr1",method = RequestMethod.GET)
    public Object setStr1(String key,String value){

    /*    RedisService service = new RedisService();
        RedisTemplate redisTemplate = service.create();*/
        System.out.println("============="+redisTemplate.opsForValue().getOperations().getKeySerializer());
        redisTemplate.opsForValue().set(key,value);
         return "ok1";
    }
}
