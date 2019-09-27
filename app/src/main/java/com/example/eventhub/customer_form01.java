package com.example.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class customer_form01 extends AppCompatActivity {

    Button proceed;
    EditText cusid, event, pkg, qty, cno, custemail;

    private boolean inValidNumber(String cno) {
        if (!Pattern.matches("[a-zA-Z]", cno)) {
            return cno.length() != 10;
        }
        return false;
    }

    private boolean validateEmailAddress(String custemail){
        String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = custemail;
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form01);

        Intent intent = getIntent();
        String eid =  intent.getStringExtra("eventID");
        TextView cevID = findViewById(R.id.event);
        cevID.setText(eid);
        String pid =  intent.getStringExtra("packageID");
        TextView cpackID = findViewById(R.id.pkg);
        cpackID.setText(pid);

        cusid = findViewById(R.id.cusid);
        event = findViewById(R.id.event);
        pkg = findViewById(R.id.pkg);
        qty = findViewById(R.id.qty);
        cno = findViewById(R.id.strr1);
        custemail = findViewById(R.id.strr2);



//insert

        proceed = (Button) findViewById(R.id.proceed);

        proceed.setOnClickListener(new View.OnClickListener() {

            form form01 = new form();

            //@Override
            public void onClick(View view) {

                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("CustomerEvent");
                try {
                    if (isEmpty(cusid.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter customer ID", Toast.LENGTH_LONG).show();
                    else if (isEmpty((event.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event name", Toast.LENGTH_LONG).show();
                    else if (isEmpty((pkg.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter event category", Toast.LENGTH_LONG).show();
                    else if (isEmpty((qty.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter  quantity", Toast.LENGTH_LONG).show();
                    else if (isEmpty((cno.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your contact number", Toast.LENGTH_LONG).show();
                    else if (isEmpty((custemail.getText().toString())))
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
                    else if (inValidNumber(cno.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a valid Number", Toast.LENGTH_LONG).show();
                    else if (!validateEmailAddress((custemail.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter a valid Email", Toast.LENGTH_LONG).show();
                    else {


                        form01.setCustomerID(cusid.getText().toString().trim());
                        form01.setEvent(event.getText().toString().trim());
                        form01.setPkg(pkg.getText().toString().trim());
                        form01.setQty(qty.getText().toString().trim());
                        form01.setContactNo(cno.getText().toString().trim());
                        form01.setCus1email(custemail.getText().toString().trim());


                        dbRef.child(form01.getCustomerID()).setValue(form01);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();
                        clearControls();

                    }



                } catch (Exception e1) {
                    Toast.makeText(getApplicationContext(), "Error" + e1, Toast.LENGTH_LONG).show();
                }

            }

            public void clearControls() {
                cusid.setText("");
                event.setText("");
                pkg.setText("");
                qty.setText("");
                cno.setText("");
                custemail.setText("");

            }
        });
    }





    public void  edit(View view) {
        Intent intent1 = new Intent(customer_form01.this,customer_crud.class);
        startActivity(intent1);
    }
}




