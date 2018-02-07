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
import static fhku.a01min_vacation.R.id.pic1;
import static java.lang.Thread.sleep;

public class ShowActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    ImageView peter;
    List<Uri> list = new ArrayList();
    private int counter = 0;
    private int currentIndex;
    private Uri url;
    private Button startButton;
    private SharedPreferences shareMusic, shareImage;
    Map<String, ?> myMap = new HashMap();
    private final int[] imageNames =
            {R.drawable.picture1, R.drawable.picture2, R.drawable.picture3, R.drawable.picture4, R.drawable.picture5, R.drawable.picture6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        //Mediaplayer für Musik im Hintergrund, momentan mit fixer Musik
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.song);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();


        final ImageView backgroundImageView = findViewById(R.id.imageview1);

            final Handler handler = new Handler();
            Runnable r = new Runnable() {
                int i=1;
                public void run() {

                    backgroundImageView.setImageResource(imageNames[i]);
                    i++;
                    if (i >= imageNames.length) {
                        i = 0;
                    }
                    handler.postDelayed(this, 1000);
                }
            };
            handler.postDelayed(r, 300);
        }

/*
    public void init(){
        shareMusic = getSharedPreferences("01Minute",MODE_PRIVATE);
        shareImage = getSharedPreferences("01Minute", MODE_PRIVATE);

        //map zuweisen
        myMap = shareImage.getAll();
        //array für die images

        String MapString;
        for(Map.Entry<String, ?> entry: myMap.entrySet()) {
            MapString = entry.getValue().toString();
            Uri url = Uri.parse(MapString);
            counter++;
            loadImage(url);
        }

    }
    public void loadImage(Uri imageUri) {
        //input stream für Bilder mit nötigem trycatch
        InputStream inputStream;

       try {
            inputStream = getContentResolver().openInputStream(imageUri);
            //get bitmap from stream
           Bitmap image = BitmapFactory.decodeStream(inputStream);

           Drawable drawable =new BitmapDrawable(getApplicationContext().getResources(),Bitmap.createBitmap(image));
           .setImageDrawable(drawable);

            //falls das Bild nicht gefunden werden kann
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

    }*/


    //damit die Musik endet wenn man die Activity verlässt
    @Override
    public void onBackPressed() {
        mMediaPlayer.stop();
        finish();
        return;
    }
}



