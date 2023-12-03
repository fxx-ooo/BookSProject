package com.fxx.books.service.impl;

import com.fxx.books.bean.Book;
import com.fxx.books.dao.IBookDao;
import com.fxx.books.dao.impl.BookDaoImpl;
import com.fxx.books.service.IBookService;

import java.util.List;

public class BookServiceImpl implements IBookService {
    private  IBookDao bookDao=new BookDaoImpl();
    @Override
    public List<Book> list(Book book) {
        return bookDao.list(book);
    }

    @Override
    public Integer saveBook(Book book) {
        return bookDao.saveBook(book);
    }

    @Override
    public Integer updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Integer deleteById(Integer id) {
        return bookDao.deleteById(id);
    }

    @Override
    public Book queryById(Integer id) {
        return bookDao.queryById(id);
    }
}
