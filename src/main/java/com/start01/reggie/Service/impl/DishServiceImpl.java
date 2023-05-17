package com.start01.reggie.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.springframework.beans.BeanUtils;
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

    /**
     * 修改时数据回显菜品信息
     * @return
     */
    @Override
    public R updateDish(Long id) {
        //查询到菜品的基本信息
        Dish Up_byId = dishService.getById(id);
        //查询到口味信息,根据dish_id查询
        LambdaQueryWrapper<DishFlavor> wrapper=new LambdaQueryWrapper();
        wrapper.eq(DishFlavor::getDishId,Up_byId.getId());
        List<DishFlavor> list = dishFlavorService.list(wrapper);
        //将口味信息和菜品基本信息分装到vo中，并且返回给前端
        Dishvo dishvo=new Dishvo();
        BeanUtils.copyProperties(Up_byId,dishvo);
        dishvo.setFlavors(list);
        return R.success(dishvo);
    }

    /**
     * 保存菜品信息
     * @param dishvo
     * @return
     */
    @Override
    public R updateWithFlavor(Dishvo dishvo) {
        //根据id修改菜品的基本信息
        boolean dish = dishService.updateById(dishvo);

        //删除掉当前菜品对应的口味信息  直接removeById，是根据口味表中id删除，而这里删除是要根据口味表中的dish_id删除
        //delete from dish_flavor where dish_id=???
        LambdaQueryWrapper<DishFlavor> flavorweapper=new LambdaQueryWrapper<>();
        flavorweapper.eq(DishFlavor::getDishId,dishvo.getId());
        dishFlavorService.remove(flavorweapper);

        //增加修改后对应菜品的口味信息
        List<DishFlavor> collect = dishvo.getFlavors().stream().map(flavor -> {
            flavor.setDishId(dishvo.getId());
            return flavor;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(collect);
        return R.success("修改成功");
    }
}
