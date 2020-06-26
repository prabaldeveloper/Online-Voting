package com.example.prabalsrivastav.onlinevoting;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentParty extends Fragment {

        // WHAT HAD I LEARN
        // HOW TO GO FROM ONE FRAGMENT TO ANOTHER
        // HOW TO OPEN A FRAGMENT FROM A FRAGMENT IN A CUSTOM ADAPTER
        // HOW TO CREATE A CUSTOM LIST VIEW
    //--------------------------------------------------------------
    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentManager manager=getFragmentManager();
        String[] partyName = {"BJP", "INC", "SP", "AAP","BSP"};
        String[] desc = {"Bharatiya Janata Party", "Indian National Congress", "Samajwadi Party", "Aam Aadmi Party","Bahujan Samaj Party"};
        Integer[] imgid = {R.drawable.bjp,R.drawable.inc,R.drawable.sp,R.drawable.aap,R.drawable.bsp};
        String[] button={"Select","Select","Select","Select","Select"};
        View view= inflater.inflate(R.layout.fragment_party, container, false);
        ListView listView = (ListView) view.findViewById(R.id.customView);

        String c = getArguments().getString("city");

        CustomAdapter adapter = new CustomAdapter(getActivity(),partyName,desc,imgid,button,manager,c);       //how to add customadapter in fragment
        listView.setAdapter(adapter);
        return view;
    }
}


class CustomAdapter extends ArrayAdapter<String> {

    private final String[] partyName;
    private final String[] desc;
    private final Integer[] imgid;
    private final String[] button;
    private final Context context;
    private final FragmentManager manager;
    private final String city;

    public CustomAdapter(Context context, String partyName[] , String[] desc,Integer imgid[],String button[],FragmentManager manager,String city) {
        super(context,R.layout.customview_layout,R.id.text,partyName);
        this.context=context;
        this.partyName=partyName;
        this.desc=desc;
        this.imgid=imgid;
        this.button=button;
        this.manager=manager;
        this.city=city;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowview=inflater.inflate(R.layout.customview_layout,null,true);


        ImageView imageView=(ImageView) rowview.findViewById(R.id.imageView);
        TextView shortform= (TextView) rowview.findViewById(R.id.text);
        TextView descp= (TextView) rowview.findViewById(R.id.textView2);
        Button but = (Button) rowview.findViewById(R.id.button);
        // Now get our resources as views

        imageView.setImageResource(imgid[position]);
        shortform.setText(partyName[position]);
        descp.setText(desc[position]);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(city.equals("Allahabad")) {
                    if (position == 0) {
                        Fragment fragment = new Reeta();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 1) {
                        Fragment fragment = new Nandi();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 2) {
                        Fragment fragment = new Rewati();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 3) {
                        Fragment fragment = new Adarsh();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 4) {
                        Fragment fragment = new Keshari();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    }
                }

                 if(city.equals("Varanasi")) {
                    if (position == 0) {
                        Fragment fragment = new Modi();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 1) {
                        Fragment fragment = new Ajay();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 2) {
                        Fragment fragment = new Shalini();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 3) {
                        Fragment fragment = new Arvind();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 4) {
                        Fragment fragment = new Vijay();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    }
                }

                if(city.equals("Noida")) {
                    if (position == 0) {
                        Fragment fragment = new Mahesh();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 1) {
                        Fragment fragment = new Ramesh();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 2) {
                        Fragment fragment = new Bhati();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 3) {
                        Fragment fragment = new Kishan();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 4) {
                        Fragment fragment = new Satveer();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    }
                }

                if(city.equals("Kanpur")) {
                    if (position == 0) {
                        Fragment fragment = new Satyadev();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 1) {
                        Fragment fragment = new Sriprakash();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 2) {
                        Fragment fragment = new Ram();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 3) {
                        Fragment fragment = new Mahmood();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    } else if (position == 4) {
                        Fragment fragment = new Saleem();
                        manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                    }
                }

                  if(city.equals("Lucknow")) {
                      if (position == 0) {
                          Fragment fragment = new Rajnath();
                          manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                      } else if (position == 1) {
                          Fragment fragment = new Pramod();
                          manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                      } else if (position == 2) {
                          Fragment fragment = new Poonam();
                          manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                      } else if (position == 3) {
                          Fragment fragment = new Syed();
                          manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                      } else if (position == 4) {
                          Fragment fragment = new Nakul();
                          manager.beginTransaction().replace(R.id.fragmentPlace, fragment).commit();
                      }
                  }
            }
        });
        return rowview;
    }

}
