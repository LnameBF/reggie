package com.start01.reggie.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.start01.reggie.entity.Setmeal;
import com.start01.reggie.util.R;

public interface SetmealService extends IService<Setmeal> {

    //分页查询
    R getByPage(Integer page, Integer pageSize);
}
