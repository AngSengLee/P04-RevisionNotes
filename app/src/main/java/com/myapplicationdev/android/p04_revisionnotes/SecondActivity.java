package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Note> note;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = findViewById(R.id.lvNotes);
        note = new ArrayList<Note>();

        //TODO implement the Custom ListView

        DBHelper db = new DBHelper(SecondActivity.this);
        note = db.getAllNotes();
        db.close();

        aa = new RevisionNotesArrayAdapter(SecondActivity.this, R.layout.row, note);
        lv.setAdapter(aa);
    }

}
