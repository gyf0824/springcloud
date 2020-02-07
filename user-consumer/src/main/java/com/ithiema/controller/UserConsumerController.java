package com.ithiema.controller;

import com.ithiema.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class UserConsumerController {

    private String URL = "http://127.0.0.1:9090/user";

    //使用restTemplate来发送获取用户微服务的数据
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserClient userClient;

    //GET请求 localhost:9091/consumer 查询所有用户
    @GetMapping
    public Map findAll(){
        //第一种方式：使用ResultTemplate对象进行RESTFull风格的请求
        //        Map result = restTemplate.getForObject(URL, Map.class);
        //第二种方式：从eureka注册中心获取注册的实例
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-service-provider");
//        ServiceInstance serviceInstance = instances.get(0);
//        //获取主机
//        String host = serviceInstance.getHost();
//        //获取端口号
//        int port = serviceInstance.getPort();
//        //拼接起来 http://localhost:9090/user
//        String url = "http://" + host + ":" + port + "/user";
//        Map result = restTemplate.getForObject(url, Map.class);
        //第三种方式：通过Feign获取数据
        Map result = userClient.findAll();
        return result;
    }

    //POST请求 localhost:9091/consumer 新增用户
    @PostMapping
    public Map save(@RequestBody Map user){
        //参数1：请求的地址
        //参数2：需要携带的参数
        //参数3：返回的结果集字节码
//        Map result = restTemplate.postForObject(URL, user, Map.class);
        Map result = userClient.save(user);
        return result;
    }

    //PUT请求  localhost:9091/consumer 更新用户
    @PutMapping
    public Map update(@RequestBody Map user){
//        restTemplate.put(URL,user);
//        //封装返回结果集
//        Map result = new HashMap();
        Map result = userClient.update(user);
        result.put("status",200);
        result.put("message","修改成功");
        return result;
    }

    //DELETE请求  localhost:9091/consumer/id 根据主键id删除用户
    @DeleteMapping("/{id}")
    public Map deleteById(@PathVariable("id") Integer id){
//        String URL1 = URL+ "/" + id;
//        restTemplate.delete(URL1);
//        //封装返回结果集
//        Map result = new HashMap();
        Map result = userClient.deleteById(id);
        result.put("status",200);
        result.put("message","删除成功");
        return result;
    }

    // GET请求  localhost:9091/consumer/id 根据主键id查询指定的用户
    @GetMapping("/{id}")
    public Map findById(@PathVariable("id") Integer id){
//        String URL2 = URL+ "/" + id;
//        Map result = restTemplate.getForObject(URL2, Map.class, id);
        Map result = userClient.findById(id);
        return result;
    }

}
