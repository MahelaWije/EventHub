package com.example.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.text.TextUtils.isEmpty;

public class updateCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_card);

        final EditText t1 = findViewById(R.id.payunum);
        final EditText t2 = findViewById(R.id.payuu);
        final EditText t3 = findViewById(R.id.payucvv);
        final EditText t4 = findViewById(R.id.paypp);

        Button btnView = findViewById(R.id.payupsearch);
        Button btnUpdate = findViewById(R.id.payupdate);
        Button btnDelete = findViewById(R.id.paydel);

        //SEARCH

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reafRef = FirebaseDatabase.getInstance().getReference().child("payment").child(t1.getText().toString());
                reafRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChildren()) {

                            t2.setText(dataSnapshot.child("payname").getValue().toString());
                            t3.setText(dataSnapshot.child("paycvv").getValue().toString());
                            t4.setText(dataSnapshot.child("payexd").getValue().toString());

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

        //UPDATE METHOD

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("payment");
                updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        payy c = new payy();

                        if(dataSnapshot.hasChild(t1.getText().toString())) {

                            try {
                                if (isEmpty(t1.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "Please enter package ID", Toast.LENGTH_LONG).show();
                                else if (isEmpty((t2.getText().toString())))
                                    Toast.makeText(getApplicationContext(), "Please enter package name", Toast.LENGTH_LONG).show();
                                else if (isEmpty((t3.getText().toString())))
                                    Toast.makeText(getApplicationContext(), "Please enter package category", Toast.LENGTH_LONG).show();
                                else if (isEmpty((t4.getText().toString())))
                                    Toast.makeText(getApplicationContext(), "Please enter Ticket Price", Toast.LENGTH_LONG).show();
                                else {

                                    c.setPaynum(t1.getText().toString());
                                    c.setPayname(t2.getText().toString());
                                    c.setPaycvv(t3.getText().toString());
                                    c.setPayexd(t4.getText().toString());


                                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("payment").child(t1.getText().toString());
                                    dbRef.setValue(c);

                                    Toast.makeText(getApplicationContext(), "Data Update Successfully!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e1) {
                                Toast.makeText(getApplicationContext(), "Error" + e1, Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"No sourse to update ",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        //DELETE
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("payment");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(t1.getText().toString())){
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("payment").child(t1.getText().toString());
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

    public void payment22(View view){
        Intent intent0001 = new Intent(updateCard.this, purchase.class);
        startActivity(intent0001);
    }
}
