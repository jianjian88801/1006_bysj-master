package cn.com.scitc.graduationproject.controller;

import cn.com.scitc.graduationproject.dao.*;
import cn.com.scitc.graduationproject.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/")
public class TeacherController {
    static Logger logger = LoggerFactory.getLogger(TeacherController.class);
    HttpSession session;
    @Autowired
    ClassDao classDao;
    @Autowired
    UsersDao usersDao;
    @Autowired
    SubjectDao subjectDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    ExamDao examDao;
    @Autowired StudentexamDao studentexamDao;
    @Autowired StudentsubjectDao studentsubjectDao;
    @Autowired PaperDao paperDao;
    @RequestMapping("/TeacherManage")
    private String TeacherManage(){
        return "/teacher/manage";
    }
    @RequestMapping("/addexam")
    private String addexam(){
        return "/teacher/addexam";
    }
    @RequestMapping("/StudentList")
    private String StudentList(HttpServletRequest request, HttpServletResponse response, Model model, Integer pageNum){
        HttpSession session = request.getSession(true);
         Integer classid= (Integer) session.getAttribute("Tclassid");
        Pjclass pjclass = classDao.findByClassid(classid);
        model.addAttribute("pj",pjclass);
        if (pageNum == null){
            pageNum = 1;
        }
        Sort sort = new Sort(Sort.Direction.ASC, "userid");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = new PageRequest(pageNum - 1, 6, sort); // （当前页， 每页记录数， 排序方式）
        Page<Users> lis = usersDao.findByClassid(classid,pageable);
      logger.info("pageNum==" + pageNum);
        Iterable<Pjclass> list = classDao.findAll();
        model.addAttribute("list",list);
        model.addAttribute("pageInfo", lis);
        response.addHeader("x-frame-options","SAMEORIGIN");
        return "/teacher/StudentList";
    }
    //删除学生
    @RequestMapping("/DeleteStu")
    private String DeleteStu(HttpServletRequest request){
        Integer userid = Integer.parseInt(request.getParameter("userid"));
       Integer sc= usersDao.deleteByUserid(userid);
       if (sc==1){
           System.out.println("删除成功！");
       }
        return "redirect:/StudentList";
    }
    //添加学生
    @RequestMapping("/addStu")
    private String addStu(String username,String userpwd,String truename,Integer classid){
        Users users = new Users();
        System.out.println(username);
      Users byname= usersDao.findByUsername(username);
      if (byname==null){
          users.setRoleid(2);
          users.setUsername(username);
          users.setUserpwd(userpwd);
          users.setTruename(truename);
          users.setClassid(classid);
          usersDao.save(users);
          return  "redirect:/StudentList";
      }else {
          System.out.println("该学生存在！");
      }
       return "";
    }
    //查所有班级
    @ResponseBody
    @RequestMapping("/findAllClass")
    private Iterable<Pjclass> pjclassList(){
        Iterable<Pjclass> pjclasses = classDao.findAll();
        return pjclasses;
    }
    //按userid查询
    @ResponseBody
    @RequestMapping("/StuEdit")
    private Users StuEdit(@RequestBody Users users){
        Users user = usersDao.findByUserid(users.getUserid());
        if (user!= null) {
            return user;
        } else {
            return null;
        }
    }
//修改学生
    @RequestMapping("/updateStu")
    private String updateStu(Users user ){
      usersDao.save(user);
        return "redirect:/StudentList";
    }
    //批量删除学生
    @RequestMapping("/deleteManyStu")
    private String ManyStu(String ids){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] stuList = ids.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Integer> LString = new ArrayList<Integer>();
        for(String str : stuList){
            LString.add(new Integer(str));
        }
        List<Users> users = usersDao.findAllById(LString);
        usersDao.deleteInBatch(users);
        return "redirect:/StudentList";
    }
    //删除所有学生
    @RequestMapping("/deleteAll")
    private String deleteAll(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        Integer classid= (Integer) session.getAttribute("classid");
        usersDao.deleteByClassid(classid);
        return "redirect:/StudentList";
    }
    //查询所有考试
    @RequestMapping("/selectexam")
    private String selectexam(Model model,Integer pageNum){
        if (pageNum == null){
            pageNum = 1;
        }
        Sort sort = new Sort(Sort.Direction.ASC, "eid");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = new PageRequest(pageNum - 1, 5, sort); // （当前页， 每页记录数， 排序方式）
        Page<Exam> examlist = examDao.findAll(pageable);
        model.addAttribute("examlist",examlist);
        return "teacher/SelectExam";
    }
    //批量删除考试 deleteManyExam
    @RequestMapping("/deleteManyExam")
    private String  deleteManyExam(String ids){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] stuList = ids.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Integer> LString = new ArrayList<Integer>();
        for(String str : stuList){
            LString.add(new Integer(str));
            Integer eid = new Integer(str);
            paperDao.deleteByEid(eid);
            studentsubjectDao.deleteByEid(eid);
            studentexamDao.deleteByEid(eid);
        }
        List<Exam> exams = examDao.findAllById(LString);
        examDao.deleteInBatch(exams);
        return "redirect:/selectexam";
    }
    //删除考试
    @RequestMapping("/deleteExam")
    private String deleteExam(Integer eid){
        paperDao.deleteByEid(eid);
        studentsubjectDao.deleteByEid(eid);
        studentexamDao.deleteByEid(eid);
        examDao.deleteByEid(eid);
        return "redirect:/selectexam";
    }
    //查询一场考试信息
    @ResponseBody
    @RequestMapping("/findByOneExam")
    private Exam findByOneExam(@RequestBody Exam exams){
        Exam exam = examDao.findByEid(exams.getEid());
        if (exam != null) {
            return exam ;
        } else {
            return null;
        }
    }
    //修改考试
    @RequestMapping("/updateExam")
    private String updateExam(Exam exam ){
        Integer eid = exam.getEid();
        String pname = exam.getPname();
        studentexamDao.updatePname(pname,eid);
        examDao.save(exam);
        return "redirect:/selectexam";
    }
    //试卷详情
    @RequestMapping("/paperDetails")
    private String paperDetails (Integer eid,Model model){
        List<Paper> tm = paperDao.finbyEid(eid);
        Exam exam = examDao.findByEid(eid);
        model.addAttribute("exam",exam);
        model.addAttribute("tm",tm);
        return "teacher/paperDetails";
    }

    //添加考试
    @RequestMapping("/insertexam")
    private String insertexam(String pname,Integer userid, Integer cno,  Integer classid, Integer singlenumber, Integer singlecore, Integer multiplenumber, Integer multiplecore, Date examdate,Date examtime,Integer testtime,Model model){
        Exam exam = new Exam();
        exam.setPname(pname);
        exam.setCno(cno);
        exam.setUserid(userid);
        exam.setClassid(classid);
        exam.setSinglenumber(singlenumber);
        exam.setSinglecore(singlecore);
        exam.setMultiplenumber(multiplenumber);
        exam.setMultiplecore(multiplecore);
        exam.setExamdate(examdate);
        exam.setExamtime(examtime);
        exam.setTesttime(testtime);
        examDao.save(exam);
        Integer eid = exam.getEid();
        System.out.println(eid);
        //单选随机组题
        List<Subject> singlelsit = subjectDao.finbytype(1, cno);
        List<Subject> resultList1 = new ArrayList<Subject>();
        Random random = new Random();
        if(singlenumber>0){
            for(int i=1;i<=singlenumber;i++){
                int n=random.nextInt(singlelsit .size());
                Subject q=singlelsit .get(n);
                if(resultList1 .contains(q)){
                    i--;
                }else{
                    resultList1.add(singlelsit.get(n));
                 Paper paper = new Paper();
                 paper.setEid(eid);
                 paper.setSid(q.getSid());
                 paper.setStype(q.getStype());
                 paper.setScontent(q.getScontent());
                 paper.setSa(q.getSa());
                 paper.setSb(q.getSb());
                 paper.setSc(q.getSc());
                 paper.setSd(q.getSd());
                 paper.setSkey(q.getSkey());
                 paperDao.save(paper);
                }
            }
        }
        //多选随机组题
        List<Subject> multiplelsit = subjectDao.finbytype(2, cno);
        List<Subject> resultList2 = new ArrayList<Subject>();
        if(multiplenumber>0){
            for(int i=1;i<=multiplenumber;i++){
                int n1=random.nextInt(multiplelsit .size());
                Subject q1=multiplelsit .get(n1);
                if(resultList2 .contains(q1)){
                    i--;
                }else{
                    resultList2.add(multiplelsit.get(n1));
                    Paper p = new Paper();
                    p.setEid(eid);
                    p.setSid(q1.getSid());
                    p.setStype(q1.getStype());
                    p.setScontent(q1.getScontent());
                    p.setSa(q1.getSa());
                    p.setSb(q1.getSb());
                    p.setSc(q1.getSc());
                    p.setSd(q1.getSd());
                    p.setSkey(q1.getSkey());
                    paperDao.save(p);
                }
            }
        }
        return "redirect:/selectexam";
    }

    //查询单选题
    @RequestMapping("/finddanxuan")
    private String danxuan(Model model,Integer pageNum,HttpServletResponse response ){
        if (pageNum == null){
            pageNum = 1;
        }
        Sort sort = new Sort(Sort.Direction.ASC, "sid");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = new PageRequest(pageNum - 1, 5, sort); // （当前页， 每页记录数， 排序方式）
        Page<Subject> lis = subjectDao.findByStype(1,pageable);
        logger.info("pageNum==" + pageNum);
        for (Subject subject : lis){
            Course byCno = courseDao.findByCno(subject.getCno());
            subject.setCourse(byCno);
        }
       model.addAttribute("pageInfo", lis);
        response.addHeader("x-frame-options","SAMEORIGIN");
        return "/teacher/Single";
    }
    //查询所有课程
    @ResponseBody
    @RequestMapping("findAllCourse")
    private Iterable<Course> courselist(){
        Iterable<Course> courses = courseDao.findAll();
        return courses;
    }
    //单选添加题目
    @RequestMapping("/addSingle")
    private String addSingle(Integer stype,String scontent,String sa,String sb,String sc,String sd,String skey,Integer cno){
        Subject sub = new Subject();
        sub.setCno(cno);
        sub.setStype(stype);
        sub.setScontent(scontent);
        sub.setSa(sa);
        sub.setSb(sb);
        sub.setSc(sc);
        sub.setSd(sd);
        sub.setSkey(skey);
        subjectDao.save(sub);
        return "redirect:/finddanxuan";
    }
    //按sid查询题目
    @ResponseBody
    @RequestMapping("/findBySid")
    private Subject findBySid(@RequestBody Subject subjects){
        Subject subject = subjectDao.findBySid(subjects.getSid());
        if (subject != null) {
            return subject ;
        } else {
            return null;
        }
    }
    //单选修改题目
    @RequestMapping("/updateSingle")
    private String updateSingle(Subject subject ){
        subjectDao.save(subject);
        return "redirect:/finddanxuan";
    }
    //单选删除
    @RequestMapping("deleteSingle")
    private String deleteSingle(@RequestParam Integer sid){
        subjectDao.deleteById(sid);
        return "redirect:/finddanxuan";
    }
    //批量删除单选
    @RequestMapping("/deleteManySingle")
    private String deleteManySingle(String ids){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] singleList = ids.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Integer> LString = new ArrayList<Integer>();
        for(String str : singleList){
            LString.add(new Integer(str));
        }
        List<Subject> subjects = subjectDao.findAllById(LString);
        subjectDao.deleteInBatch(subjects);
        return "redirect:/finddanxuan";
    }
    //查询多选选题
    @RequestMapping("/findMultiple")
    private String findMultiple(Model model,Integer pageNum,HttpServletResponse response ){
        if (pageNum == null){
            pageNum = 1;
        }
        Sort sort = new Sort(Sort.Direction.ASC, "sid");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = new PageRequest(pageNum - 1, 5, sort); // （当前页， 每页记录数， 排序方式）
        Page<Subject> lis = subjectDao.findByStype(2,pageable);
        logger.info("pageNum==" + pageNum);
        model.addAttribute("pageInfo", lis);
        response.addHeader("x-frame-options","SAMEORIGIN");
        return "/teacher/multiple";
    }
    //添加多选
    @RequestMapping("/addMultiple")
    private String addMultiple(Integer stype,String scontent,String sa,String sb,String sc,String sd,String skey,Integer cno){
        Subject sub = new Subject();
        sub.setCno(cno);
        sub.setStype(stype);
        sub.setScontent(scontent);
        sub.setSa(sa);
        sub.setSb(sb);
        sub.setSc(sc);
        sub.setSd(sd);
        sub.setSkey(skey);
        subjectDao.save(sub);
        return "redirect:/findMultiple";
    }
    //单选修改题目
    @RequestMapping("/updateMultiple")
    private String updateMultiple(Subject subject ){
        subjectDao.save(subject);
        return "redirect:/findMultiple";
    }
    //单选删除
    @RequestMapping("deleteMultiple")
    private String deleteMultiple(@RequestParam Integer sid){
        subjectDao.deleteById(sid);
        return "redirect:/findMultiple";
    }
    //批量删除单选
    @RequestMapping("/deleteManyMultiple")
    private String deleteManyMultiple(String ids){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] singleList = ids.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Integer> LString = new ArrayList<Integer>();
        for(String str : singleList){
            LString.add(new Integer(str));
        }
        List<Subject> subjects = subjectDao.findAllById(LString);
        subjectDao.deleteInBatch(subjects);
        return "redirect:/findMultiple";
    }
    //查询成绩
    @RequestMapping("/findAllScore")
  private String findAllScore(HttpServletRequest request,Integer pageNum,Model model){
        HttpSession session = request.getSession(true);
        Integer classid = (Integer)session.getAttribute("Tclassid");
        Pjclass cs = classDao.findByClassid(classid);
        model.addAttribute("cs",cs);
        if (pageNum == null){
            pageNum = 1;
        }
        Sort sort = new Sort(Sort.Direction.ASC, "seid");  // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = new PageRequest(pageNum - 1, 5, sort);
        Page<Studentexam> byClassid = studentexamDao.findByClassid(classid, pageable);
        for (Studentexam studentexam :byClassid){
            Users users = usersDao.findByUserid(studentexam.getUserid());
            studentexam.setUsers(users);
        }
        model.addAttribute("score",byClassid);
        return "teacher/AllScore";
    }
}
