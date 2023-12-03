package com.fxx.books.bean;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 书籍
 */
public class Book {
    private Integer id;//书籍编号
    private  String bookName;//书籍名称
    private  String  author;//作者
    private  String publish;//出版社
    private  String isbn;//书籍编码
    private  String  introduction;//书籍介绍
    private  String language;//书籍语言
    private BigDecimal price;//书籍售价
    private Date pubdate;//发布日期
    private  String pressmark;//书架号
    private  Integer state;//书籍这状态：0空闲  1借阅  2下架   3其他
    private  Integer isdeleted;//是否被删除： 0删除   1未删除





    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", isbn='" + isbn + '\'' +
                ", introduction='" + introduction + '\'' +
                ", language='" + language + '\'' +
                ", price=" + price +
                ", pubdate=" + pubdate +
                ", state=" + state +
                ", isdeleted=" + isdeleted +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public String getPressmark() {
        return pressmark;
    }

    public void setPressmark(String pressmark) {
        this.pressmark = pressmark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }
}
