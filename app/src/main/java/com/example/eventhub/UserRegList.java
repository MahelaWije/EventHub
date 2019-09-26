package com.example.eventhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserRegList extends AppCompatActivity {

    ListView userListview;

    ArrayList<String> customerID;
    ArrayList<String> email;
    ArrayList<String> contactnum;
    ArrayList<String> pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg_list);

        userListview = findViewById(R.id.userListview);

        customerID = new ArrayList<String>();
        email= new ArrayList<String>();
        contactnum = new ArrayList<String>();
        pwd = new ArrayList<String>();

        final ArrayList<String> userStrList = new ArrayList<>();
        DatabaseReference readRef1 = FirebaseDatabase.getInstance().getReference().child("CustomerDetails");
        readRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String EvStr = dsp.getKey();
                    userStrList.add(EvStr);
                }
                for(int i =0 ; i<userStrList.size(); i++){
                    DatabaseReference readRef2 = FirebaseDatabase.getInstance().getReference().child("CustomerDetails").child(userStrList.get(i));
                    readRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String str1, str2, str3, str4;

                            str1 = dataSnapshot.child("customerID01").getValue().toString();
                            str2 = dataSnapshot.child("email").getValue().toString();
                            str3 = dataSnapshot.child("contactNum").getValue().toString();
                            str4 = dataSnapshot.child("password").getValue().toString();

                            customerID.add(str1);
                            email.add(str2);
                            contactnum.add(str3);
                            pwd.add(str4);

                            MyEventAdapter2 adapter = new MyEventAdapter2(getApplicationContext(),customerID,email,contactnum,pwd);
                            userListview.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    } }

class MyEventAdapter2 extends ArrayAdapter<String> {
    private final ArrayList customerID;
    private final ArrayList email;
    private final ArrayList contactnum;
    private final ArrayList pwd;

    Context c;

    MyEventAdapter2(Context c, ArrayList customerID, ArrayList email, ArrayList contactnum, ArrayList pwd) {
        super(c, R.layout.userveiwlist, R.id.tvevID12, customerID);
        this.c = c;

        this.customerID = customerID;
        this.email = email;
        this.contactnum = contactnum;
        this.pwd = pwd;
    }


    @NonNull @Override

    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View userrow = layoutInflater.inflate(R.layout.userveiwlist, parent, false);

        TextView tvevID12 = userrow.findViewById(R.id.tvevID12);
        TextView tvevName12 = userrow.findViewById(R.id.tvevName12);
        TextView tvcno12 = userrow.findViewById(R.id.tvcno12);
        TextView tvpwd12 = userrow.findViewById(R.id.tvpwd12);


        tvevID12.setText(customerID.get(position).toString());
        tvevName12.setText(email.get(position).toString());
        tvcno12.setText(contactnum.get(position).toString());
        tvpwd12.setText(pwd.get(position).toString());


        return userrow;

    }
}

