package jag3498.github.com.socialconnect;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TipListAdapter extends ArrayAdapter{
    //to reference the Activity
    private final Activity context;
    private final int categoryInt;
    private final ArrayList<Tip> tipArray;


    public TipListAdapter(Activity context, ArrayList<Tip> tipArrayParam, int categoryInt){

        super(context, R.layout.listview_row , tipArrayParam);

        this.context=context;
        this.tipArray = tipArrayParam;
        this.categoryInt = categoryInt;

    }


    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        TextView tipText = (TextView) rowView.findViewById(R.id.tipText);
        tipText.setText(tipArray.get(position).getText());


        return rowView;

    };

}
