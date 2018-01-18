package discordlayout.yassinedeveloper.com.discordlayout;

/**
 * Created by root on 1/6/18.
 */

public class ChatDetails {

    String From ;
    String To;
    String Message;

    public ChatDetails(String from, String to, String message) {
        From = from;
        To = to;
        Message = message;
    }

    public String getFromEmail(){
        return From;
    }
    public String getToEmail(){
        return To;
    }
    public String getMessage(){
        return Message;
    }


}
