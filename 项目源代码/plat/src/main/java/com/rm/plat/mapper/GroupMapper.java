package com.rm.plat.mapper;


import com.rm.plat.pojo.Group;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupMapper {

    Group queryGroupById(int groupid);
    List<Group> queryGroupByName(String groupname);
    List<Group> queryGroupList();
    List<Group> queryRecommendGroup();
    List<Group> queryNewGroup();
    List<Group> queryRecommendGroupIn5();

    int addGroup(Group group);
    int updateGroup(Group group);
    int deleteGroup(int groupid);

    int cnt();
    int max();

}
