package com.start01.reggie.Controller;

import com.start01.reggie.Service.DishService;
import com.start01.reggie.entity.Dish;
import com.start01.reggie.entity.vo.Dishvo;
import com.start01.reggie.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    public R getByPage(Integer page, Integer pageSize) {


        return dishService.getByPage(page, pageSize);
    }

    @PostMapping
    public R addDish(@RequestBody Dishvo dishvo) {
        dishService.saveWithFlavor(dishvo);
        return R.success("新增菜品成功");
    }

    /**
     * 修改信息时进行页面数据回显
     * 根据id查询对应的菜品信息和口味信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R updateDish(@PathVariable Long id) {
        return dishService.updateDish(id);
    }

    @PutMapping
    public R saveDish(@RequestBody Dishvo dishvo){
        dishService.updateWithFlavor(dishvo);
        return R.success("修改成功");

    }


}
