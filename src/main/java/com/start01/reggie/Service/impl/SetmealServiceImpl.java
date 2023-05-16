package com.start01.reggie.Service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.start01.reggie.Mapper.SetmealMapper;
import com.start01.reggie.Service.SetmealService;
import com.start01.reggie.entity.Setmeal;
import com.start01.reggie.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    SetmealService setmealService;

    @Override
    public R getByPage(Integer page, Integer pageSize) {

        Page<Setmeal> page1=new Page<>();
        setmealService.page(page1);
        return R.success(page1);
    }
}
