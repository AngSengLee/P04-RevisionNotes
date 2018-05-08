package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    EditText etNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNote = findViewById(R.id.editTextNote);
        Button btnInsert = (Button)findViewById(R.id.buttonInsertNote);
        Button btnShow = (Button)findViewById(R.id.buttonShowList);


        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String note = etNote.getText().toString().trim();
                if(note.length() == 0)
                    return;

                DBHelper dbh = new DBHelper(MainActivity.this);

                String notes = etNote.getText().toString();

                rg = (RadioGroup)findViewById(R.id.radioGroupStars);
                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);

                dbh.insertNote(notes, Integer.parseInt(rb.getText().toString()));
                dbh.close();

                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();


            }
        });

        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                rg = findViewById(R.id.radioGroupStars);
                Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                int selected = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selected);
                int star = Integer.parseInt(rb.getText().toString());
                intent.putExtra("stars", star);
                String title = etNote.getText().toString();
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });
    }
}
