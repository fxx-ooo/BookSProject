package com.fxx.books.servlet;

import com.fxx.books.bean.Department;
import com.fxx.books.service.IDepartmentService;
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
@WebServlet(name="departmentServlet",urlPatterns = "/departmentServlet")
public class DepartmentServlet extends HttpServlet {
    IDepartmentService iDepartmentService=new DepartmentServiceImpl();

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
                saveOrUpdate(req, resp);
            }else if(Constant.SERVLET_TYPE_UPDATE.equals(type)){
                saveOrUpdate(req, resp);
            }else if (Constant.SERVLET_TYPE_DELETE.equals(type)){
                deleteDepartment(req, resp);
            }else if(Constant.SERVLET_TYPE_QUERYBYID.equals(type)){
                //根据id查询院系信息
                String id = req.getParameter("id");
                Department department = iDepartmentService.queryById(Integer.parseInt(id));
                //把数据保存在request作用域中
                req.setAttribute("dept",department);
                req.getRequestDispatcher("/depart/departUpdate.jsp").forward(req, resp);
               // resp.sendRedirect("/");
            }else {
                queryList(req, resp);
            }
        }else {
            queryList(req, resp);
        }


    }

    private void deleteDepartment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //根据id删除院系信息re（逻辑删除）
        String id = req.getParameter("id");
        iDepartmentService.deleteById(Integer.parseInt(id));
        resp.sendRedirect("/departmentServlet?type=query");
    }

    private void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) {
        //保存院系信息
        try {
            Department dept = RequestParameterUtils.getRequestParameterForReflect(req, Department.class);
            Integer count=-1;
            if (dept.getId()!=null&&dept.getId()>0){
                //更新
                 count = iDepartmentService.updateDepartment(dept);
            }else {
                 count = iDepartmentService.saveDepartment(dept);
            }
            if (count>0){
                //表示添加成功
                resp.sendRedirect("/departmentServlet?type=query");
            }else {
                //todo 表示添加失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询出所有的院系信息
        List<Department> list = iDepartmentService.list(null);
        //存储在request作用域中
        req.setAttribute("list",list);
        //然后跳转到院系的展示页面
        req.getRequestDispatcher("/depart/department.jsp").forward(req, resp);
    }
}
