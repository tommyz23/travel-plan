package com.example.entity;

public class Comment {
    private int commentatorId;
    private int respondent;
    private String content;
    private int score;

    public int getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(int commentatorId) {
        this.commentatorId = commentatorId;
    }

    public int getRespondent() {
        return respondent;
    }

    public void setRespondent(int respondent) {
        this.respondent = respondent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
