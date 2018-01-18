package discordlayout.yassinedeveloper.com.discordlayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddFriend extends AppCompatActivity {


    HashMap<String, String> getCurrentuser;
    Cursor server_name;
    Cursor usernametable;
    Cursor friend_name;
    String useremail , username;

    //db local
    private SQLiteHandler db;
    HashMap<String, String> user;
    String addContent;
    EditText EText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        // get action bar
        //ActionBar actionBar = getActionBar();
        // Enabling Up / Back navigation
        // actionBar.setDisplayHomeAsUpEnabled(true);

        db = new SQLiteHandler(getApplicationContext());
        user = db.getUserDetails();
        getCurrentuser = db.getCurrentUser();
        server_name = db.fetchServerName();
        friend_name = db.fetchFriendName();
        usernametable = db.fetch();
       // useremail = getCurrentuser.get("email");
        username = getCurrentuser.get("name");

        Intent intent = getIntent();
        addContent = intent.getStringExtra("add");
        setTitle("add " + addContent);

        EText = (EditText) findViewById(R.id.add_friend_text);
        //    if(addContent.equals("server")) {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.addfriend_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.send:

                String textdata = EText.getText().toString().trim();
                if (TextUtils.isEmpty(textdata)) {
                    Toast.makeText(AddFriend.this, "Discord username or email is required", Toast.LENGTH_SHORT).show();
                }else {
                    if (addContent.equals("server")) {
                        boolean exist = false;
                        if (server_name != null) {
                            if (server_name.moveToFirst()) {
                                do {
                                    String name = server_name.getString(server_name.getColumnIndex("name")); // name server
                                    String email = server_name.getString(server_name.getColumnIndex("email")); // username
                                    if (textdata.equals(name) && email.equals(username)) {
                                        exist = true;
                                    }
                                } while (server_name.moveToNext());
                            }
                        }
                        if (!exist){
                            db.addservername(textdata,username);
                            Toast.makeText(AddFriend.this, "Server " + textdata + " Added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddFriend.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(AddFriend.this, "Server " + textdata + " already exist", Toast.LENGTH_SHORT).show();
                        }

                    } else if (addContent.equals("friend")) {

                        // check if username or email   signed up
                        //if(textdata.equals(useremail) || textdata.equals(username)){
                        if(textdata.equals(username)){
                            Toast.makeText(AddFriend.this, "You can't add you name like a friend", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        boolean exist1 = false;
                        if (usernametable != null) {
                            if (usernametable.moveToFirst()) {
                                do {
                                    String name = usernametable.getString(usernametable.getColumnIndex("name"));
                                    String email = usernametable.getString(usernametable.getColumnIndex("email"));
                                    if (textdata.equals(name) ){// || textdata.equals(email)) {
                                        exist1 = true;
                                    }
                                } while (usernametable.moveToNext());
                            }
                        }
                        if (exist1) {
                            // if signed up
                            // check if username  a friends
                            boolean exist2 = false;
                            if (friend_name != null) {
                                if (friend_name.moveToFirst()) {
                                    do {
                                        String name = friend_name.getString(friend_name.getColumnIndex("name"));
                                        String email = friend_name.getString(friend_name.getColumnIndex("email"));
                                        if((textdata.equals(name)  && email.equals(username))) {
                                            exist2 = true;
                                        }
                                    } while (friend_name.moveToNext());
                                }
                            }
                            if (!exist2) {
/*
                                boolean exist_server = false;
                                if (server_name != null) {
                                    if (server_name.moveToFirst()) {
                                        do {
                                            String name = server_name.getString(server_name.getColumnIndex("name"));
                                            String email = server_name.getString(server_name.getColumnIndex("email"));
                                            if(email.equals(textdata)){
                                                db.addservername(name, textdata);
                                            }

                                        } while (server_name.moveToNext());
                                    }
                                }
*/
                               // if  (exist_server) {db.addservername(textdata, useremail);}
                                db.addfriendname(textdata, username);
                                db.addfriendname(username, textdata);
                                Toast.makeText(AddFriend.this, "Friend  " + textdata + " Added successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddFriend.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(AddFriend.this, "Friend " + textdata + " already exist", Toast.LENGTH_SHORT).show();
                            }

                           // db.addfriendname(textdata, useremail);
                           // Toast.makeText(AddFriend.this, "Friend name " + textdata + " Added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddFriend.this, "Friend " + textdata + " not exist !", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                    break;
                }
                return super.onOptionsItemSelected(item);

        }
    }
