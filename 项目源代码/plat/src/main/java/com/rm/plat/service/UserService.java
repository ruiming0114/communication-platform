package com.rm.plat.service;

import com.rm.plat.pojo.User;

import java.util.List;

public interface UserService {
    User queryUserByName(String name);
    User queryUserByID(int userid);
    List<User> queryUserList();
    List<User> queryUserListInPage(int page,int size);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int userid);

    int cnt();
    int max();
}
