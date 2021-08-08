package org.tensorflow.lite.examples.posenet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class songsAdapter extends ArrayAdapter<songs> {

    public ImageView play_button;

    public songsAdapter(Activity context, ArrayList<songs> NumberWords) {
        super(context, 0, NumberWords);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.trial, parent, false);
        }

        songs msong = getItem(position);

        TextView song_name = (TextView) listItemView.findViewById(R.id.songName);
        song_name.setText(msong.getSong_name());

        ImageView songImage=(ImageView)listItemView.findViewById(R.id.songImage);
        songImage.setImageResource(msong.getImage_id());

        play_button=(ImageView)listItemView.findViewById(R.id.Play_button);
        play_button.setImageResource(R.drawable.ic_play_arrow_black);

        return listItemView;
    }
}

