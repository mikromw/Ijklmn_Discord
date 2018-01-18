package discordlayout.yassinedeveloper.com.discordlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MyAccount extends AppCompatActivity {

    EditText Email, Username, Password;
    Button Done;
    private SQLiteHandler db;
    HashMap<String, String> user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        db = new SQLiteHandler(getApplicationContext());
        user = db.getUserDetails();

        Username = (EditText) findViewById(R.id.username);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);

        Done = (Button) findViewById(R.id.done);


        Username.setText(user.get("name"));
        Email.setText(user.get("email"));
        Password.setText(user.get("password"));

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String username = Username.getText().toString();
                db.update("1", username, email, password);

                Toast.makeText(getApplicationContext(), " User data upload ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
