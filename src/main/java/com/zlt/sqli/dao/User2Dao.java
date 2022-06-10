package com.zlt.sqli.dao;
import com.zlt.sqli.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface User2Dao {
    //用框架的时候，只需要定义方法
//    public List<User> findAll();
    public List<User> findByName(@Param("username")String username);
    public List<User> findByName2(@Param("username")String username);

//    public void add(@Param("userId")String userId,@Param("username")String username);
//    //public int add(User user);
//    public void delete(String username);
//    public void edit(@Param("username")String userId, @Param("username")String username);
//
//    public int reg(User user);
}
