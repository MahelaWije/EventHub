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

import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class customer_form02 extends AppCompatActivity {

    private boolean inValidNumber(String custphone) {
        if (!Pattern.matches("[a-zA-Z]", custphone)) {
            return custphone.length() != 10;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form02);



        final EditText t1 = findViewById(R.id.cusid);
        final EditText t2 = findViewById(R.id.event);
        final EditText t3 = findViewById(R.id.pkg);
        final EditText t4 = findViewById(R.id.qty);
        final EditText t5 = findViewById(R.id.strr5);
        final EditText t6 = findViewById(R.id.strr6);

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
                            t5.setText(dataSnapshot.child("contactNo").getValue().toString());
                            t6.setText(dataSnapshot.child("cus1email").getValue().toString());
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

                        if(dataSnapshot.hasChild(t1.getText().toString())) {

                            try {
                                if (isEmpty(t1.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter event ID", Toast.LENGTH_LONG).show();
                                else if (isEmpty((t2.getText().toString())))
                                    Toast.makeText(getApplicationContext(), "Please enter event name", Toast.LENGTH_LONG).show();
                                else if (isEmpty((t3.getText().toString())))
                                    Toast.makeText(getApplicationContext(), "Please enter event category", Toast.LENGTH_LONG).show();
                                else if (isEmpty((t4.getText().toString())))
                                    Toast.makeText(getApplicationContext(), "Please enter event venue", Toast.LENGTH_LONG).show();
                                else if (isEmpty((t5.getText().toString())))
                                    Toast.makeText(getApplicationContext(), "Please enter your contact number", Toast.LENGTH_LONG).show();
                                else if (isEmpty((t6.getText().toString())))
                                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
                                else if (inValidNumber(t6.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter a valid Number", Toast.LENGTH_LONG).show();
                /*else if (isEmpty((txtcheckout.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-Out date", Toast.LENGTH_LONG).show();*/
                                else {

                                    form02.setCustomerID(t1.getText().toString());
                                    form02.setEvent(t2.getText().toString());
                                    form02.setPkg(t3.getText().toString());
                                    form02.setQty(t4.getText().toString());
                                    form02.setContactNo(t5.getText().toString());
                                    form02.setCus1email(t6.getText().toString());

                                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("CustomerEvent").child(t1.getText().toString());
                                    dbRef.setValue(form02);

                                    Toast.makeText(getApplicationContext(), "Data Update Successfully!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                Toast.makeText(getApplicationContext(), "Error" + e1, Toast.LENGTH_LONG).show();
                            }
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

