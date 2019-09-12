package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivityOrderTickets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_order_tickets);
    }

     public void platinum01(View view){

         Intent intent01 = new Intent(MainActivityOrderTickets.this, customer_form01.class);
         startActivity(intent01);

     }

     public void gold01(View view){

        Intent intent02 = new Intent(MainActivityOrderTickets.this, gold.class);
        startActivity(intent02);

     }

     public void silver01(View view){

        Intent intent03 = new Intent(MainActivityOrderTickets.this, ticketBack.class);
        startActivity(intent03);

     }

     public void bronze01(View view){

        Intent intent04 = new Intent(MainActivityOrderTickets.this, ticketBalcony.class);
        startActivity(intent04);
     }


}
