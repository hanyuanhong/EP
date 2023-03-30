package com.hyh;

import com.hyh.dao.BookDao;
import com.hyh.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForScope {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookDao bookDao1 = (BookDao) ctx.getBean("bookDao123");
        BookDao bookDao2 = (BookDao) ctx.getBean("bookDao123");
        System.out.println(bookDao1);
        System.out.println(bookDao2);

        BookDao bookDao3 = (BookDao) ctx.getBean("bookDao456");
        BookDao bookDao4 = (BookDao) ctx.getBean("bookDao456");
        System.out.println(bookDao3);
        System.out.println(bookDao4);
    }
}
