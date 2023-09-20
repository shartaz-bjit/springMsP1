package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class StudentDao {
    private List<Student> students = new ArrayList<>();
    public List<Student> getStudents(){
        return students;
    }
    public Student addStudent(String name, String email){
        UUID id = UUID.randomUUID();
        Student student = new Student(id, name, email);
        students.add(student);
        return student;
    }

    // A flux for storing students
    private Flux<Student> studentFlux = Flux.empty();
    // Get all students and get student by their UUID
    public Flux<Student> getStudentFlux(){
        return studentFlux;
    }
    public Mono<Student> getStudentMono(UUID id){
        return studentFlux.filter(student -> student.getId().equals(id)).next();
    }
    // Create a new student
    public Mono<Student> addStudentMono(String name, String email){
        UUID id = UUID.randomUUID();
        Student student = new Student(id, name, email);
        studentFlux = studentFlux.concatWithValues(student);
        return Mono.just(student);
    }
    // If the UUID matches, update the item otherwise do nothing
    // In case of no item found, return empty mono
    public Mono<Student> updateStudent(UUID id, String name, String email) {
        return studentFlux.flatMap(student -> {
            if (student.getId().equals(id)) {
                student.setName(name);
                student.setEmail(email);
                return Mono.just(student);
            }
            return Mono.empty();
        }).next();
    }
    // If the UUID matches, skip the item, otherwise add the other items to the list
    public Mono<Void> deleteStudentById(UUID id) {
        studentFlux = studentFlux.flatMap(student -> {
            if (student.getId().equals(id)) {
                return Mono.empty();
            }
            return Mono.just(student);
        });
        return Mono.empty();
    }
}
