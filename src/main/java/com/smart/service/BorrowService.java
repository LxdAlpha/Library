package com.smart.service;

import com.smart.dao.BorrowDao;
import com.smart.domain.Book;
import com.smart.domain.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by llxxdd on 2017/6/28.
 */
@Service
public class BorrowService {
    private BorrowDao borrowDao;

    public Collection getMoreBookBorrowSort(){
        return borrowDao.bookBorrowSort();
    }

    public Collection getBorrowInfo(String str){
        return borrowDao.borrowInfo(str);
    }

    public int setInsertBorrow(Reader reader, Book book, String operator){
        return borrowDao.insertBorrow(reader, book, operator);
    }

    public int setBookRenew(int id){
        return borrowDao.renew(id);
    }

    public Collection getBorrowQuery(String str){
        return borrowDao.borrowQuery(str);
    }

    public Collection setBremind(){
        return borrowDao.bremind();
    }
    @Transactional
    public int setBack(int id, String operator){
        return borrowDao.back(id, operator);
    }

    @Autowired
    public void setBorrowDao(BorrowDao borrowDao) {
        this.borrowDao = borrowDao;
    }
}
