package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddEvent3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event3);
    }

    public void  startnextActivity1(View view) {
        Intent intent1 = new Intent(AddEvent3.this,AddEvent2.class);
        startActivity(intent1);
    }
}
