package discordlayout.yassinedeveloper.com.discordlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SettingActivity extends AppCompatActivity {
    LinearLayout myAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        myAccount = (LinearLayout) findViewById(R.id.myaccount);
        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, MyAccount.class));
            }
        });

    }
}
