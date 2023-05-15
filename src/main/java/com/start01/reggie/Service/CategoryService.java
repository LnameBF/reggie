package com.start01.reggie.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.start01.reggie.entity.Category;
import com.start01.reggie.util.R;

public interface CategoryService extends IService<Category> {

    //分页查询
    R getByPage(Integer page,Integer pageSize);
    //增加菜品
    R addCategory(Category category);
    //修改
    R updataCategory(Category category);
    //删除
    R deleteCategory(Long id);

    R getDishById(Integer id);

}
