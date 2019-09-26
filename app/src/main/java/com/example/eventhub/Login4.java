package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login4 extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login4);

        Button b1 = findViewById(R.id.adbtn2);
        username = (EditText)findViewById(R.id.ad3);
        password = (EditText)findViewById(R.id.ad4);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("user123")) {
                    Intent intent1 = new Intent(Login4.this,customer_main.class);
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
