package com.site.blog.my.core.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.site.blog.my.core.config.OSSConfig;
import com.site.blog.my.core.service.UploadService;
import com.site.blog.my.core.util.MyBlogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @program: My-newBlog
 * @description: //TODO 功能描述
 * @author: Mr.Wang
 * @create: 2021-11-27 09:47
 **/
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private OSSConfig ossConfig;


    @Override
    public String uploadOss(MultipartFile file){
        if(file.getSize() > 1024 * 1000){
            throw new RuntimeException();
        }
        String originalFilename = file.getOriginalFilename();

        //获取相关配置
        String bucketName = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        //创建oss对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        //JDK8新特性写法，构建路径
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String folder = dtf.format(ldt);
        String fileName = MyBlogUtils.generateUUID();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        //在oss上创建文件夹test路径
        String newFileName = "user/" + folder + "/" + fileName + extension;

        try {
            PutObjectResult result = ossClient.putObject(bucketName, newFileName, file.getInputStream());
            //返回访问路径
            if (null != result) {
                //https://xd-test1.oss-cn-beijing.aliyuncs.com/test/1.jpg
                String imgUrl = "https://"+bucketName+"."+endpoint+"/"+newFileName;
                return imgUrl;
            }
        } catch (Exception e) {
            log.error("上传头像失败:",e);
        } finally {
            // 关闭OSS服务
            ossClient.shutdown();
        }
        return null;
    }
}
