package com.example.controller;

import com.example.service.CommentService;
import com.example.utils.response.RespCode;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService c) {
        this.commentService = c;
    }

    @RequestMapping("/getMyList")
    public RespEntity getMyList(HttpServletRequest request) {
        String id_s = request.getParameter("id");
        if (id_s == null)
            return new RespEntity(RespCode.WRONGPARAM);
        int id = Integer.parseInt(id_s);
        return commentService.getMyList(id);
    }

    @RequestMapping("/add")
    public RespEntity add(HttpServletRequest request) {
        String commentatorId_s = request.getParameter("commentatorId");
        String respondent_s = request.getParameter("respondent");
        String content = request.getParameter("content");
        String score_s = request.getParameter("score");
        if (commentatorId_s == null || respondent_s == null || score_s == null)
            return new RespEntity(RespCode.WRONGPARAM);
        int commentatorId = Integer.parseInt(commentatorId_s);
        int respondent = Integer.parseInt(respondent_s);
        int score = Integer.parseInt(score_s);
        return commentService.add(commentatorId, respondent, content, score);
    }

    @RequestMapping("/del")
    public RespEntity del(HttpServletRequest request) {
        String commentatorId_s = request.getParameter("commentatorId");
        String respondent_s = request.getParameter("respondent");
        if (commentatorId_s == null || respondent_s == null)
            return new RespEntity(RespCode.WRONGPARAM);
        int commentatorId = Integer.parseInt(commentatorId_s);
        int respondent = Integer.parseInt(respondent_s);
        return commentService.del(commentatorId, respondent);
    }

    @RequestMapping("/getSentList")
    public RespEntity getSentList(HttpServletRequest request){
        String id_s = request.getParameter("id");
        if (id_s == null)
            return new RespEntity(RespCode.WRONGPARAM);
        int id = Integer.parseInt(id_s);
        return commentService.getSentList(id);
    }
}
