package com.example.service.Impl;

import com.example.dao.CommentDao;
import com.example.dao.UserDao;
import com.example.service.CommentService;
import com.example.utils.response.RespCode;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final UserDao userDao;
    @Autowired
    public CommentServiceImpl(CommentDao c, UserDao u){
        this.commentDao = c;
        this.userDao = u;
    }

    @Override
    public RespEntity getMyList(int id) {
        return commentDao.getMyList(id);
    }

    @Override
    public RespEntity add(int commentatorId, int respondent, String content, int score) {
        boolean add = commentDao.add(commentatorId, respondent, content, score);
        Double midScore = commentDao.getScore(respondent);
        boolean setScore = userDao.setScore(midScore, respondent);
        if (setScore && add)
            return new RespEntity(RespCode.SUCCESS, 1);
        return new RespEntity(RespCode.WARN);
    }

    @Override
    public RespEntity del(int commentatorId, int respondent) {
        boolean del = commentDao.del(commentatorId, respondent);
        double midScore = commentDao.getScore(respondent);
        boolean set = userDao.setScore(midScore, respondent);
        if (set && del)
            return new RespEntity(RespCode.SUCCESS, 1);
        return new RespEntity(RespCode.WARN);
    }

    @Override
    public RespEntity getSentList(int id) {
        return commentDao.getSentList(id);
    }
}
