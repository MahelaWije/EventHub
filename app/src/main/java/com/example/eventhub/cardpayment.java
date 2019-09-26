package com.example.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.text.TextUtils.isEmpty;

public class cardpayment extends AppCompatActivity {

    Button paysub;
    EditText payname, paynum, paycvv, payexd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardpayment);

        payname = findViewById(R.id.payxx);
        paynum = findViewById(R.id.paynum);
        paycvv = findViewById(R.id.paycvv);
        payexd = findViewById(R.id.payzz);

        paysub = (Button)findViewById(R.id.paysub);

        paysub.setOnClickListener(new View.OnClickListener() {

            payy x = new payy();

            //@Override
            public void onClick(View view) {

                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("payment");
                try {
                    if (isEmpty(payname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_LONG).show();
                    else if (isEmpty((paynum.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter card number", Toast.LENGTH_LONG).show();
                    else if (isEmpty((paycvv.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter cvv", Toast.LENGTH_LONG).show();
                    else if (isEmpty((payexd.getText().toString())))
                        Toast.makeText(getApplicationContext(), "Please enter card's expiry date", Toast.LENGTH_LONG).show();

                    else {
                        x.setPayname(payname.getText().toString().trim());
                        x.setPaynum(paynum.getText().toString().trim());
                        x.setPaycvv(paycvv.getText().toString().trim());
                        x.setPayexd(payexd.getText().toString().trim());

                        dbRef.child(x.getPaynum()).setValue(x);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();
                        clearControls();
                    }
                } catch (Exception e1) {
                    Toast.makeText(getApplicationContext(), "Error" + e1, Toast.LENGTH_LONG).show();
                }
            }

            public void clearControls() {
                payname.setText("");
                paynum.setText("");
                paycvv.setText("");
                payexd.setText("");

            }
        });
    }

    public void payment(View view){
        Intent intent0001 = new Intent(cardpayment.this, purchase.class);
        startActivity(intent0001);
    }
}
