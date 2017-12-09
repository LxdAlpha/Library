package com.smart.web;

import com.smart.domain.Manager;
import com.smart.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by llxxdd on 2017/6/27.
 */
@RestController

public class ManagerController {
    private ManagerService managerService;



    @RequestMapping(path = "/manager/login.html")
    public ModelAndView loginCheck(HttpServletRequest request, Manager managerForm){
        System.out.println("lxd" + managerForm.getName());
        boolean isValidUser =  managerService.hasMatchUser(managerForm.getName(),
                managerForm.getPwd());
        if (!isValidUser) {
            return new ModelAndView("login");
        } else {
            request.getSession().setAttribute("manager", managerForm.getName());
            return new ModelAndView("main");
        }
    }

    @RequestMapping(path = "/manager/main.html")
    public ModelAndView toMain(){
        return new ModelAndView("main");
    }

    @RequestMapping(path = "/manager/managerQuery.html")
    public ModelAndView managerQuery(HttpServletRequest request){
        String str = null;
        request.setAttribute("managerQuery", managerService.getManagerQuery(str));
        return new ModelAndView("manager");
    }

    @RequestMapping(path = "/manager/managerModifyQuery.html", method = RequestMethod.GET, params = "id")
    public ModelAndView managerModifyQuery(HttpServletRequest request, @RequestParam("id") String id){
        request.setAttribute("managerQueryif", managerService.getManagerModifyQuery(Integer.parseInt(id)));
        return new ModelAndView("manager_Modify");
    }
    /*
    @RequestMapping(path = "/manager/managerDel.html", method= RequestMethod.POST, params = "id")
    public ModelAndView managerDel(HttpServletRequest request, @RequestParam("id") String id){
        int res = managerService.setManagerDel(Integer.parseInt(id));
        if(res == 0){
            request.setAttribute("error", "删除管理员信息失败");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "3");
            return new ModelAndView("manager_ok");
        }
    }
    */

    @RequestMapping(path = "/manager/managerDel.html", method = RequestMethod.GET, params = "id")
    public ModelAndView managerDel(HttpServletRequest request, @RequestParam("id") String id){
        int res = managerService.setManagerDel(Integer.parseInt(id));
        if(res == 0){
            request.setAttribute("error", "删除管理员信息失败");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "3");
            return new ModelAndView("manager_ok");
        }
    }

    @RequestMapping(path = "/manager/managerModify.html")
    public ModelAndView managerModify(HttpServletRequest request, Manager manager){
        int res = managerService.setManagerModify(manager);
        if(res == 0){
            request.setAttribute("error", "设置管理员权限失败");
            return new ModelAndView("error");
        }else{
            request.setAttribute("para", "2");
            return new ModelAndView("manager_ok");
        }
    }

    @RequestMapping(path = "/manager/manager_add.html")
    public ModelAndView getManagerAddJsp(){
        return new ModelAndView("manager_add");
    }

    @RequestMapping(path = "/manager/managerAdd.html")
    public ModelAndView managerAdd(HttpServletRequest request, Manager manager){
        int res = managerService.setManagerAdd(manager);
        if(res == 1){
            request.setAttribute("para", "1");
            return new ModelAndView("manager_ok");
        }else if(res == 2){
            request.setAttribute("error", "不能重复添加管理员信息！");
            return new ModelAndView("error");
        }else{
            request.setAttribute("error", "添加管理员信息失败");
            return new ModelAndView("error");
        }
    }

    @RequestMapping(path = "/manager/querypwd.html")
    public ModelAndView pwdQuery(HttpServletRequest request){
        HttpSession session = request.getSession();
        String manager = (String) session.getAttribute("manager");
        request.setAttribute("pwdQueryif", managerService.getQueryPwd((String) session.getAttribute("manager")));
        return new ModelAndView("pwd_Modify");
    }

    @RequestMapping(path = "/manager/modifypwd.html")
    public ModelAndView modifypwd(HttpServletRequest request, Manager manager){
        int res = managerService.setModifyPwd(manager);
        if (res == 0) {
            request.setAttribute("error", "更改口令失败！");
            return new ModelAndView("error");
        } else {
            return new ModelAndView("pwd_ok");
        }
    }

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }
}
