package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
public class StudentController {
    StudentService studentService;

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/students")
    List<Student> students() {
        return studentService.students();
    }

    @PostMapping("/student")
    Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student.getName(), student.getEmail());
    }

    @GetMapping(value = "/rstudents", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Student> studentFlux() {
        return studentService.studentFlux();
    }

    @PostMapping("/rstudent")
    Mono<Student> addStudentMono(@RequestBody Student student) {
        return studentService.addStudentMono(student.getName(), student.getEmail());
    }

    @GetMapping("/rstudent/{id}")
    Mono<Student> getStudentById(@PathVariable UUID id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/rstudent/{id}")
    Mono<Student> updateStudent(@PathVariable UUID id, @RequestBody Student student) {
        return studentService.updateStudent(id, student.getName(), student.getEmail());
    }

    @DeleteMapping("/rstudent/{id}")
    Mono<Void> deleteStudentById(@PathVariable UUID id) {
        return studentService.deleteStudentById(id);
    }
}
