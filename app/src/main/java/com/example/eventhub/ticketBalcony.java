package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ticketBalcony extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_balcony);
    }

    public void details03(View view){
        Intent intent09 = new Intent(ticketBalcony.this, details.class);
        startActivity(intent09);
    }
}
