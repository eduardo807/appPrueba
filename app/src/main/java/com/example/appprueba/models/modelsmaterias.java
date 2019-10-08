package com.example.appprueba.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.appprueba.ConectDB;
import com.example.appprueba.ConstansDB;
import com.example.appprueba.attrs.attrsmaterias;

import java.util.ArrayList;

public class modelsmaterias extends ConectDB {


    public modelsmaterias(Context context) {
        super(context);
    }

    private ContentValues materiasMapperContentValues(attrsmaterias materias) {
        ContentValues cv = new ContentValues();
        cv.put(ConstansDB.NOMBRE, materias.getName());
        cv.put(ConstansDB.SEMESTRE, materias.getSemestre());

        return cv;
    }

    public long insertmaterias(attrsmaterias materias) {
        this.openWritableDB();
        long rowID = db.insert(ConstansDB.TABLA_MATERIAS, null, materiasMapperContentValues(materias));
        this.closeDB();
        return rowID;
    }

    public void updateMaterias(attrsmaterias materias) {
        this.openWritableDB();
        String where = ConstansDB.ID_MATE + "= ?";
        db.update(ConstansDB.TABLA_MATERIAS, materiasMapperContentValues(materias), where, new String[]{String.valueOf(materias.getId())});
        db.close();
    }
    public void deleteMaterias(int id) {
        this.openWritableDB();
        String where = ConstansDB.ID_MATE + "= ?";
        db.delete(ConstansDB.TABLA_MATERIAS, where, new String[]{String.valueOf(id)});
        this.closeDB();
    }

    public ArrayList<attrsmaterias> loadMaterias() {
        ArrayList<attrsmaterias> list = new ArrayList<attrsmaterias>();

        this.openReadableDB();
        String[] campos = new String[]{ConstansDB.ID_MATE,ConstansDB.NOMBRE, ConstansDB.SEMESTRE};
        Cursor c = db.query(ConstansDB.TABLA_MATERIAS, campos, null, null, null, null, null);

        try {
            while (c.moveToNext()) {
                attrsmaterias materia = new attrsmaterias();
                materia.setId(c.getInt(0));
                materia.setName(c.getString(1));
                materia.setSemestre(c.getString(2));
                list.add(materia);
            }
        } finally {
            c.close();
        }
        this.closeDB();
        return list;
    }

    public attrsmaterias buscarMateriaId(int materia_id) {
        attrsmaterias materia = new attrsmaterias();
        this.openReadableDB();
        String where = ConstansDB.ID_MATE + "= ?";
        String[] whereArgs = {String.valueOf(materia_id)};
        Cursor c = db.query(ConstansDB.TABLA_MATERIAS, null, where, whereArgs, null, null, null);

        if( c != null || c.getCount() <=0) {
            c.moveToFirst();
            materia.setId(c.getInt(0));
            materia.setName(c.getString(1));
            materia.setSemestre(c.getString(2));
            c.close();
        }
        this.closeDB();
        return materia;
    }
}
