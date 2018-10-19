package jag3498.github.com.socialconnect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    /** Called when the user taps the Friend button */
    public void openFriends (View view) {

        Intent intent = new Intent(this, FriendsListActivity.class);
        /*
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        */
        startActivity(intent);

    }

    public void openRankInteraction(View view){

        Intent intent = new Intent(this, RankInteractionActivity.class);
        startActivity(intent);
    }

    public void openTips(View view){

       // Intent intent = new Intent(this, TipsActivity.class);
       // startActivity(intent);

        Gson gson = new Gson();
        //Context context = getApplicationContext();

        ArrayList<Tip> tips = new ArrayList<>();

        tips.add(new Tip("Don’t allow anxiety to hold you back. Make the decision to talk to new people and to enter into conversations even when you’re feeling nervous about it.\n",1,2));
        tips.add(new Tip("If going to a party or spending time in a crowd seems overwhelming, start small.",1,2));
        tips.add(new Tip("If you want the attention off you in a conversation, get familiar with open-ended questions. Encourage others to talk so you won’t have to make the idle chit-chat.",1,3));
        tips.add(new Tip("Most people really enjoy talking about themselves. Ask a question about a person’s career, hobbies, or family. Show you’re interested in hearing what is being said.",1,1));
        tips.add(new Tip("Good manners go a long way in improving social skills. Practice being polite, showing gratitude, and using good table manners.",1,6));
        tips.add(new Tip("Non-verbal communication is very important. Pay attention to the type of body language you use.",1,4));
        tips.add(new Tip("Read up on current trends and news stories so you have something to talk about with people.",1,7));
        tips.add(new Tip("Identify negative thoughts that are likely dragging you down. Replace them with more realistic thoughts such as, “I can make conversation and I can meet new people.”",1,5));

        //add work tips
        tips.add(new Tip("",2,));


    }

    public void getRanking (){
    //Need to change to return ranking
    //Ranking test =  new Ranking();

        Gson gson = new Gson();
        Context context = getApplicationContext();
        File file = context.getFileStreamPath("rankings.txt");


        if(file.exists()){
            ArrayList<Ranking> rankingsList = gson.fromJson(readFromFile(context, "rankings.txt"), new TypeToken<ArrayList<Ranking>>(){}.getType());
            //return rankingsList.get(rankingsList.size());;
        }



       // return test;
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
