package com.example.appprueba.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appprueba.R;
import com.example.appprueba.attrs.attrsAlumnoMateria;
import com.example.appprueba.attrs.attrsAlumnos;
import com.example.appprueba.attrs.attrsmaterias;
import com.example.appprueba.materias;
import com.example.appprueba.models.modelsAlumnoMateria;
import com.example.appprueba.models.modelsmaterias;

import java.util.ArrayList;

public class adapterAlumnos extends BaseAdapter {
    private ArrayList<attrsAlumnos> listItems;
    private Context context;
    private modelsmaterias modelsmaterias;
    private modelsAlumnoMateria modelsAlumnoMateria;

    public adapterAlumnos(ArrayList<attrsAlumnos> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        attrsAlumnos alumnos = (attrsAlumnos) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.itemsalumnos, null);
        LinearLayout layoutMateria;
        layoutMateria = (LinearLayout) convertView.findViewById(R.id.listItemsMaterias);
        TextView et_name_alumn = (TextView) convertView.findViewById(R.id.et_name_alumn);
        TextView et_apellido = (TextView) convertView.findViewById(R.id.et_apellido);
        TextView et_cedula = (TextView) convertView.findViewById(R.id.et_cedula);
        modelsmaterias = new modelsmaterias(context);
        modelsAlumnoMateria = new modelsAlumnoMateria(context);
        ArrayList<attrsAlumnoMateria> listAlumnoMateria= modelsAlumnoMateria.buscarAlumnoId(alumnos.getId_alumn());

        for(int i = 0; i < listAlumnoMateria.size(); i++) {
            int materia_id=listAlumnoMateria.get(i).getId_mate();
            attrsmaterias attrsmaterias=modelsmaterias.buscarMateriaId(materia_id);
            TextView textMateria=new TextView(context);
            textMateria.setText(attrsmaterias.getName());
            textMateria.setTextSize(12);
            layoutMateria.addView(textMateria);
        }

        et_name_alumn.setText(alumnos.getEt_name_alumn());
        et_apellido.setText(alumnos.getEt_apellido_alumn());
        et_cedula.setText(alumnos.getEt_cedula_alumn());

        return convertView;
    }
}
