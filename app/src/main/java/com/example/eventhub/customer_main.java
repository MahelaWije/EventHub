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
        Intent intent1 = new Intent(customer_main.this,CustomerEventList.class);
        startActivity(intent1);
    }

    public void  xxxx(View view) {
        Intent intent1 = new Intent(customer_main.this,Login1.class);
        startActivity(intent1);
    }
}
