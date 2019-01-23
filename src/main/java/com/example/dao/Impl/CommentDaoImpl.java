package com.example.dao.Impl;

import com.example.dao.CommentDao;
import com.example.entity.Comment;
import com.example.utils.RowMapper.CommentRowMapper;
import com.example.utils.response.RespCode;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CommentDaoImpl(JdbcTemplate j){
        this.jdbcTemplate = j;
    }

    @Override
    public RespEntity getMyList(int id) {
        String sql = "SELECT * FROM COMMENT WHERE RESPONDENT=?";
        List<Comment> comments;
        try {
            comments = jdbcTemplate.query(sql, new CommentRowMapper(), id);
            if (comments == null)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, comments);
        }catch (DataAccessException e){
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }

    @Override
    public boolean add(int commentatorId, int respondent, String content, int score) {
        //自己不可以给自己刷分
        if (commentatorId == respondent)
            return false;
        String sql = "INSERT INTO COMMENT(COMMENTATOR_ID,RESPONDENT,CONTENT,SCORE) VALUES (?,?,?,?)";
        int insert;
        try{
            insert = jdbcTemplate.update(sql, commentatorId, respondent, content, score);
            if (insert == 0)
                return false;
            return true;
        }catch (DataAccessException e){
            return false;
        }
    }

    @Override
    public boolean del(int commentatorId, int respondent) {
        String sql = "DELETE FROM COMMENT WHERE COMMENTATOR_ID=? AND RESPONDENT=?";
        int del;
        try {
            del = jdbcTemplate.update(sql, commentatorId, respondent);
            if (del == 0)
                return false;
            return true;
        }catch (DataAccessException e){
            return false;
        }
    }

    @Override
    public RespEntity getSentList(int id) {
        String sql = "SELECT * FROM COMMENT WHERE COMMENTATOR_ID=?";
        List<Comment> comments;
        try {
            comments = jdbcTemplate.query(sql, new CommentRowMapper(), id);
            if (comments == null)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, comments);
        }catch (DataAccessException e){
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }

    @Override
    public double getScore(int id) {
        double score = -1;
        List<Double> list;
        String sql = "SELECT SUM(SCORE)/COUNT(*) FROM comment WHERE RESPONDENT=?";
        try {
            list = jdbcTemplate.queryForList(sql, Double.class, id);
            score = list.get(0);
        }catch (DataAccessException e){
            score = -99;
        }
        return score;
    }
}
