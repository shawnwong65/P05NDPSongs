package com.example.a16022774.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SongList extends AppCompatActivity {

    Button btn5Stars;
    ListView lv;
    CustomAdapter ca;
    ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        lv = (ListView) findViewById(R.id.lv);

        DBHelper db = new DBHelper(SongList.this);
        songs = new ArrayList<Song>();
        songs.clear();
        songs.addAll(db.getAllSongs());
        db.close();

        ca = new CustomAdapter(SongList.this, R.layout.row, songs);
        lv.setAdapter(ca);
        ca.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Intent i = new Intent(SongList.this,
                        EditActivity.class);
                Song data = songs.get(position);
                int id = position;
                String title = data.getTitle();
                String singers = data.getSingers();
                int year = data.getYear();
                int stars = data.getStars();

                Song target = new Song(title, singers, year, stars);
                i.putExtra("id", id);
                i.putExtra("data", target);
                startActivityForResult(i, 9);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            ca.notifyDataSetChanged();
        }
    }
}
