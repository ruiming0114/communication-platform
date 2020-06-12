package com.rm.plat.service;

import com.rm.plat.mapper.TopicAttentionMapper;
import com.rm.plat.mapper.TopicMapper;
import com.rm.plat.mapper.TopicReportMapper;
import com.rm.plat.pojo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    TopicMapper topicMapper;

    @Autowired
    TopicAttentionMapper topicAttentionMapper;

    @Autowired
    TopicCommentService topicCommentService;

    @Autowired
    TopicReportMapper topicReportMapper;

    @Override
    public Topic queryTopicById(int topicid) {
        return topicMapper.queryTopicById(topicid);
    }

    @Override
    public List<Topic> queryHotTopicList() {
        return topicMapper.queryHotTopicList();
    }

    @Override
    public List<Topic> queryHotTopicList5() {
        List<Topic> list = topicMapper.queryHotTopicList();
        while (list.size()<5){
            list.add(null);
        }
        return list;
    }

    @Override
    public Topic queryTopicByTitle(String title) {
        return topicMapper.queryTopicByTitle(title);
    }

    @Override
    public List<Topic> queryTopicByClassify(String classify) {
        return topicMapper.queryTopicByClassify(classify);
    }

    @Override
    public List<Topic> queryTopicByUser(int userid) {
        return topicMapper.queryTopicByUser(userid);
    }

    @Override
    public List<Topic> queryTopicList() {
        return topicMapper.queryTopicList();
    }

    @Override
    public List<Topic> queryNewTopicList() {
        return topicMapper.queryNewTopicList();
    }

    @Override
    public int addTopic(Topic topic) {
        topicMapper.addTopic(topic);
        return 1;
    }

    @Override
    public List<Topic> queryTopicByName(String name) {
        return topicMapper.queryTopicByName(name);
    }

    @Override
    public int updateTopic(Topic topic) {
        topicMapper.updateTopic(topic);
        return 1;
    }

    @Override
    public int deleteTopic(int topicid) {
        List<TopicAttention> list = topicAttentionMapper.queryAttentionListByTopicid(topicid);
        for (TopicAttention topicAttention:list){
            topicAttentionMapper.deleteTopicAttention(topicAttention.getTopicid(),topicAttention.getUserid());
        }
        List<TopicComment> list1 = topicCommentService.queryTopicCommentByTopic(topicid);
        for (TopicComment topicComment:list1){
            topicCommentService.deleteTopicComment(topicComment.getCommentid());
        }
        List<TopicReport> list2 = topicReportMapper.queryTopicReportByTopic(topicid);
        for (TopicReport topicReport:list2){
            if (topicReport.getDeal()!=1){
                topicReportMapper.deleteTopicReport(topicReport.getTopicreportid());
            }
        }
        topicMapper.deleteTopic(topicid);
        return 1;
    }

    @Override
    public int cnt() {
        return topicMapper.cnt();
    }

    @Override
    public int max() {
        return topicMapper.max();
    }

    @Override
    public List<Topic> queryMyCommentTopic(int userid) {
        List<TopicComment> list = topicCommentService.queryTopicCommentByUser(userid);
        List<Topic> res = new ArrayList<>();
        for (TopicComment topicComment:list){
            res.add(topicMapper.queryTopicById(topicComment.getTopicid()));
        }
        return res;
    }

    @Override
    public List<Topic> queryMyCommentTopic3(int userid) {
        List<TopicComment> list = topicCommentService.queryTopicCommentByUser(userid);
        List<Topic> res = new ArrayList<>();
        for (TopicComment topicComment:list){
            res.add(topicMapper.queryTopicById(topicComment.getTopicid()));
        }
        if (res.size()>3){
            return res.subList(0,3);
        }
        else {
            return res;
        }
    }

    @Override
    public List<Topic> queryTopicByUserInPage(int userid, int page, int size) {
        List<Topic> res = new ArrayList<>();
        List<Topic> list =topicMapper.queryTopicByUser(userid);
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public List<Topic> queryTopicListInPage(int page, int size) {
        List<Topic> res = new ArrayList<>();
        List<Topic> list =topicMapper.queryTopicList();
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }
}
