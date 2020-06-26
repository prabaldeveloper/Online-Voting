package com.example.prabalsrivastav.onlinevoting;


import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTopCandidate extends Fragment {
    TextView name ,party ,city ,vote;

    public FragmentTopCandidate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_candidate, container, false);
        Button button = (Button) view.findViewById(R.id.button1);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner2);
        name = (TextView) view.findViewById(R.id.textView2);
        party = (TextView) view.findViewById(R.id.textView3);
        city = (TextView) view.findViewById(R.id.textView5);
        vote = (TextView) view.findViewById(R.id.textView4);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.custom_spinner, getResources().getStringArray(R.array.city));
        arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinner.setAdapter(arrayAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String City = spinner.getSelectedItem().toString();
                Fragment fragment = null;
                if (City.equals("Select City"))
                    Toast.makeText(getActivity(), "Select a City", Toast.LENGTH_SHORT).show();
                else if(City.equals("Allahabad")){
                    name.setVisibility(View.VISIBLE);
                    party.setVisibility(View.VISIBLE);
                    city.setVisibility(View.VISIBLE);
                    vote.setVisibility(View.VISIBLE);
                    fragment = new AllahabadRank();
                    }
                else if(City.equals("Varanasi")) {
                    name.setVisibility(View.VISIBLE);
                    party.setVisibility(View.VISIBLE);
                    city.setVisibility(View.VISIBLE);
                    vote.setVisibility(View.VISIBLE);
                    fragment = new VaranasiRank();
                }
                else if(City.equals("Noida")) {
                    name.setVisibility(View.VISIBLE);
                    party.setVisibility(View.VISIBLE);
                    city.setVisibility(View.VISIBLE);
                    vote.setVisibility(View.VISIBLE);
                    fragment = new NoidaRank();
                }
                else if(City.equals("Kanpur")) {
                    name.setVisibility(View.VISIBLE);
                    party.setVisibility(View.VISIBLE);
                    city.setVisibility(View.VISIBLE);
                    vote.setVisibility(View.VISIBLE);
                    fragment = new KanpurRank();
                }
                else if(City.equals("Lucknow")) {
                    name.setVisibility(View.VISIBLE);
                    party.setVisibility(View.VISIBLE);
                    city.setVisibility(View.VISIBLE);
                    vote.setVisibility(View.VISIBLE);
                    fragment = new LucknowRank();
                }
                if(fragment!=null){
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.displayRank, fragment);
                    transaction.commit();
                }
            }
        });
        return view;
    }
}