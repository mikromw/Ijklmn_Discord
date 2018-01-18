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

import java.util.List;

/**
 * Created by csa on 3/6/2017.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyHolder>{


    List<UserDetails>  userDetails;


    public RecyclerviewAdapter(List<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_layout, parent, false);

    MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }
    public void onBindViewHolder(MyHolder holder, int position) {
        UserDetails data = userDetails.get(position);
        holder.icon.setImageResource(data.getIcon());
        holder.UserName.setText(data.getUserName());
    }

    @Override
    public int getItemCount() {
        return userDetails.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView UserName;
        private final Context context;

        public MyHolder(final View itemView) {

            super(itemView);

            context = itemView.getContext();
            final Intent[] intent = new Intent[1];

            icon = (ImageView) itemView.findViewById(R.id.icon);
            UserName = (TextView) itemView.findViewById(R.id.username);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String un = userDetails.get(getAdapterPosition()).getUserName();
                    String EMail = userDetails.get(getAdapterPosition()).getEmail();

                    intent[0] = new Intent(context, MainActivity.class);
                    intent[0].putExtra("friendname", un);
                    intent[0].putExtra("friendemail",EMail);
                    context.startActivity(intent[0]);


                }
            });
        }
    }
}