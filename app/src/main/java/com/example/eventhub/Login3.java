package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
    }

    public void  startnextActivity1(View view) {
        Intent intent1 = new Intent(Login3.this,AddEvent1.class);
        startActivity(intent1);
    }
}
