package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admin_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
    }


    public void  eventhandling01(View view) {
        Intent intent1 = new Intent(admin_menu.this,event_list.class);
        startActivity(intent1);
    }


    public void  pkghandling01(View view) {
        Intent intent1 = new Intent(admin_menu.this,pkg_list.class);
        startActivity(intent1);
    }


    public void  confirmedevents01(View view) {
        Intent intent1 = new Intent(admin_menu.this,ConfirmedEvents.class);
        startActivity(intent1);
    }

    public void  userhandling01(View view) {
        Intent intent1 = new Intent(admin_menu.this,UserRegList.class);
        startActivity(intent1);
    }
}
