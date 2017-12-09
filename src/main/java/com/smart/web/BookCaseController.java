package com.smart.web;


import com.smart.domain.BookCase;
import com.smart.service.BookCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by llxxdd on 2017/7/2.
 */
@RestController
public class BookCaseController {
    private BookCaseService bookCaseService;

    @RequestMapping(path = "/bookCase/bookCaseQuery.html")
    public ModelAndView bookCaseQuery(HttpServletRequest request){
        request.setAttribute("bookcase", bookCaseService.getBookCaseQuery());
        return new ModelAndView("bookcase");
    }

    @RequestMapping(path = "/bookCase/getBookCaseAddJsp.html")
    public ModelAndView getBookCaseAddJsp(){
        return new ModelAndView("bookcase_add");
    }

    @RequestMapping(path = "/bookCase/bookCaseAdd.html")
    public ModelAndView bookCaseAdd(HttpServletRequest request, BookCase bookCase){
        int res = bookCaseService.setBookCaseAdd(bookCase);
        if(res == 0){
            request.setAttribute("error", "书架信息添加失败！");
            return new ModelAndView("error");
        }else if(res == 2){
            request.setAttribute("error", "该书架信息已经添加！");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "1");
            return new ModelAndView("bookcase_ok");
        }
    }

    @RequestMapping(path = "/bookCase/bookCaseModifyQuery.html", method = RequestMethod.GET, params = "id")
    public ModelAndView bookCaseModifyQuery(HttpServletRequest request, @RequestParam("id") String id){
        request.setAttribute("bookCaseQueryif", bookCaseService.getBookCaseModifyQuery(id));
        return new ModelAndView("bookCase_Modify");
    }

    @RequestMapping(path = "/bookCase/bookCaseModify.html")
    public ModelAndView bookCaseModify(HttpServletRequest request, BookCase bookCase){
        int res = bookCaseService.setBookCaseModify(bookCase);
        if(res == 0){
            request.setAttribute("error", "修改书架信息失败");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "2");
            return new ModelAndView("bookcase_ok");
        }
    }

    @RequestMapping(path = "/bookCase/bookCaseDel.html", method = RequestMethod.GET, params = "id")
    public ModelAndView bookCaseDel(HttpServletRequest request, @RequestParam("id") String id){
        int res = bookCaseService.setBookCaseDel(id);
        if(res == 0){
            request.setAttribute("error", "删除书架信息失败！");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "3");
            return new ModelAndView("bookcase_ok");
        }
    }

    @Autowired
    public void setBookCaseService(BookCaseService bookCaseService) {
        this.bookCaseService = bookCaseService;
    }
}
