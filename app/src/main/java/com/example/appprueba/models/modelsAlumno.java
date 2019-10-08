package com.example.appprueba.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.appprueba.ConectDB;
import com.example.appprueba.ConstansDB;
import com.example.appprueba.attrs.attrsAlumnos;
import com.example.appprueba.attrs.attrsmaterias;
import com.example.appprueba.materias;

import java.util.ArrayList;

public class modelsAlumno extends ConectDB {

    public modelsAlumno(Context context) {
        super(context);
    }

    private ContentValues alumnoMapperContentValues(attrsAlumnos alumnos) {
        ContentValues cv = new ContentValues();
        cv.put(ConstansDB.NOMBRE_ALUMN, alumnos.getEt_name_alumn());
        cv.put(ConstansDB.APELLIDO_ALUMN, alumnos.getEt_apellido_alumn());
        cv.put(ConstansDB.CEDULA_ALUMN, alumnos.getEt_cedula_alumn());
        return cv;
    }

    public long insertAlumnos(attrsAlumnos alumnos) {
        this.openWritableDB();
        long rowID = db.insert(ConstansDB.TABLA_ALUMNO, null, alumnoMapperContentValues(alumnos));
        this.closeDB();
        return rowID;
    }

    public void updateAlumnos(attrsAlumnos alumnos) {
        this.openWritableDB();
        String where = ConstansDB.ID_ALUMN + "= ?";
        db.update(ConstansDB.TABLA_ALUMNO, alumnoMapperContentValues(alumnos), where, new String[]{String.valueOf(alumnos.getId_alumn())});
        db.close();
    }
    public void deleteAlumnos(int id) {
        this.openWritableDB();
        String where = ConstansDB.ID_ALUMN + "= ?";
        db.delete(ConstansDB.TABLA_ALUMNO, where, new String[]{String.valueOf(id)});
        this.closeDB();
    }

    public ArrayList<attrsAlumnos> loadAlumnos() {
        ArrayList<attrsAlumnos> list = new ArrayList<attrsAlumnos>();

        this.openReadableDB();
        String[] campos = new String[]{ConstansDB.ID_ALUMN,ConstansDB.NOMBRE_ALUMN, ConstansDB.APELLIDO_ALUMN, ConstansDB.CEDULA_ALUMN};
        Cursor c = db.query(ConstansDB.TABLA_ALUMNO, campos, null, null, null, null, null);

        try {
            while (c.moveToNext()) {
                attrsAlumnos alumnos = new attrsAlumnos();
                alumnos.setId_alumn(c.getInt(0));
                alumnos.setEt_name_alumn(c.getString(1));
                alumnos.setEt_apellido_alumn(c.getString(2));
                alumnos.setEt_cedula_alumn(c.getString(3));

                list.add(alumnos);
            }
        } finally {
            c.close();
        }
        this.closeDB();
        return list;
    }

    public attrsAlumnos buscarAlumnos_id(int alumnos_id) {
        attrsAlumnos alumnos = new attrsAlumnos();
        this.openReadableDB();
        String where = ConstansDB.ID_ALUMN + "= ?";
        String[] whereArgs = {String.valueOf(alumnos_id)};
        Cursor c = db.query(ConstansDB.TABLA_ALUMNO, null, where, whereArgs, null, null, null);

        if( c != null || c.getCount() <=0) {
            c.moveToFirst();
            alumnos.setId_alumn(c.getInt(0));
            alumnos.setEt_name_alumn(c.getString(1));
            alumnos.setEt_apellido_alumn(c.getString(2));
            alumnos.setEt_cedula_alumn(c.getString(3));
            c.close();
        }
        this.closeDB();
        return alumnos;
    }
}
