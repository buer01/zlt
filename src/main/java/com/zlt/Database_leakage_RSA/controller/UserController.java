package com.zlt.Database_leakage_RSA.controller;

import com.zlt.Database_leakage_RSA.dao.UserDao;
import com.zlt.Database_leakage_RSA.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api
public class UserController {

    @Autowired
    UserDao userDao;

//    @RequestMapping("getAllUsers")
//    public List<User> getAllUsers(){
//        return userDao.findAll();
//    }
//
//    @RequestMapping("getUserById")
//    public User getUserById(int id){
//        return userDao.findById(id);
//    }
    @RequestMapping(value = "getAllUsers",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查找所有用户")
    public List<User> getAllUsers(){
    return userDao.findAll();
}

    @RequestMapping(value = "getUserById",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("根据ID查找用户")
    public User getUserById(int id){
        return userDao.findById(id);
    }

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    @ApiOperation("添加用户")
    public String addUser(User user){
        userDao.addUser(user);
        return "添加成功";
    }

    @RequestMapping(value = "editUser",method = RequestMethod.POST)
    @ApiOperation("更新用户")
    public String editUser(User user){
        userDao.editUser(user);
        return "更新成功";
    }

    @RequestMapping(value="delUser",method = RequestMethod.GET)
    @ApiOperation("删除用户")
    public String delUser(int id){
        userDao.delUser(id);
        return "删除成功";
    }

}
