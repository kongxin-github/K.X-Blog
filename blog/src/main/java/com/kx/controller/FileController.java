package com.kx.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin
@RestController
public class FileController {

    @PostMapping("/file")
    public String file(@RequestParam("myfile") MultipartFile myfile, HttpServletRequest request) throws IOException{
         //上传文件是否为空？
        if (!myfile.isEmpty()){
            //获取存放文件的路径
            String path = request.getServletContext().getRealPath("upload");
            //得到上传文件的文件名
            String filename = myfile.getOriginalFilename();
            //取文件扩展名
            String suffix = filename.substring(filename.lastIndexOf("."),filename.length());
            //随机的生存一个32的字符串,作为文件名
            filename = UUID.randomUUID()+suffix;
            
            //创建File对象
            File newFile = new File(path,filename);
            
            //判断文件父目录是否存在
            if(!newFile.getParentFile().exists()){ 
                newFile.getParentFile().mkdir();
            }
 
            //通过CommonsMultipartFile的方法直接写文件
            myfile.transferTo(newFile);
            return "upload/"+filename;
        }else{
            return "";
        }
    }
}
