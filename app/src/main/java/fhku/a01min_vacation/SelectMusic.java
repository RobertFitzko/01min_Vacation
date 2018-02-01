package fhku.a01min_vacation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static fhku.a01min_vacation.SelectPicsActivity.IMAGE_GALLERY_REQUEST;

public class SelectMusic extends AppCompatActivity {

    public static final int MUSIC_GALLERY_REQUEST = 30;
    public Button backSelectMusicButton;
    public Button getMusic;
    public Uri musicUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_music);

        backStepMusic();
        init();

    }

    public void backStepMusic() {
        backSelectMusicButton = findViewById(R.id.back_select_music);
        backSelectMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectMusic.this, CreateActivity.class);
                startActivity(intent);
            }
        });
    }


    public void init() {
        getMusic = findViewById(R.id.get_music);
        getMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //impliziter Intent für Gallerie
                Intent musicPickerIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);

                File musicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                String musicDirectoryPath = musicDirectory.getPath();

                Uri data = Uri.parse(musicDirectoryPath);
                //Activity for Result aufrufen
                startActivityForResult(musicPickerIntent, MUSIC_GALLERY_REQUEST);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == MUSIC_GALLERY_REQUEST) {
            Uri uriSound = data.getData();
            Toast.makeText(this, "Music selected!", Toast.LENGTH_LONG).show();

            musicUri = uriSound;
            Log.i("MusicUri=", "" + musicUri);

        }
    }

}






