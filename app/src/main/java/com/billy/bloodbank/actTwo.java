package com.billy.bloodbank;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class actTwo extends AppCompatActivity {

    Button getformButton,getrequest,previous;
    DatabaseReference databasePatient1, databasePatient2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_two);

        getformButton =findViewById(R.id.getformButton);
        getformButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder instBuilder = new AlertDialog.Builder(actTwo.this);
                instBuilder.setTitle("IMPORTANT INSTRUCTIONS");
                View instview = getLayoutInflater().inflate(R.layout.instructions,null);

                Button accept =instview.findViewById(R.id.accept) ;
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(actTwo.this, Application_Form.class);
                        startActivity(intent);
                    }
                });
                instBuilder.setView(instview);
                AlertDialog dialog = instBuilder.create();
                dialog.show();
            }

        });


        getrequest = findViewById(R.id.requestCheck);
        getrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runtask();
            }
        });

        previous = findViewById(R.id.prevAgain);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(actTwo.this, OptionActivity.class);
                startActivity(intent11);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    public void requestData(){


        long cutoff = new Date().getTime() - 240000;
        long cutoff2 = new Date().getTime() - 300000;
        databasePatient1 = FirebaseDatabase.getInstance().getReference("Patient/24 Hrs");
       /* Query oldItems = databasePatient1.orderByChild("timeStamp").endAt(cutoff);
        oldItems.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
                    itemSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });



        databasePatient2 = FirebaseDatabase.getInstance().getReference("Patient/48 Hrs");
        Query oldItems2 = databasePatient2.orderByChild("timeStamp").endAt(cutoff2);
        oldItems2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
                    itemSnapshot.getRef().removeValue();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        })*/


        final HashMap<String,Integer> HashMap1 = new HashMap<String, Integer>();
        HashMap1.put("A+",0);
        HashMap1.put("A-",1);
        HashMap1.put("B+",2);
        HashMap1.put("B-",3);
        HashMap1.put("O+",4);
        HashMap1.put("O-",5);
        HashMap1.put("AB+",6);
        HashMap1.put("AB-",7);


        final HashMap<String,Integer> HashMap2 = new HashMap<String, Integer>();
        HashMap2.put("Whole Blood",0);
        HashMap2.put("Packed Cells",1);
        HashMap2.put("FFP",2);
        HashMap2.put("Plasma",3);
        HashMap2.put("Platelet conc.",4);

        final String Bg[] = {"A+","A-","B+","B-","O+","O-","AB+","AB-"};
        final String Bt[] = {"Whole Blood","Packed Cells","FFP","Plasma","Platelet conc."};

        databasePatient1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int[][] Array = new int[8][5];
                for(int i=0; i<8; i++)
                {
                    for(int j=0; j<5; j++)
                    {
                        Array[i][j] = 0;
                    }
                }


                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){

                    String grp = itemSnapshot.child("requiredBloodGroup").getValue().toString();
                    String type = itemSnapshot.child("requiredBloodType").getValue().toString();

                    Array[HashMap1.get(grp)][HashMap2.get(type)]++;

                    }


                    for(int i=0; i<8; i++)
                    {
                        for(int j=0; j<5; j++)
                        {
                            Log.d("Value",Bg[i] + " " + Bt[j]);
                            Log.d("Value",Integer.toString(Array[i][j]));
                        }
                    }
                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void runtask() {

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            requestData();
                        }catch (Exception e){

                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask,0,60000);

    }


}
