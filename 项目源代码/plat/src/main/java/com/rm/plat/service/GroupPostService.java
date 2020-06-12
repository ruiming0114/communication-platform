package com.rm.plat.service;

import com.rm.plat.pojo.GroupPost;

import java.util.List;

public interface GroupPostService {

    GroupPost queryGroupPostById(int grouppostid);
    List<GroupPost> queryGroupPostListByGroup(int groupid);
    List<GroupPost> queryGroupPostListByUser(int userid);
    List<GroupPost> queryGroupPostListByUserInPage(int userid,int page,int size);


    int addGroupPost(GroupPost groupPost);
    int updateGroupPost(GroupPost groupPost);
    int deleteGroupPost(int grouppostid);
    List<GroupPost> queryRecommendPost();
    int cnt();
    int max();
}
