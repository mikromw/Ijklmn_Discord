package discordlayout.yassinedeveloper.com.discordlayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

public class SplashActivity extends AppCompatActivity {
    MarshMallowPermission marshMallowPermission = new MarshMallowPermission(this);

    private SQLiteHandler db;
    HashMap<String, String> currentuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 23){
            if(!marshMallowPermission.checkPermissionForStorage()){
                marshMallowPermission.requestPermissionForStorage();
            }

        }
        db = new SQLiteHandler(getApplicationContext());
        currentuser =  db.getCurrentUser();

        if(currentuser.get("email") == null){
            startActivity(new Intent(SplashActivity.this, FrontOfPage.class));
            finish();
        }else{
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }

   //     startActivity(new Intent(SplashActivity.this, FrontOfPage.class));

        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

    }
}
