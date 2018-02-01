package fhku.a01min_vacation;


import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        viewFlipper = (ViewFlipper) this.findViewById(R.id.viewflipper);
        viewFlipper.setFlipInterval(5000);

        ImageView imageView1 = findViewById(R.id.image1);
        ImageView imageView2 = findViewById(R.id.image2);
        ImageView imageView3 = findViewById(R.id.image3);
        ImageView imageView4 = findViewById(R.id.image4);
        ImageView imageView5 = findViewById(R.id.image5);
        ImageView imageView6 = findViewById(R.id.image6);


        //Mediaplayer für Musik im Hintergrund, momentan mit fixer Musik
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.song);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

    }

    //damit die Musik endet wenn man die Activity verlässt
    @Override
    public void onBackPressed() {
        mMediaPlayer.stop();
        finish();
        return;
    }
}



