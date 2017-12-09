package com.smart.service;

import com.smart.dao.ReaderTypeDao;
import com.smart.domain.ReaderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by llxxdd on 2017/7/5.
 */
@Service
public class ReaderTypeService {
    private ReaderTypeDao readerTypeDao;

    public Collection getReaderTypeQuery(String strif){
        return readerTypeDao.query(strif);
    }

    public ReaderType getReaderTypeModifyQuery(String id){
        return readerTypeDao.queryM(id);
    }

    public int setReaderTypeModify(ReaderType readerType){
        return readerTypeDao.update(readerType);
    }

    public int setReaderTypeDel(String id){
        return readerTypeDao.delete(id);
    }

    public int setReaderTypeAdd(ReaderType readerType){
        return readerTypeDao.insert(readerType);
    }

    @Autowired
    public void setReaderTypeDao(ReaderTypeDao readerTypeDao) {
        this.readerTypeDao = readerTypeDao;
    }
}
