package org.tensorflow.lite.examples.posenet;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;


    private void releaseMediaPlayer() {

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        final ArrayList<songs> song_to_paly = new ArrayList<songs>();

        song_to_paly.add(new songs("Calm Music",R.raw.song_1,R.drawable.yoga_applogo_final));
        song_to_paly.add(new songs("Distressing Music",R.raw.song_2,R.drawable.yoga_applogo_final));
        song_to_paly.add(new songs("Afternoon Vibe",R.raw.song_3,R.drawable.yoga_applogo_final));
        song_to_paly.add(new songs("Morning Breeze",R.raw.song_4,R.drawable.yoga_applogo_final));
        song_to_paly.add(new songs("Soothing Music",R.raw.song_5,R.drawable.yoga_applogo_final));
        song_to_paly.add(new songs("Relaxing Music",R.raw.song_6,R.drawable.yoga_applogo_final));
        song_to_paly.add(new songs("Meditational Aura",R.raw.song_7,R.drawable.yoga_applogo_final));


        final songsAdapter msongsAdapter = new songsAdapter(this, song_to_paly);
        ListView songs_list = (ListView) findViewById(R.id.songs_list);
        songs_list.setAdapter(msongsAdapter);


        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final MediaPlayer.OnCompletionListener On_Completion = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        };



        songs_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                releaseMediaPlayer();

                int focusChange = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (focusChange == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(MusicActivity.this, song_to_paly.get(position).getSong_id());
                    mediaPlayer.start();
                }
                mediaPlayer.setOnCompletionListener(On_Completion);
            }
        });
    }

    }
