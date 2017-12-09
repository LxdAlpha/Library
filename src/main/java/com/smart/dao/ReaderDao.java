package com.smart.dao;

import com.smart.domain.Reader;
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
@Repository
public class ReaderDao {
    private JdbcTemplate jdbcTemplate;

    public Collection query(String strif){
        final Collection readerColl = new ArrayList();
        String sql = "";
        if(strif!="all" && strif!=null && strif!=""){
            sql="select r.*,t.name as typename,t.number from tb_reader r left join tb_readerType t on r.typeid=t.id where "+strif+"";
        }else{
            sql="select r.*,t.name as typename,t.number from tb_reader r left join tb_readerType t on r.typeid=t.id";
        }
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                Reader reader = new Reader();
                reader.setId(Integer.valueOf(rs.getString(1)));
                reader.setName(rs.getString(2));
                reader.setSex(rs.getString(3));
                reader.setBarcode(rs.getString(4));
                reader.setVocation(rs.getString(5));
                reader.setBirthday(rs.getString(6));
                reader.setPaperType(rs.getString(7));
                reader.setPaperNO(rs.getString(8));
                reader.setTel(rs.getString(9));
                reader.setEmail(rs.getString(10));
                reader.setCreateDate(rs.getString(11));
                reader.setOperator(rs.getString(12));
                reader.setRemark(rs.getString(13));
                reader.setTypeid(rs.getInt(14));
                reader.setTypename(rs.getString(15));
                reader.setNumber(rs.getInt(16));
                readerColl.add(reader);
            }
        });
        return readerColl;
    }

    public int insert(Reader reader){
        String sql1="SELECT COUNT(*) FROM tb_reader WHERE barcode='"+reader.getBarcode()+"'";
        int flag = 0;
        int count = jdbcTemplate.queryForObject(sql1, Integer.class);
        String sql = "";
        if(count > 0){
            flag = 2;
        }else{
            sql ="Insert into tb_reader (name,sex,barcode,vocation,birthday,paperType,paperNO,tel,email,createDate,operator,remark,typeid) values('"+reader.getName()+"','"+reader.getSex()+"','"+reader.getBarcode()+"','"+reader.getVocation()+"','"+reader.getBirthday()+"','"+reader.getPaperType()+"','"+reader.getPaperNO()+"','"+reader.getTel()+"','"+reader.getEmail()+"','"+reader.getCreateDate()+"','"+reader.getOperator()+"','"+reader.getRemark()+"',"+reader.getTypeid()+")";
            flag = jdbcTemplate.update(sql);
        }
        return flag;
    }

    public Reader queryM(Reader reader){
        final Reader reader1 = new Reader();
        String sql="";
        if(reader.getId()!=null){
            sql="select r.*,t.name as typename,t.number from tb_reader r left join tb_readerType t on r.typeid=t.id where r.id="+reader.getId()+"";
        }else if(reader.getBarcode()!=null){
            sql="select r.*,t.name as typename,t.number from tb_reader r left join tb_readerType t on r.typeid=t.id where r.barcode="+reader.getBarcode()+"";
        }
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                reader1.setId(Integer.valueOf(rs.getString(1)));
                reader1.setName(rs.getString(2));
                reader1.setSex(rs.getString(3));
                reader1.setBarcode(rs.getString(4));
                reader1.setVocation(rs.getString(5));
                reader1.setBirthday(rs.getString(6));
                reader1.setPaperType(rs.getString(7));
                reader1.setPaperNO(rs.getString(8));
                reader1.setTel(rs.getString(9));
                reader1.setEmail(rs.getString(10));
                reader1.setCreateDate(rs.getString(11));
                reader1.setOperator(rs.getString(12));
                reader1.setRemark(rs.getString(13));
                reader1.setTypeid(rs.getInt(14));
                reader1.setTypename(rs.getString(15));
                reader1.setNumber(rs.getInt(16));
            }
        });
        return reader1;
    }

    public int update(Reader reader){
        String sql="Update tb_reader set sex='"+reader.getSex()+"',barcode='"+reader.getBarcode()+"',vocation='"+reader.getVocation()+"',birthday='"+reader.getBirthday()+"',paperType='"+reader.getPaperType()+"',paperNO='"+reader.getPaperNO()+"',tel='"+reader.getTel()+"',email='"+reader.getEmail()+"',remark='"+reader.getRemark()+"',typeid="+reader.getTypeid()+" where id="+reader.getId()+"";
        int flag = jdbcTemplate.update(sql);
        return flag;
    }

    public int delete(String id){
        String sql="Delete from tb_reader where id="+id+"";
        int flag = jdbcTemplate.update(sql);
        return flag;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
