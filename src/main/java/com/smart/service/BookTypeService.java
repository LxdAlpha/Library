package com.smart.service;


import com.smart.dao.BookTypeDao;
import com.smart.domain.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by llxxdd on 2017/7/6.
 */
@Service
public class BookTypeService {
    private BookTypeDao bookTypeDao;

    public Collection getBookTypeQuery(String strif){
        return bookTypeDao.query(strif);
    }

    public int setBookTypeAdd(BookType bookType){
        return bookTypeDao.insert(bookType);
    }

    public BookType getBookTypeModifyQuery(String id){
        return bookTypeDao.queryM(id);
    }

    public int  setBookTypeModify(BookType bookType){
        return  bookTypeDao.update(bookType);
    }

    public int setBookTypeDel(String id){
        return bookTypeDao.delete(id);
    }

    @Autowired
    public void setBookTypeDao(BookTypeDao bookTypeDao) {
        this.bookTypeDao = bookTypeDao;
    }
}
