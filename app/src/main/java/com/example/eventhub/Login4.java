package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login4);
    }

    public void  startnextActivity1(View view) {
        Intent intent1 = new Intent(Login4.this,WishMainActivity.class);
        startActivity(intent1);
    }
}
