package com.start01.reggie.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.start01.reggie.entity.Employee;
import com.start01.reggie.util.R;

import javax.servlet.http.HttpSession;

public interface EmployeeService extends IService<Employee> {

    //登录接口
    R login(HttpSession httpSession,Employee employee);
    //退出接口
    R logout();
    //分页查询接口
    R getAll(Integer pageNum, Integer pageSize,String name);
    //添加员工接口
    R addEmployee(HttpSession session,Employee employee);
    //修改接口
    R updateEmployee(HttpSession session,Employee employee);
    //id查询员接口
    R getEmployeeById(Long id);
}
