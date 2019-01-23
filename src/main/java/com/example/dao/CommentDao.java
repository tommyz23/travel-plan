package com.example.dao;

import com.example.utils.response.RespEntity;

public interface CommentDao {
    //获取本人收到的评价
    RespEntity getMyList(int id);
    boolean add(int commentatorId, int respondent, String content, int score);
    boolean del(int commentatorId, int respondent);
    //获取本人发出的评价
    RespEntity getSentList(int id);
    double getScore(int id);
}
