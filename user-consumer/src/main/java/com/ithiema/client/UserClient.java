package com.ithiema.client;

import com.ithiema.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("user-service-provider")
public interface UserClient {

    //调用user-service-provider工程中的定义的接口
    //获取所有用户数据，GET请求方式
    //GET http://localhost/9090/user
    @GetMapping("/user")
    public Map findAll();

    @GetMapping("/user/{id}")
    //使用feign进行微服务调用时，有参数必须在@PathVariable("id")指定
    public Map findById(@PathVariable("id") Integer id);

    @PostMapping("/user")
    public Map save(@RequestBody Map user);

    //PUT请求  localhost:9090/consumer 更新用户
    @PutMapping
    public Map update(@RequestBody Map user);

    //DELETE请求  localhost:9090/consumer/id 根据主键id删除用户
    @DeleteMapping("/{id}")
    public Map deleteById(@PathVariable("id") Integer id);
}
