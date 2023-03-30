package com.hyh;

import com.hyh.service.BookService;
import com.hyh.service.impl.BookServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

//        BookService bookService = new BookServiceImpl();
        BookService bookService = (BookService) ctx.getBean("bookService123");
        bookService.save();
    }
}
