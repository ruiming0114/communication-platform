package com.rm.plat.mapper;

import com.rm.plat.pojo.Group;
import com.rm.plat.pojo.GroupPost;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupPostMapper {

    GroupPost queryGroupPostById(int grouppostid);
    List<GroupPost> queryGroupPostListByGroup(int groupid);
    List<GroupPost> queryGroupPostListByUser(int userid);

    List<GroupPost> queryRecommendPost();

    int addGroupPost(GroupPost groupPost);
    int updateGroupPost(GroupPost groupPost);
    int deleteGroupPost(int grouppostid);

    int cnt();
    int max();
}
