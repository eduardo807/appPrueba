package com.example.appprueba.attrs;

public class attrsAlumnos {

    private int id_alumn;
    private String et_name_alumn;
    private String et_apellido_alumn;
    private String et_cedula_alumn;

    public attrsAlumnos(String et_name_alumn, String et_apellido_alumn, String et_cedula_alumn) {
        this.et_name_alumn = et_name_alumn;
        this.et_apellido_alumn = et_apellido_alumn;
        this.et_cedula_alumn = et_cedula_alumn;
    }

    public attrsAlumnos() {

    }


    public int getId_alumn() {
        return id_alumn;
    }

    public void setId_alumn(int id_alumn) {
        this.id_alumn = id_alumn;
    }

    public String getEt_name_alumn() {
        return et_name_alumn;
    }

    public void setEt_name_alumn(String et_name_alumn) {
        this.et_name_alumn = et_name_alumn;
    }

    public String getEt_apellido_alumn() {
        return et_apellido_alumn;
    }

    public void setEt_apellido_alumn(String et_apellido_alumn) {
        this.et_apellido_alumn = et_apellido_alumn;
    }

    public String getEt_cedula_alumn() {
        return et_cedula_alumn;
    }

    public void setEt_cedula_alumn(String et_cedula_alumn) {
        this.et_cedula_alumn = et_cedula_alumn;
    }

    @Override
    public String toString() {
        return "attrsAlumnos{" +
                "id_alumn=" + id_alumn +
                ", et_name_alumn='" + et_name_alumn + '\'' +
                ", et_apellido_alumn='" + et_apellido_alumn + '\'' +
                ", et_cedula_alumn='" + et_cedula_alumn + '\'' +
                '}';
    }
}

