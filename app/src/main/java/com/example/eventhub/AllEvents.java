package com.example.eventhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import java.util.List;

import static com.example.eventhub.R.id.evListview;

public class AllEvents extends AppCompatActivity {

    ListView evListview;

    ArrayList<String> eventID;
    ArrayList<String> eventName;
    ArrayList<String> category;
    ArrayList<String> venue;
    ArrayList<String> time;
    ArrayList<String> date;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);

        evListview = findViewById(R.id.evListview);

        eventID = new ArrayList<String>();
        eventName= new ArrayList<String>();
        category = new ArrayList<String>();
        venue = new ArrayList<String>();
        time = new ArrayList<String>();
        date = new ArrayList<String>();

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

                            String streid, streName, strecategory, strvenue, strtime, strdate;

                            streid = dataSnapshot.child("eventID").getValue().toString();
                            streName = dataSnapshot.child("eventName").getValue().toString();
                            strecategory = dataSnapshot.child("category").getValue().toString();
                            strvenue = dataSnapshot.child("venue").getValue().toString();
                            strtime = dataSnapshot.child("time").getValue().toString();
                            strdate = dataSnapshot.child("date").getValue().toString();
                            eventID.add(streid);
                            eventName.add(streName);
                            category.add(strecategory);
                            venue.add(strvenue);
                            time.add(strtime);
                            date.add(strdate);

                            MyEventAdapter adapter = new MyEventAdapter(getApplicationContext(),eventID,eventName,category,venue,time,date);
                            evListview.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) { }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}

   class MyEventAdapter extends ArrayAdapter<String> {
       private final ArrayList eventID;
       private final ArrayList eventName;
       private final ArrayList category;
       private final ArrayList venue;
       private final ArrayList time;
       private final ArrayList date;
       Context c;

       MyEventAdapter(Context c, ArrayList eventID, ArrayList eventName, ArrayList category, ArrayList venue, ArrayList time,ArrayList date) {
           super(c, R.layout.event_row, R.id.tvevID, eventID);
           this.c = c;

           this.eventID = eventID;
           this.eventName = eventName;
           this.category = category;
           this.venue = venue;
           this.time = time;
           this.date = date;
       }
       @NonNull @Override

    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
           LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           final View evrow = layoutInflater.inflate(R.layout.event_row, parent, false);

           TextView tvevID = evrow.findViewById(R.id.tvevID);
           TextView tvevName = evrow.findViewById(R.id.tvevName);
           TextView tvcategory = evrow.findViewById(R.id.tvcategory);
           TextView tvvenue = evrow.findViewById(R.id.tvvenue);
           TextView tvtime = evrow.findViewById(R.id.tvtime);
           TextView tvdate = evrow.findViewById(R.id.tvdate);

           tvevID.setText(eventID.get(position).toString());
           tvevName.setText(eventName.get(position).toString());
           tvcategory.setText(category.get(position).toString());
           tvvenue.setText(venue.get(position).toString());
           tvtime.setText(time.get(position).toString());
           tvdate.setText(date.get(position).toString());

           evrow.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getContext(), AddEvent5.class);
                   intent.putExtra("eventID", eventID.get(position).toString());
                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   view.getContext().startActivity(intent);
               }
           });
           return evrow;
       }
}






