package com.start01.reggie.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * 元数据对象处理器
 */
@Component
public class MyMetaObjecthandler implements MetaObjectHandler {

    /**
     * 插入
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //设置创建时间和创建人
        metaObject.setValue("createTime",LocalDateTime.now());
        metaObject.setValue("createUser", new Long(2));
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser", new Long(2));


    }

    /**
     * 修改
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
//设置创建时间和创建人
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser", new Long(1));
    }
}
