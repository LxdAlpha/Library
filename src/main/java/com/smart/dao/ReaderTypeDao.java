package com.smart.dao;

import com.smart.domain.ReaderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by llxxdd on 2017/7/5.
 */
@Repository("ReaderTypeDao")
public class ReaderTypeDao {
    private JdbcTemplate jdbcTemplate;

    public Collection query(String strif){
        final Collection readerTyprColl = new ArrayList();
        String sql;
        if (strif != "all" && strif != null && strif != "") {
            sql = "select * from tb_readerType where " + strif + "";
        } else {
            sql = "select * from tb_readerType";
        }
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                ReaderType readerType = new ReaderType();
                readerType.setId(Integer.valueOf(rs.getString(1)));
                readerType.setName(rs.getString(2));
                readerType.setNumber(rs.getInt(3));
                readerTyprColl.add(readerType);
            }
        });
        return readerTyprColl;
    }

    public ReaderType queryM(String id){
        final ReaderType readerType = new ReaderType();
        String sql = "select * from tb_readerType where id="
                + id + "";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                readerType.setId(Integer.valueOf(rs.getString(1)));
                readerType.setName(rs.getString(2));
                readerType.setNumber(rs.getInt(3));
            }
        });
        return readerType;
    }

    public int update(ReaderType readerType){
        String sql = "Update tb_readerType set name='"
                + readerType.getName() + "',number="
                + readerType.getNumber() + " where id="
                + readerType.getId() + "";
        int res = jdbcTemplate.update(sql);
        return res;
    }

    public int delete(String id){
        String sql_1 = "SELECT COUNT(*) FROM tb_readertype WHERE id="
                + id + "";
        int count = jdbcTemplate.queryForObject(sql_1, Integer.class);
        int flag = 0;
        if(count >= 1){
            String sql = "Delete from tb_readerType where id=" + id + "";
            flag = jdbcTemplate.update(sql);
        }
        return flag;
    }

    public int insert(ReaderType readerType){
        String sql1 = "SELECT COUNT(*) FROM tb_readerType WHERE name='"
                + readerType.getName() + "'";
        int count = jdbcTemplate.queryForObject(sql1, Integer.class);
        String sql = "";
        int flag = 0;
        if(count >= 1){
            flag = 2;
        }else {
            sql = "Insert into tb_readerType (name,number) values('"
                    + readerType.getName() + "',"
                    + readerType.getNumber() + ")";
            flag = jdbcTemplate.update(sql);
        }
        return flag;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
