package com.example.appprueba;

public class ConstansDB {

    //General
    public static final String DB_NAME = "materias.db";
    public static final int DB_VERSION = 1;


    //TABLA MATERIAS
    public static final String TABLA_MATERIAS = "materias";

    public static final String ID_MATE = "mate_id";
    public static final String NOMBRE = "et_nombre";
    public static final String SEMESTRE = "et_semestre";


    public static final String TABLA_MATERIAS_SQL =
            "CREATE  TABLE IF NOT EXISTS " + TABLA_MATERIAS + " (" +
                    ID_MATE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOMBRE + " TEXT," +
                    SEMESTRE + " TEXT);";

    //TABLA ALUMNOS
    public static final String TABLA_ALUMNO = "alumno";

    public static final String ID_ALUMN = "alum_id";
    public static final String NOMBRE_ALUMN = "et_nombre_alumn";
    public static final String APELLIDO_ALUMN = "et_apellido";
    public static final String CEDULA_ALUMN = "et_cedula";


    public static final String TABLA_ALUMNO_SQL =
            "CREATE  TABLE IF NOT EXISTS " + TABLA_ALUMNO + " (" +
                    ID_ALUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOMBRE_ALUMN + " TEXT," +
                    APELLIDO_ALUMN + " TEXT," +
                    CEDULA_ALUMN + " TEXT);";

    // TABLE ALUMNO MATERIA REL
    public static final String TABLA_ALUMNO_MATERIA_REL = "alumno_materia_rel";
    public static final String A_M_ID = "am_id";

    public static final String TABLA_ALUMNO_MATERIA_SQL =
            "CREATE TABLE  IF NOT EXISTS " + TABLA_ALUMNO_MATERIA_REL + " (" +
                    A_M_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ID_ALUMN   + " INTEGER NOT NULL," +
                    ID_MATE   + " INTEGER NOT NULL," +
                    "FOREIGN KEY( "+ID_ALUMN+" )" +
                    " REFERENCES "+TABLA_ALUMNO+" ( "+ID_ALUMN+" ), "+
                    "FOREIGN KEY( "+ID_MATE+" )" +
                    " REFERENCES "+TABLA_MATERIAS+" ( "+ID_MATE+" )); ";
}

