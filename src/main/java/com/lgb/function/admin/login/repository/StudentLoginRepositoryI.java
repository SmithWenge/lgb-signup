package com.lgb.function.admin.login.repository;

import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.login.AdminUser;
import com.lgb.function.admin.login.StudentCourse;
import com.lgb.function.admin.login.StudentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentLoginRepositoryI {
    Page<Course> query4Page(Course course, Pageable pageable);
    StudentUser selectUnique(StudentUser studentUser);
    Course queryForCourse(int courseId);
    boolean signUp(StudentCourse studentCourse);
}
