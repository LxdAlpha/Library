package com.smart.web;

import com.smart.domain.ReaderType;
import com.smart.service.ReaderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by llxxdd on 2017/7/5.
 */
@RestController
public class ReaderTypeController {
    private ReaderTypeService readerTypeService;

    @RequestMapping(path = "/readerType/readerTypeQuery.html")
    public ModelAndView readerTypeQuery(HttpServletRequest request){
        String str = null;
        request.setAttribute("readerType", readerTypeService.getReaderTypeQuery(str));
        return new ModelAndView("readerType");
    }

    @RequestMapping(path = "/readerType/readerTypeModifyQuery.html", method = RequestMethod.GET, params = "id")
    public ModelAndView readerTypeModifyQuery(HttpServletRequest request, @RequestParam("id") String id){
        request.setAttribute("readerTypeQueryif", readerTypeService.getReaderTypeModifyQuery(id));
        return new ModelAndView("readerType_Modify");
    }

    @RequestMapping(path = "/readerType/readerTypeModify.html")
    public ModelAndView readerTypeModify(HttpServletRequest request, ReaderType readerType){
        int res = readerTypeService.setReaderTypeModify(readerType);
        if(res == 0){
            request.setAttribute("error", "修改读者类型信息失败！");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "2");
            return new ModelAndView("readerType_ok");
        }
    }

    @RequestMapping(path = "/readerType/readerTypeDel.html", method = RequestMethod.GET, params = "id")
    public ModelAndView readerTypeDel(HttpServletRequest request, @RequestParam("id") String id){
        int res = readerTypeService.setReaderTypeDel(id);
        if(res == 0){
            request.setAttribute("error", "删除读者类型信息失败！");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "3");
            return new ModelAndView("readerType_ok");
        }
    }

    @RequestMapping(path = "/readerType/getReaderType_add.html")
    public ModelAndView getReaderTypeAddJsp(){
        return new ModelAndView("readerType_add");
    }

    @RequestMapping(path = "/readerType/readerTypeAdd.html")
    public ModelAndView readerTypeAdd(HttpServletRequest request, ReaderType readerType){
        int res = readerTypeService.setReaderTypeAdd(readerType);
        if(res == 0){
            request.setAttribute("error", "读者信息添加失败");
            return new ModelAndView("error");
        }else if(res == 2){
            request.setAttribute("error", "该读者信息已经添加！");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "1");
            return new ModelAndView("readerType_ok");
        }
    }

    @Autowired
    public void setReaderTypeService(ReaderTypeService readerTypeService) {
        this.readerTypeService = readerTypeService;
    }
}
