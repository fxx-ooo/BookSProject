package com.fxx.books.dao.impl;

import com.fxx.books.bean.Classes;
import com.fxx.books.dao.IClassDao;
import com.fxx.books.utils.DelFlagE;
import com.fxx.books.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassesDaoImpl implements IClassDao {

    QueryRunner queryRunner;
    String sql;
    @Override
    public List<Classes> list(Classes cls) {
        queryRunner= MyDBUtils.getQueryRunner();
        sql="select *from t_class where is_deleted=?";

        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Classes>>() {
                @Override
                public List<Classes> handle(ResultSet rs) throws SQLException {
                    List<Classes> list=new ArrayList<>();
                    while (rs.next()){
                        Classes classes=new Classes();
                        classes.setId(rs.getInt("id"));
                        classes.setClassName(rs.getString("class_name"));
                        classes.setClassDesc(rs.getString("class_desc"));
                        classes.setDeptId(rs.getInt("dept_id"));
                        classes.setDeptName(rs.getString("dept_name"));
                        classes.setIsDeleted(rs.getInt("is_deleted"));
                        list.add(classes);
                    }
                    return list;
                }
            }, DelFlagE.NO.code);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer saveClasses(Classes cls) {

        queryRunner=MyDBUtils.getQueryRunner();
        sql="insert into t_class(class_name,class_desc,dept_id,dept_name)values(?,?,?,?)";
        try {
           return queryRunner.update(sql,cls.getClassName(),cls.getClassDesc(),cls.getDeptId(),cls.getDeptName());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer updateClasses(Classes cls) {

        queryRunner=MyDBUtils.getQueryRunner();
        sql="update t_class set class_name=?,class_desc=?,dept_id=?,dept_name=? where id=?";
        try {
            return queryRunner.update(sql,cls.getClassName(),cls.getClassDesc(),cls.getDeptId(),cls.getDeptName(),cls.getId());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer deleteById(Integer id) {
        queryRunner=MyDBUtils.getQueryRunner();
        sql="update t_class set  is_deleted=? where id=? ";
        try {
            return queryRunner.update(sql,DelFlagE.YES.code,id);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return -1;
    }

    @Override
    public Classes queryById(Integer id) {
        queryRunner= MyDBUtils.getQueryRunner();
        sql="select *from t_class where  id=? and is_deleted=?";

        try {
            return queryRunner.query(sql, new ResultSetHandler<Classes>() {
                @Override
                public Classes handle(ResultSet rs) throws SQLException {
                    Classes classes=new Classes();
                    if (rs.next()){
                        classes.setId(rs.getInt("id"));
                        classes.setClassName(rs.getString("class_name"));
                        classes.setClassDesc(rs.getString("class_desc"));
                        classes.setDeptId(rs.getInt("dept_id"));
                        classes.setDeptName(rs.getString("dept_name"));
                        classes.setIsDeleted(rs.getInt("is_deleted"));
                        return classes;
                    }
                    return null;
                }
            },id, DelFlagE.NO.code);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Classes> queryByDepartId(Integer departId) {
        queryRunner= MyDBUtils.getQueryRunner();
        sql="select *from t_class where  dept_id=? and is_deleted=?";

        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Classes>>() {
                @Override
                public List<Classes> handle(ResultSet rs) throws SQLException {
                    List<Classes> list=new ArrayList<>();
                    while (rs.next()){
                        Classes classes=new Classes();
                        classes.setId(rs.getInt("id"));
                        classes.setClassName(rs.getString("class_name"));
                        classes.setClassDesc(rs.getString("class_desc"));
                        classes.setDeptId(rs.getInt("dept_id"));
                        classes.setDeptName(rs.getString("dept_name"));
                        classes.setIsDeleted(rs.getInt("is_deleted"));
                        list.add(classes);

                    }
                    return list;
                }
            },departId, DelFlagE.NO.code);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
