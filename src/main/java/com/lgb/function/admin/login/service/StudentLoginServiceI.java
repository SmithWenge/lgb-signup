package com.lgb.function.admin.login.service;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.login.StudentCourse;
import com.lgb.function.admin.login.StudentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentLoginServiceI {
    Page<Course> list(Course course,Pageable pageable);
    StudentUser login(StudentUser studentUser);
    Course moreCourseInfo(int courseId);
    boolean add(StudentCourse studentCourse);
}
