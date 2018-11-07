package com.dj.demo.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.demo.user.entity.User;
import com.dj.demo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangxc
 * @since 2018-11-07
 */
@Controller
@RequestMapping("/user/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("toLogin")
    public String toLogin(){
        System.out.println("success");
        return "login";
    }

    /**
     * 用户登陆
     * @param user
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(User user){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        User userServiceOne = userService.getOne(queryWrapper);

        if(null != userServiceOne){
            map.put("code", 200);
            map.put("msg", "登陆成功");
        }else{
            map.put("code", -1);
            map.put("msg", "登陆失败");
        }
        return map;
    }

    /**
     * 去展示页
     * @return
     */
    @RequestMapping("showList")
    public String showList(){
        return "list";
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("findUserList")
    @ResponseBody
    public Map<String, Object> findUserList(Integer current){
        Map<String, Object> map = new HashMap<>();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("id,username,password");
        List<User> userList = userService.list(queryWrapper);
        map.put("userList", userList);

        Page<User> page = new Page<>();
        page.setCurrent(current);
        page.setSize(2);
        IPage<User> pageUserList = userService.page(page, queryWrapper);

        map.put("pageUserList", pageUserList);
        return map;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("deleteUserById")
    @ResponseBody
    public Map<String, Object> deleteUserById(Integer id){
        Map<String, Object> map = new HashMap<>();

        Boolean user = userService.removeById(id);
        if(user){
            map.put("msg", "删除成功");
        }else{
            map.put("msg", "删除失败");
        }
        return map;
    }

    /**
     * 去修改
     * @param id
     * @return
     */
    @RequestMapping("toUpdateUserById")
    public String toUpdateUserById(Integer id, ModelMap map){
        User user = userService.getById(id);
        map.put("user", user);
        return "update";
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping("updateUserById")
    @ResponseBody
    public Map<String, Object> updateUserById(User user){
        Map<String, Object> map = new HashMap<>();

        Boolean rows = userService.updateById(user);

        if(rows){
            map.put("code", "200");
            map.put("msg", "修改成功");
        }else{
            map.put("code", "-1");
            map.put("msg", "修改失败");
        }

        return map;
    }

    /**
     * 去添加
     * @return
     */
    @RequestMapping("toInsertUser")
    public String toInsertUser(){
        return "insert";
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("insertUser")
    @ResponseBody
    public Map<String, Object> insertUser(User user){
        Map<String, Object> map = new HashMap<>();

        Boolean rows = userService.save(user);

        if(rows){
            map.put("code", "200");
            map.put("msg", "添加成功");
        }else{
            map.put("code", "-1");
            map.put("msg", "添加失败");
        }

        return map;
    }

}
