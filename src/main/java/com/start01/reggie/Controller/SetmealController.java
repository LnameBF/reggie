package com.start01.reggie.Controller;


import com.start01.reggie.Service.SetmealService;
import com.start01.reggie.Service.impl.SetmealServiceImpl;
import com.start01.reggie.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    SetmealService service;


    @GetMapping("/page")
    public R getPageSetmeal(@RequestParam Integer page, @RequestParam Integer pageSize){
        return service.getByPage(page,pageSize);
    }
}
