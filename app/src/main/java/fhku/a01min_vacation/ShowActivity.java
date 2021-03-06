package fhku.a01min_vacation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static fhku.a01min_vacation.R.anim.abc_slide_in_bottom;
import static fhku.a01min_vacation.R.id.pic1;
import static java.lang.Thread.sleep;

public class ShowActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    ArrayList<Uri> list = new ArrayList<Uri>();
    private int counter = 0;
    private Uri url;
    private Uri murl;
    private SharedPreferences shareMusic, shareImage;
    Map<String, ?> myMap = new HashMap();
    Map<String, ?> myMusicMap = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();

        //Mediaplayer für Musik im Hintergrund, momentan mit fixer Musik
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer=MediaPlayer.create(this,murl);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        final ImageView backgroundImageView = findViewById(R.id.imageview1);
        final Handler handler = new Handler();
        Runnable r = new Runnable() {
            int i = 0;

            public void run() {
                backgroundImageView.setImageURI(list.get(i));
                i++;
                if (i >= list.size()) {
                    i = 0;
                }
                handler.postDelayed(this, 4000);
            }
        };
        handler.postDelayed(r, 300);
    }

    public void init() {
        shareMusic = getSharedPreferences("music", MODE_PRIVATE);
        shareImage = getSharedPreferences("01Minute", MODE_PRIVATE);

        //map zuweisen
        myMap = shareImage.getAll();
        myMusicMap = shareMusic.getAll();

        //array für die images
        String MapString;
        for (Map.Entry<String, ?> entry : myMap.entrySet()) {
            MapString = entry.getValue().toString();
            Uri url = Uri.parse(MapString);
            counter++;
            list.add(url);
        }

        String MusicMapString;
        for (Map.Entry<String, ?> entry : myMusicMap.entrySet()) {
            MusicMapString = entry.getValue().toString();
            murl = Uri.parse(MusicMapString);
        }
    }

    //damit die Musik endet wenn man die Activity verlässt
    @Override
    public void onBackPressed() {
        mMediaPlayer.stop();
        finish();
    }
}



