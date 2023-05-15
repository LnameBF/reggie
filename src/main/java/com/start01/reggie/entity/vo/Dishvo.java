package com.start01.reggie.entity.vo;

import com.start01.reggie.entity.Dish;
import com.start01.reggie.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Dishvo extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;

}
