package com.itheima.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @version: java version 1.8
 * @Author: My Radar
 * @description:
 * @date: 2022-12-05 19:37
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> execeptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());
        String message = ex.getMessage();
        if (message.contains("Duplicate entry"))
        {
            String[] strList = message.split(" ");
            String msg = strList[2] + "已存在";
            return R.error("msg");
        }
        return R.error("未知错误");
    }
}
