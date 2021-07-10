package com.example.bfest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import static android.widget.Toast.LENGTH_SHORT;

public class Createprofile extends AppCompatActivity {


    EditText leadername,groupmembers,stallid,stallname,items,semester,fieldofstudy;
    Button savebtn;
    ImageView imagelogo;
    String profileimageurl;


    private static final int imagerequestcode= 101;
      private DatabaseReference Mref;
      private FirebaseDatabase mfirebasedatabase;
      private FirebaseAuth mauth;
      private StorageReference mfirebasestorage;
      String Current_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
        // for finding ids
        ids();

        mauth=FirebaseAuth.getInstance();
        Current_user=mauth.getCurrentUser().getUid();

        mfirebasedatabase = FirebaseDatabase.getInstance();
         Mref= mfirebasedatabase.getReference().child("Students");
         mfirebasestorage=FirebaseStorage.getInstance().getReference("logoimages/");


    }


// for storing image in filestorage
    private void storeimageinfirestorage(Uri imageuri)
    {
        final  StorageReference filepath=mfirebasestorage.child(Current_user +".jpg");
        filepath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"image uploaded succesfully",LENGTH_SHORT).show();
                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                       profileimageurl= uri.toString();
                        savetodatabase();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


            }

        });


    }

    // for finding ids
    public void ids()
    {
        leadername=findViewById(R.id.leaderedittext);
        groupmembers=findViewById(R.id.groupmemberedittext);
        stallid=findViewById(R.id.stallidedittext);
        stallname=findViewById(R.id.stallnameedittext);
        items=findViewById(R.id.menuitemedittext);
        semester=findViewById(R.id.semesteredittext);
        fieldofstudy=findViewById(R.id.fieldofstudyeditext);
        savebtn=findViewById(R.id.savebutton);
        imagelogo=findViewById(R.id.imageview);
    }

// for proifle creation

    public void  save (View view)
    {


        // for insertion of data

        String leader =leadername.getText().toString();
        String members =groupmembers.getText().toString();
        String Stallid = stallid.getText().toString();

        String Stallname =stallname.getText().toString();
        final String Items =items.getText().toString();
        String Semester = semester.getText().toString();
        String study =fieldofstudy.getText().toString();

     // setting values in real time database

        Mref.child(Current_user).child("LeaderName").setValue(leader);
        Mref.child(Current_user).child("GroupMembers").setValue(members);
        Mref.child(Current_user).child("StallLocationid").setValue(Stallid);
        Mref.child(Current_user).child("StallName").setValue(Stallname);
        Mref.child(Current_user).child("MenuItems").setValue(Items);
        Mref.child(Current_user).child("Semester").setValue(Semester);
        Mref.child(Current_user).child("FieldofStudy").setValue(study);
// when data save in firebase then go back to guesthomescreen
        backtoguestscreen();




    }
// when logoimageview clik open implicit intent
    public void imageclick(View view)
    {
        //for logo image upload


                Intent i =new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,"select an image"),imagerequestcode);

            }


//bitmap for images

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == imagerequestcode && resultCode==RESULT_OK && data!=null)
    {
        Uri imageuri= data.getData();
        try { // this show image in imageview


            Bitmap bitmap = MediaStore.Images.Media.getBitmap((getContentResolver()), imageuri);
            imagelogo.setImageBitmap(bitmap);
        }
        catch (IOException e) {
            e.printStackTrace();

        }
        //passing this picture uri
        storeimageinfirestorage(imageuri);



    }}


    //for cactivity change
    public void backtoguestscreen()
    {
        Intent i = new Intent(Createprofile.this,forguesthomescreen.class);
        startActivity(i);
        finish();
    }

    //saving imageuritodatabase
    public void savetodatabase()
    {

        Mref.child(Current_user).child("logourl").setValue(profileimageurl)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"image uploaded",LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }

}
