package com.fxx.books.servlet;

import com.alibaba.fastjson.JSON;
import com.fxx.books.bean.Classes;
import com.fxx.books.bean.Department;
import com.fxx.books.bean.Student;
import com.fxx.books.service.IClassService;
import com.fxx.books.service.IDepartmentService;
import com.fxx.books.service.IStudentService;
import com.fxx.books.service.impl.ClassServiceImpl;
import com.fxx.books.service.impl.DepartmentServiceImpl;
import com.fxx.books.service.impl.StudentServiceImpl;
import com.fxx.books.utils.Constant;
import com.fxx.books.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 */
@WebServlet(name="studentServlet",urlPatterns = "/studentServlet")
public class StudentServlet extends HttpServlet {
    IStudentService iStudentService=new StudentServiceImpl();

    IDepartmentService departmentService=new DepartmentServiceImpl();

    IClassService classService=new ClassServiceImpl();

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
                queryList(req, resp);
            }else if (Constant.SERVLET_TYPE_SAVE.equals(type)){

            }else if(Constant.SERVLET_TYPE_UPDATE.equals(type)){

            }else if (Constant.SERVLET_TYPE_DELETE.equals(type)){

            }else if(Constant.SERVLET_TYPE_QUERYBYID.equals(type)){

            } else if ("saveBefore".equals(type)){
                //在添加操作之前，我们需要查询出所有的院系信息
                List<Department> depts = departmentService.list(null);
                req.setAttribute("depts",depts);
                req.getRequestDispatcher("/student/studentUpdate.jsp").forward(req,resp);
            }else if ("queryClassByDepartId".equals(type)){
                //Ajax 异步提交 根据院系编号查询对应的班级信息
                String departId = req.getParameter("departId");
                //院系编号查询对应的班级信息
                List<Classes> classes = classService.queryByDepartId(Integer.parseInt(departId));
                //把集合数据转换为json数据
                String json = JSON.toJSONString(classes);
                resp.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = resp.getWriter();
                writer.println(json);
                writer.flush();
            }else {
                queryList(req, resp);
            }

        }else {
            queryList(req, resp);
        }

    }

    private void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询学生信息
        List<Student> list = iStudentService.list(null);
        //把list保存在request作用域中
        req.setAttribute("list",list);
        req.getRequestDispatcher("/student/student.jsp").forward(req, resp);
    }
}
