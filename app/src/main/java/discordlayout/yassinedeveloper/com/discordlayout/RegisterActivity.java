package discordlayout.yassinedeveloper.com.discordlayout;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by root on 12/30/17.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    Button Registerbtn;
    ImageView backbtn;
    TextView PrivacyTerms;
    EditText EmailText, PasswordText, UsernameText;
    private SQLiteHandler db;
    HashMap<String, String> user;
    Cursor cursor;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


      Registerbtn = (Button) findViewById(R.id.registerbtn);
      backbtn = (ImageView) findViewById(R.id.backbtn);
      PrivacyTerms = (TextView) findViewById(R.id.privacyterms);

        EmailText = (EditText) findViewById(R.id.editTextEmail);
        PasswordText = (EditText) findViewById(R.id.editTextPassword);
        UsernameText = (EditText) findViewById(R.id.editTextUsername);

        db = new SQLiteHandler(getApplicationContext());

        user = db.getUserDetails();
        cursor = db.fetch();


        Registerbtn.setOnClickListener(this);
        backbtn.setOnClickListener(this);
        PrivacyTerms.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.registerbtn:
                String email = EmailText.getText().toString();
                String password = PasswordText.getText().toString();
                String username = UsernameText.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(this, "Please enter your Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "Please enter your Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(this, "Please enter your Username", Toast.LENGTH_SHORT).show();
                    return;
                }

                // see if email and password exist
                boolean exist = false;
                try {
                    if (cursor != null) {
                        // move cursor to first row
                        if (cursor.moveToFirst()) {
                            do {
                                String username_ = cursor.getString(cursor.getColumnIndex("name"));
                                String email_ = cursor.getString(cursor.getColumnIndex("email"));
                                String password_ = cursor.getString(cursor.getColumnIndex("password"));
                                if ( (email.equals(email_) ||email.equals(username_)  )&& username.equals(username_)&& password.equals(password_)) {
                                    exist = true;
                                }
                                // move to next row
                            } while (cursor.moveToNext());
                        }
                    }

                }catch (Exception e){
                    Toast.makeText(this,"Erorr : "+ e, Toast.LENGTH_SHORT).show();

                }

                if(!exist){

                    db.deleteCurrentUsers();
                    db.addCurrentuser(username,email,password);
                    db.addUser(username, email, password);
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();

                }else{
                    Toast.makeText(this, "this is account was signed up \nonly  go to login", Toast.LENGTH_SHORT).show();
                    return;
                }





                break;
            case R.id.backbtn:
                startActivity(new Intent(RegisterActivity.this, FrontOfPage.class));
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                break;
           case R.id.privacyterms:
               Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://gmail.com"));
               startActivity(i);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RegisterActivity.this, FrontOfPage.class));
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

    }
}
