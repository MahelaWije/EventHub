package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login3 extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);

        Button b1 = findViewById(R.id.adbtn1);
        username = (EditText)findViewById(R.id.ad1);
        password = (EditText)findViewById(R.id.ad2);

       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                   Intent intent1 = new Intent(getApplicationContext(), admin_menu.class);
                   startActivity(intent1);
                   clearControls();
               } else {
                   Toast.makeText(getApplicationContext(), "Username or Password Wrong", Toast.LENGTH_LONG).show();
                   clearControls();
               }
           }

           public void clearControls() {
               username.setText("");
               password.setText("");}
       });
    }
}





