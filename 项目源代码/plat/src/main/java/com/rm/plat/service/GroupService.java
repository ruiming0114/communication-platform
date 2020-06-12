package com.rm.plat.service;

import com.rm.plat.pojo.Group;

import java.util.List;

public interface GroupService {
    Group queryGroupById(int groupid);
    List<Group> queryGroupByName(String groupname);

    List<Group> queryGroupByNameInPage(String name,int page,int size);
    List<Group> queryGroupList();
    List<Group> queryGroupListInPage(int page,int size);
    List<Group> queryRecommendGroupIn5();

    int addGroup(Group group);
    int updateGroup(Group group);
    int deleteGroup(int groupid);

    List<Group> queryRecommendGroup();
    List<Group> queryRecommendGroup5();
    List<Group> queryNewGroup();


    int cnt();
    int max();
}
