package discordlayout.yassinedeveloper.com.discordlayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import discordlayout.yassinedeveloper.com.discordlayout.fragments.FragmentALL;
import discordlayout.yassinedeveloper.com.discordlayout.fragments.FragmentBLOCKED;
import discordlayout.yassinedeveloper.com.discordlayout.fragments.FragmentONLINE;
import discordlayout.yassinedeveloper.com.discordlayout.fragments.FragmentPENDING;
import discordlayout.yassinedeveloper.com.discordlayout.fragments.FragmentSUGGESTIONS;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // ViewPagerAdapter adapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    HashMap<String, String> getCurrentuser;
    //db local
    private SQLiteHandler db;
    HashMap<String, String> user;

    String friendname, username;
    ListView list;


    Integer[] imgid={
            R.drawable.aaaaa,
            R.drawable.aasdasdsdasd,
            R.drawable.addfriend,
            R.drawable.addd,
            R.drawable.asdasd,
            R.drawable.asasd,
            R.drawable.discordicon,
            R.drawable.aasdasdsdasd,
    };
    Cursor usernametable;

    RecyclerView recyclerview ,recyclerView_chat;
    List<UserDetails> userDetails = new ArrayList<>();
    List<ChatDetails> chatDetails = new ArrayList<>();

    //chat layout
    RelativeLayout chatlayout;
    // add server
    ImageView addserver, settingpage ,sendtext;
    TextView userviewname, texttitleviewserver;
    LinearLayout usernameserver;
    // friend layout menu
    LinearLayout friendslayout;
    LinearLayout layoutfriends;
    Button findfriends, buttonserver, logout;
    ImageView listfreinds;
    Cursor cursor_chat;
    Cursor server_name;
    Cursor friend_name;
    EditText textchatsend ;


    String ServerName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new SQLiteHandler(getApplicationContext());
        user = db.getUserDetails();

        getCurrentuser = db.getCurrentUser();
        friend_name = db.fetchFriendName();

        username = getCurrentuser.get("name");


        Cursor cursor = db.fetch();
        cursor_chat = db.fetchMessage();
        server_name = db.fetchServerName();

        recyclerView_chat = (RecyclerView) findViewById(R.id.recycler_view_chat);
        recyclerview = (RecyclerView) findViewById(R.id.recycler_view);



        addserver = (ImageView) findViewById(R.id.addserver);
        settingpage = (ImageView) findViewById(R.id.setting_activity);
        userviewname = (TextView) findViewById(R.id.userviewname);
        texttitleviewserver = (TextView) findViewById(R.id.texttitleviewserver);
        textchatsend = (EditText) findViewById(R.id.textchatsend);
        chatlayout = (RelativeLayout) findViewById(R.id.chatlayout);
        // friend layout menu
        friendslayout = (LinearLayout) findViewById(R.id.friendslayout);
        layoutfriends = (LinearLayout) findViewById(R.id.layoutfriends);
        usernameserver = (LinearLayout) findViewById(R.id.usernameserver);
        listfreinds = (ImageView) findViewById(R.id.listfreinds);
        findfriends = (Button) findViewById(R.id.findfriends);
        buttonserver = (Button) findViewById(R.id.buttonserver);
        logout = (Button) findViewById(R.id.logout);

        sendtext = (ImageView) findViewById(R.id.sendtext);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentALL(), "ALL");
        adapter.addFragment(new FragmentONLINE(), "ONLINE");
        adapter.addFragment(new FragmentPENDING(), "PENDING");
        adapter.addFragment(new FragmentSUGGESTIONS() , "SUGGESTIONS");
        adapter.addFragment(new FragmentBLOCKED(), "BLOCKED");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

   //     String test = "stackOverflow".toUpperCase();
   //     char first = test.charAt(0);
   //     Toast.makeText(MainActivity.this,"First : "+first ,Toast.LENGTH_SHORT).show();
    //    Toast.makeText(MainActivity.this,"Server count  : "+server_name.getCount(),Toast.LENGTH_SHORT).show();

       userviewname.setText(getCurrentuser.get("email").toString());

        try {

        int s = 0;
        if (server_name != null) {
            if (server_name.moveToFirst()) {
                do {
                    //String name = server_name.getString(server_name.getColumnIndex("name"));
                    String email = server_name.getString(server_name.getColumnIndex("email"));
                    if (email.equals(username)) {
                        s++;
                    }
                } while (server_name.moveToNext());
            }
        }

        list = (ListView) findViewById(R.id.listserver);

            final String itemname[] = new String[s];
            //final String items[] = new String[server_name.getCount()];
            final String itemservername[] = new String[s];
            int b = 0;
            if (server_name != null) {
                if (server_name.moveToFirst()) {
                    do {
                        String name = server_name.getString(server_name.getColumnIndex("name"));
                        String email = server_name.getString(server_name.getColumnIndex("email"));
                        if (email.equals(username)) {
                            itemservername[b] = name;
                            String test = name;//.toUpperCase();
                            char first = test.toUpperCase().charAt(0);
                            itemname[b] = "" + first;//String.valueOf(first)    name.toUpperCase().toCharArray()[0];
                            b++;
                        }
                    } while (server_name.moveToNext());
                }


                CustomListAdapter adapter2 = new CustomListAdapter(this, itemname);
                list.setAdapter(adapter2);
            }


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                    String Slecteditem = itemservername[+position];
                    ServerName = Slecteditem;
                    texttitleviewserver.setText(Slecteditem);
                    buttonserver.setText(Slecteditem);
                    //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                    layoutfriends.setVisibility(View.GONE);
                    usernameserver.setVisibility(View.VISIBLE);
                }
            });

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Erorr : "+e, Toast.LENGTH_SHORT).show();
                }

            buttonserver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTitle(ServerName);
                    tabLayout.setVisibility(View.GONE);
                    viewPager.setVisibility(View.GONE);
                    chatlayout.setVisibility(View.VISIBLE);
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    startActivity(new Intent(MainActivity.this,MainActivity.class)
                            .putExtra("friendname",ServerName));
                    finish();

                }
            });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteCurrentUsers();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
        findfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
                chatlayout.setVisibility(View.GONE);
                //  chatlayoutserver.setVisibility(View.GONE);
                 DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
            addserver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(MainActivity.this,AddFriend.class));
                    Intent i = new Intent(MainActivity.this, AddFriend.class);
                    i.putExtra("add", "server");
                    startActivity(i);
                    finish();
                }
            });
            // list friends listviewfriends
            settingpage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, MyAccount.class));
                }
            });

            //cursor_servers = db.fetchServerName();

        Intent intent = getIntent();
        friendname = intent.getStringExtra("friendname");
     //   friendemail = intent.getStringExtra("friendemail");
        ServerName = friendname;
    //    Toast.makeText(getApplicationContext(),"Username : "+ username+"\nFriendname : "+friendname, Toast.LENGTH_SHORT).show();


        if (friendname != null) {
            setTitle(friendname);
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            chatlayout.setVisibility(View.VISIBLE);
            //  chatlayoutserver.setVisibility(View.GONE);
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            // chat friends

            boolean exist = false;
            if (server_name != null) {
                if (server_name.moveToFirst()) {
                    do {
                        String name = server_name.getString(server_name.getColumnIndex("name")); // name server
                        String email = server_name.getString(server_name.getColumnIndex("email")); // username
                        if (friendname.equals(name)) {
                            exist = true;
                        }
                    } while (server_name.moveToNext());
                }
            }


            if (cursor_chat != null) {
                if (cursor_chat.moveToFirst()) {
                    do {
                        String fromemail = cursor_chat.getString(cursor_chat.getColumnIndex("from_email"));
                        String toemail = cursor_chat.getString(cursor_chat.getColumnIndex("to_email"));
                        String message = cursor_chat.getString(cursor_chat.getColumnIndex("message"));

                        if(exist){
                           if(toemail.equals(friendname)){
                               chatDetails.add(new ChatDetails(fromemail, toemail, message));
                           }
                       }else {
                           if ((fromemail.equals(username) && toemail.equals(friendname)) || (fromemail.equals(friendname) && toemail.equals(username))) {
                               chatDetails.add(new ChatDetails(fromemail, toemail, message));
                           }
                       }

                    } while (cursor_chat.moveToNext());
                }
            }

            textchatsend.setHint("message to " + friendname);

            sendtext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String msg = textchatsend.getText().toString();
                    db.SendMessage(username, friendname, msg);
                    textchatsend.setText("");
                    chatDetails.add(new ChatDetails(username, friendname, msg));
                }
            });
        }
                RecyclerviewAdapterChat recycler2 = new RecyclerviewAdapterChat(chatDetails);
                RecyclerView.LayoutManager layoutmanager2 = new LinearLayoutManager(this);
                recyclerView_chat.setLayoutManager(layoutmanager2);
                recyclerView_chat.setItemAnimator(new DefaultItemAnimator());
                recyclerView_chat.setAdapter(recycler2);


            listfreinds.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layoutfriends.setVisibility(View.VISIBLE);
                    usernameserver.setVisibility(View.GONE);
                }
            });


        // fetch frinds name
        int i = 0;
        if (friend_name != null) {
            // move cursor to first row
            if (friend_name.moveToFirst()) {
                do {
                    String email = friend_name.getString(friend_name.getColumnIndex("email"));
                    String name = friend_name.getString(friend_name.getColumnIndex("name"));
                    if(email.equals(getCurrentuser.get("email"))){ // add there friends only
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
            RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(this);
            recyclerview.setLayoutManager(layoutmanager);
            recyclerview.setItemAnimator(new DefaultItemAnimator());
            recyclerview.setAdapter(recycler);


        }


    // Adapter for the viewpager using FragmentPagerAdapter
        class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.addfriend:
                startActivity(new Intent(this,AddFriend.class).putExtra("add","friend"));
                break;

        }
        return super.onOptionsItemSelected(item);


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
          /*  case R.id.:
                startActivity(new Intent(this,AddFriend.class));
                break;
           case R.id.addgroup:
                   startActivity(new Intent(this,AddGroup.class));
                  break;
*/
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
