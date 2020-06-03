package com.kx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    //ID
    private Integer id;
    //标题
    private String title;
    //内容
    private String content;
    //日期
    private String created;
}
