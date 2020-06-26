package com.example.prabalsrivastav.onlinevoting;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterForm extends AppCompatActivity {

    // Firebase Database
    FirebaseDatabase database;
    DatabaseReference reference ,ref ,ref2;

    EditText name, age, loginId, password, confirmpassword, aCardNo;
    TextView showError;
    Button button;
    Spinner spinner;
    ImageView showPassword1,showPassword2;
    int passwordNotVisible1=1,passwordNotVisible2=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        getSupportActionBar().setTitle("Registration Form");

        name = (EditText) findViewById(R.id.editText1);
        age = (EditText) findViewById(R.id.editText2);
        loginId = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);
        confirmpassword = (EditText) findViewById(R.id.editText5);
        aCardNo = (EditText) findViewById(R.id.editText6);
        button = (Button) findViewById(R.id.button);
        showError = (TextView) findViewById(R.id.textView2);
        spinner = (Spinner) findViewById(R.id.spinner);

        showPassword1 = (ImageView) findViewById(R.id.imageView5);
        showPassword2 = (ImageView) findViewById(R.id.imageView6);
        // Displaying the password of password field
        showPassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordNotVisible1 == 1) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible1 = 0;
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible1 = 1;
                }
                password.setSelection(password.length());
            }
        });
        // Displaying the password of confirm password field
        showPassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordNotVisible2 == 1) {
                    confirmpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible2 = 0;
                } else {
                    confirmpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible2 = 1;
                }
                confirmpassword.setSelection(confirmpassword.length());
            }
        });

        //------------------------------------------- setting fragment so that data can be sent(note that we are not replacing activity with fragment)

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.custom_spinner, getResources().getStringArray(R.array.city));
        arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinner.setAdapter(arrayAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameverification() && ageVerfication() && loginVerfication() && passVerfication() && aadharVerfication() && cityVerfication()) {
                    // Function to insert details of a person in firebase database
                    final String Name     = name.getText().toString();
                    final String Age      = age.getText().toString();
                    final String Login    = loginId.getText().toString();
                    final String Password = password.getText().toString();
                    final String ACardNo  = aCardNo.getText().toString();
                    final String City     = spinner.getSelectedItem().toString();
                    final String logpass  = Login + Password;
                    // Checking whether Login ID and Aadhar Card Number Already Exists or not
                    ref = FirebaseDatabase.getInstance().getReference();
                    ref.child("Details").orderByChild("loginId").equalTo(Login).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // User Exists
                                // Do your stuff here if user already exists
                                loginId.setError("Login ID Already Exists");
                            } else {
                                ref2 = FirebaseDatabase.getInstance().getReference();
                                ref2.child("Details").orderByChild("aCardNo").equalTo(ACardNo).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            // User Exists
                                            // Do your stuff here if user already exists
                                            aCardNo.setError("Aadhar Number Already Exists");
                                        } else {
                                            //User Does Not Exists
                                            NewMember member = new NewMember(Name, Age, Login, Password, ACardNo, City, logpass);
                                            reference = database.getInstance().getReference().child("Details");  // Details is basically table name
                                            reference.child(Login).setValue(member);
                                            Toast.makeText(RegisterForm.this, "Submited Sucessfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(RegisterForm.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            public boolean nameverification() {
                // FOR NAME
                String val = name.getText().toString().trim();
                String checkName = "(?=.*[a-zA-Z])" +
                        ".{5,20}";
                if (val.isEmpty()) {
                    name.setError("Field cannot be empty");
                    return false;
                } else if (!val.matches(checkName)) {
                    name.setError("Please Enter a Valid Name");
                    return false;
                } else
                    return true;
            }

            public boolean ageVerfication() {
                // FOR AGE
                String ageCheck = age.getText().toString();
                if (TextUtils.isEmpty(ageCheck)) {
                    age.setError("Please Enter Valid Age");
                    return false;
                }
                int ageValid = Integer.valueOf(ageCheck);
                if (ageValid > 150) {
                    age.setError("Please Enter Valid Age");
                    return false;
                } else if (ageValid < 18) {
                    age.setError("You Are Not Eligible to Vote");
                    return false;
                } else
                    return true;
            }

            public boolean loginVerfication() {
                // FOR LOGIN ID
                String loginCheck = loginId.getText().toString().trim();
                if (TextUtils.isEmpty(loginCheck)) {
                    loginId.setError("Please Enter Valid Login ID");
                    return false;
                }
                if (loginCheck.length() != 6) {
                    loginId.setError("login id must be of six digits");
                    return false;
                } else
                    return true;
            }

            public boolean passVerfication() {
                // FOR PASSWORD
                String checkPassword = password.getText().toString().trim();
                String regexPassword = "" +
                        "(?=.*[a-zA-Z])" + // any letter
                        //"(?=.*[0-9])"      + // atleast one number
                        //"(?=.*[a-z])"      + // atleast one lowercase letter
                        //"(?=.*[A-Z])"      + // atleast one uppercase letter
                        //"(?=\\S+$)"        + // no white spaces
                        //"(?=.*[@#$%^&+=])" +  //atleast one special character
                        ".{6,15}";   // atleast 8 characters  ".{8,20};
                if (checkPassword.isEmpty()) {
                    password.setError("Field cannot be empty");
                    return false;
                } else if (!checkPassword.matches(regexPassword)) {
                    password.setError("Password Should Contain atleast 6 chacraters");
                    return false;
                } else {
                    if (confirmVerification(checkPassword))
                        return true;
                    else
                        return false;
                }
            }

            public boolean confirmVerification(String checkPassword) {
                // FOR CONFIRM PASSWORD
                String conPass = confirmpassword.getText().toString();
                if (!checkPassword.equals(conPass)) {
                    confirmpassword.setError("Password does not match");
                    return false;
                } else
                    return true;
            }

            public boolean aadharVerfication() {
                // FOR AADAHAR CARD NUMBER
                String aCardN = aCardNo.getText().toString();
                if (aCardN.length() != 12) {
                    aCardNo.setError("Aadhar Number must be of 12 digits");
                    return false;
                } else
                    return true;
            }

            public boolean cityVerfication() {
                // FOR SELECT CITY(SPINNER)
                String spinnerText = spinner.getSelectedItem().toString();
                if (spinnerText.equals("Select City")) {
                    showError.setText("Please Select a City");
                    return false;
                } else {
                    showError.setVisibility(View.INVISIBLE);
                    return true;
                }
            }
        });
    }
}

