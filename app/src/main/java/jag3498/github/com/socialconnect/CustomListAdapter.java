package jag3498.github.com.socialconnect;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import jag3498.github.com.socialconnect.R;

public class CustomListAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    //to store the list of countries
  //  private final String[] nameArray;

    //to store the list of countries
   // private final String[] infoArray;

    private final ArrayList<Friend> nameArray;


    public CustomListAdapter(Activity context, ArrayList<Friend> nameArrayParam){

        super(context, R.layout.listview_row , nameArrayParam);

        this.context=context;
        this.nameArray = nameArrayParam;

    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameTextViewID);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.infoTextViewID);
        TextView friendRankField = (TextView) rowView.findViewById(R.id.friendRank);
        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray.get(position).getFirstname() + " " + nameArray.get(position).getLastname());
        infoTextField.setText(nameArray.get(position).getNotes());


        friendRankField.setText(Integer.toString(position+1));

        return rowView;

    };

}
