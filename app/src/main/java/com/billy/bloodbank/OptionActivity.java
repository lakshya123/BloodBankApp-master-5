package com.billy.bloodbank;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.widget.Button;
import android.widget.Toast;


public class OptionActivity extends AppCompatActivity {

    public final String TAG = "ola";

    private static final String URL = "https://api.myjson.com/bins/e7ozx";

    FirebaseAuth mAuth;
    TextView Username;
    TextView last_donation;
    TextView next_donation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        Username = findViewById(R.id.Username);
        Username.setText("Hi" + "\n"+currentUser.getPhoneNumber()+"!");

        final StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                User[] user = gson.fromJson(s, User[].class);
                for (User user1 : user) {
                    if (user1.getPhoneNumber().equals(currentUser.getPhoneNumber())) {
                        last_donation = findViewById(R.id.last_donation);
                        next_donation = findViewById(R.id.next_donation);
                        last_donation.setText(user1.getLastDonationDate());
                        next_donation.setText(user1.getNextDonationDate());
                        Log.i(TAG, next_donation.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(OptionActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void toactTwo(View view){
        Intent intent10 = new Intent(OptionActivity.this, actTwo.class);
        startActivity(intent10);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void getBloodButton(View view){
        Intent intent4 = new Intent(OptionActivity.this, bloodCount.class);
        startActivity(intent4);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}
