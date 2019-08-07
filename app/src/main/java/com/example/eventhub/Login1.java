package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
    }

    public void  startnextActivity1(View view) {
        Intent intent1 = new Intent(Login1.this,Login2.class);
        startActivity(intent1);
    }

    public void  startnextActivity2(View view) {
        Intent intent1 = new Intent(Login1.this,Register.class);
        startActivity(intent1);
    }


}
