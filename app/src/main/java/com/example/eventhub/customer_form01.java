package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.text.TextUtils.isEmpty;

public class customer_form01 extends AppCompatActivity {

    Button proceed;
    EditText cusid, event, pkg, qty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form01);

        Intent intent = getIntent();
        String eid =  intent.getStringExtra("eventID");
        TextView cevID = findViewById(R.id.event);
        cevID.setText(eid);
        String pid =  intent.getStringExtra("packageID");
        TextView cpackID = findViewById(R.id.pkg);
        cpackID.setText(pid);

        cusid = findViewById(R.id.cusid);
        event = findViewById(R.id.event);
        pkg = findViewById(R.id.pkg);
        qty = findViewById(R.id.qty);




        proceed = (Button) findViewById(R.id.proceed);

        proceed.setOnClickListener(new View.OnClickListener() {

            form form01 = new form();

            //@Override
            public void onClick(View view) {
                //AddEvent2 addEvent2 = new AddEvent2();
                // AddEvent2 addEvent2 = new AddEvent2();

                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("CustomerEvent");
                try {
                    if (isEmpty(cusid.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter event ID", Toast.LENGTH_LONG).show();
                    else if (isEmpty((event.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event name", Toast.LENGTH_LONG).show();
                    else if (isEmpty((pkg.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event category", Toast.LENGTH_LONG).show();
                    else if (isEmpty((qty.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event venue", Toast.LENGTH_LONG).show();
                /*else if (isEmpty((txtcontact.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your contact number", Toast.LENGTH_LONG).show();
                else if (isEmpty((txtcheckin.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-In date", Toast.LENGTH_LONG).show();
                else if (isEmpty((txtcheckout.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-Out date", Toast.LENGTH_LONG).show();*/
                    else {


                        form01.setCustomerID(cusid.getText().toString().trim());
                        form01.setEvent(event.getText().toString().trim());
                        form01.setPkg(pkg.getText().toString().trim());
                        form01.setQty(qty.getText().toString().trim());
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


                        dbRef.child(form01.getCustomerID()).setValue(form01);

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
                cusid.setText("");
                event.setText("");
                pkg.setText("");
                qty.setText("");
                    /*txtcontact.setText("");
                    txtnic.setText("");
                    txtrooms.setText("");*/
            }
        });
    }





    public void  edit(View view) {
        Intent intent1 = new Intent(customer_form01.this,customer_crud.class);
        startActivity(intent1);
    }
}




