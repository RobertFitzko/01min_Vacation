package fhku.a01min_vacation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.File;

public class SelectMusic extends AppCompatActivity {

    public static final int MUSIC_GALLERY_REQUEST = 30;
    public Button backSelectMusicButton;
    public Button getMusic;
    public Uri musicUri;
    public SharedPreferences saveM;

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
        saveM = getSharedPreferences("01Minute", MODE_PRIVATE);

        getMusic = findViewById(R.id.get_music);
        getMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //impliziter Intent für Gallerie
                Intent musicPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                Log.i("Music Picker", musicPickerIntent.toString());
                File musicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);

                String musicDirectoryPath = musicDirectory.getPath();

                Uri data = Uri.parse(musicDirectoryPath);
                musicPickerIntent.setDataAndType(data, "audio/*");

                //Activity for Result aufrufen
                startActivityForResult(musicPickerIntent, MUSIC_GALLERY_REQUEST);

            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //wenn es sich wirklich um einen aufruf von unseren buttons handelt
            if (requestCode == MUSIC_GALLERY_REQUEST) {
                Uri musicUri = data.getData();

                this.saveMusic(musicUri);
                this.loadMusic();
            }
            ;
        }
        ;
    }

    public void loadMusic() {

        String uri = saveM.getString("music", null);
        Log.i("SAVE MUSIC", "Uri = " + musicUri);
    }


    public void saveMusic(Uri musicUri) {

        SharedPreferences.Editor editor = saveM.edit();
        editor.putString("music", musicUri.toString());
        editor.commit();

        Log.i("SAVE MUSIC", "Uri: " + musicUri);
    }
}
