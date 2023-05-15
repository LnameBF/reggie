package com.start01.reggie.Controller;


import com.start01.reggie.Service.CategoryService;
import com.start01.reggie.entity.Category;
import com.start01.reggie.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    /**
     * 员工分页查询
     */
    @GetMapping("/page")
    public R getByPageCategory(Integer page, Integer pageSize) {
        return categoryService.getByPage(page, pageSize);
    }

    /**
     * 添加菜品
     *
     * @param category
     * @return
     */
    @PostMapping
    public R addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    /**
     * 修改菜品信息
     *
     * @param category
     * @return
     */
    @PutMapping
    public R updataCategory(@RequestBody Category category) {
        return categoryService.updataCategory(category);
    }

    /**
     * id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public R deleteCategory(@RequestParam("ids") Long id) {
        return categoryService.deleteCategory(id);
    }

    /**
     * 菜品管理中页面加载菜品分类
     * @param type_id
     * @return
     */
    @GetMapping("/list")
    public R getList(@RequestParam("type") Integer type_id) {
        return categoryService.getDishById(type_id);

    }

}
