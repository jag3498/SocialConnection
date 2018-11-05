package jag3498.github.com.socialconnect;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        Gson gson = new Gson();
        ArrayList<Tip> tipsAdd = new ArrayList<>();

        tipsAdd.add(new Tip("Don’t allow anxiety to hold you back. Make the decision to talk to new people and to enter into conversations even when you’re feeling nervous about it.\n",1,2));
        tipsAdd.add(new Tip("If going to a party or spending time in a crowd seems overwhelming, start small.",1,2));
        tipsAdd.add(new Tip("If you want the attention off you in a conversation, get familiar with open-ended questions. Encourage others to talk so you won’t have to make the idle chit-chat.",1,3));
        tipsAdd.add(new Tip("Most people really enjoy talking about themselves. Ask a question about a person’s career, hobbies, or family. Show you’re interested in hearing what is being said.",1,1));
        tipsAdd.add(new Tip("Good manners go a long way in improving social skills. Practice being polite, showing gratitude, and using good table manners.",1,6));
        tipsAdd.add(new Tip("Non-verbal communication is very important. Pay attention to the type of body language you use.",1,4));
        tipsAdd.add(new Tip("Read up on current trends and news stories so you have something to talk about with people.",1,7));
        tipsAdd.add(new Tip("Identify negative thoughts that are likely dragging you down. Replace them with more realistic thoughts such as, “I can make conversation and I can meet new people.”",1,5));

        //add work tips
        tipsAdd.add(new Tip("Compliments can be a great way to open the door to a conversation. Offer a co-worker a compliment on a presentation he gave at a meeting or compliment your neighbor on his new car.",2,1));
        tipsAdd.add(new Tip("Empathy is especially important when dealing with clients who come to you with questions or problems. You need to express genuine concern for their issues, and help solve them.",2,2));
        tipsAdd.add(new Tip("Cooperation is especially important when you work on a team. You need to be able to work with others to reach a common goal. However, even if you do not work on a team, cooperation is still important. You need to be able to work alongside colleagues to help achieve the goals of your organization.",2,3));
        tipsAdd.add(new Tip("Verbal communication is an extremely important social skill in every job. You need to express yourself using clear language that others can understand. You need to be able to speak in person, on the phone, and via email with others.",2,4));

        // add romantic tips
        tipsAdd.add(new Tip("Get a Hobby: The adage, “If you cultivate an interest, people will find you interesting” really is true. Get yourself involved in something that will get you out the door and engaged while broadening your social circle. Take a cooking class, sign up for language lessons, learn to skate or join a book club. Hobbies also provide handy conversational fodder for first dates.",3,1));
        tipsAdd.add(new Tip("Volunteer. It doesn’t matter what you do (dog walking at the local shelter, serving soup at the homeless kitchen). Volunteering will put you in the path of like-minded people with good hearts and a strong sense of community",3,7));
        tipsAdd.add(new Tip("People often say \"you never get a second chance to make a first impression\", and that's actually quite true—which is why it's so important to start on the right foot.",3,6));
        tipsAdd.add(new Tip("On a date, make sure you don’t do all the talking. Try to keep what you have to say short and concise. You don’t want to start boring your date. If this date goes well, there will be lots more opportunities to share your stories in the future. ",3,3));
        tipsAdd.add(new Tip("Talking about your ex is dangerous territory. It’s best to stay away from the conversation altogether. Your date will not be interested and it can make things feel awkward between you. If your date does bring up the subject, try to keep answers short (without appearing suspicious).",3,2));

        String tipSave = gson.toJson(tipsAdd);
        Context context = getApplicationContext();


        writeToFile("tips.txt",tipSave, context);

        TextView title = (TextView)findViewById(R.id.textView2);
        TextView tip1 = (TextView)findViewById(R.id.textView7);
        TextView tip2 = (TextView)findViewById(R.id.textView9);
        TextView tip3 = (TextView)findViewById(R.id.textView13);
        TextView tip4 = (TextView)findViewById(R.id.textView15);


        //Context context = getApplicationContext();
        File file = context.getFileStreamPath("rankings.txt");

        if(file.exists()){
            Ranking ranking = getRanking();

           // Gson gson = new Gson();
            Log.e("TIP!!!!!!", gson.toJson(ranking));
            final ArrayList<Tip> tips = gson.fromJson(readFromFile(context, "tips.txt"), new TypeToken<ArrayList<Tip>>(){}.getType());



            tip1.setText(tips.get(ranking.getTip1()).getText());
            tip2.setText(tips.get(ranking.getTip2()).getText());
            tip3.setText(tips.get(ranking.getTip3()).getText());
            tip4.setText(tips.get(ranking.getTip4()).getText());
        }
        else{

            title.setVisibility(View.INVISIBLE);
            tip1.setVisibility(View.INVISIBLE);
            tip2.setVisibility(View.INVISIBLE);
            tip3.setVisibility(View.INVISIBLE);
            tip4.setVisibility(View.INVISIBLE);


        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "test";
            String description = "test";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
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

       Intent intent = new Intent(this, TipsActivity.class);
       startActivity(intent);

        Gson gson = new Gson();
        Context context = getApplicationContext();

        //ArrayList<Tip> Tip = new ArrayList<>();
       // ArrayList<Tip> Tip = gson.fromJson(readFromFile(context, "tips.txt"), new TypeToken<ArrayList<Tip>>(){}.getType());



    }

    public Ranking getRanking(){
    //Need to change to return ranking
    //Ranking test =  new Ranking();

        Gson gson = new Gson();
        Context context = getApplicationContext();
        ArrayList<Ranking> rankingsList = gson.fromJson(readFromFile(context, "rankings.txt"), new TypeToken<ArrayList<Ranking>>(){}.getType());



        return rankingsList.get(rankingsList.size()- 1);

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

    private void writeToFile(String fileName, String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


}
