package com.example.eventhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class customer_form02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form02);



        final EditText t1 = findViewById(R.id.cusid);
        final EditText t2 = findViewById(R.id.event);
        final EditText t3 = findViewById(R.id.pkg);
        final EditText t4 = findViewById(R.id.qty);
        //final EditText t5 = findViewById(R.id.EdayID);

        Button btnView = findViewById(R.id.search1);
        Button btnUpdate = findViewById(R.id.update1);
        Button btnDelete = findViewById(R.id.delete1);

        //search Method//

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reafRef = FirebaseDatabase.getInstance().getReference().child("CustomerEvent").child(t1.getText().toString());
                reafRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChildren()) {
                            //t1.setText(dataSnapshot.child("customerID").getValue().toString());
                            t2.setText(dataSnapshot.child("event").getValue().toString());
                            t3.setText(dataSnapshot.child("pkg").getValue().toString());
                            t4.setText(dataSnapshot.child("qty").getValue().toString());
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
                DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("CustomerEvent");
                updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        form form02 = new form();

                        if(dataSnapshot.hasChild(t1.getText().toString())){

                            form02.setCustomerID(t1.getText().toString());
                            form02.setEvent(t2.getText().toString());
                            form02.setPkg(t3.getText().toString());
                            form02.setQty(t4.getText().toString());
                            //ft.setExday(t5.getText().toString());

                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("CustomerEvent").child(t1.getText().toString());
                            dbRef.setValue(form02);

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

        //delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("CustomerEvent");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(t1.getText().toString())){
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("CustomerEvent").child(t1.getText().toString());
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

}

