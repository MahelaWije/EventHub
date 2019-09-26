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

import static android.text.TextUtils.isEmpty;

public class Register extends AppCompatActivity {

    Button reg;
    EditText custid, custemail, custphone, custpwd;

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

                /*else if (isEmpty((txtcheckin.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-In date", Toast.LENGTH_LONG).show();
                else if (isEmpty((txtcheckout.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-Out date", Toast.LENGTH_LONG).show();*/
                    else {


                        userReg.setCustomerID(custid.getText().toString().trim());
                        userReg.setEmail(custemail.getText().toString().trim());
                        userReg.setContactNum(custphone.getText().toString().trim());
                        userReg.setPassword(custpwd.getText().toString().trim());

                    /*addEvent2.setFullName(txtname.getText().toString().trim());
                    addEvent2.setAddress(txtaddress.getText().toString().trim());

                    try{
                        addEvent2.setContactnumber(Integer.parseInt(txtcontact.getText().toString().trim()));
                    }catch (NumberFormatException ex1){
                        Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_LONG).show();
                    }

                    try{
                        makereservation.setRooms(Integer.parseInt(txtrooms.getText().toString().trim()));
                    }catch(NumberFormatException e2){
                        Toast.makeText(getApplicationContext(), "Invalid Room Type", Toast.LENGTH_LONG).show();
                    }*/


                        dbRef.child(userReg.getCustomerID01()).setValue(userReg);

                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                        clearControls();

                        //Intent i = new Intent(getApplicationContext(), makereservation4.class);
                        //startActivity(i);
                    }

                    //});

                } catch (Exception e1) {
                    Toast.makeText(getApplicationContext(), "Error" + e1, Toast.LENGTH_LONG).show();
                }

            }

            public void clearControls() {
                custid.setText("");
                custemail.setText("");
                custphone.setText("");
                custpwd.setText("");

                    /*txtcontact.setText("");
                    txtnic.setText("");
                    txtrooms.setText("");*/
            }
        });
    }


    public void  startnextActivity1(View view) {
        Intent intent1 = new Intent(Register.this,Login1.class);
        startActivity(intent1);
    }
}
