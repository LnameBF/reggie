package com.start01.reggie.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.start01.reggie.Mapper.EmployeeMapper;
import com.start01.reggie.Service.EmployeeService;
import com.start01.reggie.entity.Employee;
import com.start01.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
        implements EmployeeService {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 登录功能
     * @param employee
     * @return
     */
    @Override
    public R login(HttpSession httpSession,Employee employee) {
        //将前端返回的密码进行md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        //查询数据库中是否有对应的用户名
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        //数据库中查询到的数据
        Employee one = employeeService.getOne(queryWrapper);


        //如果没有查询到数据
        if (one==null){
            return R.error("请检查用户名后重试");
        }
       //用户名如果存在，进行一个密码比对
        if(!one.getPassword().equals(password)){
            return R.error("密码错误");
        }
        if(one.getStatus()==0){
            return R.error("账号已经被封禁");
        }
        httpSession.setAttribute("employee",one.getId());
        httpSession.getAttribute("employee");
        System.out.println("------");
        return R.success(one);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public R logout() {

        return R.success("退出成功");
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public R getAll(Integer pageNum, Integer pageSize,String name) {
        //分页构造器
        Page infoPage=new Page(pageNum,pageSize);

        //条件构造
        LambdaQueryWrapper<Employee> wrapper=new LambdaQueryWrapper();
        wrapper.like(!StringUtils.isEmpty(name),Employee::getUsername,name);
        //添加排序条件
        wrapper.orderByDesc(Employee::getUpdateTime);
        //执行sql
        employeeService.page(infoPage, wrapper);
        System.out.println(infoPage.getRecords());
        return R.success(infoPage);
    }

    /**
     * 新增功能
     * @param session
     * @param employee
     * @return
     */
    @Override
    public R addEmployee(HttpSession session, Employee employee) {
        System.out.println(employee.getUsername());
        //查询数据库中是否存在该账号
        //设置查询规则
        LambdaQueryWrapper<Employee> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername,employee.getUsername());
        Employee one = employeeService.getOne(wrapper);

        //如果存在
        //设置了异常处理器，该代码废弃
//        if (one!=null){
//            return R.error("该账号已经存在");
//        }
        //如果不存在
        //创建初始密码

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
        //设置新增时间和更新时间
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setCreateTime(LocalDateTime.now());
        //创建创建人和更新人
        //employee.setCreateUser((Long) session.getAttribute("employee"));
        //employee.setUpdateUser((Long) session.getAttribute("employee"));


        System.out.println(employee);
        boolean save = employeeService.save(employee);
        if(save){
            return R.success("添加成功");
        }
        return R.error("添加失败");
    }

    /**
     * 修改员工信息
     * @param session
     * @param employee
     * @return
     */
    @Override
    public R updateEmployee(HttpSession session, Employee employee) {
        LambdaQueryWrapper<Employee> wrapper=new LambdaQueryWrapper();
        wrapper.eq(Employee::getId,employee.getId());
        //设置修改人和修改时间
        //employee.setUpdateTime(LocalDateTime.now());

        employee.setUpdateUser((Long) session.getAttribute("employee"));
        boolean update = employeeService.update(employee,wrapper);
        if(update){
            return R.success("修改成功");
        }

        return R.error("修改失败");
    }

    /**
     * 按照id进行查询
     * @param id
     * @return
     */
    @Override
    public R getEmployeeById(Long id) {
        Employee byId = employeeService.getById(id);
        if(byId==null){
            return R.error("没有找到该员工");
        }
        return R.success(byId);
    }


}
