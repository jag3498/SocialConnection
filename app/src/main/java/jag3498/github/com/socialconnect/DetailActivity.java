package jag3498.github.com.socialconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Friend savedExtra = (Friend) getIntent().getSerializableExtra("friend");


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



    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
