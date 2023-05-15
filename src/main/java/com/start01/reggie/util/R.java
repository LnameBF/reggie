package com.start01.reggie.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果类
 *
 * @param <T>
 */
@Data
public class R<T> {

    private Integer code; //编码
    private String msg; //信息
    private T data; // 数据
    private Map map = new HashMap<>(); //动态数据

    public static <T> R<T> success(T obj) {
        R<T> r = new R<>();
        r.data = obj;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String s) {
        R<T> r = new R<>();
        r.msg = s;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
