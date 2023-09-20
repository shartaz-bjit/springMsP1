package com.feeham.msstudent.networkmanager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "teacher-app")
public interface TeacherFeignClient {
    @GetMapping("/hello-teacher")
    public String helloFromTeacher(@RequestParam String teacherName);
}