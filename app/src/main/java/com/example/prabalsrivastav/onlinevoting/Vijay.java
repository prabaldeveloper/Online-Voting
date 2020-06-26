package com.example.prabalsrivastav.onlinevoting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Vijay extends Fragment {

    public Vijay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.vijay_layout, container, false);
        final TextView textView = (TextView) view.findViewById(R.id.textView10);
        voteButton frag = new voteButton();
        Bundle bundle = new Bundle();
        bundle.putString("id","25");
        frag.setArguments(bundle);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.voteButton, frag);
        transaction.commit();
        return view;
    }

}
