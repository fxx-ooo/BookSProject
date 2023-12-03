package com.fxx.books.service.impl;

import com.fxx.books.bean.User;
import com.fxx.books.dao.IUserDao;
import com.fxx.books.dao.impl.UserDaoImpl;
import com.fxx.books.service.IUserService;

import java.util.List;

/**
 * 用户Service的实现
 * 通过dao的调用来完成复杂的业务逻辑
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao=new UserDaoImpl();

    @Override
    public List<User> getUser(User user) {
        //处理对应的业务逻辑
        return userDao.list(user);
    }

    @Override
    public Integer addUser(User user) {
        return  userDao.save(user);

    }

    @Override
    public Integer deleteById(Integer id) {
        return userDao.deleteById(id);
    }

    @Override
    public User queryById(Integer id) {
        return userDao.queryById(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public String checkUserName(String userName) {
        return userDao.checkUserName(userName);
    }

    @Override
    public User checkUserNameAndPassword(String userName, String password) {
        return userDao.checkUserNameAndPassword(userName,password);
    }


}
