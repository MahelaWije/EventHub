package com.example.eventhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddEvent5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event5);

        final EditText t1 = findViewById(R.id.eid);
        final EditText t2 = findViewById(R.id.ename);
        final EditText t3 = findViewById(R.id.ecategory);
        final EditText t4 = findViewById(R.id.evenue);
        //final EditText t5 = findViewById(R.id.EdayID);

        Button btnView = findViewById(R.id.search);
        Button btnUpdate = findViewById(R.id.update);
        Button btnDelete = findViewById(R.id.delete);

        //Inset Method//

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reafRef = FirebaseDatabase.getInstance().getReference().child("EventDetails").child(t1.getText().toString());
                reafRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChildren()) {
                            t2.setText(dataSnapshot.child("eventName").getValue().toString());
                            t3.setText(dataSnapshot.child("category").getValue().toString());
                            t4.setText(dataSnapshot.child("venue").getValue().toString());
                            //t5.setText(dataSnapshot.child("exday").getValue().toString());
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        //Update Method

       btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("EventDetails");
                updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        AddEvent addEvent = new AddEvent();

                        if(dataSnapshot.hasChild(t1.getText().toString())){

                            addEvent.setEventName(t2.getText().toString());
                            addEvent.setCategory(t3.getText().toString());
                            addEvent.setVenue(t4.getText().toString());
                            //ft.setExday(t5.getText().toString());

                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("EventDetails").child(t1.getText().toString());
                            dbRef.setValue(addEvent);

                            Toast.makeText(getApplicationContext(),"Data Update Successfully!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No sourse to update ",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("EventDetails");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(t1.getText().toString())){
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("EventDetails").child(t1.getText().toString());
                            dbRef.removeValue();

                            Toast.makeText(getApplicationContext(),"Data deleted succesfully!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"No Source to delete!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void  startnextActivity1(View view) {
        Intent intent1 = new Intent(AddEvent5.this,AddEvent4.class);
        startActivity(intent1);
    }

}
