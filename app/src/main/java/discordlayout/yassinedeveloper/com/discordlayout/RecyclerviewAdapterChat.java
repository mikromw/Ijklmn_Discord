package discordlayout.yassinedeveloper.com.discordlayout;

/**
 * Created by root on 10/19/17.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by csa on 3/6/2017.
 */



public class RecyclerviewAdapterChat extends RecyclerView.Adapter<RecyclerviewAdapterChat.MyHolder>{


    List<ChatDetails>  chatDetails;
    private SQLiteHandler db;
    HashMap<String, String> user, currentuser;
    //Cursor cursor;
    String username;


    public RecyclerviewAdapterChat(List<ChatDetails> chatDetails) {
        this.chatDetails = chatDetails;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_layout_chat, parent, false);

    MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }
    public void onBindViewHolder(MyHolder holder, int position) {



        ChatDetails data = chatDetails.get(position);
       // holder.icon.setImageResource(data.getIcon());

        if(data.getFromEmail().equals(currentuser.get("email"))){
            holder.icon.setImageResource(R.drawable.addd);
            holder.TextFrom.setText(data.getFromEmail());
        }else{
            holder.icon.setImageResource(R.drawable.aasdasdsdasd);
            holder.TextFrom.setText(data.getFromEmail());
        }

        holder.TextTo.setText(data.getMessage());



    }

    @Override
    public int getItemCount() {
        return chatDetails.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView TextFrom;
        TextView TextTo;
        private final Context context;


        public MyHolder(final View itemView) {

            super(itemView);

            context = itemView.getContext();
            final Intent[] intent = new Intent[1];

            db = new SQLiteHandler(context.getApplicationContext());
            user = db.getUserDetails();
            currentuser =  db.getCurrentUser();


            icon = (ImageView) itemView.findViewById(R.id.icon);
            TextTo = (TextView) itemView.findViewById(R.id.textmessage);
            TextFrom = (TextView) itemView.findViewById(R.id.textfrom);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                /*
                    String un = userDetails.get(getAdapterPosition()).getUserName();
                    String EMail = userDetails.get(getAdapterPosition()).getEmail();

                    intent[0] = new Intent(context, MainActivity.class);
                    intent[0].putExtra("friendname", un);
                    intent[0].putExtra("friendemail",EMail);
                    context.startActivity(intent[0]);
*/

                }
            });
        }
    }
}