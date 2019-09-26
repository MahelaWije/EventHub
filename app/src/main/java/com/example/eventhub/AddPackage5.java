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

import static android.text.TextUtils.isEmpty;

public class AddPackage5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_package5);

        Intent intent = getIntent();
        String pid = intent.getStringExtra("packageID");

        final EditText t1 = findViewById(R.id.pid);
        final EditText t2 = findViewById(R.id.pname);
        final EditText t3 = findViewById(R.id.pcategory);
        final EditText t4 = findViewById(R.id.pticket);
        final EditText t5 = findViewById(R.id.poffers);

        Button btnView = findViewById(R.id.search1);
        Button btnUpdate = findViewById(R.id.update1);
        Button btnDelete = findViewById(R.id.delete1);

        t1.setText(pid);

        //Search Method//


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reafRef = FirebaseDatabase.getInstance().getReference().child("PackageDetails").child(t1.getText().toString());
                reafRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChildren()) {
                            t2.setText(dataSnapshot.child("packageName").getValue().toString());
                            t3.setText(dataSnapshot.child("category").getValue().toString());
                            t4.setText(dataSnapshot.child("ticketPrice").getValue().toString());
                            t5.setText(dataSnapshot.child("offers").getValue().toString());
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
                DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("PackageDetails");
                updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        AddPack addPack = new AddPack();

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
                                else if (isEmpty((t5.getText().toString())))
                                    Toast.makeText(getApplicationContext(), "Please enter Offers", Toast.LENGTH_LONG).show();
                /*else if (isEmpty((txtcheckin.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-In date", Toast.LENGTH_LONG).show();
                else if (isEmpty((txtcheckout.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your Check-Out date", Toast.LENGTH_LONG).show();*/
                                else {

                                    addPack.setPackageID(t1.getText().toString());
                                    addPack.setPackageName(t2.getText().toString());
                                    addPack.setCategory(t3.getText().toString());
                                    addPack.setTicketPrice(t4.getText().toString());
                                    addPack.setOffers(t5.getText().toString());

                                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("PackageDetails").child(t1.getText().toString());
                                    dbRef.setValue(addPack);

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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("PackageDetails");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(t1.getText().toString())){
                            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("PackageDetails").child(t1.getText().toString());
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
