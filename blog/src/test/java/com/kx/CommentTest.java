package com.kx;


import com.kx.pojo.Comment;
import com.kx.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTest {
    @Autowired
    private CommentService commentService;

    //发布评论
    @Test
    public void testInsertComment(){
        Comment comment = new Comment();
        comment.setArticleid(23);
        comment.setUserid(2);
        comment.setContent("感谢博主分享");
        commentService.insertComment(comment);
    }

    //根据id删除评论
    @Test
    public void testDeleteCommentById(){
        commentService.deleteCommentById(5,2);
    }
}
