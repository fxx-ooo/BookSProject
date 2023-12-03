package com.fxx.books.servlet;

import com.fxx.books.bean.Classes;
import com.fxx.books.bean.Department;
import com.fxx.books.service.IClassService;
import com.fxx.books.service.IDepartmentService;
import com.fxx.books.service.impl.ClassServiceImpl;
import com.fxx.books.service.impl.DepartmentServiceImpl;
import com.fxx.books.utils.Constant;
import com.fxx.books.utils.RequestParameterUtils;
import com.fxx.books.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 */
@WebServlet(name="classServlet",urlPatterns = "/classServlet")
public class ClassServlet extends HttpServlet {
    IClassService classService=new ClassServiceImpl();

    IDepartmentService departmentService=new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求类型：
        String type = req.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        //2、如果type不为空，根据type的不同值来进行操作
        if (StringUtils.isNotEmpty(type)){
            if (Constant.SERVLET_TYPE_QUERY.equals(type)){
                queryClass(req, resp);
            }else if (Constant.SERVLET_TYPE_SAVE.equals(type)){
                saveAllUpdateClass(req, resp);
            }else if(Constant.SERVLET_TYPE_UPDATE.equals(type)){
                //更新数据
                saveAllUpdateClass(req,resp);
            }else if (Constant.SERVLET_TYPE_DELETE.equals(type)){
                deleteClass(req, resp);
            }else if(Constant.SERVLET_TYPE_QUERYBYID.equals(type)){
                queryClassById(req, resp);
            }else if("queryAllDept".equals(type)){
                //查询所有的院系信息
                List<Department> list = departmentService.list(null);
                req.setAttribute("depts",list );
                req.getRequestDispatcher("/class/classUpdate.jsp").forward(req,resp);
            }else {
                queryClass(req, resp);
            }
        }else {
            queryClass(req, resp);
        }
    }

    private void deleteClass(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //根据ID删除数据：逻辑删除
        String id = req.getParameter("id");
        classService.deleteById(Integer.parseInt(id));
        resp.sendRedirect("/classServlet?type=query");
    }

    private void queryClassById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //进入修改页面前的操作：根据ID查询出对应的班级信息，同时还需要查询出所有的院系信息更新时候的下拉
        String id = req.getParameter("id");
        Classes classes = classService.queryById(Integer.parseInt(id));
        //查询出所有偶的院系信息
        List<Department> list = departmentService.list(null);
        req.setAttribute("cls",classes);
        req.setAttribute("depts",list );
        //跳转到修改的页面
        req.getRequestDispatcher("/class/classUpdate.jsp").forward(req, resp);
    }

    private void  saveAllUpdateClass(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Classes cls = RequestParameterUtils.getRequestParameterForReflect(req, Classes.class);
            Integer count;
            //req.setAttribute("cls",classes);
            if (StringUtils.isEmpty(cls.getDeptName())&&cls.getDeptId()!=null){
                //根据院系编号查询出对应的院系名称
                Department department = departmentService.queryById(cls.getDeptId());
                cls.setDeptName(department.getDepartment());
            }
            if (cls.getId()!=null&&cls.getId()>0){
                //表示是更新操作
                count = classService.updateClasses(cls);
            }else {
                //表示是添加操作
                //保存班级信息
                count=classService.saveClasses(cls);
            }
            if(count>0){
                //保存成功就重定向查询
                resp.sendRedirect("/classServlet?type=query");
            }else {
                //todo 跳转到失败页面
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void queryClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Classes> list = classService.list(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/class/class.jsp").forward(req, resp);
    }
}
