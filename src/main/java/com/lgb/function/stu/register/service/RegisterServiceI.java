package com.lgb.function.stu.register.service;

import com.lgb.function.stu.login.StudentUser;

public interface RegisterServiceI {
    boolean add(StudentUser studentUser);
    boolean existCardNum(StudentUser studentUser);
}
