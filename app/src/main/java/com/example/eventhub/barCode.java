package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class barCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code);
    }

    public void  cardpay01(View view) {
        Intent intent1 = new Intent(barCode.this,cardpayment.class);
        startActivity(intent1);
    }
}
