package com.smart.web;

import com.smart.domain.Book;
import com.smart.domain.Reader;
import com.smart.service.BookService;
import com.smart.service.BorrowService;
import com.smart.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by llxxdd on 2017/6/28.
 */
@RestController
public class BorrowController {
    private BorrowService borrowService;
    private BookService bookService;
    private ReaderService readerService;


    @RequestMapping(path = "/borrow/bookBorrowSort.html")
    public ModelAndView bookBorrowSort(HttpServletRequest request){
        System.out.println("zhaodaole");
        request.setAttribute("bookBorrowSort", borrowService.getMoreBookBorrowSort());
        return new ModelAndView("bookBorrowSort");
    }

    @RequestMapping(path = "/borrow/bookborrow.html")
    public ModelAndView bookBorrow(HttpServletRequest request){
        Reader reader1 = new Reader();
        reader1.setBarcode(request.getParameter("barcode"));
        Reader reader = readerService.getReaderModifyQuery(reader1);
        request.setAttribute("readerinfo", reader);

        request.setAttribute("borrowinfo", borrowService.getBorrowInfo(request.getParameter("barcode")));

        String f = request.getParameter("f");
        String key = request.getParameter("inputkey");

        if (key != null && !key.equals("")) {
            String operator = request.getParameter("operator");
            Book bookForm=bookService.getBookByfAndKey(f, key);
            if (bookForm!=null){
                int ret = borrowService.setInsertBorrow(reader, bookService.getBookByfAndKey(f, key), operator);
                if (ret == 1) {
                    request.setAttribute("bar", request.getParameter("barcode"));
                    return new ModelAndView("bookBorrow_ok");

                } else {
                    request.setAttribute("error", "添加借阅信息失败!");
                    return new ModelAndView("error");
                }
            }else{
                request.setAttribute("error", "没有该图书!");
                return new ModelAndView("error");
            }
        }
        return new ModelAndView("bookBorrow");
    }

    @RequestMapping(path = "/borrow/bookrenew.html")
    public ModelAndView bookReNew(HttpServletRequest request){
        Reader reader1 = new Reader();
        reader1.setBarcode(request.getParameter("barcode"));
        Reader reader = readerService.getReaderModifyQuery(reader1);
        request.setAttribute("readerinfo", reader);

        request.setAttribute("borrowinfo", borrowService.getBorrowInfo(request.getParameter("barcode")));

        if(request.getParameter("id")!=null){
            int id = Integer.parseInt(request.getParameter("id"));
            if (id > 0) { //执行继借操作
                int ret = borrowService.setBookRenew(id);
                if (ret == 0) {
                    request.setAttribute("error", "图书继借失败!");
                    return new ModelAndView("error");
                } else {
                    request.setAttribute("bar", request.getParameter("barcode"));
                    return new ModelAndView("bookRenew_ok");
                }
            }
        }
        return new ModelAndView("bookRenew");
    }

    @RequestMapping(path = "/borrow/bookback.html")
    public ModelAndView bookBack(HttpServletRequest request){
        Reader reader1 = new Reader();
        reader1.setBarcode(request.getParameter("barcode"));
        Reader reader = readerService.getReaderModifyQuery(reader1);
        request.setAttribute("readerinfo", reader);

        request.setAttribute("borrowinfo", borrowService.getBorrowInfo(request.getParameter("barcode")));

        if(request.getParameter("id")!=null){
            int id = Integer.parseInt(request.getParameter("id"));
            String operator=request.getParameter("operator");
            if (id > 0) { //执行归还操作
                int ret = borrowService.setBack(id, operator);
                if (ret == 0) {
                    request.setAttribute("error", "图书归还失败!");
                    return new ModelAndView("error");
                } else {
                    request.setAttribute("bar", request.getParameter("barcode"));
                    return new ModelAndView("bookBack_ok");
                }
            }
        }
        return new ModelAndView("bookBack");
    }

    @RequestMapping(path = "/borrow/borrowQuery.html")
    public ModelAndView borrowQuery(HttpServletRequest request){
        String str=null;
        String flag[]=request.getParameterValues("flag");
        if (flag!=null){
            String aa = flag[0];
            if ("a".equals(aa)) {
                if (request.getParameter("f") != null) {
                    str = request.getParameter("f") + " like '%" +
                            request.getParameter("key") + "%'";
                }
            }
            if ("b".equals(aa)) {
                String sdate = request.getParameter("sdate");
                String edate = request.getParameter("edate");
                if (sdate != null && edate != null) {
                    str = "borrowTime between '" + sdate + "' and '" + edate +
                            "'";
                }
            }
            //同时选择日期和条件进行查询
            if (flag.length == 2) {
                if (request.getParameter("f") != null) {
                    str = request.getParameter("f") + " like '%" +
                            request.getParameter("key") + "%'";
                }
                String sdate = request.getParameter("sdate");
                String edate = request.getParameter("edate");
                String str1 = null;
                if (sdate != null && edate != null) {
                    str1 = "borrowTime between '" + sdate + "' and '" + edate +
                            "'";
                }
                str = str + " and borr." + str1;
            }
        }
        request.setAttribute("borrowQuery",borrowService.getBorrowQuery(str));
        return new ModelAndView("borrowQuery");
    }

    @RequestMapping(path = "/borrow/Bremind.html")
    public ModelAndView bremind(HttpServletRequest request){
        request.setAttribute("Bremind",borrowService.setBremind());
        return new ModelAndView("bremind");
    }


    @Autowired
    public void setReaderService(ReaderService readerService) {
        this.readerService = readerService;
    }

    @Autowired
    public void setBorrowService(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
