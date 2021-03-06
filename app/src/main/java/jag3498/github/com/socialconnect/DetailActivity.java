package jag3498.github.com.socialconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Friend savedExtra = (Friend) getIntent().getSerializableExtra("friend");
        int pos  = 0;
        pos = getIntent().getIntExtra("pos", pos);

        EditText firstName = (EditText) findViewById(R.id.firstNameInput);
        EditText lastName = (EditText) findViewById(R.id.lastNameInput);
        EditText birthday = (EditText) findViewById(R.id.birthdayInput);
        EditText startday = (EditText) findViewById(R.id.startDateInput);
        EditText note = (EditText) findViewById(R.id.notesInput);


        firstName.setText(savedExtra.getFirstname());
        lastName.setText(savedExtra.getLastname());
        birthday.setText(savedExtra.getBirthday());
        startday.setText(savedExtra.getstartDate());
        note.setText(savedExtra.getNotes());

        firstName.setTag(Integer.valueOf(pos));



    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    public void updateFriend(View view) {

        Context context = getApplicationContext();
        Gson gson = new Gson();
        final ArrayList<Friend> friendlist1 = gson.fromJson(readFromFile(context), new TypeToken<ArrayList<Friend>>(){}.getType());



        EditText firstName = (EditText) findViewById(R.id.firstNameInput);
        EditText lastName = (EditText) findViewById(R.id.lastNameInput);
        EditText birthday = (EditText) findViewById(R.id.birthdayInput);
        EditText startday = (EditText) findViewById(R.id.startDateInput);
        EditText note = (EditText) findViewById(R.id.notesInput);

        int pos = (Integer) firstName.getTag();

        friendlist1.get(pos).setFirstname(firstName.getText().toString());
        friendlist1.get(pos).setLastname(lastName.getText().toString());
        friendlist1.get(pos).setBirthday(birthday.getText().toString());
        friendlist1.get(pos).setstartDate(startday.getText().toString());
        friendlist1.get(pos).setNotes(note.getText().toString());

        String friendsave;
        friendsave =  gson.toJson(friendlist1);

       // Context context = getApplicationContext();

        writeToFile(friendsave, context);

        //Context context = getApplicationContext();
        CharSequence text = "Saved!!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();


    }

    public void deleteFriend(View view) {

        Context context = getApplicationContext();
        Gson gson = new Gson();
        final ArrayList<Friend> friendlist1 = gson.fromJson(readFromFile(context), new TypeToken<ArrayList<Friend>>(){}.getType());

        EditText firstName = (EditText) findViewById(R.id.firstNameInput);
        int pos = (Integer) firstName.getTag();


        friendlist1.remove(pos);

        String friendsave;
        friendsave =  gson.toJson(friendlist1);

        // Context context = getApplicationContext();

        writeToFile(friendsave, context);

        //Context context = getApplicationContext();
        CharSequence text = "Removed!!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();



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
