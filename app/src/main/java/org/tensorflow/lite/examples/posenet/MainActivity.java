package org.tensorflow.lite.examples.posenet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private org.tensorflow.lite.examples.posenet.yoga_poses_adapter yoga_poses_adapter;
    private List<yoga_pose> yoga_pose_list = new ArrayList<>();
    private ImageSlider imageSlider;
    private yoga_poses_adapter.recyclerViewClickListner recyclerViewClickListner;
    private CardView tryDiffferentYoga;
    private CardView music;
    private AlertDialog.Builder dialogBuiler;
    private AlertDialog dialog;
    private Button addimage;
    private Button done;
    private Uri imageuri;
    private Bitmap imageBitmap;
    private ImageView previewImage;
    private Boolean image_added = false;

//    @Override
//    protected void onPause() {
//        super.onPause();
//        MusicActivity musicActivity = new MusicActivity();
//        musicActivity.anoop();
//    }

    private void chooseImage() {

        Intent gallery = new Intent(Intent.ACTION_PICK);
        gallery.setType("image/*"); // change the image to the file you want.
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "select Image"), 69);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 69 && resultCode == RESULT_OK) {
            imageuri = data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
                previewImage.setImageBitmap(imageBitmap);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        tryDiffferentYoga = findViewById(R.id.tryDiffferentYoga);
        music = findViewById(R.id.music);


        // asking for read internal storage permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permission, 69);
        }

        // image slider code base
        imageSlider = findViewById(R.id.image_slider);
        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.quote8, null));
        images.add(new SlideModel(R.drawable.quote6, null));
        images.add(new SlideModel(R.drawable.quote7, null));
        images.add(new SlideModel(R.drawable.yoga_is_a_mirror_to, null));
        images.add(new SlideModel(R.drawable.yoga_quote3, null));
        images.add(new SlideModel(R.drawable.yoga_quote4, null));
        images.add(new SlideModel(R.drawable.yoga_quote5, null));
        imageSlider.setImageList(images, ScaleTypes.CENTER_CROP);


        // on click listner to recycler view
        setOnClickListener();
        yoga_poses_adapter = new yoga_poses_adapter(yoga_pose_list, recyclerViewClickListner);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(yoga_poses_adapter);


        // add elements to recycler view
        yoga_pose_list.add(new yoga_pose(R.drawable.tadasan_logo, "Tadasan"));
        yoga_pose_list.add(new yoga_pose(R.drawable.vrikshasan_logo, "Vrikshasan"));
        yoga_pose_list.add(new yoga_pose(R.drawable.virbhadrasan_logo, "Virbhadrasan"));
        yoga_pose_list.add(new yoga_pose(R.drawable.ut_logo, "Utthita Trikonasana"));
        yoga_pose_list.add(new yoga_pose(R.drawable.vv_logo, "Viparita Virabhadrasana"));
        yoga_pose_list.add(new yoga_pose(R.drawable.utk_logo, "Utkatasana"));


        // Code for create custom aasan
        tryDiffferentYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogBuiler = new AlertDialog.Builder(MainActivity.this);
                View custom_aasan_popup = getLayoutInflater().inflate(R.layout.custom_aasan_popup, null);
                addimage = (Button) custom_aasan_popup.findViewById(R.id.addimage);
                done = (Button) custom_aasan_popup.findViewById(R.id.done);
                previewImage = (ImageView) custom_aasan_popup.findViewById(R.id.previewImage);
                done.setVisibility(View.GONE);
                dialogBuiler.setView(custom_aasan_popup);
                dialog = dialogBuiler.create();
                dialog.show();


                //code for  reading image from internal memory
                addimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseImage();
                        done.setVisibility(View.VISIBLE);
                    }
                });

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        setContentView(R.layout.tfe_pn_activity_camera_custom);
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                        FragmentManager mfragmentManager = getSupportFragmentManager();
                        FragmentTransaction mifragmentTransaction = mfragmentManager.beginTransaction();
                        PosenetActivity_custom miposenetActivity = new PosenetActivity_custom();
                        Bundle mbundle = new Bundle();
                        mbundle.putByteArray("image_bitmap", byteArray);
                        miposenetActivity.setArguments(mbundle);
                        mifragmentTransaction.add(R.id.containerCustom, miposenetActivity);
                        mifragmentTransaction.commit();
                        dialog.dismiss();
                    }
                });
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });

    }

    // this is for recycler view
    private void setOnClickListener() {
        recyclerViewClickListner = new yoga_poses_adapter.recyclerViewClickListner() {
            @Override
            public void onclick(View view, int position) {



                Intent i = new Intent(MainActivity.this, suryanamaskar.class);

                if (yoga_pose_list.get(position).getAasan_name() == "Tadasan") {

                    i.putExtra("Image", R.drawable.tadasan_removebg_preview);
                    i.putExtra("info", R.string.Tadasan);
                    i.putExtra("aasan_name", yoga_pose_list.get(position).getAasan_name());
                    startActivity(i);
                } else if (yoga_pose_list.get(position).getAasan_name() == "Vrikshasan") {

                    i.putExtra("Image", R.drawable.vrikshasana_removebg_preview);
                    i.putExtra("info", R.string.Vrikshasan);
                    i.putExtra("aasan_name", yoga_pose_list.get(position).getAasan_name());
                    startActivity(i);
                } else if (yoga_pose_list.get(position).getAasan_name() == "Virbhadrasan") {

                    i.putExtra("Image", R.drawable.virchadrasan_posture_removebg_preview);
                    i.putExtra("info", R.string.Virvhadrasana);
                    i.putExtra("aasan_name", yoga_pose_list.get(position).getAasan_name());
                    startActivity(i);
                } else if (yoga_pose_list.get(position).getAasan_name() == "Utthita Trikonasana") {

                    i.putExtra("Image", R.drawable.ut_posture_removebg_preview);
                    i.putExtra("info", R.string.Ut);
                    i.putExtra("aasan_name", yoga_pose_list.get(position).getAasan_name());
                    startActivity(i);
                } else if (yoga_pose_list.get(position).getAasan_name() == "Viparita Virabhadrasana") {

                    i.putExtra("Image", R.drawable.vv_posture_removebg_preview);
                    i.putExtra("info", R.string.vv);
                    i.putExtra("aasan_name", yoga_pose_list.get(position).getAasan_name());
                    startActivity(i);
                } else if (yoga_pose_list.get(position).getAasan_name() == "Utkatasana") {

                    i.putExtra("Image", R.drawable.utk_posture_removebg_preview);
                    i.putExtra("info", R.string.Utkatasana);
                    i.putExtra("aasan_name", yoga_pose_list.get(position).getAasan_name());
                    startActivity(i);
                }
            }
        };
    }


}
