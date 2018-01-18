package discordlayout.yassinedeveloper.com.discordlayout.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import discordlayout.yassinedeveloper.com.discordlayout.R;

/**
 * Created by Anu on 22/04/17.
 */



public class FragmentONLINE extends Fragment {

    public FragmentONLINE() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_online, container, false);
        /*
        View rootView = inflater.inflate(R.layout.fragment_online, container, false);
        RelativeLayout btn = (RelativeLayout) rootView.findViewById(R.id.usersonile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                i.putExtra("name","chat");
                startActivity(i);
            }
        });
        return rootView;
*/
    }

}