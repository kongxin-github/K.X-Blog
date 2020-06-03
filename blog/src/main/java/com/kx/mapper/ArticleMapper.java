package com.kx.mapper;

import com.kx.pojo.Article;

import java.util.List;

public interface ArticleMapper {
    //查询所有文章列表，按时间排序
    public List<Article> selectArticleByDate();

    //查询所有文章列表，按热度排序（评论和赞）
    public List<Article> selectArticleByZan();

    //查询所有评论及对应访客和文章标题
    public List<Article> selectTitlesWithComment();

    //根据id查询文章详情，包括所有评论
    public Article selectArticleByID(int id);

    //根据id查询文章详情，不包括评论（修改文章使用）
    public Article selectArticle(int id);

    //发布文章
    public void insertArticle(Article article);

    //修改文章
    public void updateArticle(Article article);

    //根据id删除文章
    public void deleteArticle(int id);

    //根据id增加点击量
    public void updateArticleHits(int id);

    //根据id增加赞数
    public void updateArticleZan(int id);

    //根据id增加评论数
    public void updateArticleAddComments(int id);

    //根据id减少评论数
    public void updateArticleSubComments(int id);
}
