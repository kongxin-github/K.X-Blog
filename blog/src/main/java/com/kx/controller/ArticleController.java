package com.kx.controller;

import com.kx.pojo.Article;
import com.kx.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    //查询所有文章列表，按时间排序
    @GetMapping("/articles")
    public List<Article> selectArticleByDate(){
        List<Article> articles = articleService.selectArticleByDate();
        return articles;
    }

    //查询所有文章列表，按热度排序（评论和赞）
    @GetMapping("/top")
    public List<Article> selectArticleByZan(){
        List<Article> articles = articleService.selectArticleByZan();
        return articles;
    }

    //查询所有评论及对应访客和文章标题
    @GetMapping("/comments")
    public List<Article> selectTitlesWithComment(){
        List<Article> articles = articleService.selectTitlesWithComment();
        return articles;
    }

    //根据id查询文章详情，包括所有评论,同时增加点击量
    @GetMapping("/article/{id}")
    public Article selectArticleByID(@PathVariable("id") int id){
        Article article = articleService.selectArticleByID(id);
        return article;
    }

    //根据id查询文章详情，不包括所有评论（修改文章使用）,不必增加点击量
    @GetMapping("/getarticle/{id}")
    public Article selectArticle(@PathVariable int id){
        return articleService.selectArticle(id);
    }

    //发布文章
    @PostMapping("/article")
    public String insertArticle(@RequestBody Article article){
        articleService.insertArticle(article);
        return "success";
    }

    //修改文章
    @PutMapping("/article")
    public String updateArticle(@RequestBody Article article){
        articleService.updateArticle(article);
        return "success";
    }

    //根据id删除文章
    @DeleteMapping("/article/{id}")
    public String deleteArticle(@PathVariable int id){
        articleService.deleteArticle(id);
        return "success";
    }

    //根据id增加赞数
    @PutMapping("/article/{id}")
    public String updateArticleZan(@PathVariable int id){
        articleService.updateArticleZan(id);
        return "success";
    }
}
