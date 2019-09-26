package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class Register extends AppCompatActivity {


    Button reg;
    EditText custid, custemail, custphone, custpwd;

    private boolean inValidNumber(String custphone) {
        if (!Pattern.matches("[a-zA-Z]", custphone)) {
            return custphone.length() != 10;
        }
        return false;
    }

    private boolean validateEmailAddress(String custemail){
        String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = custemail;
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        custid = findViewById(R.id.custid);
        custemail = findViewById(R.id.custemail);
        custphone = findViewById(R.id.custphone);
        custpwd = findViewById(R.id.custpwd);

        reg = (Button) findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener() {

            UserReg userReg = new UserReg();

            @Override
            public void onClick(View view) {

                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("CustomerDetails");
                try {
                    if (isEmpty(custid.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Username", Toast.LENGTH_LONG).show();
                    else if (isEmpty((custemail.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter Email", Toast.LENGTH_LONG).show();
                    else if (isEmpty((custphone.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter Contact Number", Toast.LENGTH_LONG).show();
                    else if (isEmpty((custpwd.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_LONG).show();
                    else if (inValidNumber(custphone.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter a valid Number", Toast.LENGTH_LONG).show();
                    else if (!validateEmailAddress(custemail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a valid Email", Toast.LENGTH_LONG).show();

                    else {

                        userReg.setCustomerID(custid.getText().toString().trim());
                        userReg.setEmail(custemail.getText().toString().trim());
                        userReg.setContactNum(custphone.getText().toString().trim());
                        userReg.setPassword(custpwd.getText().toString().trim());


                        dbRef.child(userReg.getCustomerID01()).setValue(userReg);

                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                        clearControls();

                    }


                } catch (Exception e1) {
                    Toast.makeText(getApplicationContext(), "Error" + e1, Toast.LENGTH_LONG).show();
                }

            }

            public void clearControls() {
                custid.setText("");
                custemail.setText("");
                custphone.setText("");
                custpwd.setText("");


            }
        });
    }


    public void  startnextActivity1(View view) {
        Intent intent1 = new Intent(Register.this,Login1.class);
        startActivity(intent1);
    }
}
