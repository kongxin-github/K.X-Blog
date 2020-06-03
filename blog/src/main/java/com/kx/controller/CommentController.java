package com.kx.controller;

import com.kx.pojo.Comment;
import com.kx.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    //发布评论
    @PostMapping("/comment")
    public String insertComment(@RequestBody Comment comment){
        commentService.insertComment(comment);
        return "success";
    }

    //根据id删除评论
    @DeleteMapping("/comment/{id}/{articleid}")
    public String deleteCommentById(@PathVariable int id,@PathVariable int articleid){
        commentService.deleteCommentById(id,articleid);
        return "success";
    }
}
