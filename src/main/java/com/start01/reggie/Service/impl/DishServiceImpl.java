package com.start01.reggie.Service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.start01.reggie.Mapper.DishMapper;
import com.start01.reggie.Service.DishFlavorService;
import com.start01.reggie.Service.DishService;
import com.start01.reggie.entity.Dish;
import com.start01.reggie.entity.DishFlavor;
import com.start01.reggie.entity.vo.Dishvo;
import com.start01.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishService dishService;


    @Autowired
    private DishFlavorService dishFlavorService;

    @Override
    public R getByPage(Integer page, Integer pageSize) {
        Page infopage = new Page(page, pageSize);
        dishService.page(infopage);
        return R.success(infopage);
    }

    @Override
    public R addDish(Dish dish) {
        return null;
    }

    /**
     * 保存提交的菜品信息
     *
     * @param dishVo
     */
    @Transactional
    @Override
    public void saveWithFlavor(Dishvo dishVo) {


        //首先保存菜品的基本信息
        this.save(dishVo);

        Long id = dishVo.getId();
        System.out.println(id);
        //这里的dish_id是null，因为这里才保存菜品，dishvo中还没有dish_id，这里对dish_id进行封装
        List<DishFlavor> collect = dishVo.getFlavors().stream().map(dishFlavor -> {
            dishFlavor.setDishId(id);
            return dishFlavor;
        }).collect(Collectors.toList());
        boolean b = dishFlavorService.saveBatch(collect);
        System.out.println(b);


    }
}
