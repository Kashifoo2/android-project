package com.example.bfest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static android.widget.Toast.LENGTH_SHORT;

public class forguesthomescreen extends AppCompatActivity {

   private FirebaseAuth mAuth;
    Button signoutbtn;
    String tag="create profile";
    Button btn;

    private RecyclerView mRecycleview;
    RecyclerView.LayoutManager layoutManager;
    String Current_user;
    private FirebaseAuth mauth;

    private DatabaseReference mref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forguesthomescreen);

        mAuth = FirebaseAuth.getInstance();
        signoutbtn = findViewById(R.id.signout);
        mRecycleview = findViewById(R.id.Recyclerview);

        mRecycleview.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        mRecycleview.setLayoutManager(layoutManager);
        mauth= FirebaseAuth.getInstance();
        Current_user=mauth.getCurrentUser().getUid();
        mref= FirebaseDatabase.getInstance().getReference().child("Students");


    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<checkprofilemodel> options=
                new FirebaseRecyclerOptions.Builder<checkprofilemodel>()
                        .setQuery(mref,checkprofilemodel.class).build();

        FirebaseRecyclerAdapter<checkprofilemodel,viewholder> adapter =
                new FirebaseRecyclerAdapter<checkprofilemodel,viewholder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull viewholder viewholder, final int position, @NonNull checkprofilemodel checkprofilemodel) {

                        viewholder.stallname.setText(checkprofilemodel.getStallName());
                        Picasso.get().load(checkprofilemodel.getLogourl()).into(viewholder.getImageView());
// for post checking detail
                        final String postkey= getRef(position).getKey();
                        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent clickpost=new Intent(getApplicationContext(),checkpost.class);
                                clickpost.putExtra("postkey",postkey);
                                startActivity(clickpost);
                            }
                        });

                    }
                    @NonNull
                    @Override
                    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.checkprofilemodel,parent,false);
                        viewholder holder = new viewholder(view);

                        return holder;
                    }
                };
        mRecycleview.setAdapter(adapter);
        adapter.startListening();

    }


    //optionmenu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmeni, menu);
        return true;

    }



    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.signout) {
            ///signout
            mAuth.signOut();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "succesfully logout", LENGTH_SHORT).show();


        }



       else if (item.getItemId()== R.id.profiles) {

            Intent i =new Intent(forguesthomescreen.this,Createprofile.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "create profile", LENGTH_SHORT).show();



        }

        else if (item.getItemId()== R.id.seeproifile) {

            Intent i =new Intent(forguesthomescreen.this,checkprofile.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), " check profile", LENGTH_SHORT).show();
        }


        return true;
    }




}
