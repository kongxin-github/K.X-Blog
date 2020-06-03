package com.kx.mapper;

import com.kx.pojo.Comment;

public interface CommentMapper {

    //发布评论
    public void insertComment(Comment comment);


    //根据id删除评论
    public void deleteCommentById(int id);

    //根据articleid删除该文章所有评论
    public void deleteCommentByArticleId(int articleid);
}
