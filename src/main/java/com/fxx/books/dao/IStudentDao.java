package com.fxx.books.dao;
import com.fxx.books.bean.Student;

import java.util.List;

public interface IStudentDao {

    public List<Student> list(Student stu);

    public Integer saveStudent(Student stu);

    public Integer updateStudent(Student stu);

    public  Integer deleteById( Integer id);

    public  Student queryById(Integer id);
}
