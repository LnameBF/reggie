package com.start01.reggie.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.start01.reggie.Mapper.CategoryMapper;
import com.start01.reggie.Service.CategoryService;
import com.start01.reggie.Service.DishService;
import com.start01.reggie.Service.SetmealService;
import com.start01.reggie.entity.Category;
import com.start01.reggie.entity.Dish;
import com.start01.reggie.entity.Setmeal;
import com.start01.reggie.util.CustomExpection;
import com.start01.reggie.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    public CategoryService categoryService;

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;


    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public R getByPage(Integer page, Integer pageSize) {
        //创建分页
        Page pageinfo = new Page<>(page, pageSize);

        //设置查询规则
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Category::getSort);


        categoryService.page(pageinfo,wrapper);


        return R.success(pageinfo);
    }

    /**
     * 增加菜品
     *
     * @param category
     * @return
     */
    @Override
    public R addCategory(Category category) {
        return categoryService.save(category) ? R.success("添加成功") : R.error("添加失败");
    }

    /**
     * 修改菜品信息
     * @param category
     * @return
     */
    @Override
    public R updataCategory(Category category) {
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Category::getId,category.getId());
        return categoryService.update(category,wrapper) ? R.success("修改成功") : R.error("修改失败");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    public R deleteCategory(Long id) {
        //查询当前分类是否关联了菜品，如果关联，抛出一个业务异常
        LambdaQueryWrapper<Dish> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(wrapper);
        if(count>0){
            throw  new CustomExpection("删除失败，该分类已经关联了其他菜品");
        }
        //是否关联了套餐，如果关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> wrapper1=new LambdaQueryWrapper<>();
        wrapper1.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(wrapper1);
        if(count1>0){
            throw  new CustomExpection("删除失败，该分类已经关联了其他套餐");
        }
        return categoryService.removeById(id) ? R.success("删除成功") : R.error("删除失败");
    }

    @Override
    public R getDishById(Integer id) {
        //查询菜品分类的属性，对应的category_id应该是1
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Category::getType,id);
        List<Category> list = categoryService.list(wrapper);
        return R.success(list);
    }


}
