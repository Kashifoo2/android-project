package com.example.bfest;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class checkpost extends AppCompatActivity {

    public TextView leader, stallname, members,stallid,menu,semester, fieldstudy;
    ImageView imageView;
    String postkey;
    private DatabaseReference mref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkpost);



        leader=findViewById(R.id.leaderedittext);
        members=findViewById(R.id.groupmemberedittext);
        stallid=findViewById(R.id.stallidedittext);
        stallname=findViewById(R.id.stallnameedittext);
        menu=findViewById(R.id.menuitemedittext);

        imageView=findViewById(R.id.imageview);


        postkey=getIntent().getExtras().get("postkey").toString();
        mref= FirebaseDatabase.getInstance().getReference().child("Students").child(postkey);


        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String leadername=dataSnapshot.child("LeaderName").getValue().toString();
                leader.setText(leadername);
                String groupmembers=dataSnapshot.child("GroupMembers").getValue().toString();
                members.setText(groupmembers);
                String logo=dataSnapshot.child("logourl").getValue().toString();
                Picasso.get().load(logo).into(imageView);
                String locationid=dataSnapshot.child("StallLocationid").getValue().toString();
                stallid.setText(locationid);
                String sname=dataSnapshot.child("StallName").getValue().toString();
                stallname.setText(sname);
                String items=dataSnapshot.child("MenuItems").getValue().toString();
                menu.setText(items);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




}
