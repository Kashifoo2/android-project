package com.example.bfest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.widget.Toast.LENGTH_SHORT;

public class guestsignup extends AppCompatActivity {

    EditText emailtxt,passswordtxt;
    Button savebtn;
    private   FirebaseAuth mAuth;
    String TAG="MyTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestsignup);

        emailtxt=findViewById(R.id.email);
        passswordtxt=findViewById(R.id.password);
        savebtn=findViewById(R.id.savebutton);

        mAuth=FirebaseAuth.getInstance();

    }
// for guest signup
    public void Save (View v)
    {

        if(!Validateemail() | !Validatepassword()) {

        return;
        }
        else {

            String email = emailtxt.getText().toString();
            String password = passswordtxt.getText().toString();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Log.d(TAG, "onComplete: ");
                                Toast.makeText(getApplicationContext(), "please now sign in", LENGTH_SHORT).show();
                   //when task is succesful
                                sendtomainactivity();

                            } else {
                                Toast.makeText(getApplicationContext(), "Error is occured", LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }

// going to main activity

    public void sendtomainactivity()
    {

        Log.d(TAG, "sendtomainactivity: ");
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
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
        String  password = passswordtxt.getEditableText().toString().trim();
        if(password.isEmpty())
        {
            passswordtxt.setError("password is required");
            return false;
        }
        else if( password.length()< 6)
        {
            passswordtxt.setError("password must be greater then six character" );
            return false;
        }
        else
        {
            passswordtxt.setError(null);
            return  true;
        }



    }
}
