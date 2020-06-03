package com.kx.controller;


import com.kx.pojo.Notice;
import com.kx.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //根据id查询公告
    @GetMapping("/notice/{id}")
    public Notice selectNoticeById(@PathVariable("id") Integer id){
        Notice notice = noticeService.selectNoticeById(id);
        return notice;
    }

    //查询公告列表
    @GetMapping("/notices")
    public List<Notice> selectNotice(){
        List<Notice> notices = noticeService.selectNotice();
        return notices;
    }

    //发布公告
    @PostMapping("/notice")
    public int insertNotice(@RequestBody Notice notice){
        int i = noticeService.insertNotice(notice);
        return i;
    }

    @PutMapping("/notice")
    public int updateNotice(@RequestBody Notice notice){
        int i = noticeService.updateNotice(notice);
        return i;
    }

    @DeleteMapping("/notice/{id}")
    public int deleteNoticeById(@PathVariable("id") Integer id){
        int i = noticeService.deleteNoticeById(id);
        return i;
    }
}
