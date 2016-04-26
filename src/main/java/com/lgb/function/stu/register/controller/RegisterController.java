package com.lgb.function.stu.register.controller;

import com.lgb.arc.utils.ConstantFields;
import com.lgb.function.stu.login.StudentUser;
import com.lgb.function.stu.register.service.RegisterServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterServiceI registerService;

    @RequestMapping(value = "/routeAdd", method = RequestMethod.GET)
    public String routeAddStudent() {
        return "stu/register/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStudent(StudentUser studentUser, RedirectAttributes redirectAttributes) {
        if (registerService.add(studentUser)) {
            if (studentUser.getStuCardNum().length()<0 && studentUser.getStuCardNum().length()>6) {
                // 长度
                if(registerService.existCardNum(studentUser)){
                    return "redirect:/register/routeAdd.action";
                }
            }
            if (studentUser.getStuName().length()<0 && studentUser.getStuName().length()>10) {
                //名字
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuGender()!= null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStudentStartDate()!= null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuTelOne().length()<6 && studentUser.getStuTelOne().length()>11) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuTelTwo().length()<6 && studentUser.getStuTelTwo().length()>11) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuType()!= null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuIdentifiedType() > 0) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuIdentifiedNum() != null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuOldWorkType()!= null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuOldWorkPlaceType() != null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuOldWorkPlaceName()!= null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuPolitical()!= null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuBirthday() != null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuLastEightNum().length() != 8) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuCheck() != null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuHealth() != null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuLocation() != null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuEducational() != null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuLevel()!=null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuSpeciality() != null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuPreferential() != null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuDependentsTel().length()<6 && studentUser.getStuDependentsTel().length()>11) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuDependentsDesc() != null) {
                return "redirect:/register/routeAdd.action";
            }
            if (studentUser.getStuNationality()!= null) {
                return "redirect:/register/routeAdd.action";
            }
            redirectAttributes.addFlashAttribute(ConstantFields.ADD_SUCCESS_KEY, ConstantFields.ADD_SUCCESS_MESSAGE);
            return "redirect:/register/routeAdd.action";
        }
        redirectAttributes.addFlashAttribute(ConstantFields.ADD_FAILURE_KEY, ConstantFields.ADD_FAILURE_MESSAGE);
        return "redirect:/register/routeAdd.action";
    }

    @RequestMapping(value = "/cardNum", method = RequestMethod.POST)
    @ResponseBody
    public boolean nameExist(StudentUser studentUser) {
        if (registerService.existCardNum(studentUser)) return true;
        else return false;
    }

}
