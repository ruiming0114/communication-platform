package com.rm.plat.mapper;

import com.rm.plat.pojo.GroupPost;
import com.rm.plat.pojo.GroupPostReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupPostReplyMapper {

    GroupPostReply queryGroupPostReplyById(int grouppostreplyid);
    List<GroupPostReply> queryGroupPostReplyByGroupPost(int grouppostid);
    List<GroupPostReply> queryGroupPostReplyByUser(int userid);

    int addGroupPostReply(GroupPostReply groupPostReply);
    int updateGroupPostReply(GroupPostReply groupPostReply);
    int deleteGroupPostReply(int grouppostreplyid);

    int cnt();
    int max();
    int cntByGroupPost(int grouppostid);
}
