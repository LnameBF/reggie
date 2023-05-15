package com.start01.reggie.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@ControllerAdvice(annotations = {RestController.class})
@ResponseBody
@Slf4j
public class ExpectionHandler {
    /**
     * 进行数据库异常处理
     *
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R expectionhandler(SQLIntegrityConstraintViolationException exception) {
        log.info(exception.toString());
        if (exception.getMessage().contains("Duplicate entry")) {
            String[] s = exception.getMessage().split(" ");
            String exmessage = s[2] + "已经存在";
            return R.error(exmessage);
        }
        return R.error("未知错误");
    }

    @ExceptionHandler(CustomExpection.class)
    public R CustomExpection(CustomExpection customExpection) {
        return R.error(customExpection.getMessage());
    }


}
