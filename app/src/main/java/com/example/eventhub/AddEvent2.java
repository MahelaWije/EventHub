package com.example.eventhub;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AddEvent2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event2);
    }

    public void  startnextActivity1(View view) {
        Intent intent1 = new Intent(AddEvent2.this,AddEvent3.class);
        startActivity(intent1);
    }

    public void  startnextActivity2(View view) {
        Intent intent1 = new Intent(AddEvent2.this,AddEvent1.class);
        startActivity(intent1);
    }

}
