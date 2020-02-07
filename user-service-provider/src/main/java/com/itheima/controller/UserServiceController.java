package com.itheima.controller;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserServiceController {
    @Autowired
    private UserService userService;


    //GET请求，http:localhost:9090/user  查询所有用户
    @GetMapping
    public Map findAll(){
        List<User> userList = userService.findAll();
        Map result = new HashMap();
        result.put("status",200);
        result.put("message","查询全部成功");
        result.put("data",userList);
        return result;
    }

    //POST请求，http:localhost:9090/user  新增用户
    @PostMapping
    public Map save(@RequestBody User user){
        userService.save(user);
        Map result = new HashMap();
        result.put("status",200);
        result.put("message","保存成功");
        return result;
    }


    //PUT请求，http:localhost:9090/user  更新用户
    @PutMapping
    public Map update(@RequestBody User user){
        userService.update(user);
        Map result = new HashMap();
        result.put("status",200);
        result.put("message","修改成功");
        return result;
    }

    //DELETE请求，http:localhost:9090/user/id  根据主键id删除用户
    @DeleteMapping("/{id}")
    public Map deleteById(@PathVariable("id") Integer id){
        userService.delete(id);
        Map result = new HashMap();
        result.put("status",200);
        result.put("message","根据主键id删除成功");
        return result;
    }

    //GET请求，http:localhost:9090/user/id  根据主键id查询用户
    @GetMapping("/{id}")
    public Map findById(@PathVariable("id") Integer id){
        User user = userService.findById(id);
        Map result = new HashMap();
        result.put("status",200);
        result.put("message","根据主键id查询成功");
        result.put("data",user);
        return result;
    }

}
