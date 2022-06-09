package com.dao;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    //定义方法
    public List<User> findAll();
    public User findById(int userId);
    public String addUser(User user);
    public String editUser(User user);
    public String delUser(int id);

}
