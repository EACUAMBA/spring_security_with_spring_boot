package com.mafurrasoft.springsecurity.domain;

public class Estudante {
    private final Integer id;
    private final String name;

    public Estudante(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
