package jag3498.github.com.socialconnect;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

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
import java.util.Random;

public class RankInteractionActivity extends AppCompatActivity {


    Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_interaction);

        Context context = getApplicationContext();

        final ArrayList<Friend> friendlist1 = gson.fromJson(readFromFile(context, "friends.txt"), new TypeToken<ArrayList<Friend>>(){}.getType());

        ArrayList<String> names = new ArrayList<>();

        names.add("Select friend...");

        for(Friend friend: friendlist1 ){
            names.add(friend.getFirstname() + " "+ friend.getLastname());
        }



        Spinner spinner = (Spinner) findViewById(R.id.spinnerFriend);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



    }
    public void saveInteraction(View view){

        Context context = getApplicationContext();

        //  ArrayList<Interaction> interactions = new ArrayList<>();
      //  Friend test = new Friend("Jack", "Giebel", "03/04/1998", "1/1/2008", "He is a cool dude");
       // Interaction interaction = new Interaction(test, 1, 1, 5, 5);



       final ArrayList<Interaction> interactions = gson.fromJson(readFromFile(context, "interactions.txt"), new TypeToken<ArrayList<Interaction>>(){}.getType());
       final ArrayList<Friend> friends = gson.fromJson(readFromFile(context, "friends.txt"), new TypeToken<ArrayList<Friend>>(){}.getType());

        Spinner spinner = (Spinner) findViewById(R.id.spinnerFriend);
        EditText time = (EditText) findViewById(R.id.time);
        EditText people = (EditText) findViewById(R.id.people);
        RadioGroup comfort = (RadioGroup) findViewById(R.id.comfort);
        RadioGroup know = (RadioGroup) findViewById(R.id.know);

        int comfortId = comfort.getCheckedRadioButtonId();
        RadioButton comfortButton = (RadioButton) findViewById(comfortId);

        int knowId = comfort.getCheckedRadioButtonId();
        RadioButton knowButton = (RadioButton) findViewById(knowId);



        Friend currentFriend =  friends.get(spinner.getSelectedItemPosition()-1);
        interactions.add(new Interaction(currentFriend, Integer.parseInt(time.getText().toString()), Integer.parseInt(people.getText().toString()), Integer.parseInt(comfortButton.getText().toString()), Integer.parseInt(knowButton.getText().toString())));


        String intSave;
        intSave =  gson.toJson(interactions);
        writeToFile(intSave,context, "interactions.txt" );

       /*_________________
       /
       /
       /
       Logic to create ranking
       /
       /
       /____________________
       */


        final ArrayList<Tip> tips = gson.fromJson(readFromFile(context, "tips.txt"), new TypeToken<ArrayList<Tip>>(){}.getType());
        final ArrayList<Interaction> interactions1 = gson.fromJson(readFromFile(context, "interactions.txt"), new TypeToken<ArrayList<Interaction>>(){}.getType());

        double numPeopleAvg = 0;
        double comfortAvg= 0;
        double knowAvg= 0;
        double IntervalAvg= 0;
        int numPeopleMax = Integer.MIN_VALUE;
        int numPeopleMin = Integer.MAX_VALUE;

        int tip1= 0;
        int tip2= 0;
        int tip3= 0;
        int tip4= 0;


        for(Interaction interaction : interactions1){

            numPeopleAvg += interaction.getNumPeople();
            comfortAvg += interaction.getComfort();
            knowAvg+= interaction.getKnow();

            //Get max and min people so we can get a reletive out of 5 number of people index.
            if(interaction.getNumPeople() > numPeopleMax){
                numPeopleMax = interaction.getNumPeople();
            }
            if(interaction.getNumPeople() < numPeopleMin){
                numPeopleMin =interaction.getNumPeople();
            }

        }

        numPeopleAvg = numPeopleAvg / interactions1.size();
        comfortAvg = comfortAvg / interactions1.size();
        knowAvg = knowAvg / interactions1.size();

        boolean peopleHigh = true;
        boolean comfortHigh = true;
        boolean knowHigh = true;

        //convert numPeople into out of 5
       double converter = numPeopleMin / numPeopleMax;
       numPeopleAvg = converter * 5;

       //Check ig values are closer to the min or max ratings
       if(numPeopleAvg <= 2.5){
           peopleHigh = false;
        }
        else if(numPeopleAvg > 2.5){
           peopleHigh = true;
        }
        if(comfortAvg <= 2.5){
            comfortHigh = false;
        }
        else if(comfortAvg > 2.5){
            comfortHigh = true;
        }
        if(knowAvg <= 2.5){
        knowHigh = false;
        }
        else if(knowAvg > 2.5){
        knowHigh = true;
        }

        //Get an arraylist of the three types of tips.

        int type1 = 0;
        int type2 = 0;
        int type3 = 0;
        int type4 = 5; //Placeholder tip category 5 bc we dont select this type anywhere else

        //first get the type of tips we want
        if(peopleHigh){
            type1 = 1;
        }
        else{
            type1 =2;
        }
        if(comfortHigh){
            type2 = 3;
        }
        else{
            type2 = 6;
        }
        if(knowHigh){
            type3 = 4;
        }
        else{
            type3 = 7;
        }

        //Arraylists of these tip types to randomly pick from

        ArrayList<TipSelection> tips1 = new ArrayList<TipSelection>();
        ArrayList<TipSelection> tips2 = new ArrayList<TipSelection>();
        ArrayList<TipSelection> tips3 = new ArrayList<TipSelection>();
        ArrayList<TipSelection> tips4 = new ArrayList<TipSelection>();

        //Add the matching tips to these arraylists
        for(Tip tip:tips){
            if(tip.getType() == type1){
                tips1.add(new TipSelection(tips.indexOf(tip),tip));
            }
            if(tip.getType() == type2){
                tips2.add(new TipSelection(tips.indexOf(tip),tip));
            }
            if(tip.getType() == type3){
                tips3.add(new TipSelection(tips.indexOf(tip),tip));
            }
            if(tip.getType() == type4){
                tips4.add(new TipSelection(tips.indexOf(tip),tip));
            }

        }

        //Randomly select matching tips to add to the ranking
        Random rand = new Random();

        tip1= tips1.get(rand.nextInt(tips1.size())).getTipValue() ;
        tip2= tips2.get(rand.nextInt(tips2.size())).getTipValue() ;
        tip3= tips3.get(rand.nextInt(tips3.size())).getTipValue() ;
        tip4= tips4.get(rand.nextInt(tips4.size())).getTipValue() ;



        //Create ranking with all the data we just created
        Ranking rank = new Ranking(numPeopleAvg,comfortAvg,knowAvg,IntervalAvg, tip1, tip2, tip3, tip4);

        //save the rankings to file
        File file = context.getFileStreamPath("rankings.txt");
        if(file.exists()){
            ArrayList<Ranking> rankings = gson.fromJson(readFromFile(context, "rankings.txt"), new TypeToken<ArrayList<Ranking>>(){}.getType());

           String rankSave =  gson.toJson(rankings);
           writeToFile(rankSave,context, "rankings.txt" );

        }
        else{
            ArrayList<Ranking> rankings = new ArrayList<>();
            rankings.add(rank);

            String rankSave =  gson.toJson(rankings);
            writeToFile(rankSave,context, "rankings.txt" );

        }
        
        //Intent intent = new Intent(RankInteractionActivity.this, NewFriendActivity.class);
        //startActivity(intent);
        //Log.d("myTag",intSave);

        finish();


    }



    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }


    private String readFromFile(Context context, String filename) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(filename);

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

    private void writeToFile(String data,Context context, String filename) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
