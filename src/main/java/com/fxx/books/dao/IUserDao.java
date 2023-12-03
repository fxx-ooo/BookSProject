package com.fxx.books.dao;

import com.fxx.books.bean.User;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 根据用户信息查询用户
     * @param user
     * @return
     */
    public List<User> list(User user);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public Integer save(User user);

    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    public Integer deleteById(Integer id);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public User queryById(Integer id);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public Integer updateUser(User user);

    /**
     * 检查账号是否存在
     * @param userName
     * @return
     */
    public String checkUserName(String userName);

    /**
     * 认证检查--->登录检查
     * @param userName
     * @param password
     * @return
     *   User  null---->表示登录失败
     */
    public User checkUserNameAndPassword(String userName,String password);

}
