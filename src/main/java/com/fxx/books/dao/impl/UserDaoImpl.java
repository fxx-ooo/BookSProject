package com.fxx.books.dao.impl;

import com.fxx.books.bean.User;
import com.fxx.books.dao.IUserDao;
import com.fxx.books.utils.DelFlagE;
import com.fxx.books.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao接口的实现类
 */
public class UserDaoImpl implements IUserDao {


    @Override
    public List<User> list(User user) {

        //通过DBUtils来实现数据库的操作
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        //构建sql语句
        String sql = "select*from t_user where is_deleted=?";


        List<User> list = null;
        try {
            //这里这个BeanListHandler转换不了驼峰命名法
            //list = queryRunner.query(sql, new BeanListHandler<User>(User.class));

            //用ResultSetHandler,之后重写里面的handle方法
            list = queryRunner.query(sql, new ResultSetHandler<List<User>>() {
                @Override
                public List<User> handle(ResultSet resultSet) throws SQLException {
                    List<User> list = new ArrayList<>();
                    while (resultSet.next()) {
                        User user = new User();
                        user.setId(resultSet.getInt("id"));
                        user.setUserName(resultSet.getString("user_name"));
                        user.setPassword(resultSet.getString("password"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPhoneNum(resultSet.getString("phone_num"));
                        user.setSalt(resultSet.getString("salt"));
                        user.setIsDeleted(resultSet.getInt("is_deleted"));
                        list.add(user);
                    }

                    return list;
                }
            }, DelFlagE.NO.code);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return list;
    }
    /**
     * 添加用户的方法
     *
     * @param user
     * @return
     */
    @Override
    public Integer save(User user) {

        //1、获取QueryRunner对象
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();

        String sql = "insert into t_user(user_name,password,phone_num,email,salt)values(?,?,?,?,?)";

        try {
            return queryRunner.update(sql, user.getUserName()
                    , user.getPassword()
                    , user.getPhoneNum()
                    , user.getEmail()
                    , user.getSalt());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;//返回0说明 插入失败
    }
    @Override
    public Integer deleteById(Integer id) {

        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        //物理删除（数据彻底无了）
        //String sql="delete from t_user where id=?";
        //逻辑删除
        String sql = "update t_user set is_deleted=? where id=?";
        try {
            //把is_deleted字段更新为零，表示这条字段被删除了[逻辑删除]
            return queryRunner.update(sql, DelFlagE.YES.code, id);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return -1;
    }

    @Override
    public User queryById(Integer id) {

        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select *from t_user where id=? and is_deleted=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<User>() {

                @Override
                public User handle(ResultSet rs) throws SQLException {
                    if (rs.next()) {
                        User user = new User();
                        user.setUserName(rs.getString("user_name"));
                        user.setId(id);
                        user.setPassword(rs.getString("password"));
                        user.setPhoneNum(rs.getString("phone_num"));
                        user.setEmail(rs.getString("email"));
                        return user;
                    }
                    return null;
                }
            }, id, DelFlagE.NO.code);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer updateUser(User user) {

        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update t_user set user_name=?, password=?, phone_num=?, email=? where id=?";
        try {
            return queryRunner.update(sql, user.getUserName(), user.getPassword(), user.getPhoneNum(), user.getEmail(), user.getId());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return -1;
    }

    @Override
    public String checkUserName(String userName) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql="select count(1) from t_user where is_deleted=? and user_name=?";
        try {
            int count=queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet rs ) throws SQLException {
                    rs.next();
                    int count = rs.getInt(1);
                    return count;
                }
            },DelFlagE.NO.code,userName);
            return count==0?"success" : "error";
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "error";
    }

    @Override
    public User checkUserNameAndPassword(String userName, String password) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql="select *from t_user where is_deleted=? and user_name=? and password=?";
        try {
           return queryRunner.query(sql, new ResultSetHandler<User>() {
                @Override
                public User handle(ResultSet rs) throws SQLException {
                    if (rs.next()) {
                        User user = new User();
                        user.setUserName(rs.getString("user_name"));
                        user.setId(rs.getInt("id"));
                        user.setPassword(rs.getString("password"));
                        user.setPhoneNum(rs.getString("phone_num"));
                        user.setEmail(rs.getString("email"));
                        return user;
                    }
                    return null;
                }
            },DelFlagE.NO.code,userName,password);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        UserDaoImpl dao = new UserDaoImpl();
        for (User user : dao.list(null)) {
            System.out.println(user);
        }
    }
}
