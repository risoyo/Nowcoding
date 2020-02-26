package com.nowcoder.community.controller;

import com.nowcoder.community.entity.ReturnMessage;
import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @Autowired
    private ReturnService returnService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot.";
    }

    @RequestMapping("/daotest")
    @ResponseBody
    public String daoTest() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求数据
        System.out.println(request.getMethod());// 在控制台打印出请求数据
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        // 访问url：http://localhost:8080/community/alpha/http?code=123&name=zhangsan
        System.out.println(request.getParameter("code"));// request通过此方法接收参数
        System.out.println(request.getParameter("name"));// request通过此方法接收参数

        // 返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter()
        ) {
            writer.write("<h1>nowcode</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // GET
    // 请求URL： /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET) // path:指定请求路径，method：指定请求方法
    @ResponseBody
    // 在getStudents方法的入参中加入变量，@RequestParam(name = "current",required = false) int current的意思是在请求url中截取名为
    // current的变量，将其赋值给int current;required = false代表非强制要求;defaultValue = "1"代表默认值为1
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "1") int limit
    ) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // url:/student/123 将具体条件放到路径中
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    // 路径变量的情况，需要用不同的注解和取值方式
    public String getSpecStudent(@PathVariable("id") int id) {
        int a = 123;
        int b = (1 + 2) + 3;
        System.out.println(a);
        System.out.println(b);
        return "a student" + id;
    }

    @PostMapping("/student")
//    @ResponseBody指定返回值格式为自定义
    @ResponseBody
//    指定传入的值，@RequestBody来接收前台传来的json，HttpServletRequest传入一些参数
    public Map<String, Object> login(@RequestBody Map<String, Object> map, HttpServletRequest request) {
//        获取前台传入json的name节点的值
        String loginId = (String) map.get("name");
        String password = (String) map.get("password");
        System.out.println(loginId);
        System.out.println(password);
        System.out.println(request.getRequestURL());
//        初始化值
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", 3028271);
        returnMap.put("verfy", 3028271);
        return returnMap;
    }


    // 响应JSON数据
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 15);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 15);
        list.add(emp);
        emp = new HashMap<>();
        emp.put("name", "李四");
        emp.put("age", 20);
        list.add(emp);
        emp = new HashMap<>();
        emp.put("name", "王五");
        emp.put("age", 25);
        list.add(emp);

        return list;
    }

    @RequestMapping(path = "/testRequireToken")//定义请求url
    @ResponseBody//定义返回类型为自定义类型
    public String initAuth(){
        return "hello";
    }


    @RequestMapping(path = "/testReturnMessage", method = RequestMethod.GET)//定义请求url
    @ResponseBody//定义返回类型为自定义类型
    public ReturnMessage<?> initReturnMessage(){
        Map<String,Object> message = new HashMap<>();
        message.put("message1",1111);
        message.put("message2",2222);
        List<Map<String, Object>> indexPostList = new ArrayList<>();//返回的列表集合
        indexPostList.add(message);
        message.put("message3",1111);
        message.put("message4",2222);
        indexPostList.add(message);
        return returnService.successWithObjectAndMessage("10",indexPostList);
//        return returnMessageUtil.error(30001,"数据错误");
    }
}
