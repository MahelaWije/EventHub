package com.example.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.text.TextUtils.isEmpty;

public class cardpayment extends AppCompatActivity {

    Button paysub;
    EditText payname, paynum, paycvv, payexd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardpayment);


        payname = findViewById(R.id.payxx);
        paynum = findViewById(R.id.paynum);
        paycvv = findViewById(R.id.paycvv);
        payexd = findViewById(R.id.payzz);


        paysub = (Button)findViewById(R.id.paysub);


        paysub.setOnClickListener(new View.OnClickListener() {

            payy x = new payy();

            //@Override
            public void onClick(View view) {
                //AddEvent2 addEvent2 = new AddEvent2();
                // AddEvent2 addEvent2 = new AddEvent2();

                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("payment");
                try {
                    if (isEmpty(payname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter event ID", Toast.LENGTH_LONG).show();
                    else if (isEmpty((paynum.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event name", Toast.LENGTH_LONG).show();
                    else if (isEmpty((paycvv.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event category", Toast.LENGTH_LONG).show();
                    else if (isEmpty((payexd.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event venue", Toast.LENGTH_LONG).show();
                /*else if (isEmpty((txtcontact.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your contact number", Toast.LENGTH_LONG).show();
                else if (isEmpty((txtcheckin.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-In date", Toast.LENGTH_LONG).show();
                else if (isEmpty((txtcheckout.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-Out date", Toast.LENGTH_LONG).show();*/
                    else {


                        x.setPayname(payname.getText().toString().trim());
                        x.setPaynum(paynum.getText().toString().trim());
                        x.setPaycvv(paycvv.getText().toString().trim());
                        x.setPayexd(payexd.getText().toString().trim());
                    /*addEvent2.setCheckout(txtcheckout.getText().toString().trim());
                    addEvent2.setFullName(txtname.getText().toString().trim());
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


                        dbRef.child(x.getPaynum()).setValue(x);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();
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
                payname.setText("");
                paynum.setText("");
                paycvv.setText("");
                payexd.setText("");
                    /*txtcontact.setText("");
                    txtnic.setText("");
                    txtrooms.setText("");*/
            }
        });

    }


    public void payment(View view){
        Intent intent0001 = new Intent(cardpayment.this, purchase.class);
        startActivity(intent0001);
    }


}
