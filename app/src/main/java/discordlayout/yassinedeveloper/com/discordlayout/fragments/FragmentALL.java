package discordlayout.yassinedeveloper.com.discordlayout.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import discordlayout.yassinedeveloper.com.discordlayout.R;
import discordlayout.yassinedeveloper.com.discordlayout.RecyclerviewAdapter;
import discordlayout.yassinedeveloper.com.discordlayout.SQLiteHandler;
import discordlayout.yassinedeveloper.com.discordlayout.UserDetails;

/**
 * Created by Anu on 22/04/17.
 */


public class FragmentALL extends Fragment {


    public FragmentALL() {
        // Required empty public constructor
    }

    RecyclerView recyclerview;
    List<UserDetails> userDetails = new ArrayList<>();

    private SQLiteHandler db;
    HashMap<String, String> user;
    Cursor friend_name;
    HashMap<String, String> getCurrentuser;
    String  username;

    Integer[] imgid={
            R.drawable.aaaaa,
            R.drawable.aasdasdsdasd,
            R.drawable.addfriend,
            R.drawable.addd,
            R.drawable.asdasd,
            R.drawable.asasd,
            R.drawable.discordicon,
            R.drawable.aasdasdsdasd,
            R.drawable.aaaaa,
            R.drawable.aasdasdsdasd,
            R.drawable.addfriend,
            R.drawable.addd,
            R.drawable.asdasd,
            R.drawable.asasd,
            R.drawable.discordicon,
            R.drawable.aaaaa,
            R.drawable.aasdasdsdasd,
            R.drawable.addfriend,
            R.drawable.addd,
            R.drawable.asdasd,
            R.drawable.asasd,
            R.drawable.discordicon,
            R.drawable.aasdasdsdasd,
            R.drawable.aaaaa,
            R.drawable.aasdasdsdasd,
            R.drawable.addfriend,
            R.drawable.addd,
            R.drawable.asdasd,
            R.drawable.asasd,
            R.drawable.discordicon,
            R.drawable.aasdasdsdasd,
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//       return inflater.inflate(R.layout.fragment_all, container, false);

        View rootView = inflater.inflate(R.layout.fragment_all, container, false);


        db = new SQLiteHandler(getContext());
        user = db.getUserDetails();
        getCurrentuser = db.getCurrentUser();
        username = getCurrentuser.get("name");
        Cursor cursor = db.fetch();
        friend_name = db.fetchFriendName();

        recyclerview = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        /*

        for(int i  = 1 ;i < cursor.getColumnCount() ; i++){
            userDetails.add(new UserDetails(cursor.getString(cursor.getColumnIndex("name")) , imgid[i]));
            cursor.moveToNext();
        }
        */

        int i = 0;
        if (friend_name != null) {
            // move cursor to first row
            if (friend_name.moveToFirst()) {
                do {
                    String email = friend_name.getString(friend_name.getColumnIndex("email"));
                    String name = friend_name.getString(friend_name.getColumnIndex("name"));
                    if(email.equals(username)){ // add there friends only
                        userDetails.add(new UserDetails(
                                name,
                                email,
                                imgid[i]));
                        i++;
                    }
                    // move to next row
                } while (friend_name.moveToNext());
            }
        }

        RecyclerviewAdapter recycler = new RecyclerviewAdapter(userDetails);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(recycler);

     /*
        listView = (ListView) rootView.findViewById(R.id.list_view);
        adapter = new SimpleCursorAdapter, R.layout.activity_view_record, cursor , from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
*/
        return rootView;
    }
}