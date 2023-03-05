package com.seeksolution.repoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextName,editTextRepo;
    Button button_Add;
//        button_Update,button_delete, View1;
     DataHelp DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName=findViewById(R.id.et_Name);
        editTextRepo=findViewById(R.id.et_Reponame);
        button_Add=findViewById(R.id.main_add_btn);
//        button_delete=findViewById(R.id.main_delete_);
//        button_Update=findViewById(R.id.main_update_btn);
//        View1=findViewById(R.id.main_View);

        DB=new DataHelp(this);
        button_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editTextName.getText().toString();
                String repo=editTextRepo.getText().toString();




                SharedPreferences sharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("name",name);
                editor.putString("repo",repo);
                editor.commit();


                Boolean checkinsertdata=DB.insertuserdata(name,repo);

                if (checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "DATA INSERTED SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "DATA CAN'T INSERTED", Toast.LENGTH_SHORT).show();
                }

                if (validateName(name) & validateRepo(repo)) {
                    Intent intent = new Intent(getApplicationContext(), DataShow.class);
                    startActivity(intent);
                    finish();
                }else
                {
                    Toast.makeText(MainActivity.this, "Please Enter details", Toast.LENGTH_SHORT).show();
                }
            }
        });



//        button_Update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String nameTEXT=editTextName.getText().toString();
//                String REPOTEXT=editTextRepo.getText().toString();
//
//
//                Boolean checkupdatedata=DB.updateuserdata(nameTEXT,REPOTEXT);
//
//                if (checkupdatedata==true){
//                    Toast.makeText(MainActivity.this, "DATA UPDATED SUCCESSFUL", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(MainActivity.this, "DATA CAN'T UPDATE", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });

//        button_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String nameTEXT=editTextName.getText().toString();
//
//
//                Boolean checkdeletedata=DB.deletedata(nameTEXT);
//
//                if (checkdeletedata==true){
//                    Toast.makeText(MainActivity.this, "DATA DELETED SUCCESSFUL", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(MainActivity.this, "DATA CAN'T DELETED", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });

//        View1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(android.view.View view) {
//                Cursor cr=DB.getdata();
//                if (cr.getCount()==0){
//                    Toast.makeText(MainActivity.this, "NO DATA EXISTS", Toast.LENGTH_SHORT).show();
//                return;
//                }
//                StringBuffer buffer=new StringBuffer();
//                while (cr.moveToNext()){
//                    buffer.append("Name :" +cr.getString(0)+"\n");
//                    buffer.append("RepoName :" +cr.getString(1)+"\n");
//                }
//                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("User Data");
//                builder.setMessage(buffer.toString());
//                builder.show();
//            }
//        });


    }
    public boolean  validateName(String name){
        if (name.isEmpty()){
            editTextName.requestFocus();
            editTextName.setError("Required field");
            return  false;
        }
        editTextName.setError("");
        return true;
    }
    public boolean  validateRepo(String repo){
        if (repo.isEmpty()){
            editTextRepo.requestFocus();
            editTextRepo.setError("Required field");
            return  false;
        }
        editTextRepo.setError("");
        return true;
    }
}