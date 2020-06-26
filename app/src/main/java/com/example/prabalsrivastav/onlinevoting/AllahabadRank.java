package com.example.prabalsrivastav.onlinevoting;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllahabadRank extends Fragment {
    //DatabaseHelper var1;
    ListviewAdapter listAdapter;
    FirebaseDatabase database;
    DatabaseReference reference;

    public AllahabadRank() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_allahabad_rank, container, false);
        // Displaying the ranking of allahabad candidates on the basis of votes in a listView
        ListView listView = (ListView) view.findViewById(R.id.listView1);
        listAdapter = new ListviewAdapter(getActivity(),R.layout.customize_sqlite_data);
        listView.setAdapter(listAdapter);
        String City = "Allahabad";

        reference = database.getInstance().getReference();
        reference.child(City).orderByChild("vote").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds :dataSnapshot.getChildren()) {
                    String name = ds.child("name").getValue().toString();
                    String party = ds.child("party").getValue().toString();
                    String city = ds.child("city").getValue().toString();
                    String bote = ds.child("vote").getValue().toString();
                    int convert = Integer.parseInt(bote);
                    convert = - convert;
                    String vote = String.valueOf(convert);
                    sqliteData sqlitedata = new sqliteData(name,party,city,vote);
                    listAdapter.add(sqlitedata);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
