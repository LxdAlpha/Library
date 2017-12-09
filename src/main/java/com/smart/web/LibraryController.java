package com.smart.web;

import com.smart.domain.Library;
import com.smart.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by llxxdd on 2017/7/1.
 */
@RestController
public class LibraryController {
    private  LibraryService libraryService;

    @RequestMapping(path = "/library/libraryQuery.html")
    public ModelAndView libraryModifyQuery(HttpServletRequest request){
        Library library = libraryService.getLibraryModifyQuery();
        request.setAttribute("libraryModifyif", library);
        return new ModelAndView("library_modify");
    }

    @RequestMapping(path = "/library/libraryModify.html")
    public ModelAndView libraryModify(HttpServletRequest request, Library library){
        int res = 0;
        res = libraryService.setLibraryModify(library);
        if(res == 0){
            request.setAttribute("error", "图书馆信息修改失败");
            return new ModelAndView("error");
        }else{
            return new ModelAndView("library_ok");
        }
    }

    @Autowired
    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
}
