package com.example.appprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appprueba.attrs.attrsmaterias;
import com.example.appprueba.models.modelsmaterias;

public class activity_crearmaterias extends AppCompatActivity implements View.OnClickListener {

    private modelsmaterias modelsmaterias;
    private String tag_i = "sms_info";
    TextView et_name;
    TextView et_semestre;
    Button bt_guardar;
    Bundle datos;
    int editId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearmaterias);
        modelsmaterias=new modelsmaterias(this);
        datos = getIntent().getExtras();
        et_name = (TextView) findViewById(R.id.et_nombre);
        et_semestre = (TextView) findViewById(R.id.et_semestre);


        bt_guardar = (Button) findViewById(R.id.bt_guardar);
        bt_guardar.setOnClickListener(activity_crearmaterias.this);
        bt_guardar.setText("GUARDAR");
        if (datos != null) {
            String editName = datos.getString("et_name");
            String editSemestre = datos.getString("et_semestre");
            editId = datos.getInt("id");
            et_name.setText(editName);
            et_semestre.setText(editSemestre);
            bt_guardar.setText("Editar");
        }
    }


    public void onClick(View v) {
        Boolean EmptyName=true;
        Boolean EmptySemestre=true;
        String s_active="0";

        if (TextUtils.isEmpty(et_name.getText())){
            EmptyName=false;
            et_name.setHint("Introduzca el nombre");
            et_name.setError("El campo nombre es obligatorio");
        }
        if (TextUtils.isEmpty(et_semestre.getText())){
            EmptySemestre=false;
            et_semestre.setError("Por favor introduzca el semestre");

        }
        if (EmptyName && EmptySemestre){
            attrsmaterias materias = new attrsmaterias(et_name.getText().toString(), et_semestre.getText().toString());
            if(bt_guardar.getText()=="GUARDAR") {
                Long rowId = modelsmaterias.insertmaterias(materias);
                editId=rowId.intValue();
                Log.i(tag_i, "Se creo la materia con id = " + Long.toString(rowId));
                Toast.makeText(getApplication(),
                        "Se ha creado la materia \n" + et_name.getText(),
                        Toast.LENGTH_SHORT).show();

            }else{
                materias.setId(editId);
                modelsmaterias.updateMaterias(materias);
                Toast.makeText(getApplication(),
                        "Se ha editado la materia: \n" + et_name.getText(),
                        Toast.LENGTH_SHORT).show();

            }
            Intent intent = new Intent(this, materias.class);
            startActivityForResult(intent, 0);
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
