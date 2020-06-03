package com.kx.service;

import com.kx.mapper.ArticleMapper;
import com.kx.mapper.CommentMapper;
import com.kx.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;
    //查询所有文章列表，按时间排序
    public List<Article> selectArticleByDate(){
        return articleMapper.selectArticleByDate();
    }

    //查询所有文章列表，按热度排序（评论和赞）
    public List<Article> selectArticleByZan(){
        return articleMapper.selectArticleByZan();
    }

    //查询所有评论及对应访客和文章标题
    public List<Article> selectTitlesWithComment(){
        return articleMapper.selectTitlesWithComment();
    }

    //根据id查询文章详情，包括所有评论,同时增加点击量
    public Article selectArticleByID(int id){
        articleMapper.updateArticleHits(id);
        return articleMapper.selectArticleByID(id);
    }

    //根据id查询文章详情，不包括所有评论（修改文章使用）,不必增加点击量
    public Article selectArticle(int id){
        return articleMapper.selectArticle(id);
    }
    //发布文章
    public void insertArticle(Article article){
        article.setHits(0);
        article.setZan(0);
        article.setComments(0);
        articleMapper.insertArticle(article);
    }

    //修改文章
    public void updateArticle(Article article){
        articleMapper.updateArticle(article);
    }

    //根据id删除文章
    public void deleteArticle(int id){
        articleMapper.deleteArticle(id);
        //删除所有评论
        commentMapper.deleteCommentByArticleId(id);
    }

    //根据id增加赞数
    public void updateArticleZan(int id){
        articleMapper.updateArticleZan(id);
    }
}
