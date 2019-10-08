package com.example.appprueba.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.appprueba.ConectDB;
import com.example.appprueba.ConstansDB;
import com.example.appprueba.attrs.attrsAlumnoMateria;

import java.util.ArrayList;

public class modelsAlumnoMateria extends ConectDB {

    public modelsAlumnoMateria(Context context) {
        super(context);
    }

    private ContentValues alumnoMateriaMapperContentValues(attrsAlumnoMateria alumnoMateria) {
        ContentValues cv = new ContentValues();
        cv.put(ConstansDB.ID_MATE, alumnoMateria.getId_mate());
        cv.put(ConstansDB.ID_ALUMN, alumnoMateria.getId_alum());
        return cv;
    }


    public long insertAlumnoMateria(attrsAlumnoMateria alumnoMateria) {
        this.openWritableDB();
        long rowID = db.insert(ConstansDB.TABLA_ALUMNO_MATERIA_REL, null, alumnoMateriaMapperContentValues(alumnoMateria));
        this.closeDB();
        return rowID;
    }

    public void updateContactGroup(attrsAlumnoMateria alumnoMateria) {
        this.openWritableDB();
        String where = ConstansDB.A_M_ID + "= ?";
        db.update(ConstansDB.TABLA_ALUMNO_MATERIA_REL, alumnoMateriaMapperContentValues(alumnoMateria), where, new String[]{String.valueOf(alumnoMateria.getId())});
        db.close();
    }

    public void deleteContacGroup(int id) {
        this.openWritableDB();
        String where = ConstansDB.A_M_ID + "= ?";
        db.delete(ConstansDB.TABLA_ALUMNO_MATERIA_REL, where, new String[]{String.valueOf(id)});
        this.closeDB();
    }

    public void deleteXContactId(int materia_id) {
        this.openWritableDB();
        String where = ConstansDB.ID_ALUMN + "= ?";
        db.delete(ConstansDB.TABLA_ALUMNO_MATERIA_REL, where, new String[]{String.valueOf(materia_id)});
        this.closeDB();
    }

    public ArrayList<attrsAlumnoMateria> loadAlumnoMateria() {

        ArrayList<attrsAlumnoMateria> list = new ArrayList<attrsAlumnoMateria>();

        this.openReadableDB();
        String[] campos = new String[]{ConstansDB.A_M_ID, ConstansDB.ID_ALUMN,ConstansDB.ID_MATE};
        Cursor c = db.query(ConstansDB.TABLA_ALUMNO_MATERIA_REL, campos, null, null, null, null, null);

        try {
            while (c.moveToNext()) {
                attrsAlumnoMateria alumnoMateria = new attrsAlumnoMateria();
                alumnoMateria.setId(c.getInt(0));
                alumnoMateria.setId_alum(c.getInt(1));
                alumnoMateria.setId_mate(c.getInt(2));
                list.add(alumnoMateria);
            }
        } finally {
            c.close();
        }
        this.closeDB();

        return list;
    }

    public ArrayList<attrsAlumnoMateria> buscarMateriaId(int materia_id) {

        ArrayList<attrsAlumnoMateria> list = new ArrayList<attrsAlumnoMateria>();
        this.openReadableDB();
        String where = ConstansDB.ID_MATE + "= ?";
        String[] whereArgs = {String.valueOf(materia_id)};
        Cursor c = db.query(ConstansDB.TABLA_ALUMNO_MATERIA_REL, null, where, whereArgs, null, null, null);

        try {
            while (c.moveToNext()) {
                attrsAlumnoMateria alumnoMateria = new attrsAlumnoMateria();
                alumnoMateria.setId(c.getInt(0));
                alumnoMateria.setId_alum(c.getInt(1));
                alumnoMateria.setId_mate(c.getInt(2));
                list.add(alumnoMateria);
            }
        } finally {
            c.close();
        }
        this.closeDB();
        return list;
    }

    public ArrayList<attrsAlumnoMateria> buscarAlumnoId(int alumno_id) {

        ArrayList<attrsAlumnoMateria> list = new ArrayList<attrsAlumnoMateria>();
        this.openReadableDB();
        String where = ConstansDB.ID_ALUMN + "= ?";
        String[] whereArgs = {String.valueOf(alumno_id)};
        Cursor c = db.query(ConstansDB.TABLA_ALUMNO_MATERIA_REL, null, where, whereArgs, null, null, null);

        try {
            while (c.moveToNext()) {
                attrsAlumnoMateria alumnoMateria = new attrsAlumnoMateria();
                alumnoMateria.setId(c.getInt(0));
                alumnoMateria.setId_alum(c.getInt(1));
                alumnoMateria.setId_mate(c.getInt(2));
                list.add(alumnoMateria);
            }
        } finally {
            c.close();
        }
        this.closeDB();
        return list;
    }

    public ArrayList<attrsAlumnoMateria> buscarAlumnoIdMateriaId(int alumno_id,int materia_id) {

        ArrayList<attrsAlumnoMateria> list = new ArrayList<attrsAlumnoMateria>();
        this.openReadableDB();
        String where = ConstansDB.ID_ALUMN + "= ? AND "+
                ConstansDB.ID_MATE + " =?";
        String[] whereArgs = {String.valueOf(alumno_id),String.valueOf(materia_id)};
        Cursor c = db.query(ConstansDB.TABLA_ALUMNO_MATERIA_REL, null, where, whereArgs, null, null, null);

        try {
            while (c.moveToNext()) {
                attrsAlumnoMateria alumnoMateria = new attrsAlumnoMateria();
                alumnoMateria.setId(c.getInt(0));
                alumnoMateria.setId_alum(c.getInt(1));
                alumnoMateria.setId_mate(c.getInt(2));
                list.add(alumnoMateria);
            }
        } finally {
            c.close();
        }
        this.closeDB();
        return list;
    }

}