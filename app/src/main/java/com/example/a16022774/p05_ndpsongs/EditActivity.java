package com.example.a16022774.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    EditText etID, etTitle, etSingers, etYear;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rg;
    Song data;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etID = (EditText) findViewById(R.id.etID);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etSingers = (EditText) findViewById(R.id.etSingers);
        etYear = (EditText) findViewById(R.id.etYear);

        rg = (RadioGroup) findViewById(R.id.rg);
        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);
        rb5 = (RadioButton) findViewById(R.id.radioButton5);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");
        id = i.getIntExtra("id", 0);
        String title = data.getTitle();
        String singers = data.getSingers();
        int year = data.getYear();
        int stars = data.getStars();

        etID.setText(id + "");
        etTitle.setText(title);
        etSingers.setText(singers);
        etYear.setText(year + "");

        if (stars == 5){
            rb5.setChecked(true);
        }else if (stars == 4){
            rb4.setChecked(true);
        }else if (stars == 3){
            rb3.setChecked(true);
        }else if (stars == 2){
            rb2.setChecked(true);
        }else if (stars == 1){
            rb1.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(EditActivity.this);
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSingers.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));

                int selectedRadioButton = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedRadioButton);
                data.setStars(Integer.parseInt(rb.getText().toString()));
                db.updateSong(data);
                db.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(EditActivity.this);
                db.deleteSong(id);
                db.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
