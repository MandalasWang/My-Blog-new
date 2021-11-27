package com.site.blog.my.core.service;


import org.springframework.web.multipart.MultipartFile;

public interface UploadService {


     String uploadOss(MultipartFile file);
}
