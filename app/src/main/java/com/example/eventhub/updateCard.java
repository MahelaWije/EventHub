package com.example.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class updateCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_card);
    }

    public void purcahse02(View view){
        Intent intent0001 = new Intent(updateCard.this, purchase.class);
        startActivity(intent0001);
    }
}
