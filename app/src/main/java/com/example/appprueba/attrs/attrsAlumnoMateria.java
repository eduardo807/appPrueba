package com.example.appprueba.attrs;

public class attrsAlumnoMateria {
    private int id;
    private int id_alum;
    private int id_mate;

    public attrsAlumnoMateria(int id_alum, int id_mate){
        this.id_alum = id_alum;
        this.id_mate = id_mate;
    }

    public attrsAlumnoMateria() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_alum() {
        return id_alum;
    }

    public void setId_alum(int id_alum) {
        this.id_alum = id_alum;
    }

    public int getId_mate() {
        return id_mate;
    }

    public void setId_mate(int id_mate) {
        this.id_mate = id_mate;
    }

    @Override
    public String toString() {
        return "attrsAlumnoMateria{" +
                "id=" + id +
                ", id_alum=" + id_alum +
                ", id_mate=" + id_mate +
                '}';
    }
}
