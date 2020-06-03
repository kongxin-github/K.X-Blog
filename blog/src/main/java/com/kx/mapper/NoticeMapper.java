package com.kx.mapper;



import com.kx.pojo.Notice;

import java.util.List;

public interface NoticeMapper {
    //根据id查询公告详情
    public Notice selectNoticeById(Integer id);

    //查询全部公告列表
    public List<Notice> selectNotice();

    //发布公告
    public int insertNotice(Notice notice);

    //根据id修改公告
    public int updateNotice(Notice notice);

    //根据id删除公告
    public int deleteNoticeById(Integer id);
}
