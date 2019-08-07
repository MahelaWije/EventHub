package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class platinum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platinum);
    }

    public void front01(View view){
        Intent intent05 = new Intent(platinum.this, ticketFront.class);
        startActivity(intent05);

    }
}
