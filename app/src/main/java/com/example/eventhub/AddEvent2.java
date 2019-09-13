package com.example.eventhub;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;

public class AddEvent2 extends AppCompatActivity {

    Button add;
    EditText eid, ename, eecategory, evenue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event2);

        eid = findViewById(R.id.eid);
        ename = findViewById(R.id.ename);
        eecategory = findViewById(R.id.eecategory);
        evenue = findViewById(R.id.evenue);



        add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {

            AddEvent addEvent = new AddEvent();

            //@Override
            public void onClick(View view) {
                //AddEvent2 addEvent2 = new AddEvent2();
               // AddEvent2 addEvent2 = new AddEvent2();

                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("EventDetails");
                try {
                    if (isEmpty(eid.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter event ID", Toast.LENGTH_LONG).show();
                    else if (isEmpty((ename.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event name", Toast.LENGTH_LONG).show();
                    else if (isEmpty((eecategory.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event category", Toast.LENGTH_LONG).show();
                    else if (isEmpty((evenue.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event venue", Toast.LENGTH_LONG).show();
                /*else if (isEmpty((txtcontact.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your contact number", Toast.LENGTH_LONG).show();
                else if (isEmpty((txtcheckin.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-In date", Toast.LENGTH_LONG).show();
                else if (isEmpty((txtcheckout.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-Out date", Toast.LENGTH_LONG).show();*/
                    else {


                        addEvent.setEventID(eid.getText().toString().trim());
                        addEvent.setEventName(ename.getText().toString().trim());
                        addEvent.setCategory(eecategory.getText().toString().trim());
                        addEvent.setVenue(evenue.getText().toString().trim());
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


                        dbRef.child(addEvent.getEventID()).setValue(addEvent);

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
                eid.setText("");
                ename.setText("");
                eecategory.setText("");
                evenue.setText("");
                    /*txtcontact.setText("");
                    txtnic.setText("");
                    txtrooms.setText("");*/
            }
});
        }
       //}*/


            public void startnextActivity1(View view) {
                Intent intent1 = new Intent(AddEvent2.this, AddEvent3.class);
                startActivity(intent1);
            }

            public void startnextActivity2(View view) {
                Intent intent1 = new Intent(AddEvent2.this, AddEvent1.class);
                startActivity(intent1);
            }


         }


