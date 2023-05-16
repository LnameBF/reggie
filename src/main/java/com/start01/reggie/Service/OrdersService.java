package com.start01.reggie.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.start01.reggie.entity.Orders;
import com.start01.reggie.util.R;


/**
 * 订单表(Orders)表服务接口
 *
 * @author makejava
 * @since 2023-05-16 17:47:42
 */
public interface OrdersService extends IService<Orders> {
    R selectPage(Integer size, Integer pageSize);



}
