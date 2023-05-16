package com.start01.reggie.Controller;

import com.start01.reggie.Service.OrdersService;
import com.start01.reggie.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单表(Orders)表控制层
 *
 * @author makejava
 * @since 2023-05-16 17:47:38
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @GetMapping
    public R selectAllByPage(@RequestParam Integer size, @RequestParam Integer pageSize){
        return ordersService.selectPage(size,pageSize);
    }

}

