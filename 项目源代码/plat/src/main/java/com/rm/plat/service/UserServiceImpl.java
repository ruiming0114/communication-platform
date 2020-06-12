package com.rm.plat.service;

import com.rm.plat.mapper.UserMapper;
import com.rm.plat.pojo.Book;
import com.rm.plat.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> queryUserList() {
        return userMapper.queryUserList();
    }

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
    public List<User> queryUserListInPage(int page, int size) {
        List<User> res = new ArrayList<>();
        List<User> list = new ArrayList<>();
        List<User> list1 = userMapper.queryUserList();
        for (User user:list1){
            if (user.getPerms().equals("user")){
                list.add(user);
            }
        }
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public int cnt() {
        return userMapper.cnt();
    }

    @Override
    public int max() {
        return userMapper.max();
    }
}
