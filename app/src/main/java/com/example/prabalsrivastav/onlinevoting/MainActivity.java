package com.example.prabalsrivastav.onlinevoting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;

    EditText editText1 ,editText2;
    Button button1,button2;
    ImageView showPassword;
    int passwordNotVisible=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Login");
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 =(EditText) findViewById(R.id.editText2);
        button1 = (Button) findViewById(R.id.button);
        button2=(Button) findViewById(R.id.button2);

        showPassword = (ImageView) findViewById(R.id.imageView3);
        //Displaying the content of password field on click
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordNotVisible == 1) {
                    editText2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {
                    editText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }
                editText2.setSelection(editText2.length());
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            final String login = editText1.getText().toString();
            final String psw = editText2.getText().toString();
            if(login.equals(""))
                editText1.setError("Login Id Can't Be Empty");
            else if(psw.equals(""))
                editText2.setError("Password Can't Be Empty");
            else {
                String logPass = login + psw;
                ref = database.getInstance().getReference();
                ref.child("Details").orderByChild("logpass").equalTo(logPass).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            // Login ID and Password match
                            Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, HowAppWorks.class);
                            intent.putExtra("Login",login);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Either Login Id or Password is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
            }
        });
       button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,RegisterForm.class);
            startActivity(intent);
            }
        });
    }
}

