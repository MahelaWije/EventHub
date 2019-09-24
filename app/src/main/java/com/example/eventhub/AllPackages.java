package com.example.eventhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class AllPackages extends AppCompatActivity {

    ListView packListview;

    ArrayList<String> packageID;
    ArrayList<String> packageName;
    ArrayList<String> packagecategory;
    ArrayList<String> ticketPrice;
    ArrayList<String> offers;
    //ArrayList<String> photo_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_packages);

        packListview = findViewById(R.id.packListview);

        packageID = new ArrayList<String>();
        packageName= new ArrayList<String>();
        packagecategory = new ArrayList<String>();
        ticketPrice = new ArrayList<String>();
        offers = new ArrayList<String>();
        //photo_link = new ArrayList<String>();

        final ArrayList<String> PackStrList = new ArrayList<>();
        DatabaseReference readRef1 = FirebaseDatabase.getInstance().getReference().child("PackageDetails");
        readRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String PackStr = dsp.getKey();
                    PackStrList.add(PackStr);
                }
                for(int i =0 ; i<PackStrList.size(); i++){
                    DatabaseReference readRef2 = FirebaseDatabase.getInstance().getReference().child("PackageDetails").child(PackStrList.get(i));
                    readRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String strpid, strpName, strpcategory, strticket, stroffers;

                            strpid = dataSnapshot.child("packageID").getValue().toString();
                            strpName = dataSnapshot.child("packageName").getValue().toString();
                            strpcategory = dataSnapshot.child("category").getValue().toString();
                            strticket = dataSnapshot.child("ticketPrice").getValue().toString();
                            stroffers = dataSnapshot.child("offers").getValue().toString();
                            packageID.add(strpid);
                            packageName.add(strpName);
                            packagecategory.add(strpcategory);
                            ticketPrice.add(strticket);
                            offers.add(stroffers);

                            MyEventAdapter adapter = new MyEventAdapter(getApplicationContext(),packageID,packageName,packagecategory,ticketPrice,offers);
                            packListview.setAdapter(adapter);
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

class MyPackageAdapter extends ArrayAdapter<String> {
    private final ArrayList packageID;
    private final ArrayList packageName;
    private final ArrayList packagecategory;
    private final ArrayList ticketPrice;
    private final ArrayList offers;
    Context c;

    MyPackageAdapter(Context c, ArrayList packageID, ArrayList packageName, ArrayList packagecategory, ArrayList ticketPrice, ArrayList offers) {
        super(c, R.layout.event_row, R.id.tvevID, packageID);
        this.c = c;

        this.packageID = packageID;
        this.packageName = packageName;
        this.packagecategory = packagecategory;
        this.ticketPrice = ticketPrice;
        this.offers = offers;
    }


    @NonNull @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View evrow = layoutInflater.inflate(R.layout.event_row, parent, false);

        TextView tvpID = evrow.findViewById(R.id.tvpID);
        TextView tvpName = evrow.findViewById(R.id.tvpName);
        TextView tvpcategory = evrow.findViewById(R.id.tvpcategory);
        TextView tvpticket = evrow.findViewById(R.id.tvpticket);
        TextView tvpoffers = evrow.findViewById(R.id.tvpoffers);
        //ImageView stdPhoto = stdrow.findViewById(R.id.stdPhoto);

        tvpID.setText(packageID.get(position).toString());
        tvpName.setText(packageName.get(position).toString());
        tvpcategory.setText(packagecategory.get(position).toString());
        tvpticket.setText(ticketPrice.get(position).toString());
        tvpoffers.setText(offers.get(position).toString());

        return evrow;

    }
}
