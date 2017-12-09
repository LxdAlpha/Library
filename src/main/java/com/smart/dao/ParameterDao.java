package com.smart.dao;

import com.smart.domain.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by llxxdd on 2017/7/2.
 */
@Repository
public class ParameterDao {
    private JdbcTemplate jdbcTemplate;

    public Parameter query(){
        String sql = "select * from tb_parameter where id=1";
        final Parameter parameter = new Parameter();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                parameter.setId(Integer.valueOf(rs.getString(1)));
                parameter.setCost(rs.getInt(2));
                parameter.setValidity(rs.getInt(3));
            }
        });
        return parameter;
    }

    public int update(Parameter parameter){
        String sql="UPDATE tb_parameter SET cost="+parameter.getCost()+",validity="+parameter.getValidity()+" where id=1";
        int res = jdbcTemplate.update(sql);
        return res;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
