package com.seeksolution.repoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DataShow extends AppCompatActivity {
    TextView textViewName,textViewRepo;
         String textName,textRepo;
         ImageView imageView;
         Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);
        textViewName=findViewById(R.id.tvName);
        textViewRepo=findViewById(R.id.tvRepo);
        imageView=findViewById(R.id.imageShare);
          button=findViewById(R.id.btnAdd);

        SharedPreferences sharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);
      textName=  sharedPreferences.getString("name",null);
      textRepo=  sharedPreferences.getString("repo",null);

      textViewRepo.setText(textRepo);
      textViewName.setText(textName);
      imageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent sendIntent = new Intent();
              sendIntent.setAction(Intent.ACTION_SEND);
              sendIntent.putExtra(Intent.EXTRA_TEXT, textRepo);
              sendIntent.setType("text/plain");

              Intent shareIntent = Intent.createChooser(sendIntent, null);
              startActivity(shareIntent);
          }
      });

      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(DataShow.this,MainActivity.class);
              startActivity(intent);
              finish();
          }
      });
    }
}