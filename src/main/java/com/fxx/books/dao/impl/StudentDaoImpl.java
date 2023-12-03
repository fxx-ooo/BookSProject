package com.fxx.books.dao.impl;

import com.fxx.books.bean.Student;
import com.fxx.books.dao.IStudentDao;
import com.fxx.books.utils.DelFlagE;
import com.fxx.books.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {
    QueryRunner queryRunner;
    String sql;
    @Override
    public List<Student> list(Student stu) {
        queryRunner= MyDBUtils.getQueryRunner();
        sql="select *from t_student where is_deleted=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<List<Student>>() {
                @Override
                public List<Student> handle(ResultSet rs) throws SQLException {
                    Student stu=new Student();
                    List<Student> list=new ArrayList<>();
                    while (rs.next()){
                       stu.setId(rs.getInt("id"));
                       stu.setStuNum(rs.getString("stu_num"));
                       stu.setStuName(rs.getString("stu_name"));
                       stu.setPhoneNum(rs.getString("phone_num"));;
                       stu.setGender(rs.getString("gender"));
                       stu.setAddress(rs.getString("address"));
                       stu.setClassId(rs.getInt("classid"));
                       stu.setClassName(rs.getString("class_name"));
                       stu.setDepartId(rs.getInt("departid"));
                       stu.setDepartName(rs.getString("depart_name"));
                       stu.setIsDeleted(rs.getInt("is_deleted"));
                        list.add(stu);
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
    public Integer saveStudent(Student stu) {

        queryRunner=MyDBUtils.getQueryRunner();
        sql="insert into t_student(stu_num,stu_name,phone_num,gender,address,classid,class_name,departid,depart_name)values(?,?,?,?,?,?,?,?,?,?)";
        try {
            return queryRunner.update(sql, stu.getStuNum(),
                    stu.getStuName(),
                    stu.getPhoneNum(),
                    stu.getGender(),
                    stu.getAddress(),
                    stu.getClassId(),
                    stu.getClassName(),
                    stu.getDepartId(),
                    stu.getDepartName());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer updateStudent(Student stu) {

       queryRunner=MyDBUtils.getQueryRunner();
        sql="update t_student set stu_num=?,stu_name=?,phone_num=?,gender=?,address=?,classid=?,class_name=?,departid=?,depart_name=? where id=?";
        try {
            return queryRunner.update(sql, stu.getStuNum(),
                    stu.getStuName(),
                    stu.getPhoneNum(),
                    stu.getGender(),
                    stu.getAddress(),
                    stu.getClassId(),
                    stu.getClassName(),
                    stu.getDepartId(),
                    stu.getDepartName(),
                    stu.getId());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return -1;
    }

    @Override
    public Integer deleteById(Integer id) {
        queryRunner=MyDBUtils.getQueryRunner();
        sql="update t_student set  is_deleted=? where id=? ";
        try {
            return queryRunner.update(sql,DelFlagE.YES.code,id);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return -1;
    }

    @Override
    public Student queryById(Integer id) {
        queryRunner= MyDBUtils.getQueryRunner();
        sql="select *from t_student where is_deleted=? and id=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<Student>() {
                @Override
                public Student handle(ResultSet rs) throws SQLException {
                    Student stu=new Student();
                    if (rs.next()){
                        stu.setId(rs.getInt("id"));
                        stu.setStuNum(rs.getString("stu_num"));
                        stu.setStuName(rs.getString("stu_name"));
                        stu.setPhoneNum(rs.getString("phone_num"));;
                        stu.setGender(rs.getString("gender"));
                        stu.setAddress(rs.getString("address"));
                        stu.setClassId(rs.getInt("classid"));
                        stu.setClassName(rs.getString("class_name"));
                        stu.setDepartId(rs.getInt("departid"));
                        stu.setDepartName(rs.getString("depart_name"));
                        stu.setIsDeleted(rs.getInt("is_deleted"));
                        return stu;
                    }
                    return null;
                }
            }, DelFlagE.NO.code,id);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
