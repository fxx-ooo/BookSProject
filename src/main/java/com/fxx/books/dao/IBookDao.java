package com.fxx.books.dao;

import com.fxx.books.bean.Book;

import java.util.List;

/**
 * book的Dao接口
 */
public interface IBookDao {
    /**
     * 查询所有书籍信息
     * @return
     */
    public List<Book> list(Book book);

    /**
     * 添加书籍信息
     * @param book
     * @return
     */
    public Integer saveBook(Book book);

    /**
     * 书籍的更新操作
     * @param book
     * @return
     */
    public Integer updateBook(Book book);

    /**
     * 根据编号删除书籍操作
     * @param id
     * @return
     */
    public  Integer deleteById( Integer id);

    public  Book queryById(Integer id);
}
