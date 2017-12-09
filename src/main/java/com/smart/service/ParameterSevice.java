package com.smart.service;

import com.smart.dao.ParameterDao;
import com.smart.domain.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by llxxdd on 2017/7/2.
 */
@Service
public class ParameterSevice {
    private ParameterDao parameterDao;

    public Parameter getParameterModifyQuery(){
        return parameterDao.query();
    }

    public int setParameterModify(Parameter parameter){
        int res = parameterDao.update(parameter);
        return res;
    }
    @Autowired
    public void setParameterDao(ParameterDao parameterDao) {
        this.parameterDao = parameterDao;
    }
}
