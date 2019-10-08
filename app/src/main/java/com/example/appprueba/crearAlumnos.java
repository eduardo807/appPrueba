package com.example.appprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appprueba.attrs.attrsAlumnoMateria;
import com.example.appprueba.attrs.attrsAlumnos;
import com.example.appprueba.attrs.attrsmaterias;
import com.example.appprueba.models.modelsAlumno;
import com.example.appprueba.models.modelsAlumnoMateria;
import com.example.appprueba.models.modelsmaterias;

import java.util.ArrayList;
import java.util.List;

public class crearAlumnos extends AppCompatActivity implements View.OnClickListener {

    private modelsAlumno modelsAlumno;
    //AQUI
    private modelsmaterias modelsmaterias;
    private CheckBox[] groupCheckBox;
    private LinearLayout layoutInput;
    private modelsAlumnoMateria modelsAlumnoMateria;

    private String tag_i = "informacion";
    TextView et_name_alumn;
    TextView et_apellido_alumn;
    TextView et_cedula;
    Button bt_save;
    Bundle datos;
    int editId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alumnos);
        //AQUI
        layoutInput = findViewById(R.id.layout_materia);
        datos=getIntent().getExtras();
        modelsAlumno =new modelsAlumno(this);
        modelsmaterias =new modelsmaterias(this);
        modelsAlumnoMateria = new modelsAlumnoMateria(this);
        et_name_alumn =(TextView) findViewById(R.id.et_name_alumn);
        et_apellido_alumn =(TextView) findViewById(R.id.et_apellido);
        et_cedula =(TextView) findViewById(R.id.et_cedula);
        List<attrsmaterias> list= modelsmaterias.loadMaterias();
        if (list.size()==0){
            Intent intent =new Intent(this,activity_crearmaterias.class);
            startActivityForResult(intent,0);
        }else{
            groupCheckBox = new CheckBox[list.size()];
            for(int i = 0; i < list.size(); i++) {
                groupCheckBox[i] = new CheckBox(this);
                groupCheckBox[i].setText(list.get(i).getName()+" - "+list.get(i).getSemestre());
                groupCheckBox[i].setId(list.get(i).getId());
                Log.i(tag_i, "iterador " + i);
                Log.i(tag_i, "datos " + datos);

                if (datos!=null){
                    editId=datos.getInt("id_alumn");
                    ArrayList<attrsAlumnoMateria> listContactGroup= modelsAlumnoMateria.buscarAlumnoIdMateriaId(editId,list.get(i).getId());
                    if (listContactGroup.size()==1){
                        groupCheckBox[i].setChecked(true);
                    }
                }

                layoutInput.addView(groupCheckBox[i]);
            }
        }

        bt_save = (Button) findViewById(R.id.bt_save);
        bt_save.setOnClickListener(crearAlumnos.this);
        bt_save.setText("GUARDAR");

        if (datos != null) {
            String editName = datos.getString("et_name_alumn");
            String editApellido = datos.getString("et_apellido_alumn");
            String editCedula = datos.getString("et_cedula_alumn");
            editId = datos.getInt("id_alumn");
            et_name_alumn.setText(editName);
            et_apellido_alumn.setText(editApellido);
            et_cedula.setText(editCedula);
            bt_save.setText("Editar");
        }
    }

    @Override
    public void onClick(View view) {

        int length = groupCheckBox.length;
        Boolean EmptyName=true;
        Boolean EmptyApellido=true;
        Boolean EmptyMateria=false;

        if (TextUtils.isEmpty(et_name_alumn.getText())){
            EmptyName=false;
            et_name_alumn.setHint("Introduzca el nombre");
            et_name_alumn.setError("El campo nombre es obligatorio");
        }
        if (TextUtils.isEmpty(et_apellido_alumn.getText())){
            EmptyApellido=false;
            et_apellido_alumn.setError("Por favor introduzca el apellido");

        }

        for(int i = 0; i < length; i++) {
            if (groupCheckBox[i].isChecked()) {
                EmptyMateria = true;
            }
        }

        if (EmptyName && EmptyApellido){
            if (EmptyMateria){
                attrsAlumnos alumnos = new attrsAlumnos(et_name_alumn.getText().toString(), et_apellido_alumn.getText().toString(), et_cedula.getText().toString());
                if(bt_save.getText()=="GUARDAR") {
                    Long rowId = modelsAlumno.insertAlumnos(alumnos);
                    editId=rowId.intValue();
                    Log.i(tag_i, "Se creo el alumno con id = " + Long.toString(rowId));
                    Toast.makeText(getApplication(),
                            "Se ha creado el alumno \n" + et_name_alumn.getText(),
                            Toast.LENGTH_SHORT).show();

                }else{
                    alumnos.setId_alumn(editId);
                    modelsAlumno.updateAlumnos(alumnos);
                    modelsAlumnoMateria.deleteXContactId(editId);
                    Toast.makeText(getApplication(),
                            "Se ha editado el alumno: \n" + et_name_alumn.getText(),
                            Toast.LENGTH_SHORT).show();

                }
                for(int i = 0; i < length; i++)
                    if (groupCheckBox[i].isChecked()) {
                        attrsAlumnoMateria alumnoMateria = new attrsAlumnoMateria(editId, groupCheckBox[i].getId());
                         modelsAlumnoMateria.insertAlumnoMateria(alumnoMateria);

                    }
                Intent intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, 0);


            }else{
                Toast.makeText(getApplication(), "Debe selecionar por lo menos una materia", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
