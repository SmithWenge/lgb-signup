package com.lgb.function.stu.login.service;

import com.lgb.function.stu.course.Course;
import com.lgb.function.stu.course.Department;
import com.lgb.function.stu.course.Major;
import com.lgb.function.stu.login.StudentCourse;
import com.lgb.function.stu.login.StudentUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentLoginServiceI {
    Page<Course> list(Course course,Pageable pageable);
    StudentUser login(StudentUser studentUser);
    Course moreCourseInfo(int courseId);
    boolean add(StudentCourse studentCourse);
    Page<Course> querySign4Page(Course course,Pageable pageable);
    boolean delete(StudentCourse studentCourse);
    List<Department> departments();
    List<Major> majors(int departmentId);
}
