package com.smart.web;

import com.smart.domain.Reader;
import com.smart.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by llxxdd on 2017/7/5.
 */
@RestController
public class ReaderController {
    private ReaderService readerService;

    @RequestMapping(path = "/reader/readerQuery.html")
    public ModelAndView readerQuery(HttpServletRequest request){
        String str = null;
        request.setAttribute("reader", readerService.getReaderQuery(str));
        return new ModelAndView("reader");
    }

    @RequestMapping(path = "/reader/getReader_addJsp.html")
    public ModelAndView getReader_addJsp(){
        return new ModelAndView("reader_add");
    }

    @RequestMapping(path = "/reader/readerAdd.html")
    public ModelAndView readerAdd(HttpServletRequest request, Reader reader){
        Date date1 = new Date();
        java.sql.Date date=new java.sql.Date(date1.getTime());
        reader.setCreateDate(date.toString());
        int res = readerService.setReadAdd(reader);
        if(res == 0){
            request.setAttribute("error", "读者信息添加失败");
            return new ModelAndView("error");
        }else if(res == 2){
            request.setAttribute("error", "该读者信息已存在");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "1");
            return new ModelAndView("reader_ok");
        }
    }

    @RequestMapping(path = "/reader/readerModifyQuery.html", method = RequestMethod.GET, params = "id")
    public ModelAndView readerModifyQuery(HttpServletRequest request, @RequestParam("id") String id){
        Reader reader = new Reader();
        reader.setId(Integer.parseInt(id));
        request.setAttribute("readerQueryif", readerService.getReaderModifyQuery(reader));
        return new ModelAndView("reader_Modify");
    }

    @RequestMapping(path = "/reader/readerModify.html")
    public ModelAndView readerModify(HttpServletRequest request, Reader reader){
        int res = readerService.setReaderModify(reader);
        if(res == 0){
            request.setAttribute("error", "修改读者信息失败");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "2");
            return new ModelAndView("reader_ok");
        }
    }

    @RequestMapping(path = "/reader/readerDel.html", method = RequestMethod.GET, params = "id")
    public ModelAndView readerDel(HttpServletRequest request, @RequestParam("id") String id){
        int res = readerService.setReaderDel(id);
        if(res == 0){
            request.setAttribute("error", "删除读者信息失败");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "3");
            return new ModelAndView("reader_ok");
        }
    }
    @Autowired
    public void setReaderService(ReaderService readerService) {
        this.readerService = readerService;
    }
}
