package discordlayout.yassinedeveloper.com.discordlayout;

import android.content.Intent;
import android.database.Cursor;
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

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener {
    Button Loginbutton ;
    TextView ForgotPassword;
    ImageView Backbutton;
    EditText EmailText, PasswordText;

    private SQLiteHandler db;
    HashMap<String, String> user, currentuser;
    Cursor cursor;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // ActionBar actionBar = getSupportActionBar();
       // actionBar.hide();

        Loginbutton = (Button) findViewById(R.id.loginbutton);
        Backbutton = (ImageView) findViewById(R.id.backbutton);
        ForgotPassword = (TextView) findViewById(R.id.forgotpassword);

        EmailText = (EditText) findViewById(R.id.editTextEmail);
        PasswordText = (EditText) findViewById(R.id.editTextPassword);

        db = new SQLiteHandler(getApplicationContext());
        user = db.getUserDetails();
        currentuser =  db.getCurrentUser();
        cursor = db.fetch();

        EmailText.setText(currentuser.get("email"));
        PasswordText.setText(currentuser.get("password"));

        Loginbutton.setOnClickListener(this);
        Backbutton.setOnClickListener(this);
        ForgotPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.loginbutton:
                String Email = EmailText.getText().toString().trim();
                String Password = PasswordText.getText().toString().trim();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(this, "Please enter your Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // see if email and password correct
                boolean exist = false;
try {
    if (cursor != null) {
        // move cursor to first row
        if (cursor.moveToFirst()) {
            do {
                String username_ = cursor.getString(cursor.getColumnIndex("name"));
                String email_ = cursor.getString(cursor.getColumnIndex("email"));
                String password_ = cursor.getString(cursor.getColumnIndex("password"));
                if ((Email.equals(email_) || (Email.equals(username_))) && Password.equals(password_)) {
                    exist = true;
                    username = username_;
                }
                // move to next row
            } while (cursor.moveToNext());
        }
    }

}catch (Exception e){
    Toast.makeText(this,"Erorr : "+ e, Toast.LENGTH_SHORT).show();

}


                if(exist == true){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    db.deleteCurrentUsers();
                    db.addCurrentuser(username,Email,Password);
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                    finish();
                }else{
                    Toast.makeText(this, "Error  Email or Password not correct\nIf you not have account Go to Register Page ", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.backbutton:
                startActivity(new Intent(LoginActivity.this, FrontOfPage.class));
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                break;
            case R.id.forgotpassword:
                String email = EmailText.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(this, "Please enter your Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this," Go to Your inbox email to find a new password ",Toast.LENGTH_SHORT).show();
                //finish();
                break;
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, FrontOfPage.class));
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}