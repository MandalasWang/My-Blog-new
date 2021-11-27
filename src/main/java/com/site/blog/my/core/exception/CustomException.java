package com.site.blog.my.core.exception;

import lombok.Data;

/**
 * @program: My-newBlog
 * @description: //TODO 功能描述
 * @author: Mr.Wang
 * @create: 2021-11-27 09:50
 **/
@Data
public class CustomException extends Throwable {

    int code;
    String errorMsg;

    public CustomException(){

    }

    public CustomException(int code,String msg){
        this.code=code;
        this.errorMsg=msg;
    }
}
