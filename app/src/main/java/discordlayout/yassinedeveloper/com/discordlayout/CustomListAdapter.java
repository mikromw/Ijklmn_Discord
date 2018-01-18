package discordlayout.yassinedeveloper.com.discordlayout;

/**
 * Created by root on 1/9/18.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;

    public CustomListAdapter(Activity context, String[] itemname) {
        super(context, R.layout.mylist_server, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist_server, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.nameservertextlist);

        txtTitle.setText(itemname[position]);

        return rowView;

    };
}