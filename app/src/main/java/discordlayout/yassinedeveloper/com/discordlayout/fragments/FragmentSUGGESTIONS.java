package discordlayout.yassinedeveloper.com.discordlayout.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import discordlayout.yassinedeveloper.com.discordlayout.R;

/**
 * Created by Anu on 22/04/17.
 */



public class FragmentSUGGESTIONS extends android.support.v4.app.Fragment {

    public FragmentSUGGESTIONS() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suggestions, container, false);
        /*
        View rootView = inflater.inflate(R.layout.fragment_suggestions, container, false);
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