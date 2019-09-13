package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class customer_crud extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_crud);
    }


    public void  edit(View view) {
        Intent intent1 = new Intent(customer_crud.this,customer_form02.class);
        startActivity(intent1);
    }
}
