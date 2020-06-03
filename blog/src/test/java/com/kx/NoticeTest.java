package com.kx;


import com.kx.pojo.Notice;
import com.kx.service.NoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void testSelectNotice(){
        List<Notice> notices = noticeService.selectNotice();
        System.out.println(notices);
    }

    @Test
    public void testSelectNoticeById(){
        Notice notice = noticeService.selectNoticeById(1);
        System.out.println(notice);
    }

    @Test
    public void testInsertNotice(){
        Notice notice = new Notice();
        notice.setTitle("关于转载博客说明");
        notice.setContent("请各位转载博客时请注明出处");
        int i = noticeService.insertNotice(notice);
        System.out.println(i);
    }

    @Test
    public void testUpdateNotice(){
        Notice notice = new Notice();
        notice.setId(1);
        notice.setTitle("关于转载博客说明");
        notice.setContent("转载博客请注明出处，支持原创");
        int i = noticeService.updateNotice(notice);
        System.out.println(i);
    }

    @Test
    public void testDeleteNoticeById(){
        int i = noticeService.deleteNoticeById(2);
        System.out.println(i);
    }
}
