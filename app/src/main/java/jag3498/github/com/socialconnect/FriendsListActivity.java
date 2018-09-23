package jag3498.github.com.socialconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FriendsListActivity extends AppCompatActivity {


    ListView listView;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

      //  Toolbar myToolbar = (Toolbar) findViewById(R.id.friendListToolbar);
       // setSupportActionBar(myToolbar);




        ArrayList<Friend> friendlist = new ArrayList<Friend>();
       // ArrayList<String> list = new ArrayList<String>();

/*
        friendlist.add(new Friend("Jack", "Giebel", "03/04/1998", "1/1/2008", "He is a cool dude"));
        friendlist.add(new Friend("Kelly", "Wallert", "09/03/1999", "10/31/2017", "Kelly is a nice friend"));
        friendlist.add(new Friend("Paul", "Suchko", "06/12/1998", "9/1/2016", "Paul is a guy with a job at PPG"));
        friendlist.add(new Friend("Ben", "Eppinger", "07/27/1997", "8/24/2016", "Ben was my college RA"));
        friendlist.add(new Friend("Jack", "O'Sullivan", "01/01/1990", "1/1/2015", "Lives down the street at home"));
        friendlist.add(new Friend("Bertha", "Mohammed", "08/16/1991", "1/1/2014", "Drives a nice car"));
        friendlist.add(new Friend("Michael", "Boles", "03/08/1994", "1/1/2015", "He is an eagle fans, hates the steelers"));
        friendlist.add(new Friend("Beatrice", "Burgess", "01/01/1995", "1/1/2015", "Runs the website neteret.net"));

        String friendsave;
        friendsave =  gson.toJson(friendlist);
*/
        Context context = getApplicationContext();

       // writeToFile(friendsave, context);

        final ArrayList<Friend> friendlist1 = gson.fromJson(readFromFile(context), new TypeToken<ArrayList<Friend>>(){}.getType());


        CustomListAdapter whatever = new CustomListAdapter(this, friendlist1);
        listView = (ListView) findViewById(R.id.friend_list);
        listView.setAdapter(whatever);


        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(FriendsListActivity.this, DetailActivity.class);


                Friend currentFriend = friendlist1.get(position);
                intent.putExtra("friend", currentFriend);
                intent.putExtra("pos", position);

                int requestCode = 0;

                startActivity(intent);
            }
        });

    }

    public void addFriend(View view){
        Intent intent = new Intent(FriendsListActivity.this, NewFriendActivity.class);
        startActivity(intent);


    }

    @Override
    public void onRestart(){
        super.onRestart();
        this.finish();
        startActivity(getIntent());
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("friends.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("friends.txt");

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
