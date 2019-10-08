package com.example.appprueba.attrs;

import android.content.Intent;

public class attrsmaterias {

    private int id;
    private String et_name;
    private String et_semestre;

    public attrsmaterias(String et_name, String et_semestre) {
        this.et_name = et_name;
        this.et_semestre = et_semestre;

    }

    public attrsmaterias() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return et_name;
    }

    public void setName(String et_name) {
        this.et_name = et_name;
    }

    public String getSemestre() {
        return et_semestre;
    }

    public void setSemestre(String et_semestre) {
        this.et_semestre = et_semestre;
    }

    @Override
    public String toString() {
        return "attrsmaterias{" +
                "id=" + id +
                ", et_name='" + et_name + '\'' +
                ", et_semestre='" + et_semestre + '\'' +
                '}';
    }
}
