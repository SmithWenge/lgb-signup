package com.lgb.function.admin.login.service;


import com.lgb.function.admin.course.Course;
import com.lgb.function.admin.login.StudentCourse;
import com.lgb.function.admin.login.repository.StudentLoginRepository;
import com.lgb.function.admin.login.StudentUser;
import com.lgb.function.support.log.repository.LogRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentLoginService implements StudentLoginServiceI {
    @Autowired
    private StudentLoginRepository studentLoginRepository;

    @Override
    public StudentUser login(StudentUser studentUser) {
        StudentUser loginUser = studentLoginRepository.selectUnique(studentUser);

        return loginUser;
    }

    @Override
    public Page<Course> list(Course course,Pageable pageable) {
        return studentLoginRepository.query4Page(course,pageable);
    }

    @Override
    public Course moreCourseInfo(int courseId) {
        return studentLoginRepository.queryForCourse(courseId);
    }

    @Override
    public boolean add(StudentCourse studentCourse) {

        boolean tmp = studentLoginRepository.signUp(studentCourse);

        return tmp;
    }

}
