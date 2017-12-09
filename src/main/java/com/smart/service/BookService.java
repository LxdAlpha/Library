package com.smart.service;

import com.smart.dao.BookDao;
import com.smart.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by llxxdd on 2017/7/6.
 */
@Service
public class BookService {
    private BookDao bookDao;

    public Collection getBookQuery(String strif){
        return bookDao.query(strif);
    }

    public Book getBookDetail(String id){
        return bookDao.queryM(id);
    }

    public int setBookAdd(Book book){
        return bookDao.insert(book);
    }

    public Book getBookModifyQuery(String id){
        return bookDao.queryM(id);
    }

    public int setBookModify(Book book){
        return bookDao.update(book);
    }

    public int setBookDel(String id){
        return bookDao.delete(id);
    }

    public Book getBookByfAndKey(String f, String key){
        return bookDao.queryB(f, key);
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
