package com.smart.web;

import com.smart.domain.Parameter;
import com.smart.service.ParameterSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by llxxdd on 2017/7/2.
 */
@RestController
public class ParameterController {
    private ParameterSevice parameterSevice;

    @RequestMapping(path = "/parameter/parameterQuery.html")
    public ModelAndView parameterModifyQuery(HttpServletRequest request){
        request.setAttribute("parameterModifyif", parameterSevice.getParameterModifyQuery());
        return  new ModelAndView("parameter_modify");
    }

    @RequestMapping(path = "/parameter/parameterModify.html")
    public ModelAndView parameterModify(HttpServletRequest request, Parameter parameter){
        int res = parameterSevice.setParameterModify(parameter);
        if(res == 0){
            request.setAttribute("error", "参数设置信息修改失败！");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "2");
            return  new ModelAndView("parameter_ok");
        }
    }
    @Autowired
    public void setParameterSevice(ParameterSevice parameterSevice) {
        this.parameterSevice = parameterSevice;
    }
}
