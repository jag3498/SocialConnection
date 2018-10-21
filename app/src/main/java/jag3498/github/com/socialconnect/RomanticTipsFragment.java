package jag3498.github.com.socialconnect;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RomanticTipsFragment extends Fragment {
    ListView listView;
    Gson gson = new Gson();

    public RomanticTipsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Set template to list template
        View rootView = inflater.inflate(R.layout.tip_category_list, container, false);

        //get context and gson
        Gson gson = new Gson();
        Context context = getActivity();

        //get arraylist of tips from file
        ArrayList<Tip> tips = gson.fromJson(readFromFile(context, "tips.txt"), new TypeToken<ArrayList<Tip>>(){}.getType());

        //add only current category tips from that file to arraylist
        ArrayList<Tip> catTips = new ArrayList<>();
        for (Tip tip: tips){
            if(tip.getType() == 3){
                catTips.add(tip);
            }

        }

        //set list adapter with list of current tips
        TipListAdapter tipsList = new TipListAdapter(getActivity(), catTips);
        listView = rootView.findViewById(R.id.tip_list);
        listView.setAdapter(tipsList);



        return rootView;




    }

    private String readFromFile(Context context, String fileName) {

        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

}
