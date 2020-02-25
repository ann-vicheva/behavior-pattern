package com.example.pp_lab4_poved;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void memento(View view){
        Intent intent = new Intent(MainActivity.this, MementoActivity.class);
        startActivity(intent);
    }

    public void state(View view){
        Intent intent = new Intent(MainActivity.this, StateActivity.class);
        startActivity(intent);
    }

    public void command(View view){
        Intent intent = new Intent(MainActivity.this, CommandActivity.class);
        startActivity(intent);
    }

    public void interpreter(View view){
        Intent intent = new Intent(MainActivity.this, InterpreterActivity.class);
        startActivity(intent);
    }

    public void template(View view){
        Intent intent = new Intent(MainActivity.this, TemplateActivity.class);
        startActivity(intent);
    }
}
