package com.mafurrasoft.springsecurity.controller;

import com.mafurrasoft.springsecurity.domain.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/estudantes")
public class StudentController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Jorge Himbu"),
            new Student(2, "Rodrigo Kumba"),
            new Student(3, "Ilurio Plunito"),
            new Student(4, "Gutyo Kilamba")
    );

    @GetMapping(path = "/{estudanteId}")
    public Student getEstudante(@PathVariable("estudanteId") Integer estudanteId){
        return STUDENT_LIST.stream()
                .filter((student)->{return student.getId().equals(estudanteId);})
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Estudante " + estudanteId + " não existe."));
    }
}
