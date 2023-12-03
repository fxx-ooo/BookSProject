package com.fxx.books.service.impl;

import com.fxx.books.bean.Department;
import com.fxx.books.dao.impl.DepartmentDaoImpl;
import com.fxx.books.service.IDepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {

    DepartmentDaoImpl departmentDao=new DepartmentDaoImpl();

    @Override
    public List<Department> list(Department dept) {
        return departmentDao.list(dept);
    }

    @Override
    public Integer saveDepartment(Department dept) {
        return departmentDao.saveDepartment(dept);
    }

    @Override
    public Integer updateDepartment(Department dept) {
        return departmentDao.updateDepartment(dept);
    }

    @Override
    public Integer deleteById(Integer id) {
        return departmentDao.deleteById(id);
    }

    @Override
    public Department queryById(Integer id) {
        return departmentDao.queryById(id);
    }
}
