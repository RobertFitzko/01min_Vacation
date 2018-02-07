package fhku.a01min_vacation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.io.InputStream;
import java.util.ArrayList;

public class SelectPicsActivity extends AppCompatActivity {
    public static final int IMAGE_GALLERY_REQUEST = 20;
    public ImageButton[] images = new ImageButton[6];
    public SharedPreferences saveData;
    public int currentImageButton;
    public Button backSelectPicsButton;
    public Uri[] picUri = new Uri[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pics);

        init();
        backStepPic();
    }

    public void backStepPic() {
        backSelectPicsButton = findViewById(R.id.back_select_pics);
        backSelectPicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectPicsActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init() {
        saveData = getSharedPreferences("01Minute", MODE_PRIVATE);

        images[0] = (ImageButton) findViewById(R.id.pic1);
        images[1] = (ImageButton) findViewById(R.id.pic2);
        images[2] = (ImageButton) findViewById(R.id.pic3);
        images[3] = (ImageButton) findViewById(R.id.pic4);
        images[4] = (ImageButton) findViewById(R.id.pic5);
        images[5] = (ImageButton) findViewById(R.id.pic6);


        for (int i = 0; i < images.length; i++) {
            images[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //impliziter Intent für Gallerie
                    Intent imagePickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    //Wo sind die Bilder?
                    File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    String pictureDirectoryPath = pictureDirectory.getPath();
                    //URI
                    Uri data = Uri.parse(pictureDirectoryPath);
                    //Data und Typ setzen, in diesem Fall alle Bild-Dateitypen
                    imagePickerIntent.setDataAndType(data, "image/*");
                    //switch case um jeweils Bild auf den jeweils geklickten Button zuzuweisen
                    switch (v.getId()) {
                        case R.id.pic1:
                            currentImageButton = 0;
                            break;
                        case R.id.pic2:
                            currentImageButton = 1;
                            break;
                        case R.id.pic3:
                            currentImageButton = 2;
                            break;
                        case R.id.pic4:
                            currentImageButton = 3;
                            break;
                        case R.id.pic5:
                            currentImageButton = 4;
                            break;
                        case R.id.pic6:
                            currentImageButton = 5;
                            break;
                        default:
                            throw new RuntimeException("Unknow button ID");
                    }
                    //Activity for Result aufrufen
                    startActivityForResult(imagePickerIntent, IMAGE_GALLERY_REQUEST);
                }
            });
        }
        loadImages();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //wenn es sich wirklich um einen aufruf von unseren buttons handelt
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                //Uri imageUri = data.getData(); // dont know if thats right
                Uri imageUri = data.getData();
                Log.i("PicUri = ", "" + imageUri);
                this.saveImage(currentImageButton, imageUri);
                this.loadImage(imageUri);
            }
        }
    }

    public void loadImages() {

        for (int i = 0; i < 6; i++) {
            String uri = saveData.getString("image" + i, null);

            if (uri != null) {
                Uri parsedUri = Uri.parse(uri);
                picUri[i] = parsedUri;
                loadImage(parsedUri);
            }
        }
    }

    public void saveImage(int index, Uri imageUri) {
        picUri[index] = imageUri;

        SharedPreferences.Editor editor = saveData.edit();
        editor.putString("image" + index, imageUri.toString());
        editor.commit();

        Log.i("SAVE IMAGES", "Uri: " + imageUri + ", index: " + index);
    }

    public void loadImage(Uri imageUri) {
        //input stream für Bilder mit nötigem trycatch
        InputStream inputStream;
        try {
            inputStream = getContentResolver().openInputStream(imageUri);
            //get bitmap from stream
            Bitmap image = BitmapFactory.decodeStream(inputStream);
            //image wird auf den jeweiligen button gesetzt falls frei
            images[currentImageButton].setImageBitmap(image);
            //falls das Bild nicht gefunden werden kann
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}