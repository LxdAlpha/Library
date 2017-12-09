package com.smart.dao;

import com.smart.domain.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by llxxdd on 2017/7/1.
 */
@Repository
public class LibraryDao {
    private JdbcTemplate jdbcTemplate;

    public Library query(){
        String sql = "select * from tb_library where id=1";
        final Library library = new Library();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                library.setId(Integer.valueOf(rs.getString(1)));
                library.setLibraryname(rs.getString(2));
                library.setCurator(rs.getString(3));
                library.setTel(rs.getString(4));
                library.setAddress(rs.getString(5));
                library.setEmail(rs.getString(6));
                library.setUrl(rs.getString(7));
                library.setCreateDate(rs.getString(8));
                library.setIntroduce(rs.getString(9));
            }
        });
        return library;
    }

    public int update(Library library){
        String sql="UPDATE tb_library SET libraryname='"+library.getLibraryname()+"',curator='"+library.getCurator()+"',tel='"+library.getTel()+"',address='"+library.getAddress()+"',email='"+library.getEmail()+"',url='"+library.getUrl()+"',createDate='"+library.getCreateDate()+"',introduce='"+library.getIntroduce()+"' where id=1";
        int res = jdbcTemplate.update(sql);
        return res;
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
