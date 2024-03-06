package cn.com.scitc.graduationproject.controller;

import cn.com.scitc.graduationproject.dao.ClassDao;
import cn.com.scitc.graduationproject.dao.UsersDao;
import cn.com.scitc.graduationproject.model.Pjclass;
import cn.com.scitc.graduationproject.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class LoginController {
    HttpSession session;
    @Autowired
    ClassDao classDao;
    @Autowired
    UsersDao usersDao;
    @RequestMapping("/login")
    private String login(){
        return "/student/login";
    }
    @PostMapping("/CanLogin")
    private String CanLogin(String username, String userpwd,HttpServletRequest request){
        Iterable<Users> users = usersDao.findAll();
        String errorInfo = "";
        for (Users lis : users){
            if(lis.getUsername().equals(username) && lis.getUserpwd().equals(userpwd)){
               if (lis.getRoleid().equals(1)){
                   request.getSession().setAttribute("Tlis",lis);
                   Integer id = lis.getClassid();
                   request.getSession().setAttribute("Tclassid",id);
                   return "redirect:/TeacherManage";
               }else if (lis.getRoleid().equals(2)){
                   request.getSession().setAttribute("lis",lis);
                   Integer usid = lis.getUserid();
                   Integer id = lis.getClassid();
                   request.getSession().setAttribute("classid",id);
                   request.getSession().setAttribute("userid",usid);
                   return "redirect:/StuMan";
               }else if (lis.getRoleid().equals(3)){
                   request.getSession().setAttribute("Alis",lis);
                   request.getSession().setMaxInactiveInterval(30 * 60);
                   return "redirect:/AdminManage";
               }
            }else {
                errorInfo = "账号或密码错误";
                request.getSession().setAttribute("errorInfo",errorInfo);
            }

        }
        return "redirect:/login";
    }
    @GetMapping("/register")
    private  String register(Model model){
        Iterable<Pjclass> list = classDao.findAll();
        model.addAttribute("list",list);
        return "/student/register";
    }
    @PostMapping("/CanRegister")
    private  String CanRegister(Integer roleid,String username,String userpwd,String truename ,Integer classid ,HttpServletRequest request){
        System.out.println(roleid+":"+username+":"+userpwd+":"+truename+":"+classid);
        String wk = "";
        String cz = "";
        String cg = "";
        Users users = new Users();
        users.setRoleid(roleid);
        users.setUsername(username);
        users.setUserpwd(userpwd);
        users.setTruename(truename);
        users.setClassid(classid);
       Users byUsername = usersDao.findByUsername(username);
        System.out.println(byUsername);
        if (byUsername==null){
            if(userpwd.equals("")){
               wk = "密码或密码不能为空！";
                request.getSession().setAttribute("wk",wk);

            }else {
                if (truename.equals("")){
                    wk = "密码或密码不能为空！";
                    request.getSession().setAttribute("wk",wk);
                }
                else {
                    usersDao.save(users);

                    cg = "注册成功，请登陆！";
                    request.getSession().setAttribute("cg",cg);
                }
            }
        }else {
            System.out.println("cz");
             cz = "该用户名以存在！";
            request.getSession().setAttribute("cz",cz);
            return "redirect:/register";
        }
        return "redirect:/register";
    }
    @RequestMapping("/StuMan")
    private String StuMan(){
        return "/student/StuMan";
    }
    //学生注销
    @RequestMapping("/logout")
    private String logout(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        System.out.println("注销");
        session.removeAttribute("lis");
        session.removeAttribute("classid");
        session.removeAttribute("classid");
        session.removeAttribute("errorInfo");
        session.removeAttribute("cxdl");
        return  "redirect:/login";
    }
    //老师注销
    @RequestMapping("/Tlogout")
    private  String Tlogout(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute("Tlis");
        session.removeAttribute("Tclassid");
        session.removeAttribute("Tuserid");
        return  "redirect:/login";
    }
    //管理员注销
    @RequestMapping("/Alogout")
    private  String Alogout(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute("Alis");
        return  "redirect:/login";
    }
    //登录SESSION
    @RequestMapping("/llogout")
    private  String llogout(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute("errorInfo");
        session.removeAttribute("cxdl");
        return  "redirect:/login";
    }
    //注册清除session
    @RequestMapping("/zclogout")
    private String zclogout(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        System.out.println("注册清除ssesion成功！");
        session.removeAttribute("wk");
        session.removeAttribute("wk");
        return "redirect:/register";
    }
    //修改密码
    @PostMapping("/xiugai")
    private String xiugai( Integer userid,String userpwd,HttpServletRequest request){
       Integer u = usersDao.updatepwd(userpwd, userid);
        String cxdl ="";
        if (u==1){
            cxdl = "cxdl";
            request.getSession().setAttribute("cxdl",cxdl);
            return "redirect:/login";
        }
        return "redirect:/logout";
    }
}
