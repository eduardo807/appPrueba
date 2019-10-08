package com.example.appprueba;

import android.content.Intent;
import android.os.Bundle;

import com.example.appprueba.adapter.adaptermaterias;
import com.example.appprueba.attrs.attrsmaterias;
import com.example.appprueba.models.modelsmaterias;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class materias extends AppCompatActivity {

    private modelsmaterias modelsmaterias;
    private ListView listviewmaterias;
    private adaptermaterias adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listviewmaterias=(ListView) findViewById(R.id.listviewmaterias);
        modelsmaterias=new modelsmaterias(this);
        adaptador=new adaptermaterias(modelsmaterias.loadMaterias(),this);
        listviewmaterias.setAdapter(adaptador);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(),activity_crearmaterias.class);
                startActivityForResult(intent,0);
            }
        });
        listviewmaterias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                attrsmaterias materias= (attrsmaterias) ((ListAdapter) adaptador).getItem(position);
                Toast.makeText(getApplication(),
                        "Iniciar screen de detalle para: \n" + materias.getId(),
                        Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(view.getContext(),activity_crearmaterias.class);
                intent.putExtra("id",materias.getId());
                intent.putExtra("et_name",materias.getName());
                intent.putExtra("et_semestre",materias.getSemestre());
                startActivityForResult(intent,0);
            }

        });


    }

}
