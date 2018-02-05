package fhku.a01min_vacation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
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
    //in der saveM müsste es jetzt drinnen sein
    public SharedPreferences saveM;
    public Uri[] muUri = new Uri[1];
    public int currentMusic;

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
                Intent musicPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                Log.i("Music Picker", musicPickerIntent.toString());
                File musicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                String musicDirectoryPath = musicDirectory.getPath();
                Log.i("Music Path", musicDirectoryPath);
                //da haben wir das path schon mit musicDirectoryPath
                Uri dataM = Uri.parse(musicDirectoryPath);
                //Activity for Result aufrufen
                startActivityForResult(musicPickerIntent, MUSIC_GALLERY_REQUEST);


//neu
                /*
                try{
                    saveM = getSharedPreferences("musicDirectoryPath",0);
                    String audiouri = saveM.getString("musicDirectoryPath", null);
                    if (audiouri !=null) {
                        Uri mUri = Uri.parse(audiouri);
                        mnp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mnp.setDataSource(c, mUri);
                        mnp.prepare();
                        mnp.start();
                        Log.i("Shared Path", audiouri);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                */

            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode == RESULT_OK) {
            //wenn es sich wirklich um einen aufruf von unseren buttons handelt
            if(requestCode==IMAGE_GALLERY_REQUEST){
                Uri imageUri = data.getData();

                Log.i("PicUri = ",""+ musicUri);

                this.saveMusic(currentMusic, musicUri);
                this.loadMusic();
            };
        };

    }

    public void loadMusic(){
        for(int i = 0; i<1;i++) {
            String uri = saveM.getString("music", null);
            Log.i("SAVE MUSIC", "Uri = " + musicUri);

        if (uri != null) {
            Uri parsedUri = Uri.parse(uri);
            musicUri = parsedUri;
            loadMusic();
        }

        }
    }

    public void saveMusic(int index, Uri musicUri) {
        muUri[index] = musicUri;

        SharedPreferences.Editor editor = saveM.edit();
        editor.putString("music" + index, musicUri.toString());
        editor.commit();

        Log.i("SAVE MUSIC", "Uri: " + musicUri + ", index: " + index);
    }
}




//neu

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataM) {
        super.onActivityResult(requestCode, resultCode, dataM);
        if (requestCode == 1) {
            if (dataM != null) {
                mp = new MediaPlayer();
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    uri = dataM.getData();
                    String pathm = uri.toString();
                    SharedPreferences.Editor editor = getSharedPreferences("mypref", 0).edit();
                    editor.putString("useraudio ", pathm);
                    editor.commit();
                    if (uri != null) {
                        mp.setDataSource(getApplicationContext(), uri);
                        mp.prepare();
                        mp.start();
                       Log.i("Index = ", pathm);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }


        }

    }

}


/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataM) {
        super.onActivityResult(requestCode, resultCode, dataM);
        if (resultCode == RESULT_OK && requestCode == MUSIC_GALLERY_REQUEST) {
            Uri uriSound = dataM.getData();
            Toast.makeText(this, "Music selected!", Toast.LENGTH_LONG).show();

            musicUri = uriSound;
            Log.i("MusicUri=", "" + musicUri);
            //this.saveMusic(,musicUri);

        }
    }
    public void saveMusic(int index, Uri musicUri) {

        SharedPreferences.Editor editor = saveM.edit();
        editor.putString("music" + index, musicUri.toString());
        editor.commit();

        Log.i("SAVE MUSIC", "Uri: " + musicUri + ", index: " + index);
    }

    public void loadMusic(){
        String uri = saveM.getString("music",null);
        Log.i("SAVE MUSIC", "Uri = "+ musicUri);

        if (uri != null) {
            Uri parsedUri = Uri.parse(uri);

        }
    }
*/
