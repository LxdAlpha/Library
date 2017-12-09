package com.smart.dao;

import com.smart.domain.BookCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by llxxdd on 2017/7/2.
 */
@Repository
public class BookCaseDao {
    private JdbcTemplate jdbcTemplate;

    public Collection query(String strif){
        final Collection bookcaseColl = new ArrayList();
        String sql = "";
        if(strif!="all" && strif!=null && strif!=""){
            sql="select * from tb_bookcase where "+strif+"";
        }else{
            sql="select * from tb_bookcase";
        }
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                BookCase bookCase = new BookCase();
                bookCase.setId(Integer.valueOf(rs.getString(1)));
                bookCase.setName(rs.getString(2));
                bookcaseColl.add(bookCase);
            }
        });
        return bookcaseColl;
    }

    public int insert(BookCase bookCase){
        String sql1="SELECT COUNT(* ) FROM tb_bookcase WHERE name='"+bookCase.getName()+"'";
        int count = jdbcTemplate.queryForObject(sql1, Integer.class);
        int res = 0;
        String sql = "";
        if(count > 0){
            res = 2;
        }else if(count == 0){
            sql ="Insert into tb_bookcase (name) values('"+bookCase.getName()+"')";
            res = jdbcTemplate.update(sql);
        }
        return res;
    }

    public BookCase queryM(String id){
        final BookCase bookCase = new BookCase();
        String sql="select * from tb_bookcase where id="+Integer.parseInt(id.trim())+"";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                bookCase.setId(Integer.valueOf(rs.getString(1)));
                bookCase.setName(rs.getString(2));
            }
        });
        return bookCase;
    }

    public int update(BookCase bookCase){
        String sql="Update tb_bookcase set name='"+bookCase.getName()+"' where id="+bookCase.getId()+"";
        int res = 0;
        res = jdbcTemplate.update(sql);
        return res;
    }

    public int delete(String id){
        String sql_1="SELECT COUNT(*) FROM tb_bookcase WHERE id="+Integer.parseInt(id.trim())+"";
        int count = jdbcTemplate.queryForObject(sql_1, Integer.class);
        int res = 0;
        if(count == 1){
            String sql="Delete from tb_bookcase where id="+ Integer.parseInt(id.trim())+"";
            res = jdbcTemplate.update(sql);
        }
        return res;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
