package com.kx;

import com.kx.pojo.Article;
import com.kx.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {
    @Autowired
    private ArticleService articleService;

    //查询所有文章列表，按时间排序
    @Test
    public void testSelectArticleByDate(){
        List<Article> articles = articleService.selectArticleByDate();
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    //查询所有文章列表，按热度排序（评论和赞）
    @Test
    public void testSelectArticleByZan(){
        List<Article> articles = articleService.selectArticleByZan();
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    //根据id查询文章详情，包括所有评论,同时增加点击量
    @Test
    public void testSelectArticleByID(){
        Article article = articleService.selectArticleByID(8);
        System.out.println(article);
    }

    //发布文章
    @Test
    public void testInsertArticle(){
        Article article = new Article();
        article.setTitle("动态网站课程设计");
        article.setContent("K.X的个人博客");
        articleService.insertArticle(article);
    }

    //修改文章
    @Test
    public void testUpdateArticle(){
        Article article = new Article();
        article.setId(3);
        article.setContent("K.X的CSDN个人博客");
        articleService.updateArticle(article);
    }

    //根据id删除文章
    @Test
    public void testDeleteArticle(){
        articleService.deleteArticle(3);
    }

    //根据id增加赞数
    @Test
    public void testUpdateArticleZan(){
        articleService.updateArticleZan(4);
    }
}
