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

import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class AddEvent2 extends AppCompatActivity {


    Button add;
    EditText eid, ename, eecategory, evenue, etime, evdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event2);

        eid = findViewById(R.id.eid);
        ename = findViewById(R.id.ename);
        eecategory = findViewById(R.id.eecategory);
        evenue = findViewById(R.id.evenue);
        evdate = findViewById(R.id.evdate);
        etime = findViewById(R.id.etime);



        add = (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            AddEvent addEvent = new AddEvent();

            @Override
            public void onClick(View view) {
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
                    else if (isEmpty((evdate.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event date", Toast.LENGTH_LONG).show();
                    else if (isEmpty((etime.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter event time", Toast.LENGTH_LONG).show();
                    else {
                        addEvent.setEventID(eid.getText().toString().trim());
                        addEvent.setEventName(ename.getText().toString().trim());
                        addEvent.setCategory(eecategory.getText().toString().trim());
                        addEvent.setVenue(evenue.getText().toString().trim());
                        addEvent.setDate(evdate.getText().toString().trim());
                        addEvent.setTime(etime.getText().toString().trim());
                        dbRef.child(addEvent.getEventID()).setValue(addEvent);
                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();
                        clearControls();
                    }
                } catch (Exception e1) {
                    Toast.makeText(getApplicationContext(), "Error" + e1, Toast.LENGTH_LONG).show();
                }

            }

            public void clearControls() {
                eid.setText("");
                ename.setText("");
                eecategory.setText("");
                evenue.setText("");
                evdate.setText("");
                etime.setText("");
            }
});
        }


            public void startnextActivity2(View view) {
                Intent intent1 = new Intent(AddEvent2.this, admin_menu.class);
                startActivity(intent1);
            }


         }


