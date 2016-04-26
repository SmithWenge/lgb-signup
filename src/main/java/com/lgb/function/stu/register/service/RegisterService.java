package com.lgb.function.stu.register.service;

import com.lgb.function.stu.login.StudentUser;
import com.lgb.function.stu.register.repository.RegisterRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements RegisterServiceI {
    @Autowired
    private RegisterRepositoryI registerRepository;

    @Override
    public boolean add(StudentUser studentUser) {

        boolean tmp = registerRepository.insert(studentUser);

        return tmp;
    }

    @Override
    public boolean existCardNum(StudentUser studentUser) {
        return registerRepository.selectCardNum(studentUser) == 0 ? true : false;
    }
}
