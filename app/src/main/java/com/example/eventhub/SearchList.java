package com.example.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SearchList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
    }

    public void startTheWishlist(View view){

        Intent intent = new Intent(SearchList.this,TheWishlist.class);
        startActivity(intent);
    }

    public void startType(View view){

        Intent intent = new Intent(SearchList.this,Type.class);
        startActivity(intent);
    }

    public void startLocation(View view){

        Intent intent = new Intent(SearchList.this,Location.class);
        startActivity(intent);
    }

    public void startDate(View view){

        Intent intent = new Intent(SearchList.this,Date.class);
        startActivity(intent);
    }
}
