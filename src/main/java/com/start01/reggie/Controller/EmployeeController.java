package com.start01.reggie.Controller;

import com.start01.reggie.Service.EmployeeService;
import com.start01.reggie.entity.Employee;
import com.start01.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param httpSession 将数据存入网页
     * @param employee    实体类
     * @return
     */
    @PostMapping("/login")
    public R login(HttpSession httpSession, @RequestBody Employee employee) {
        return employeeService.login(httpSession, employee);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public R logout(HttpSession session) {
        //清理session
        session.removeAttribute("employee");
        return employeeService.logout();
    }

    @PostMapping("/test")
    public String test() {
        return "test success";
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R getAll(Integer page, Integer pageSize,String name) {
        R all = employeeService.getAll(page, pageSize,name);
        log.info(all.toString());
        return all;
    }

    /**
     * 添加操作
     */
    @PostMapping
    public R addEmployee(HttpSession session,@RequestBody Employee employee) {
        return employeeService.addEmployee(session,employee);
    }

    @PutMapping
    public R UpdataEmployee(HttpSession session,@RequestBody Employee employee){
         return  employeeService.updateEmployee(session,employee);

    }
    @GetMapping("/{id}")
    public R getEmplyeeById(@PathVariable Long id){
       return employeeService.getEmployeeById(id);
    }
}
