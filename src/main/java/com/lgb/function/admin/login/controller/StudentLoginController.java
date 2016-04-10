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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        course.setStudentId(loginUser.getStuId());
        session.setAttribute(ConstantFields.SESSION_STU_ID_KEY,loginUser.getStuId());

        if (loginUser != null) {
            Page<Course> page = studentLoginService.list(course, pageable);
            mav.addObject(ConstantFields.PAGE_KEY, page);
            mav.addObject(ConstantFields.SESSION_STU_KEY, loginUser);
            mav.setViewName("stu/login/list");
            session.setAttribute(ConstantFields.SESSION_STU_KEY, loginUser);
        }
        else {
            mav.setViewName("redirect:/stu/routeLogin.action");
        }
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginGet(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                               Pageable pageable, Course course, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute(ConstantFields.SESSION_STU_ID_KEY) != null) {
            StudentUser user = (StudentUser) session.getAttribute(ConstantFields.SESSION_STU_KEY);
            if (user != null) {
                course.setStudentId((Integer) session.getAttribute(ConstantFields.SESSION_STU_ID_KEY));

                Page<Course> page = studentLoginService.list(course, pageable);
                mav.addObject(ConstantFields.PAGE_KEY, page);
                mav.setViewName("stu/login/list");
            }
            else {
                mav.setViewName("redirect:/stu/routeLogin.action");
            }
        } else {
            mav.setViewName("redirect:/stu/routeLogin.action");
        }
        return mav;
    }

    @RequestMapping(value = "/routeLogin", method = RequestMethod.GET)
    public String routeLogin(HttpSession session) {
        if (session.getAttribute(ConstantFields.SESSION_STU_KEY) != null){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/stu/login.action");
            return null;
        }else {
            return "stu/login/stuLogin";
        }
    }

    @RequestMapping(value = "/querySign",method = RequestMethod.GET)
    public ModelAndView querySign(@PageableDefault(value = ConstantFields.DEFAULT_PAGE_SIZE)
                                 Pageable pageable, StudentUser studentUser, Course course, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        course.setStudentId((Integer) session.getAttribute(ConstantFields.SESSION_STU_ID_KEY));
        if (studentUser != null) {
            Page<Course> page = studentLoginService.querySign4Page(course, pageable);
            mav.addObject(ConstantFields.PAGE_KEY, page);
            mav.setViewName("stu/login/querySign");
        }
        else {
            mav.setViewName("redirect:/stu/login.action");
        }
        return mav;
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
        return new ModelAndView("redirect:/stu/login.action");
    }
    @RequestMapping(value = "/courseInfoSign/{courseId}", method = RequestMethod.GET)
    public ModelAndView moreCourseInfoSign(@PathVariable("courseId") int courseId ) {
        Course course = studentLoginService.moreCourseInfo(courseId);
        if (course != null){
            ModelAndView mav = new ModelAndView("stu/login/moreInfoSign");
            mav.addObject(ConstantFields.COURSE_INFO_KEY, course);
            return mav;
        }
        return new ModelAndView("redirect:/stu/login.action");
    }

    @RequestMapping(value = "/signUp/{courseId}", method = RequestMethod.GET)
    public String signUp(@PathVariable("courseId") int courseId,StudentCourse studentCourse, HttpSession session,
                         RedirectAttributes redirectAttributes) {
        Integer studentId = (Integer)session.getAttribute(ConstantFields.SESSION_STU_ID_KEY);

        studentCourse.setStudentId(studentId);
        studentCourse.setCourseId(courseId);
        if (studentLoginService.add(studentCourse) == true) {

            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/stu/login.action";
        }
        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/stu/routeSignUp.action";
    }

    @RequestMapping(value = "/routeSignUp", method = RequestMethod.GET)
    public String routeAddStudent() {
        return "stu/login/moreInfo";
    }

    @RequestMapping(value = "/delete/{courseId}", method = RequestMethod.GET)
    public String delete(@PathVariable("courseId") int courseId,StudentCourse studentCourse, HttpSession session,
                         RedirectAttributes redirectAttributes) {
        Integer studentId = (Integer)session.getAttribute(ConstantFields.SESSION_STU_ID_KEY);
        studentCourse.setStudentId(studentId);
        studentCourse.setCourseId(courseId);
        if (studentLoginService.delete(studentCourse) == true) {
            redirectAttributes.addFlashAttribute(ConstantFields.DELETE_SUCCESS_KEY, ConstantFields.DELETE_SUCCESS_MESSAGE);
            return "redirect:/stu/login.action";
        }
        redirectAttributes.addFlashAttribute(ConstantFields.DELETE_FAILURE_KEY, ConstantFields.DELETE_FAILURE_MESSAGE);
        return "redirect:/stu/login.action";

    }
}
