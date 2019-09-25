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

public class CustomerEventList extends AppCompatActivity {

    ListView customerevListview;

    ArrayList<String> eventID;
    ArrayList<String> eventName;
    ArrayList<String> category;
    ArrayList<String> venue;
    ArrayList<String> time;
    //ArrayList<String> photo_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_event_list);


        customerevListview = findViewById(R.id.customerevListview);

        eventID = new ArrayList<String>();
        eventName= new ArrayList<String>();
        category = new ArrayList<String>();
        venue = new ArrayList<String>();
        time = new ArrayList<String>();
        //photo_link = new ArrayList<String>();

        final ArrayList<String> EvStrList = new ArrayList<>();
        DatabaseReference readRef1 = FirebaseDatabase.getInstance().getReference().child("EventDetails");
        readRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String EvStr = dsp.getKey();
                    EvStrList.add(EvStr);
                }
                for(int i =0 ; i<EvStrList.size(); i++){
                    DatabaseReference readRef2 = FirebaseDatabase.getInstance().getReference().child("EventDetails").child(EvStrList.get(i));
                    readRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String streid, streName, strecategory, strvenue, strtime;

                            streid = dataSnapshot.child("eventID").getValue().toString();
                            streName = dataSnapshot.child("eventName").getValue().toString();
                            strecategory = dataSnapshot.child("category").getValue().toString();
                            strvenue = dataSnapshot.child("venue").getValue().toString();
                            strtime = dataSnapshot.child("time").getValue().toString();
                            eventID.add(streid);
                            eventName.add(streName);
                            category.add(strecategory);
                            venue.add(strvenue);
                            time.add(strtime);

                            Mycustomer1Adapter adapter = new Mycustomer1Adapter(getApplicationContext(),eventID,eventName,category,venue,time);
                            customerevListview.setAdapter(adapter);
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

class Mycustomer1Adapter extends ArrayAdapter<String> {
    private final ArrayList eventID;
    private final ArrayList eventName;
    private final ArrayList category;
    private final ArrayList venue;
    private final ArrayList time;
    Context c;

    Mycustomer1Adapter(Context c, ArrayList eventID, ArrayList eventName, ArrayList category, ArrayList venue, ArrayList time) {
        super(c, R.layout.customerevent, R.id.custvevID, eventID);
        this.c = c;

        this.eventID = eventID;
        this.eventName = eventName;
        this.category = category;
        this.venue = venue;
        this.time = time;
    }


    @NonNull @Override

    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View evrow = layoutInflater.inflate(R.layout.customerevent, parent, false);

        TextView custvevID = evrow.findViewById(R.id.custvevID);
        TextView custvevName = evrow.findViewById(R.id.custvevName);
        TextView custvcategory = evrow.findViewById(R.id.custvcategory);
        TextView custvvenue = evrow.findViewById(R.id.custvvenue);
        TextView custvtime = evrow.findViewById(R.id.custvtime);
        //ImageView stdPhoto = stdrow.findViewById(R.id.stdPhoto);

        custvevID.setText(eventID.get(position).toString());
        custvevName.setText(eventName.get(position).toString());
        custvcategory.setText(category.get(position).toString());
        custvvenue.setText(venue.get(position).toString());
        custvtime.setText(time.get(position).toString());

        evrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CustomerPackageList.class);
                intent.putExtra("eventID", eventID.get(position).toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });

        return evrow;

    }
}
//Photo add to imageview here
       /* Glide.with(context)
                .load(photo_link.get(position).toString())
                .into(stdPhoto);
        return stdrow;
    }*/



