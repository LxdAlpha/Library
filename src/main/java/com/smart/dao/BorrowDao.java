package com.smart.dao;

import com.smart.domain.Book;
import com.smart.domain.Borrow;
import com.smart.domain.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by llxxdd on 2017/6/27.
 */
@Repository("BorrowDao")
public class BorrowDao {
    private JdbcTemplate jdbcTemplate;

    public Collection bookBorrowSort() {
        String sql = "select * from (SELECT bookid,count(bookid) as degree FROM tb_borrow group by bookid) as borr join (select b.*,c.name as bookcaseName,p.pubname,t.typename from tb_bookinfo b left join tb_bookcase c on b.bookcase=c.id join tb_publishing p on b.ISBN=p.ISBN join tb_booktype t on b.typeid=t.id where b.del=0) as book on borr.bookid=book.id order by borr.degree desc limit 10 ";
        System.out.println("图书借阅排行："+sql);
        final  Collection coll = new ArrayList();

        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                Borrow borrow = new Borrow();
                borrow.setBookId(resultSet.getInt(1));
                borrow.setDegree(resultSet.getInt(2));
                borrow.setBookBarcode(resultSet.getString(3));
                borrow.setBookName(resultSet.getString(4));
                borrow.setAuthor(resultSet.getString(6));
                borrow.setPrice(Float.valueOf(resultSet.getString(9)));
                borrow.setBookcaseName(resultSet.getString(16));
                borrow.setPubName(resultSet.getString(17));
                borrow.setBookType(resultSet.getString(18));
                coll.add(borrow);
            }
        });
        return coll;
    }

    public Collection borrowInfo(String str){
        String sql="select borr.*,book.bookname,book.price,pub.pubname,bs.name bookcasename,r.barcode from (select * from tb_borrow where ifback=0) as borr left join tb_bookinfo book on borr.bookid=book.id join tb_publishing pub on book.isbn=pub.isbn join tb_bookcase bs on book.bookcase=bs.id join tb_reader r on borr.readerid=r.id where r.barcode='"+str+"'";
        final Collection coll = new ArrayList();
         jdbcTemplate.query(sql, new RowCallbackHandler() {
             public void processRow(ResultSet rs) throws SQLException {
                 Borrow borrow = new Borrow();
                 borrow.setId(Integer.valueOf(rs.getInt(1)));
                 borrow.setBorrowTime(rs.getString(4));
                 borrow.setBackTime(rs.getString(5));
                 borrow.setBookName(rs.getString(8));
                 borrow.setPrice(Float.valueOf(rs.getFloat(9)));
                 borrow.setPubName(rs.getString(10));
                 borrow.setBookcaseName(rs.getString(11));
                 coll.add(borrow);
             }
         });
        return coll;
    }

    public int insertBorrow(Reader reader, Book book, String operator){
        Date dateU=new Date();
        java.sql.Date date=new java.sql.Date(dateU.getTime());
        String sql1="select t.days from tb_bookinfo b left join tb_booktype t on b.typeid=t.id where b.id="+book.getId()+"";
        int days = 0;
        days = jdbcTemplate.queryForObject(sql1, Integer.class);
        Calendar calendar =new GregorianCalendar();
        calendar.setTime(dateU);
        long time = calendar.getTimeInMillis();
        time = time + (long)days * (long)86400000;
        calendar.setTimeInMillis(time);
        java.sql.Date timeDate = new java.sql.Date(calendar.getTime().getTime());
        String sql ="Insert into tb_borrow (readerid,bookid,borrowTime,backTime,operator) values("+reader.getId()+","+book.getId()+",'"+date+"','"+timeDate+"','"+operator+"')";
        int flag = jdbcTemplate.update(sql);
        return flag;
    }

    public int renew(int id){
        String sql0="SELECT bookid FROM tb_borrow WHERE id="+id+"";
        String sql1 = "select t.days from tb_bookinfo b left join tb_booktype t on b.typeid=t.id where b.id=" +
                jdbcTemplate.queryForObject(sql0, Integer.class) + "";
        int days = 0;
        days = jdbcTemplate.queryForObject(sql1, Integer.class);
        String sql2 = "select backtime from tb_borrow where id = " + id + "";
        java.sql.Date lastBackTime = jdbcTemplate.queryForObject(sql2, java.sql.Date.class);

        Calendar calendar =new GregorianCalendar();
        calendar.setTime(lastBackTime);
        long time = calendar.getTimeInMillis();
        time = time + (long)days * (long)86400000;
        calendar.setTimeInMillis(time);
        java.sql.Date timeDate = new java.sql.Date(calendar.getTime().getTime());
        String sql = "UPDATE tb_borrow SET backtime='" + timeDate +
                "' where id=" + id + "";
        int flag = jdbcTemplate.update(sql);
        return flag;
    }

    public int back(int id, String operator){
        String sql0="SELECT readerid FROM tb_borrow WHERE id="+id+"";
        String sql1="SELECT bookid FROM tb_borrow WHERE id="+id+"";
        int readerid = jdbcTemplate.queryForObject(sql0, Integer.class);
        int bookid = jdbcTemplate.queryForObject(sql1, Integer.class);
        int flag=0;
        Date dateU = new Date();
        java.sql.Date date = new java.sql.Date(dateU.getTime());
        String sql2="INSERT INTO tb_giveback (readerid,bookid,backTime,operator) VALUES("+readerid+","+bookid+",'"+date+"','"+operator+"')";
        int ret = jdbcTemplate.update(sql2);
        if(ret==1){
            String sql3 = "UPDATE tb_borrow SET ifback=1 where id=" + id +
                    "";
            flag = jdbcTemplate.update(sql3);
        }else{
            flag=0;
        }
        return flag;
    }

    public Collection borrowQuery(String strif){
        String sql="";
        if(strif!="all" && strif!=null && strif!=""){
            sql="select * from (select borr.borrowTime,borr.backTime,book.barcode,book.bookname,r.name readername,r.barcode readerbarcode,borr.ifback from tb_borrow borr join tb_bookinfo book on book.id=borr.bookid join tb_reader r on r.id=borr.readerid) as borr where borr."+strif+"";
        }else{
            sql="select * from (select borr.borrowTime,borr.backTime,book.barcode,book.bookname,r.name readername,r.barcode readerbarcode,borr.ifback from tb_borrow borr join tb_bookinfo book on book.id=borr.bookid join tb_reader r on r.id=borr.readerid) as borr";
        }
        final Collection coll=new ArrayList();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                Borrow form = new Borrow();
                form.setBorrowTime(rs.getString(1));
                form.setBackTime(rs.getString(2));
                form.setBookBarcode(rs.getString(3));
                form.setBookName(rs.getString(4));
                form.setReaderName(rs.getString(5));
                form.setReaderBarcode(rs.getString(6));
                form.setIfBack(rs.getInt(7));
                coll.add(form);
            }
        });
        return coll;
    }


    public Collection bremind(){
        Date dateU = new Date();
        java.sql.Date date = new java.sql.Date(dateU.getTime());
        String sql="select borr.borrowTime,borr.backTime,book.barcode,book.bookname,r.name readername,r.barcode readerbarcode from tb_borrow borr join tb_bookinfo book on book.id=borr.bookid join tb_reader r on r.id=borr.readerid where borr.backTime <='"+date+"'";
        final Collection coll=new ArrayList();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                Borrow form = new Borrow();
                form.setBorrowTime(rs.getString(1));
                form.setBackTime(rs.getString(2));
                form.setBookBarcode(rs.getString(3));
                form.setBookName(rs.getString(4));
                form.setReaderName(rs.getString(5));
                form.setReaderBarcode(rs.getString(6));
                coll.add(form);
            }
        });
        return coll;
    }
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
