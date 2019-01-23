package com.example.service;

import com.example.utils.response.RespEntity;

public interface CommentService {
    //获取本人收到的评价
    RespEntity getMyList(int id);
    RespEntity add(int commentatorId, int respondent, String content, int score);
    RespEntity del(int commentatorId, int respondent);
    //获取本人发出的评价
    RespEntity getSentList(int id);
}
