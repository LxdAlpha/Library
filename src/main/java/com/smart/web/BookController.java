package com.smart.web;

import com.smart.domain.Book;
import com.smart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by llxxdd on 2017/7/6.
 */
@RestController
public class BookController {
    private BookService bookService;
    private BookTypeService bookTypeService;
    private BookCaseService bookCaseService;
    private PublishingService publishingService;

    @RequestMapping(path = "/book/bookQuery.html")
    public ModelAndView bookQuery(HttpServletRequest request){
        String str = null;
        request.setAttribute("book", bookService.getBookQuery(str));
        return new ModelAndView("book");
    }

    @RequestMapping(path = "/book/bookDetail.html", method = RequestMethod.GET, params = "id")
    public ModelAndView bookDetail(HttpServletRequest request, @RequestParam("id") String id){
        request.setAttribute("bookDetail", bookService.getBookDetail(id));
        return new ModelAndView("book_detail");
    }

    @RequestMapping(path = "/book/getbook_addjsp.html")
    public ModelAndView getbook_addjsp(HttpServletRequest request){
        String str = null;
        request.setAttribute("bookType", bookTypeService.getBookTypeQuery(str));
        request.setAttribute("bookCase", bookCaseService.getBookCaseQuery());
        request.setAttribute("publishing",publishingService.getQuery(str));
        return new ModelAndView("book_add");
    }

    @RequestMapping(path = "/book/bookAdd.html")
    public ModelAndView bookAdd(HttpServletRequest request, Book book){
        Date date1=new Date();
        java.sql.Date date=new java.sql.Date(date1.getTime());
        book.setInTime(date.toString());
        int res = bookService.setBookAdd(book);
        if(res == 1){
            request.setAttribute("para", "1");
            return new ModelAndView("book_ok");
        }else if(res == 2){
            request.setAttribute("error", "该图书信息已经添加！");
            return new ModelAndView("error");
        }else{
            request.setAttribute("error", "图书信息添加失败！");
            return new ModelAndView("error");
        }
    }


    @RequestMapping(path = "/book/bookModifyQuery.html", method = RequestMethod.GET, params = "id")
    public ModelAndView bookModifyQuery(HttpServletRequest request, @RequestParam("id") String id){
        request.setAttribute("bookQueryif", bookService.getBookModifyQuery(id));

        String str = null;
        request.setAttribute("bookType", bookTypeService.getBookTypeQuery(str));
        request.setAttribute("bookCase", bookCaseService.getBookCaseQuery());
        request.setAttribute("publishing",publishingService.getQuery(str));

        return new ModelAndView("book_Modify");
    }

    @RequestMapping(path = "/book/bookModify.html")
    public ModelAndView bookModify(HttpServletRequest request, Book book){
        int res = bookService.setBookModify(book);
        if(res == 0){
            request.setAttribute("error", "修改图书信息失败！");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "2");
            return new ModelAndView("book_ok");
        }
    }

    @RequestMapping(path = "/book/bookDel.html", method = RequestMethod.GET, params = "id")
    public ModelAndView bookDel(HttpServletRequest request, @RequestParam("id") String id){
        int res = bookService.setBookDel(id);
        if(res == 0){
            request.setAttribute("error", "删除图书信息失败！");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "3");
            return new ModelAndView("book_ok");
        }
    }

    @RequestMapping(path = "/book/getbookBorrowjsp.html")
    public ModelAndView getBookBorrowJsp(HttpServletRequest request){
        return new ModelAndView("bookBorrow");
    }

    @RequestMapping(path = "/book/getbookRenewjsp.html")
    public ModelAndView getbookRenewjsp(){
        return new ModelAndView("bookRenew");
    }

    @RequestMapping(path = "/book/getbookBackjsp.html")
    public ModelAndView getBookBackJsp(){
        return new ModelAndView("bookBack");
    }

    @RequestMapping(path = "/book/bookifQuery.html")
    public ModelAndView bookIfQuery(HttpServletRequest request){
        String str = null;
        if(request.getParameter("f")!=null){
            str = request.getParameter("f") + " like '%" +
                    request.getParameter("key") + "%";
        }
        request.setAttribute("ifbook",bookService.getBookQuery(str));
        return new ModelAndView("bookQuery");
    }

    @RequestMapping(path = "/book/bookDetail.html")
    public ModelAndView bookDetail(HttpServletRequest request){
        request.setAttribute("bookDetail",bookService.getBookModifyQuery(request.getParameter("ID")));
        return new ModelAndView("book_detail");
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setBookTypeService(BookTypeService bookTypeService) {
        this.bookTypeService = bookTypeService;
    }

    @Autowired
    public void setBookCaseService(BookCaseService bookCaseService) {
        this.bookCaseService = bookCaseService;
    }

    @Autowired
    public void setPublishingService(PublishingService publishingService) {
        this.publishingService = publishingService;
    }
}
