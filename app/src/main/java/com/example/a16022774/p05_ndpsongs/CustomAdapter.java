package com.example.a16022774.p05_ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Song> {

    Context context;
    ArrayList<Song> songs;
    int resource;
    ImageView iv1, iv2, iv3, iv4, iv5;

    public CustomAdapter(Context context, int resource, ArrayList<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.songs = songs;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        TextView tvYear = (TextView) rowView.findViewById(R.id.tvYear);
        TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        TextView tvSingers = (TextView) rowView.findViewById(R.id.tvSingers);

        ImageView iv1 = (ImageView) rowView.findViewById(R.id.imageView1star);
        ImageView iv2 = (ImageView) rowView.findViewById(R.id.imageView2star);
        ImageView iv3 = (ImageView) rowView.findViewById(R.id.imageView3star);
        ImageView iv4 = (ImageView) rowView.findViewById(R.id.imageView4star);
        ImageView iv5 = (ImageView) rowView.findViewById(R.id.imageView5star);

        Song song = songs.get(position);
        tvYear.setText(song.getYear() + "");
        tvTitle.setText(song.getTitle());
        tvSingers.setText(song.getSingers());

        //Check if the property for starts == 5, if so, "light" up the stars
        if (song.getStars() == 5) {
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (song.getStars() == 4) {
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (song.getStars() == 3) {
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (song.getStars() == 2) {
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }

        return rowView;
    }


}
