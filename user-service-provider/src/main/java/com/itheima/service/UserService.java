package com.itheima.service;

import com.itheima.dao.UserDao;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.selectAll();
    }

    public void save(User user) {
        userDao.insert(user);
    }

    public void update(User user) {
        userDao.updateByPrimaryKey(user);
    }

    public void delete(Integer id) {
        userDao.deleteByPrimaryKey(id);
    }

    public User findById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

}
