package com.example.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class purchase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
    }
    public void update01(View view){
        Intent intent0001 = new Intent(purchase.this, updateCard.class);
        startActivity(intent0001);
    }

    public void succuss01(View view){
        Intent intent0001 = new Intent(purchase.this, Success.class);
        startActivity(intent0001);
    }

    public void succuss002(View view){
        Intent intent0001 = new Intent(purchase.this, cardpayment.class);
        startActivity(intent0001);
    }
}
