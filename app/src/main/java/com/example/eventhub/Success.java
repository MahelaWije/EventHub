package com.example.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
    }


    public void  success01(View view) {
        Intent intent1 = new Intent(Success.this,TheWishlist.class);
        startActivity(intent1);
    }


    public void  logout01(View view) {
        Intent intent1 = new Intent(Success.this,Login2.class);
        startActivity(intent1);
    }
}
