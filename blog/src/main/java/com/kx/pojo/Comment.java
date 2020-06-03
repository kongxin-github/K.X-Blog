package com.kx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    //id
    private int id;
    //文章id
    private int articleid;
    //用户id
    private int userid;
    //用户
    private User user;
    //内容
    private String content;
    //创建时间
    private String created;
}
