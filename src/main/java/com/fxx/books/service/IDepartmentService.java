package com.fxx.books.service;
import com.fxx.books.bean.Department;

import java.util.List;

public interface IDepartmentService {


    public List<Department> list(Department dept);

    public Integer saveDepartment(Department dept);

    public Integer updateDepartment(Department dept);

    public  Integer deleteById( Integer id);

    public  Department queryById(Integer id);
}
