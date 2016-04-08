package com.lgb.function.admin.login.controller;

import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.login.StudentCourse;
import com.lgb.function.admin.login.StudentUser;
import com.lgb.function.admin.login.service.StudentLoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/stu")
public class StudentLoginController {

    @Autowired
    private StudentLoginServiceI studentLoginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView stuLogin(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                    Pageable pageable, StudentUser studentUser, Course course, HttpSession session) {
        StudentUser loginUser = studentLoginService.login(studentUser);
        ModelAndView mav = new ModelAndView();

        if (loginUser != null) {
            Page<Course> page = studentLoginService.list(course, pageable);
            mav.addObject(ConstantFields.PAGE_KEY, page);
            mav.setViewName("stu/login/list");
            session.setAttribute(ConstantFields.SESSION_STU_KEY, loginUser);
            session.setAttribute("stuId",loginUser.getStuId());

        }
        else {
            mav.setViewName("redirect:/stu/routeLogin.action");
        }
        return mav;
    }

    @RequestMapping(value = "/routeLogin", method = RequestMethod.GET)
    public String routeLogin() {
        return "stu/login/stuLogin";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(ConstantFields.SESSION_STU_KEY);

        return "redirect:/stu/routeLogin.action";
    }

    @RequestMapping(value = "/courseInfo/{courseId}", method = RequestMethod.GET)
    public ModelAndView moreCourseInfo(@PathVariable("courseId") int courseId ) {
        Course course = studentLoginService.moreCourseInfo(courseId);
        if (course != null){
            ModelAndView mav = new ModelAndView("stu/login/moreInfo");
            mav.addObject(ConstantFields.COURSE_INFO_KEY, course);
            return mav;
        }
        return new ModelAndView("redirect:/stu/routeLogin.action");
    }

    @RequestMapping(value = "/signUp/{courseId}", method = RequestMethod.GET)
    public String signUp(@PathVariable("courseId") int courseId,StudentCourse studentCourse, HttpSession session) {
        Integer studentId = (Integer)session.getAttribute("stuId");
        studentCourse.setStudentId(studentId);
        studentCourse.setCourseId(courseId);
        if (studentLoginService.add(studentCourse)) {
            return "redirect:/stu/login/courseInfo/{courseId}.action";
        }
        return "redirect:/stu/login/routeSignUp.action";
    }

    @RequestMapping(value = "/routeSignUp", method = RequestMethod.GET)
    public String routeAddStudent() {
        return "stu/login/signUp";
    }
}
