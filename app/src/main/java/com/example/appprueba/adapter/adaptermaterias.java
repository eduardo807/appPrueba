package com.example.appprueba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appprueba.R;
import com.example.appprueba.attrs.attrsAlumnos;
import com.example.appprueba.attrs.attrsmaterias;

import java.util.ArrayList;

public class adaptermaterias extends BaseAdapter {
    private ArrayList<attrsmaterias> listItems;
    private Context context;

    public adaptermaterias(ArrayList<attrsmaterias> listItems, Context context) {
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
        attrsmaterias materias = (attrsmaterias) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.itemsmaterias, null);
        TextView name = (TextView) convertView.findViewById(R.id.et_nombre);
        TextView semestre = (TextView) convertView.findViewById(R.id.et_semestre);
        name.setText(materias.getName());
        semestre.setText(materias.getSemestre());


        return convertView;
    }

}
