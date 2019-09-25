package com.example.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WishMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_main);
    }

    public void startType(View view){

        Intent intent = new Intent(WishMainActivity.this,Type.class);
        startActivity(intent);
    }

    public void startLocation(View view){

        Intent intent = new Intent(WishMainActivity.this,Location.class);
        startActivity(intent);
    }

    public void startDate(View view){

        Intent intent = new Intent(WishMainActivity.this,Date.class);
        startActivity(intent);
    }

    public void startSearchList(View view){

        Intent intent = new Intent(WishMainActivity.this,CustomerEventList.class);
        startActivity(intent);
    }

    public void startPackList(View view){

        Intent intent = new Intent(WishMainActivity.this,CustomerPackageList.class);
        startActivity(intent);
    }
}
