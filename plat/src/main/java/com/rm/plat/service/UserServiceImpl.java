package com.rm.plat.service;

import com.rm.plat.mapper.UserMapper;
import com.rm.plat.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    public User queryUserByID(int userid) {
        return userMapper.queryUserByID(userid);
    }

    @Override
    public int addUser(User user) {
        userMapper.addUser(user);
        return 1;
    }

    @Override
    public int updateUser(User user) {
        userMapper.updateUser(user);
        return 1;
    }

    @Override
    public int deleteUser(int userid) {
        userMapper.deleteUser(userid);
        return 1;
    }

    @Override
    public int cnt() {
        return userMapper.cnt();
    }
}
