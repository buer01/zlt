package com.zlt.sqli.controller;

import com.zlt.sqli.dao.User2Dao;
import com.zlt.sqli.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("SQL")//表示指定类，常用于整合
public class SQLController {
    @Autowired
    User2Dao userDao;

    @RequestMapping (value="sqli1",method= RequestMethod.GET)
    @ApiOperation("字符型注入")
    @ResponseBody
    public List<User> sqli1(String username){
        List<User> user = userDao.findByName(username);
        return user;
    }//121 or 1=1

    @RequestMapping(value="sqli2",method= RequestMethod.GET)
    @ApiOperation("字符型注入")
    @ResponseBody
    public List<User> sqli2(String username){
        List<User> user = userDao.findByName2(username);
        return user;
    }//121' or 1=1

    @RequestMapping(value="sqli3",method= RequestMethod.GET)
    @ApiOperation("过滤union、select和空格等")
    @ResponseBody
    public String sqli3(String username){
        List<User> user =null;
        String[] wafs = {"Select","SELECT","Union","UNION","select","union","/*","#","--"," "};
        if (username !=null){
            for (String waf : wafs) {
                if (username.contains(waf)){
                    username = username.replaceAll(waf,"");
                    System.out.println(username);
                }
            }
            user = userDao.findByName(username);
        }else {
            return "Please input the ID as parameter with numeric value";
        }
        return user.toString();
    }//浏览器输入121%0aor%0a1=1

    @RequestMapping(value="sqli4",method= RequestMethod.GET)
    @ApiOperation("布尔盲注")
    @ResponseBody
    public String sqli4(String username){
         List<User> users = null;
         users = userDao.findByName(username);
         if (users != null && users.size() != 0){
             return "You are in...........";
         }else {
             return "Please input the ID as parameter with numeric value";
         }
    }//121 and ascii((substr(database(),1,1)))>116
    //121 and ascii((substr(database(),1,1)))>118
}
