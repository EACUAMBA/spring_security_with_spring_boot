package com.mafurrasoft.springsecurity.controller.managment;

import com.mafurrasoft.springsecurity.domain.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController(value = "managementStudentController")
@RequestMapping(path = "management/api/v1/students")
public class  StudentController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Jorge Himbu"),
            new Student(2, "Rodrigo Kumba"),
            new Student(3, "Ilurio Plunito"),
            new Student(4, "Gutyo Kilamba")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getStudentList(){
        System.out.println("getStudentList");
        return STUDENT_LIST;
    }

    @GetMapping(path = "{studentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public void print_A_Student(@PathVariable("studentId") Integer studentId){
        System.out.println("print_A_Student");
        System.out.println(STUDENT_LIST.stream()
                .filter((student)->{return student.getId().equals(studentId);})
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Student " + studentId +" does not exists.")));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('student:write', 'course:write')")
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("registerNewStudent");
        System.out.println(student);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write', 'course:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.println("updateStudent");
        System.out.println(String.format("%nID - %d,%n New Student Instance: %s", studentId, student));
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write', 'course:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("deleteStudent");
        System.out.println("StudentId: " + studentId);
    }


}
