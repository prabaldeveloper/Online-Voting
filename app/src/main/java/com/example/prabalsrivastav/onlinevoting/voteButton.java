package com.example.prabalsrivastav.onlinevoting;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
    public class voteButton extends Fragment {
    Button button;
    FirebaseDatabase database;
    DatabaseReference ref;
    long value;
    public voteButton() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.vote_button_layout, container, false);
        final String iD;
        iD = getArguments().getString("id");
        button= (Button) view.findViewById(R.id.button);

        final TextView textView = (TextView) view.findViewById(R.id.textView10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increasing the vote count if the button is pressed
                if(iD.equals("1") || iD.equals("5") || iD.equals("11") || iD.equals("17") || iD.equals("18")) {
                    ref = database.getInstance().getReference();
                    ref.child("Allahabad").child(iD).child("vote").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            value = (long) dataSnapshot.getValue();
                            value--;
                            dataSnapshot.getRef().setValue(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }

                else if(iD.equals("2") || iD.equals("3") || iD.equals("9") || iD.equals("22") || iD.equals("25")) {
                    ref = database.getInstance().getReference();
                    ref.child("Varanasi").child(iD).child("vote").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            value = (long) dataSnapshot.getValue();
                            value--;
                            dataSnapshot.getRef().setValue(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }

                else if(iD.equals("4") || iD.equals("6") || iD.equals("7") || iD.equals("16") || iD.equals("20")) {
                    ref = database.getInstance().getReference();
                    ref.child("Noida").child(iD).child("vote").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            value = (long) dataSnapshot.getValue();
                            value--;
                            dataSnapshot.getRef().setValue(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }

                else if(iD.equals("8") || iD.equals("15") || iD.equals("19") || iD.equals("21") || iD.equals("23")) {
                    ref = database.getInstance().getReference();
                    ref.child("Kanpur").child(iD).child("vote").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            value = (long) dataSnapshot.getValue();
                            value--;
                            dataSnapshot.getRef().setValue(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }

                else if(iD.equals("10") || iD.equals("12") || iD.equals("13") || iD.equals("14") || iD.equals("24")) {
                    ref = database.getInstance().getReference();
                    ref.child("Lucknow").child(iD).child("vote").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            value = (long) dataSnapshot.getValue();
                            value--;
                            dataSnapshot.getRef().setValue(value);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    preferences.edit().putBoolean("isEnabled",false).commit();
                    button.setEnabled(false);

                    textView.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean enabled = preferences.getBoolean("isEnabled",true);
        button.setEnabled(enabled);
    }
}
