package com.smart.dao;

import com.smart.domain.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by llxxdd on 2017/7/6.
 */
@Repository
public class BookTypeDao {
    private JdbcTemplate jdbcTemplate;

    public Collection query(String strif){
        final Collection bookTypeColl = new ArrayList();
        String sql = "";
        if (strif != "all" && strif != null && strif != "") {
            sql = "select * from tb_bookType where " + strif + "";
        } else {
            sql = "select * from tb_bookType";
        }
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                BookType bookType = new BookType();
                bookType.setId(Integer.valueOf(rs.getString(1)));
                bookType.setTypeName(rs.getString(2));
                bookType.setDays(rs.getInt(3));
                bookTypeColl.add(bookType);
            }
        });
        return bookTypeColl;
    }

    public int insert(BookType bookType){
        String sql1 = "SELECT COUNT(*) FROM tb_bookType WHERE typename='"
                + bookType.getTypeName() + "'";
        int count = jdbcTemplate.queryForObject(sql1, Integer.class);
        int flag = 0;
        String sql = "";
        if(count >= 1){
            flag = 2;
        }else{
            sql = "Insert into tb_bookType (typename,days) values('"
                    + bookType.getTypeName() + "',"
                    + bookType.getDays() + ")";
            flag = jdbcTemplate.update(sql);
        }
        return flag;
    }

    public BookType queryM(String id){
        final BookType bookType = new BookType();
        String sql = "select * from tb_bookType where id=" + id + "";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                bookType.setId(Integer.valueOf(rs.getString(1)));
                bookType.setTypeName(rs.getString(2));
                bookType.setDays(rs.getInt(3));
            }
        });
        return bookType;
    }

    public int update(BookType bookType){
        String sql = "Update tb_bookType set typename='"
                + bookType.getTypeName() + "',days="
                + bookType.getDays() + " where id=" + bookType.getId()
                + "";
        int flag = 0;
        flag = jdbcTemplate.update(sql);
        return flag;
    }

    public int delete(String id){
        String sql_1 = "SELECT COUNT(*) FROM tb_bookinfo WHERE typeid=" + id + "";
        int flag = 0;
        int count = jdbcTemplate.queryForObject(sql_1, Integer.class);
        if(count == 0){
            String sql = "Delete from tb_bookType where id=" + id + "";
            flag = jdbcTemplate.update(sql);
        }
        return flag;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
