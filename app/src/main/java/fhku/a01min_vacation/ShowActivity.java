package fhku.a01min_vacation;


import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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
        

        //Mediaplayer für Musik im Hintergrund, momentan mit fixer Musik
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.song);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

    }

    //damit die Musik endet wenn man die Activity verlässt
@Override
    public void onBackPressed(){
        mMediaPlayer.stop();
        finish();
        return;
    }
}



