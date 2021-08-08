package org.tensorflow.lite.examples.posenet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class suryanamaskar extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suryanamaskar);
        TextView information;
        ImageView postureImage;

        information= findViewById(R.id.information);
        postureImage=findViewById(R.id.posturImage);

        final int image=getIntent().getIntExtra("Image",R.drawable.image);
        final int info=getIntent().getIntExtra("info",R.string.default_dialouge);
        final String aasan_name_display=getIntent().getStringExtra("aasan_name");

        information.setText(getString(info));
        postureImage.setImageResource(image);


        FloatingActionButton try_now=(FloatingActionButton)findViewById(R.id.try_now);
        try_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(suryanamaskar.this, "The timer will set up after your posture matches minimum 75% and timer will reset everytime your posture distorts ",
                        Toast.LENGTH_LONG).show();
                setContentView(R.layout.tfe_pn_activity_camera);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                FragmentManager mfragmentManager=getSupportFragmentManager();
                FragmentTransaction mfragmentTransaction= mfragmentManager.beginTransaction();
                PosenetActivity mposenetActivity=new PosenetActivity();
                Bundle mbundle=new Bundle();
                mbundle.putInt("image_id",image);
                mbundle.putString("aasan_name",aasan_name_display);
                mposenetActivity.setArguments(mbundle);
                mfragmentTransaction.add(R.id.container,mposenetActivity);
                mfragmentTransaction.commit();

            }
        });
    }
}