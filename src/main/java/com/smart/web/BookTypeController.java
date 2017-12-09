package com.smart.web;

import com.smart.domain.BookType;
import com.smart.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by llxxdd on 2017/7/6.
 */
@RestController
public class BookTypeController {
    private BookTypeService bookTypeService;

    @RequestMapping(path = "/bookType/bookTypeQuery.html")
    public ModelAndView bookTypeQuery(HttpServletRequest request){
        String str = null;
        request.setAttribute("bookType", bookTypeService.getBookTypeQuery(str));
        return new ModelAndView("bookType");
    }

    @RequestMapping(path = "/bookType/getBookType_addJsp.html")
    public ModelAndView getBookTypeAddJsp(){
        return new ModelAndView("bookType_add");
    }

    @RequestMapping(path = "/bookType/bookTypeAdd.html")
    public ModelAndView bookTypeAdd(HttpServletRequest request, BookType bookType){
        int res = bookTypeService.setBookTypeAdd(bookType);
        if(res == 0){
            request.setAttribute("error", "图书类型添加失败");
            return new ModelAndView("error");
        }else if(res == 2){
            request.setAttribute("error", "该图书类型已经添加");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "1");
            return new ModelAndView("bookType_ok");
        }
    }

    @RequestMapping(path = "/bookType/bookTypeModifyQuery.html", method = RequestMethod.GET, params = "id")
    public ModelAndView bookTypeModifyQuery(HttpServletRequest request, @RequestParam("id") String id){
        request.setAttribute("bookTypeQueryif", bookTypeService.getBookTypeModifyQuery(id));
        return new ModelAndView("bookType_Modify");
    }

    @RequestMapping(path = "/bookType/bookTypeModify.html")
    public ModelAndView bookTypeModify(HttpServletRequest request, BookType bookType){
        int res = bookTypeService.setBookTypeModify(bookType);
        if(res == 0){
            request.setAttribute("error", "修改图书类型信息失败");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "2");
            return new ModelAndView("bookType_ok");
        }
    }

    @RequestMapping(path = "/bookType/bookTypeDel.html", method = RequestMethod.GET, params = "id")
    public ModelAndView bookTypeDel(HttpServletRequest request, @RequestParam("id") String id){
        int res = bookTypeService.setBookTypeDel(id);
        if(res == 0){
            request.setAttribute("error", "删除图书类型信息失败");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "3");
            return new ModelAndView("bookType_ok");
        }
    }

    @Autowired
    public void setBookTypeService(BookTypeService bookTypeService) {
        this.bookTypeService = bookTypeService;
    }
}
