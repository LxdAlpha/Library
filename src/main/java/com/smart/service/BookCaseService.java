package com.smart.service;

import com.smart.dao.BookCaseDao;
import com.smart.domain.BookCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by llxxdd on 2017/7/2.
 */
@Service
public class BookCaseService {
    private BookCaseDao bookCaseDao;

    public Collection getBookCaseQuery(){
        String str = null;
        return bookCaseDao.query(str);
    }

    public int setBookCaseAdd(BookCase bookCase){
        return bookCaseDao.insert(bookCase);
    }

    public BookCase getBookCaseModifyQuery(String id){
        return bookCaseDao.queryM(id);
    }

    public int setBookCaseModify(BookCase bookCase){
        return bookCaseDao.update(bookCase);
    }

    public int setBookCaseDel(String id){
        return bookCaseDao.delete(id);
    }
    @Autowired
    public void setBookCaseDao(BookCaseDao bookCaseDao) {
        this.bookCaseDao = bookCaseDao;
    }
}
