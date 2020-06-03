package com.kx.service;


import com.kx.mapper.NoticeMapper;
import com.kx.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    //查询所有公告列表
    public List<Notice> selectNotice(){
        return noticeMapper.selectNotice();
    }

    //根据id查看公告详情
    public Notice selectNoticeById(Integer id){
        return noticeMapper.selectNoticeById(id);
    }

    //发布公告
    public int insertNotice(Notice notice){
        return noticeMapper.insertNotice(notice);
    }

    //根据id修改公告
    public int updateNotice(Notice notice){
        return noticeMapper.updateNotice(notice);
    }

    //根据id删除公告
    public int deleteNoticeById(Integer id){
        return noticeMapper.deleteNoticeById(id);
    }
}
