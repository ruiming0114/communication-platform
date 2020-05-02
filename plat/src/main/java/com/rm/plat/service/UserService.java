package com.rm.plat.service;

import com.rm.plat.pojo.User;

public interface UserService {
    User queryUserByName(String name);
    User queryUserByID(int userid);

    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int userid);

    int cnt();
}
