package com.example.bfest;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class checkprofile extends AppCompatActivity {





    private DatabaseReference Mref;
    private FirebaseDatabase mfirebasedatabase;
    private FirebaseAuth mauth;
    private StorageReference mfirebasestorage;
    String Current_user;
    public TextView leader, stallname, members,stallid,menu,semester, fieldstudy;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkprofile);

        leader=findViewById(R.id.leaderedittext);
        members=findViewById(R.id.groupmemberedittext);
        stallid=findViewById(R.id.stallidedittext);
        stallname=findViewById(R.id.stallnameedittext);
        menu=findViewById(R.id.menuitemedittext);
        semester=findViewById(R.id.semesteredittext);
        fieldstudy=findViewById(R.id.fieldofstudyeditext);
        imageView=findViewById(R.id.imageview);




        mauth=FirebaseAuth.getInstance();
        Current_user=mauth.getCurrentUser().getUid();

        mfirebasedatabase = FirebaseDatabase.getInstance();
        Mref= mfirebasedatabase.getReference().child("Students").child(Current_user);



    }

    @Override
    protected void onStart() {
        super.onStart();
        Mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
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
                    String smester=dataSnapshot.child("Semester").getValue().toString();
                    semester.setText(smester);
                    String study=dataSnapshot.child("FieldofStudy").getValue().toString();
                    fieldstudy.setText(study);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

