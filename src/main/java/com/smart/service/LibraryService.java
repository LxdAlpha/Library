package com.smart.service;

import com.smart.dao.LibraryDao;
import com.smart.domain.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by llxxdd on 2017/7/1.
 */
@Service
public class LibraryService {
    private LibraryDao libraryDao;

    public Library getLibraryModifyQuery(){
        Library library = libraryDao.query();
        return library;
    }

    @Transactional
    public int setLibraryModify(Library library){
        int res = libraryDao.update(library);
        return res;
    }

    @Autowired
    public void setLibraryDao(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
    }

}
