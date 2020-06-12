package com.rm.plat.mapper;

import com.rm.plat.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    User queryUserByName(String name);

    List<User> queryUserList();
    User queryUserByID(int userid);

    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int userid);
    int cnt();

    int max();
}
