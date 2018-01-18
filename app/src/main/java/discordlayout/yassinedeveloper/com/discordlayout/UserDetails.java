package discordlayout.yassinedeveloper.com.discordlayout;

import android.support.annotation.NonNull;

/**
 * Created by root on 1/6/18.
 */

public class UserDetails {

    private Integer mIcon;
    private String mUserName;
    String Email;

    public UserDetails(@NonNull String username,String email, @NonNull Integer icon) {
        mUserName = username;
        mIcon = icon;
        Email = email;

    }

    public String getUserName() {
        return mUserName;
    }
    public Integer getIcon() {
        return mIcon;
    }
    String getEmail(){return Email;}
}
