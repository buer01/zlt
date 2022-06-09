package com.zlt.upload;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
@CrossOrigin
//仅设置了对php的阻拦，能不能上传php文件呢
@Controller
public class uploadController {

    /**
     * 1.文件保存在服务器，url地址保存在数据库
     * 上传成功之后返回成功保存的url地址
     */
    @PostMapping("/upload")
    public @ResponseBody
    String upload(@RequestParam MultipartFile file, HttpServletRequest request){
        //

        if(!file.isEmpty()){
            String uploadPath = "/Users/zhangyunlong/Desktop/java_Maven/diyici/src/main/resources/uploaded/";//"/Users/zhangyunlong/Desktop/load/";
            // 如果目录不存在则创建
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String OriginalFilename = file.getOriginalFilename();//获取原文件名
            String suffixName = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));//获取文件后缀名
            //System.out.println(suffixName);//.txt
            if(!suffixName.equals(".php"))
            {
            //重新随机生成名字
            String filename = UUID.randomUUID().toString() +suffixName;
            File localFile = new File(uploadPath+filename);
                try {
                    file.transferTo(localFile); //把上传的文件保存至本地
                    /**
                     * 这里应该把filename保存到数据库,供前端访问时使用
                     */
                    return filename;//上传成功，返回保存的文件地址
                }catch (IOException e){
                    e.printStackTrace();
                    System.out.println("上传失败");
                    return "上传失败";
                }
                }else{
                    //System.out.println("不是那个类型");
                    return "这格式不太正确啊，上点心啊";
                }
            }else{
            System.out.println("文件为空");
            return "文件为空";
        }
    }



}


