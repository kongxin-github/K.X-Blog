package com.kx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    //id
    private int id;
    //标题
    private String title;
    //内容
    private String content;
    //创建时间
    private String created;
    //点击数
    private int hits;
    //点赞数
    private int zan;
    //评论数
    private int comments;
    //评论列表
    private List<Comment> commentList;
}
