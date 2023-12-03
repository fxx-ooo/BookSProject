package com.fxx.books.service.impl;

import com.fxx.books.bean.Student;
import com.fxx.books.dao.IStudentDao;
import com.fxx.books.dao.impl.StudentDaoImpl;
import com.fxx.books.service.IStudentService;

import java.util.List;

public class StudentServiceImpl implements IStudentService {

    StudentDaoImpl studentDao=new StudentDaoImpl();

    @Override
    public List<Student> list(Student stu) {
        return studentDao.list(stu);
    }

    @Override
    public Integer saveStudent(Student stu) {
        return studentDao.saveStudent(stu);
    }

    @Override
    public Integer updateStudent(Student stu) {
        return studentDao.updateStudent(stu);
    }

    @Override
    public Integer deleteById(Integer id) {
        return studentDao.deleteById(id);
    }

    @Override
    public Student queryById(Integer id) {
        return studentDao.queryById(id);
    }
}
