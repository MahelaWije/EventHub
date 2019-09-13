package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.text.TextUtils.isEmpty;

public class AddPackage2 extends AppCompatActivity {


    Button add1;
    EditText pid, pname, ecategory, pticket, poffers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_package2);

        pid = findViewById(R.id.pid);
        pname = findViewById(R.id.pname);
        ecategory = findViewById(R.id.ecategory);
        pticket = findViewById(R.id.pticket);
        poffers = findViewById(R.id.poffers);



        add1 = (Button) findViewById(R.id.add1);

        add1.setOnClickListener(new View.OnClickListener() {

            AddPack addPack = new AddPack();

            //@Override
            public void onClick(View view) {
                //AddEvent2 addEvent2 = new AddEvent2();
                // AddEvent2 addEvent2 = new AddEvent2();

                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("PackageDetails");
                try {
                    if (isEmpty(pid.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter event ID", Toast.LENGTH_LONG).show();
                    else if (isEmpty((pname.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event name", Toast.LENGTH_LONG).show();
                    else if (isEmpty((ecategory.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event category", Toast.LENGTH_LONG).show();
                    else if (isEmpty((pticket.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event venue", Toast.LENGTH_LONG).show();
                    else if (isEmpty((poffers.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your contact number", Toast.LENGTH_LONG).show();
                /*else if (isEmpty((txtcheckin.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-In date", Toast.LENGTH_LONG).show();
                else if (isEmpty((txtcheckout.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-Out date", Toast.LENGTH_LONG).show();*/
                    else {


                        addPack.setPackageID(pid.getText().toString().trim());
                        addPack.setPackageName(pname.getText().toString().trim());
                        addPack.setCategory(ecategory.getText().toString().trim());
                        addPack.setTicketPrice(pticket.getText().toString().trim());
                        addPack.setOffers(poffers.getText().toString().trim());
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


                        dbRef.child(addPack.getPackageID()).setValue(addPack);

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
                pid.setText("");
                pname.setText("");
                ecategory.setText("");
                pticket.setText("");
                poffers.setText("");
                    /*txtnic.setText("");
                    txtrooms.setText("");*/
            }
        });
    }
}
