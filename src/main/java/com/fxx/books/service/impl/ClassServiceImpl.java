package com.fxx.books.service.impl;

import com.fxx.books.bean.Classes;
import com.fxx.books.dao.IClassDao;
import com.fxx.books.dao.impl.ClassesDaoImpl;
import com.fxx.books.service.IClassService;

import java.util.List;

public class ClassServiceImpl implements IClassService {

    IClassDao classesDao=new ClassesDaoImpl();

    @Override
    public List<Classes> list(Classes cls) {
        return classesDao.list(cls);
    }

    @Override
    public Integer saveClasses(Classes cls) {
        return classesDao.saveClasses(cls);
    }

    @Override
    public Integer updateClasses(Classes cls) {
        return classesDao.updateClasses(cls);
    }

    @Override
    public Integer deleteById(Integer id) {
        return classesDao.deleteById(id);
    }

    @Override
    public Classes queryById(Integer id) {
        return classesDao.queryById(id);
    }

    @Override
    public List<Classes> queryByDepartId(Integer departId) {
        return classesDao.queryByDepartId(departId);
    }
}
