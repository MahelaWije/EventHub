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

public class ConfirmedEvents extends AppCompatActivity {

    ListView customer2evListview;

    ArrayList<String> CustomerID;
    ArrayList<String> event;
    ArrayList<String> pkg;
    ArrayList<String> qty;
    //ArrayList<String> time;
    //ArrayList<String> photo_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_events);

        customer2evListview = findViewById(R.id.customer2evListview);

        CustomerID = new ArrayList<String>();
        event= new ArrayList<String>();
        pkg = new ArrayList<String>();
        qty = new ArrayList<String>();
        //photo_link = new ArrayList<String>();

        final ArrayList<String> EvStrList = new ArrayList<>();
        DatabaseReference readRef1 = FirebaseDatabase.getInstance().getReference().child("CustomerEvent");
        readRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String EvStr = dsp.getKey();
                    EvStrList.add(EvStr);
                }
                for(int i =0 ; i<EvStrList.size(); i++){
                    DatabaseReference readRef2 = FirebaseDatabase.getInstance().getReference().child("CustomerEvent").child(EvStrList.get(i));
                    readRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String str1, str2, str3, str4;

                            str1 = dataSnapshot.child("customerID").getValue().toString();
                            str2 = dataSnapshot.child("event").getValue().toString();
                            str3 = dataSnapshot.child("pkg").getValue().toString();
                            str4 = dataSnapshot.child("qty").getValue().toString();

                            CustomerID.add(str1);
                            event.add(str2);
                            pkg.add(str3);
                            qty.add(str4);


                            Mycustomer2Adapter adapter = new Mycustomer2Adapter(getApplicationContext(),CustomerID,event,pkg,qty);
                            customer2evListview.setAdapter(adapter);
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

class Mycustomer2Adapter extends ArrayAdapter<String> {
    private final ArrayList CustomerID;
    private final ArrayList event;
    private final ArrayList pkg;
    private final ArrayList qty;

    Context c;

    Mycustomer2Adapter(Context c, ArrayList CustomerID, ArrayList event, ArrayList pkg, ArrayList qty) {
        super(c, R.layout.confirmevent, R.id.custvevID1, CustomerID);
        this.c = c;

        this.CustomerID = CustomerID;
        this.event = event;
        this.pkg = pkg;
        this.qty = qty;
    }


    @NonNull @Override

    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View evrow = layoutInflater.inflate(R.layout.confirmevent, parent, false);

        TextView custvevID = evrow.findViewById(R.id.custvevID1);
        TextView custvevName = evrow.findViewById(R.id.custvevName1);
        TextView custvcategory = evrow.findViewById(R.id.custvcategory1);
        TextView custvvenue = evrow.findViewById(R.id.custvvenue1);

        //ImageView stdPhoto = stdrow.findViewById(R.id.stdPhoto);

        custvevID.setText(CustomerID.get(position).toString());
        custvevName.setText(event.get(position).toString());
        custvcategory.setText(pkg.get(position).toString());
        custvvenue.setText(qty.get(position).toString());


        /*evrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), customer_form01.class);
                intent.putExtra("eventID", eventID.get(position).toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });*/

        return evrow;

    }
}
