package com.mafurrasoft.springsecurity.controller;

import com.mafurrasoft.springsecurity.domain.Estudante;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/estudante")
public class EstudanteController {

    private static final List<Estudante> STUDENT_LIST = Arrays.asList(
            new Estudante(1, "Jorge Himbu"),
            new Estudante(2, "Rodrigo Kumba"),
            new Estudante(3, "Ilurio Plunito"),
            new Estudante(4, "Gutyo Kilamba")
    );

    @GetMapping(path = "/{estudanteId}")
    public Estudante getEstudante(@PathVariable("estudanteId") Integer estudanteId){
        return STUDENT_LIST.stream()
                .filter((estudante)->{return estudante.getId().equals(estudanteId);})
                .findFirst()
                .orElseThrow(()->{return new IllegalStateException("Estudante " + estudanteId + " não existe.");});
    }
}
