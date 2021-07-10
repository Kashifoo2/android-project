package com.example.bfest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {


    Button guestbtn,studentbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        guestbtn=findViewById(R.id.guest);
        studentbtn=findViewById(R.id.Student);

    }

    public  void Student(View v)
    {
        Intent i =new Intent(this,guestsignup.class);
        startActivity(i);

    }

}
