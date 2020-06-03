package com.kx.service;

import com.kx.mapper.ArticleMapper;
import com.kx.mapper.CommentMapper;
import com.kx.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;

    //发布评论
    public void insertComment(Comment comment){
        commentMapper.insertComment(comment);
        articleMapper.updateArticleAddComments(comment.getArticleid());
    }

    //根据id删除评论
    public void deleteCommentById(int id,int articleid){
        commentMapper.deleteCommentById(id);
        //同时文章的评论数减一
        articleMapper.updateArticleSubComments(articleid);
    }
}
