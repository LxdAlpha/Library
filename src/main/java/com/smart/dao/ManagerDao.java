package com.smart.dao;

import com.smart.domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by llxxdd on 2017/6/27.
 */
@Repository("ManagerDao")
public class ManagerDao {
    private JdbcTemplate jdbcTemplate;

    private  final static String MATCH_COUNT_SQL = " SELECT count(*) FROM tb_manager  " +
            " WHERE name =? and PWD=? ";

    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    public Manager query_p(String str) {
        String sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_manager m left join tb_purview p on m.id=p.id where m.name=? ";
        final Manager manager = new Manager();
        jdbcTemplate.query(sql, new Object[]{str},
                new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                manager.setId(Integer.valueOf(rs.getString(1)));
                manager.setName(rs.getString(2));
                manager.setPwd(rs.getString(3));
                manager.setSysset(rs.getInt(4));
                manager.setReaderset(rs.getInt(5));
                manager.setBookset(rs.getInt(6));
                manager.setBorrowback(rs.getInt(7));
                manager.setSysquery(rs.getInt(8));
            }
        });
        return manager;
    }

    public Collection query(String queryif){
        final Collection managercoll = new ArrayList();
        String sql = "";
        if(queryif == null || queryif.equals("") || queryif.equals("all")){
            sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_manager m left join tb_purview p on m.id=p.id";
        }else{
            sql="select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_manager m left join tb_purview p on m.id=p.id where m.name='"+queryif+"'";
        }
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                Manager manager = new Manager();
                manager.setId(Integer.valueOf(rs.getString(1)));
                manager.setName(rs.getString(2));
                manager.setPwd(rs.getString(3));
                manager.setSysset(rs.getInt(4));
                manager.setReaderset(rs.getInt(5));
                manager.setBookset(rs.getInt(6));
                manager.setBorrowback(rs.getInt(7));
                manager.setSysquery(rs.getInt(8));
                managercoll.add(manager);
            }
        });
        return managercoll;
    }

    public int delete(int id){
        int res = 0;
        String sql = "DELETE FROM tb_manager where id=" + id +"";
        res = jdbcTemplate.update(sql);
        if(res != 0){
            String sql1 = "DELETE FROM tb_purview where id=" + id +"";
            jdbcTemplate.update(sql1);
        }
        return res;
    }

    public Manager query_update(int id){
        final Manager manager = new Manager();
        String sql = "select m.*,p.sysset,p.readerset,p.bookset,p.borrowback,p.sysquery from tb_manager m left join tb_purview p on m.id=p.id where m.id=" + id + "";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                manager.setId(Integer.valueOf(rs.getString(1)));
                manager.setName(rs.getString(2));
                manager.setPwd(rs.getString(3));
                manager.setSysset(rs.getInt(4));
                manager.setReaderset(rs.getInt(5));
                manager.setBookset(rs.getInt(6));
                manager.setBorrowback(rs.getInt(7));
                manager.setSysquery(rs.getInt(8));
            }
        });
        return manager;
    }

    public int update(final Manager manager){
        String sql1="SELECT * FROM tb_purview WHERE id="+manager.getId()+"";
        final Manager manager1 = new Manager();
        String sql = "";
        jdbcTemplate.query(sql1, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                manager1.setId(resultSet.getInt(1));
            }
        });
        if(manager1.getId() == manager.getId()){
            sql = "Update tb_purview set sysset=" + manager.getSysset() +
                    ",readerset=" + manager.getReaderset() + ",bookset="+manager.getBookset()+",borrowback="+manager.getBorrowback()+",sysquery="+manager.getSysquery()+" where id=" + manager.getId() + "";
        }else{
            sql="INSERT INTO tb_purview values("+manager.getId()+","+manager.getSysset()+","+manager.getReaderset()+","+manager.getBookset()+","+manager.getBorrowback()+","+manager.getSysquery()+")";
        }
        int res = jdbcTemplate.update(sql);
        return res;
    }

    public int insert(Manager manager){
        String sql1 = "SELECT COUNT(*) FROM tb_manager WHERE name='"+manager.getName()+"'";
        int count = jdbcTemplate.queryForObject(sql1, Integer.class);
        int res = 0;
        String sql = "";
        if(count > 0){
            res = 2;
        }else{
            sql = "INSERT INTO tb_manager (name,pwd) values('" +
                    manager.getName() + "','" +
                    manager.getPwd() +
                    "')";
            res = jdbcTemplate.update(sql);
        }
        return res;
    }

    public Manager query_pwd(String name){
        String sql = "SELECT * FROM tb_manager WHERE name='" +name + "'";
        final Manager manager = new Manager();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                manager.setId(Integer.valueOf(rs.getString(1)));
                manager.setName(rs.getString(2));
                manager.setPwd(rs.getString(3));
            }
        });
        return manager;
    }

    public int updatePwd(Manager manager){
        String sql="UPDATE tb_manager SET pwd='"+manager.getPwd()+"' where name='"+manager.getName()+"'";
        int ret=jdbcTemplate.update(sql);
        return ret;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
