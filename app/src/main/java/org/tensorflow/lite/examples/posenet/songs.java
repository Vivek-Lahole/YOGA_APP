package org.tensorflow.lite.examples.posenet;

public class songs {


    private String song_name;
    private int song_id=0;
    private int image_id=0;
   
    public songs(String song_name, int song_id, int image_id) {
        this.song_name = song_name;
        this.song_id = song_id;
        this.image_id = image_id;
    }


    public String getSong_name() {
        return song_name;
    }

    public int getImage_id() {
        return image_id;
    }

    public int getSong_id() {
        return song_id;
    }

}
