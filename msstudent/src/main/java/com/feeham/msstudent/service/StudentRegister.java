package com.feeham.msstudent.service;

import com.feeham.msstudent.networkmanager.TeacherFeignClient;
import org.springframework.stereotype.Service;

@Service
public class StudentRegister {
    private final TeacherFeignClient teacherFeignClient;

    public StudentRegister(TeacherFeignClient teacherFeignClient) {
        this.teacherFeignClient = teacherFeignClient;
    }

    public String registerStudent(String teacherName){
        return teacherFeignClient.helloFromTeacher(teacherName);
    }
}
