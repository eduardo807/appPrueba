package com.example.appprueba;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.appprueba.adapter.adapterAlumnos;
import com.example.appprueba.adapter.adaptermaterias;
import com.example.appprueba.attrs.attrsAlumnos;
import com.example.appprueba.attrs.attrsmaterias;
import com.example.appprueba.models.modelsAlumno;
import com.example.appprueba.models.modelsmaterias;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Object modelsmaterias,modelsAlumno ;
    private ListView listviewmaterias,listviewAlumno ;
    private adapterAlumnos adaptador;
    private String tag_i="informacion:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listviewmaterias=(ListView) findViewById(R.id.listviewmaterias);
        modelsAlumno =new modelsAlumno(this);
        adaptador=new adapterAlumnos(((modelsAlumno) modelsAlumno).loadAlumnos(),this);
        listviewAlumno=(ListView) findViewById(R.id.listviewAlumno);
        listviewAlumno.setAdapter(adaptador);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(),crearAlumnos.class);
                startActivityForResult(intent,0);

            }
        });

        listviewAlumno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                attrsAlumnos alumnos= (attrsAlumnos) ((ListAdapter) adaptador).getItem(position);
                Toast.makeText(getApplication(),
                        "Iniciar screen de detalle para: \n" + alumnos.getId_alumn(),
                        Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(view.getContext(),crearAlumnos.class);
                intent.putExtra("id_alumn",alumnos.getId_alumn());
                intent.putExtra("et_name_alumn",alumnos.getEt_name_alumn());
                intent.putExtra("et_apellido_alumn",alumnos.getEt_apellido_alumn());
                intent.putExtra("et_cedula_alumn",alumnos.getEt_cedula_alumn());
                startActivityForResult(intent,0);
            }

        });

        listviewAlumno.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                attrsAlumnos alumnos= (attrsAlumnos) ((ListAdapter) adaptador).getItem(position);
                return true;
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        modelsmaterias=new modelsmaterias(this);
        List<attrsmaterias> listMaterias= ((modelsmaterias) modelsmaterias).loadMaterias();

        if (id == R.id.nav_materia) {

            if (listMaterias.size()==0){
                Intent intent =new Intent(this,activity_crearmaterias.class);
                startActivityForResult(intent,0);

            }else{
                Intent intent = new Intent(this,materias.class);
                startActivityForResult(intent, 0);
            }

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
