package com.example.prabalsrivastav.onlinevoting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LucknowCandidate extends Fragment {


    public LucknowCandidate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.lucknow_layout, container, false);
        Button button1 =(Button) view.findViewById(R.id.button1);
        Button button2 =(Button) view.findViewById(R.id.button2);
        Button button3 =(Button) view.findViewById(R.id.button3);
        Button button4 =(Button) view.findViewById(R.id.button4);
        Button button5 =(Button) view.findViewById(R.id.button5);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Rajnath();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragmentPlace,fragment).commit();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Pramod();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragmentPlace,fragment).commit();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Poonam();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragmentPlace,fragment).commit();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Syed();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragmentPlace,fragment).commit();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Nakul();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragmentPlace,fragment).commit();
            }
        });
        return view;
    }
}
