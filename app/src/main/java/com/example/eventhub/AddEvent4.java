package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddEvent4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event4);
    }

    public void  startnextActivity1(View view) {
        Intent intent1 = new Intent(AddEvent4.this,AddEvent1.class);
        startActivity(intent1);
    }

    public void  startnextActivity2(View view) {
        Intent intent1 = new Intent(AddEvent4.this,AddEvent5.class);
        startActivity(intent1);
    }
}
