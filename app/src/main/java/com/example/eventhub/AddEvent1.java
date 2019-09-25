package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddEvent1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event1);
    }

    public void  startnextActivity1(View view) {
        Intent intent1 = new Intent(AddEvent1.this,AddEvent2.class);
        startActivity(intent1);
    }

    public void  startnextActivity2(View view) {
        Intent intent1 = new Intent(AddEvent1.this,AddEvent4.class);
        startActivity(intent1);
    }

    public void  startnextActivity3(View view) {
        Intent intent1 = new Intent(AddEvent1.this,Login2.class);
        startActivity(intent1);
    }

    public void  startnextActivity4(View view) {
        Intent intent1 = new Intent(AddEvent1.this,AddPackage2.class);
        startActivity(intent1);
    }

    public void  startnextActivity5(View view) {
        Intent intent1 = new Intent(AddEvent1.this,AddPackage5.class);
        startActivity(intent1);
    }

    public void  startnextActivity6(View view) {
        Intent intent1 = new Intent(AddEvent1.this,AllEvents.class);
        startActivity(intent1);
    }

    public void  startnextActivity7(View view) {
        Intent intent1 = new Intent(AddEvent1.this,AllPackagesNew.class);
        startActivity(intent1);
    }

    public void  startnextActivity8(View view) {
        Intent intent1 = new Intent(AddEvent1.this,ConfirmedEvents.class);
        startActivity(intent1);
    }
}
