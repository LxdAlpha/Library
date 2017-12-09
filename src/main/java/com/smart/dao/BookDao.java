package com.smart.dao;

import com.smart.domain.Book;
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
public class BookDao {
    private JdbcTemplate jdbcTemplate;

    public Collection query(String strif){
        final Collection bookColl = new ArrayList();
        String sql = "";
        if (strif != "all" && strif != null && strif != "") {
            sql = "select * from (select b.*,c.name as bookcaseName,p.pubname as publishing,t.typename from tb_bookinfo b left join tb_bookcase c on b.bookcase=c.id join tb_publishing p on b.ISBN=p.ISBN join tb_booktype t on b.typeid=t.id where b.del=0)  as book where book."
                    + strif + "'";
        } else {
            sql = "select b.*,c.name as bookcaseName,p.pubname as publishing,t.typename from tb_bookinfo b left join tb_bookcase c on b.bookcase=c.id join tb_publishing p on b.ISBN=p.ISBN join tb_booktype t on b.typeid=t.id where b.del=0";
        }
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                Book book = new Book();
                book.setBarcode(rs.getString(1));
                book.setBookName(rs.getString(2));
                book.setTypeId(rs.getInt(3));
                book.setAuthor(rs.getString(4));
                book.setTranslator(rs.getString(5));
                book.setIsbn(rs.getString(6));
                book.setPrice(Float.valueOf(rs.getString(7))); // 此处必须进行类型转换
                book.setPage(rs.getInt(8));
                book.setBookcaseid(rs.getInt(9));
                book.setInTime(rs.getString(10));
                book.setOperator(rs.getString(11));
                book.setDel(rs.getInt(12));
                book.setId(Integer.valueOf(rs.getString(13)));
                book.setBookcaseName(rs.getString(14));
                book.setPublishing(rs.getString(15));
                book.setTypeName(rs.getString(16));
                bookColl.add(book);
            }
        });
        return bookColl;
    }



    public int insert(Book book){
        String sql1 = "SELECT COUNT(*) FROM tb_bookinfo WHERE barcode='"
                + book.getBarcode() + "' or bookname='"
                + book.getBookName() + "'";
        int count = jdbcTemplate.queryForObject(sql1, Integer.class);
        int flag = 0;
        String sql = "";
        if(count >= 1){
            flag = 2;
        }else{
            sql = "Insert into tb_bookinfo (barcode,bookname,typeid,author,translator,isbn,price,page,bookcase,inTime,operator) values('"
                    + book.getBarcode()
                    + "','"
                    + book.getBookName()
                    + "',"
                    + book.getTypeId()
                    + ",'"
                    + book.getAuthor()
                    + "','"
                    + book.getTranslator()
                    + "','"
                    + book.getIsbn()
                    + "',"
                    + book.getPrice()
                    + ","
                    + book.getPage()
                    + ","
                    + book.getBookcaseid()
                    + ",'"
                    + book.getInTime()
                    + "','"
                    + book.getOperator()
                    + "')";
            flag = jdbcTemplate.update(sql);
        }
        return flag;
    }

    public Book queryM(String id){
        final Book book = new Book();
        String sql = "select b.*,c.name as bookcaseName,p.pubname as publishing,t.typename from tb_bookinfo b left join tb_bookcase c on b.bookcase=c.id join tb_publishing p on b.ISBN=p.ISBN join tb_booktype t on b.typeid=t.id where b.id="
                + id + "";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                book.setBarcode(rs.getString(1));
                book.setBookName(rs.getString(2));
                book.setTypeId(rs.getInt(3));
                book.setAuthor(rs.getString(4));
                book.setTranslator(rs.getString(5));
                book.setIsbn(rs.getString(6));
                book.setPrice(Float.valueOf(rs.getString(7))); // 此处必须进行类型转换
                book.setPage(rs.getInt(8));
                book.setBookcaseid(rs.getInt(9));
                book.setInTime(rs.getString(10));
                book.setOperator(rs.getString(11));
                book.setDel(rs.getInt(12));
                book.setId(Integer.valueOf(rs.getString(13)));
                book.setBookcaseName(rs.getString(14));
                book.setPublishing(rs.getString(15));
                book.setTypeName(rs.getString(16));
            }
        });
        return book;
    }

    public int update(Book book){
        String sql = "Update tb_bookinfo set typeid=" + book.getTypeId()
                + ",author='" + book.getAuthor() + "',translator='"
                + book.getTranslator() + "',isbn='" + book.getIsbn()
                + "',price=" + book.getPrice() + ",page="
                + book.getPage() + ",bookcase=" + book.getBookcaseid()
                + " where id=" + book.getId() + "";
        int flag = jdbcTemplate.update(sql);
        return flag;
    }

    public int delete(String id){
        String sql = "UPDATE tb_bookinfo SET del=1 where id="
                + id + "";
        int flag = jdbcTemplate.update(sql);
        return flag;
    }

    public Book queryB(String f, String key){
        final Book book = new Book();
        String sql = "select b.*,c.name as bookcaseName,p.pubname as publishing,t.typename from tb_bookinfo b left join tb_bookcase c on b.bookcase=c.id join tb_publishing p on b.ISBN=p.ISBN join tb_booktype t on b.typeid=t.id where b."
                + f + "='" + key + "'";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                book.setBarcode(rs.getString(1));
                book.setBookName(rs.getString(2));
                book.setTypeId(rs.getInt(3));
                book.setAuthor(rs.getString(4));
                book.setTranslator(rs.getString(5));
                book.setIsbn(rs.getString(6));
                book.setPrice(Float.valueOf(rs.getString(7))); // 此处必须进行类型转换
                book.setPage(rs.getInt(8));
                book.setBookcaseid(rs.getInt(9));
                book.setInTime(rs.getString(10));
                book.setOperator(rs.getString(11));
                book.setDel(rs.getInt(12));
                book.setId(Integer.valueOf(rs.getString(13)));
                book.setBookcaseName(rs.getString(14));
                book.setPublishing(rs.getString(15));
                book.setTypeName(rs.getString(16));
            }
        });
        return book;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
