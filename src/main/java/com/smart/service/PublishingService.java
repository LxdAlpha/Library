package com.smart.service;

import com.smart.dao.PublishingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by llxxdd on 2017/7/6.
 */
@Service
public class PublishingService {
    private PublishingDao publishingDao;

    public Collection getQuery(String strif){
        return publishingDao.query(strif);
    }

    @Autowired
    public void setPublishingDao(PublishingDao publishingDao) {
        this.publishingDao = publishingDao;
    }
}
