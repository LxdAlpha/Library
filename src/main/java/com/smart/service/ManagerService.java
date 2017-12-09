package com.smart.service;

import com.smart.dao.ManagerDao;
import com.smart.domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by llxxdd on 2017/6/27.
 */
@Service
public class ManagerService {
    private ManagerDao managerDao;

    public boolean hasMatchUser(String userName, String password) {
        int matchCount =managerDao.getMatchCount(userName, password);
        return matchCount > 0;
    }

    public Collection getManagerQuery(String str){
        return managerDao.query(str);
    }

    public int setManagerDel(int id){
        return managerDao.delete(id);
    }

    public Manager getManagerModifyQuery(int id){
        return managerDao.query_update(id);
    }

    public int setManagerModify(Manager manager){
        return managerDao.update(manager);
    }

    public int setManagerAdd(Manager manager){
        return managerDao.insert(manager);
    }

    public Manager getQueryPwd(String name){
        return managerDao.query_pwd(name);
    }

    public int setModifyPwd(Manager manager){
        return managerDao.updatePwd(manager);
    }
    @Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }
}
