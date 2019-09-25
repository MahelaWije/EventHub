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

import static com.example.eventhub.R.layout.customerpack;
import static com.example.eventhub.R.layout.package_row;

public class CustomerPackageList extends AppCompatActivity {

    //Intent intent = getIntent();
    //String eid =  intent.getStringExtra("eventID");


    ListView cuspackListview;

    ArrayList<String> packageID;
    ArrayList<String> packageName;
    ArrayList<String> packagecategory;
    ArrayList<String> ticketPrice;
    ArrayList<String> offers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_package_list);

        //TextView cevID = findViewById(R.id.cuseventid);
        //cevID.setText(eid);

        cuspackListview = findViewById(R.id.cuspackListview);

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

                            MyCustomerPackageAdapter1 adapter = new MyCustomerPackageAdapter1(getApplicationContext(),packageID,packageName,packagecategory,ticketPrice,offers);
                            cuspackListview.setAdapter(adapter);
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

class MyCustomerPackageAdapter1 extends ArrayAdapter<String> {
    private final ArrayList packageID;
    private final ArrayList packageName;
    private final ArrayList packagecategory;
    private final ArrayList ticketPrice;
    private final ArrayList offers;
    Context c;

    MyCustomerPackageAdapter1(Context c, ArrayList packageID, ArrayList packageName, ArrayList packagecategory, ArrayList ticketPrice, ArrayList offers) {
        super(c, customerpack, R.id.custvpID, packageID);
        this.c = c;

        this.packageID = packageID;
        this.packageName = packageName;
        this.packagecategory = packagecategory;
        this.ticketPrice = ticketPrice;
        this.offers = offers;
    }


    @NonNull @Override

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(customerpack, parent, false);

        TextView custvpID = row.findViewById(R.id.custvpID);
        TextView custvpName = row.findViewById(R.id.custvpName);
        TextView custvpcategory = row.findViewById(R.id.custvpcategory);
        TextView custvpticket = row.findViewById(R.id.custvpticket);
        TextView custvpoffers = row.findViewById(R.id.custvpoffers);
        //ImageView stdPhoto = stdrow.findViewById(R.id.stdPhoto);




        custvpID.setText(packageID.get(position).toString());
        custvpName.setText(packageName.get(position).toString());
        custvpcategory.setText(packagecategory.get(position).toString());
        custvpticket.setText(ticketPrice.get(position).toString());
        custvpoffers.setText(offers.get(position).toString());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), customer_form01.class);
                intent.putExtra("packageID", packageID.get(position).toString());
                //intent.putExtra("eventID", eid);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });

        return row;

    }
}
