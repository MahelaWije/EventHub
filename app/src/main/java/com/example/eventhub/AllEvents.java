package com.example.eventhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
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
    //ArrayList<String> photo_link;

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

                            MyEventAdapter adapter = new MyEventAdapter(getApplicationContext(),eventID,eventName,category,venue,time);
                            evListview.setAdapter(adapter);
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

   class MyEventAdapter extends ArrayAdapter<String> {
       private final ArrayList eventID;
       private final ArrayList eventName;
       private final ArrayList category;
       private final ArrayList venue;
       private final ArrayList time;
       Context c;

       MyEventAdapter(Context c, ArrayList eventID, ArrayList eventName, ArrayList category, ArrayList venue, ArrayList time) {
           super(c, R.layout.event_row, R.id.tvevID, eventID);
           this.c = c;

           this.eventID = eventID;
           this.eventName = eventName;
           this.category = category;
           this.venue = venue;
           this.time = time;
       }


       @NonNull @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
           LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           View evrow = layoutInflater.inflate(R.layout.event_row, parent, false);

           TextView tvevID = evrow.findViewById(R.id.tvevID);
           TextView tvevName = evrow.findViewById(R.id.tvevName);
           TextView tvcategory = evrow.findViewById(R.id.tvcategory);
           TextView tvvenue = evrow.findViewById(R.id.tvvenue);
           TextView tvtime = evrow.findViewById(R.id.tvtime);
           //ImageView stdPhoto = stdrow.findViewById(R.id.stdPhoto);

           tvevID.setText(eventID.get(position).toString());
           tvevName.setText(eventName.get(position).toString());
           tvcategory.setText(category.get(position).toString());
           tvvenue.setText(venue.get(position).toString());
           tvtime.setText(time.get(position).toString());

           return evrow;

       }
}
           //Photo add to imageview here
       /* Glide.with(context)
                .load(photo_link.get(position).toString())
                .into(stdPhoto);
        return stdrow;
    }*/





