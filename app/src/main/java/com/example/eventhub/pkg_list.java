package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class pkg_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkg_list);
    }


    public void  addpkg01(View view) {
        Intent intent1 = new Intent(pkg_list.this,AddPackage2.class);
        startActivity(intent1);
    }


    public void  pkglist01(View view) {
        Intent intent1 = new Intent(pkg_list.this,AllPackagesNew.class);
        startActivity(intent1);
    }
}
