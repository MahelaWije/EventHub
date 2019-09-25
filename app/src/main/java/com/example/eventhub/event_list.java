package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class event_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
    }


    public void  addevent01(View view) {
        Intent intent1 = new Intent(event_list.this,AddEvent2.class);
        startActivity(intent1);
    }


    public void  eventlist01(View view) {
        Intent intent1 = new Intent(event_list.this,AllEvents.class);
        startActivity(intent1);
    }
}
