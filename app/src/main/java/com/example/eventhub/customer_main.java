package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class customer_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
    }

    public void  cusmain02(View view) {
        Intent intent1 = new Intent(customer_main.this,customer_form01.class);
        startActivity(intent1);
    }
}
