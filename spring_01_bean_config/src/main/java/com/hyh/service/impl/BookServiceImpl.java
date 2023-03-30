package com.hyh.service.impl;

import com.hyh.dao.BookDao;
import com.hyh.service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookDaoProp;

    public void save() {
        System.out.println("book service save!");
        bookDaoProp.save();
    }

    public void setBookDaoProp(BookDao bookDaoProp) {
        this.bookDaoProp = bookDaoProp;
    }
}
