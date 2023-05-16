package com.start01.reggie.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.start01.reggie.entity.Dish;
import com.start01.reggie.entity.vo.Dishvo;
import com.start01.reggie.util.R;

public interface DishService extends IService<Dish> {
    /**
     * 分页查询
     */
     R getByPage(Integer page,Integer pageSize);

     R addDish(Dish dish);


    //需要操作两张表，一张是dish，一张是dishflavor
    public void saveWithFlavor(Dishvo dishVo);


    R  updateDish(Long id);

    R saveDish(Dishvo dishvo);
}
