package fhku.a01min_vacation;


import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ShowActivity extends AppCompatActivity {

    ImageSwitcher imageSwitcher;
    MediaPlayer mMediaPlayer;
    private int currentIndex;
    private final String[] imageNames={"picture1", "picture2", "picture3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

/*        ImageView imageView1 = findViewById(R.id.image1);
        ImageView imageView2 = findViewById(R.id.image2);
        ImageView imageView3 = findViewById(R.id.image3);
        ImageView imageView4 = findViewById(R.id.image4);
        ImageView imageView5 = findViewById(R.id.image5);
        ImageView imageView6 = findViewById(R.id.image6);*/


        //Mediaplayer für Musik im Hintergrund, momentan mit fixer Musik
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.song);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        imageSwitcher = (ImageSwitcher) this.findViewById(R.id.imageswitcher);

        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setAnimation(out);

        imageSwitcher.postDelayed(new Runnable() {
            int i = 0;
            public void run() {
                imageSwitcher.setImageResource(
                        i++ % 2 == 0 ?
                                R.drawable.picture1 :
                                R.drawable.picture2);
                imageSwitcher.postDelayed(this, 1000);
            }
        }, 10000);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());

                imageView.setBackgroundColor(Color.LTGRAY);
                imageView.setScaleType(ImageView.ScaleType.CENTER);

                ImageSwitcher.LayoutParams params = new ImageSwitcher.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT);
                imageView.setLayoutParams(params);
                return imageView;
            }
        });
        this.currentIndex = 0;
        this.showImage(this.currentIndex);
    }
        private void showImage(int imgIndex) {
            String imageName= this.imageNames[imgIndex];

            int resId= getDrawableResIdByName(imageName);
            if(resId!=  0) {
                this.imageSwitcher.setImageResource(resId);
            }
        }

    //damit die Musik endet wenn man die Activity verlässt
    @Override
    public void onBackPressed() {
        mMediaPlayer.stop();
        finish();
        return;
    }
    public int getDrawableResIdByName(String resName)  {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("MyLog", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }
}



