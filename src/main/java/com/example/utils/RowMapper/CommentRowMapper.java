package com.example.utils.RowMapper;

import com.example.entity.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentatorId(resultSet.getInt("commentator_id"));
        comment.setContent(resultSet.getString("content"));
        comment.setRespondent(resultSet.getInt("respondent"));
        comment.setScore(resultSet.getInt("score"));
        return comment;
    }
}
