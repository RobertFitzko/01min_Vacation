package fhku.a01min_vacation;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ShowActivity extends AppCompatActivity {

    ImageSwitcher imageSwitcher;
    MediaPlayer mMediaPlayer;
    private int currentIndex;
    private Uri url;
    private SharedPreferences shareMusic, shareImage;
    /*private final int[] imageNames =
            {R.drawable.picture1, R.drawable.picture2, R.drawable.picture3, R.drawable.picture4, R.drawable.picture5, R.drawable.picture6};
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();


        //Mediaplayer für Musik im Hintergrund, momentan mit fixer Musik
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.song);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        //ImageSwitcher für die Slideshow
        imageSwitcher = (ImageSwitcher) this.findViewById(R.id.imageswitcher);

        //Animation für die Slideshow
        Animation in = AnimationUtils.loadAnimation(this, R.anim.in_from_left);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.out_from_right);
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setAnimation(out);

        //autostart und switch
        imageSwitcher.postDelayed(new Runnable() {
            private int index = 0;

            public void run() {
                imageSwitcher.setImageURI(url);
                    index++;
                imageSwitcher.postDelayed(this, 5000);
            }
        }, 5000);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());

                imageView.setBackgroundColor(Color.LTGRAY);
                ImageSwitcher.LayoutParams params = new ImageSwitcher.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT);
                imageView.setLayoutParams(params);
                return imageView;
            }
        });

    }

    public void init(){
        shareMusic = getSharedPreferences("01Minute",MODE_PRIVATE);
        shareImage = getSharedPreferences("01Minute", MODE_PRIVATE);

        //map erstellen
        Map<String, ?> myMap = new HashMap();
        //map zuweisen
        myMap = shareImage.getAll();
        //array für die images
        String myImages[] = new String[6];

        String MapString;
        for(Map.Entry<String, ?> entry: myMap.entrySet()) {
            MapString = entry.getValue().toString();
            Uri url = Uri.parse(MapString);
            //imageSwitcher.setImageURI(url);
        }
    }



    //damit die Musik endet wenn man die Activity verlässt
    @Override
    public void onBackPressed() {
        mMediaPlayer.stop();
        finish();
        return;
    }
}



