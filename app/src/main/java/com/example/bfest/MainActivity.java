package com.example.bfest;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    EditText emailtxt,passwordtxt;
    TextView signuptxv;
    Button loginbtn;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailtxt=findViewById(R.id.studentid);
        passwordtxt=findViewById(R.id.password);
        signuptxv=findViewById(R.id.signuptext);
        loginbtn=findViewById(R.id.Loginbutton);

        mAuth=FirebaseAuth.getInstance();


    }

    // for signup activity
    public void signup(View view)
    {
        Intent intent = new Intent(this,signup.class);
        startActivity(intent);
    }


    // for login
    public void Login(View view)
    {




            if(!Validateemail() | !Validatepassword()) {

                return;
            }
            else {

                String email = emailtxt.getText().toString();
                String password = passwordtxt.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {


                                    Toast.makeText(getApplicationContext(), "welcome to Bfest", LENGTH_SHORT).show();
                                    sendtohomeactivity();
                                    updateUI();

                                } else {
                                    Toast.makeText(getApplicationContext(), "please enter correct email or password", LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI();
    }




    public void updateUI()
        {
            FirebaseUser user = mAuth.getCurrentUser();

            if(user==null)
            {
                return;

            }
                else
            {
            }

        }


    // going to main activity

        public void sendtohomeactivity()
        {


            Intent i = new Intent(getApplicationContext(),forguesthomescreen.class);
            startActivity(i);

        }

        // validation of email
        public Boolean Validateemail()
        {
            String email = emailtxt.getEditableText().toString().trim();
            if(email==null)
            {
                emailtxt.setError("email is required");
                return false;
            }
            else if( !Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                emailtxt.setError("email not matched : email is invalid" );
                return false;
            }
            else
            {
                emailtxt.setError(null);
                return  true;
            }


        }

        // validation of password

        public   Boolean Validatepassword()
        {
            String  password = passwordtxt.getEditableText().toString().trim();
            if(password.isEmpty())
            {
                passwordtxt.setError("password is required");
                return false;
            }
            else if( password.length()< 6)
            {
                passwordtxt.setError("password must be greater then six character" );
                return false;
            }
            else
            {
                passwordtxt.setError(null);
                return  true;
            }



        }





    }




