package jag3498.github.com.socialconnect;

import android.content.Context;
import android.content.Intent;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

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

        //Intent intent = new Intent(RankInteractionActivity.this, NewFriendActivity.class);
        //startActivity(intent);
        Log.d("myTag",intSave);

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
