package com.start01.reggie.Service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.start01.reggie.entity.Orders;
import com.start01.reggie.Mapper.OrdersMapper;
import com.start01.reggie.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.start01.reggie.Service.OrdersService;

/**
 * 订单表(Orders)表服务实现类
 *
 * @author makejava
 * @since 2023-05-16 17:47:44
 */
@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    OrdersService ordersService;


    /**
     * 分页显示
     * @param size
     * @param pageSize
     * @return
     */
    public R selectPage(Integer size,Integer pageSize){
        Page<Orders> pageinfo=new Page<>(size,pageSize);
        ordersService.page(pageinfo);
        return R.success(pageinfo);

    }



}

