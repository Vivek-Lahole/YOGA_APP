package org.tensorflow.lite.examples.posenet;

public class yoga_pose {

    private int aasan_Image_Id=0;
    private String aasan_name;

    public yoga_pose(int aasan_Image_Id, String aasan_name) {
        this.aasan_Image_Id = aasan_Image_Id;
        this.aasan_name = aasan_name;
    }

    public int getAasan_Image_Id() {
        return aasan_Image_Id;
    }

    public String getAasan_name() {
        return aasan_name;
    }

}
