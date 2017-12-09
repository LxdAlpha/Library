package com.smart.dao;

import com.smart.domain.Publishing;
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
public class PublishingDao {
    private JdbcTemplate jdbcTemplate;

    public Collection query(String strif){
        final Collection pubColl = new ArrayList();
        String sql = "";
        if(strif!="all" && strif!=null && strif!=""){
            sql="select * from tb_publishing where "+strif+"";
        }else{
            sql="select * from tb_publishing";
        }
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                Publishing publishing = new Publishing();
                publishing.setIsbn(rs.getString(1));
                publishing.setPubname(rs.getString(2));
                pubColl.add(publishing);
            }
        });
        return pubColl;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
