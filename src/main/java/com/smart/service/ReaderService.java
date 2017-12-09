package com.smart.service;

import com.smart.dao.ReaderDao;
import com.smart.domain.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by llxxdd on 2017/7/5.
 */
@Service
public class ReaderService {
    private ReaderDao readerDao;

    public Collection getReaderQuery(String strif){
        return readerDao.query(strif);
    }

    public int setReadAdd(Reader reader){
        return readerDao.insert(reader);
    }

    public Reader getReaderModifyQuery(Reader reader){
        return readerDao.queryM(reader);
    }

    public int setReaderModify(Reader reader){
        return readerDao.update(reader);
    }

    public int setReaderDel(String id){
        return readerDao.delete(id);
    }

    @Autowired
    public void setReaderDao(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }
}
