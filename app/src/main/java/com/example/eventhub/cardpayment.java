package com.example.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class cardpayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardpayment);
    }

    public void payment(View view){
        Intent intent0001 = new Intent(cardpayment.this, updateCard.class);
        startActivity(intent0001);
    }
}
