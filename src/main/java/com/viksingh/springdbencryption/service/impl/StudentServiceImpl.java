package com.viksingh.springdbencryption.service.impl;

import com.viksingh.springdbencryption.repository.StudentRepository;
import com.viksingh.springdbencryption.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void saveStudent() {

    }
}
