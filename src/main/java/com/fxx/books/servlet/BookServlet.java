package com.fxx.books.servlet;

import com.fxx.books.bean.Book;
import com.fxx.books.service.IBookService;
import com.fxx.books.service.impl.BookServiceImpl;
import com.fxx.books.utils.Constant;
import com.fxx.books.utils.RequestParameterUtils;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name ="bookServlet",urlPatterns = "/bookServlet")
public class BookServlet extends HttpServlet {
    private IBookService bookService=new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //编码方式已在过滤器中设置
        //req.setCharacterEncoding("UTF-8");
        //1、获取提交的type数据
        String type = req.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        switch (type){
            case Constant.SERVLET_TYPE_SAVE:
                saveOrUpdateBook(req, resp);
                break;
            case Constant.SERVLET_TYPE_QUERYBYID:
                queyById(req, resp);
                break;
            case Constant.SERVLET_TYPE_UPDATE:
                saveOrUpdateBook(req,resp);
                break;
            case Constant.SERVLET_TYPE_QUERY:
                queryBookList(req, resp);
                break;
            case Constant.SERVLET_TYPE_DELETE:
                deleteById(req, resp);
                break;
            default:
                queryBookList(req, resp);
                break;
        }
    }

    private void queyById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据编号查询书籍book信息
        String id = req.getParameter("id");
        Book book = bookService.queryById(Integer.parseInt(id));
        req.setAttribute("book",book);
        req.getRequestDispatcher("/book/bookUpdate.jsp").forward(req, resp);
    }

    private void deleteById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //逻辑删除用户信息
        String idDelete= req.getParameter("id");
        bookService.deleteById(Integer.parseInt(idDelete));
        resp.sendRedirect("/bookServlet?type=query");
    }

    private void saveOrUpdateBook(HttpServletRequest req, HttpServletResponse resp) {
        //表示完成book数据的添加
        try {
            //通过反射帮助我们从request中快速提取到表单的信息到book对象中提升开发效率
            Book book = RequestParameterUtils.getRequestParameterForReflect(req, Book.class);
            Integer count=-1;
            if(book.getId()!=null&&book.getId()>0){
                //表示更新
                count=bookService.updateBook(book);
            }else {
                //表示添加操作
                count = bookService.saveBook(book);
                count=-1;
            }
            if (count==-1){
                //表示添加成功
                resp.sendRedirect("/bookServlet?type=query");
            }else {
                //TODO 表示添加失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //bookService
    }

    private void queryBookList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询所有的书籍信息
        List<Book> list = bookService.list(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/book/book.jsp").forward(req, resp);
    }
}
