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

public class SelectPicsActivity extends AppCompatActivity {
    public static final int IMAGE_GALLERY_REQUEST = 20;
    public ImageButton[] images = new ImageButton[6];
    public SharedPreferences saveData;
    public int currentImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pics);

        init();
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
                    Intent imagePickerIntent = new Intent(Intent.ACTION_PICK);
                    //Wo sind die Bilder?
                    File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    String pictureDirectoryPath = pictureDirectory.getPath();
                    //URI
                    Uri data = Uri.parse(pictureDirectoryPath);
                    //set data and type - all image types
                    imagePickerIntent.setDataAndType(data, "image/*");

                    //invoking Activity for Result
                    startActivityForResult(imagePickerIntent, IMAGE_GALLERY_REQUEST);
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
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode == RESULT_OK) {
            if(requestCode==IMAGE_GALLERY_REQUEST){
                Uri imageUri = data.getData();
                //input stream for pictures with exception handling
                InputStream inputStream;

                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    //get bitmap from stram
                    Bitmap image = BitmapFactory.decodeStream(inputStream);

                    //image wird auf den jeweiligen button gesetzt falls frei
                        images[currentImageButton].setImageBitmap(image);
                    
                    //falls das Bild nicht gefunden werden kann
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this,"Bild konnte nicht gefunden werden",Toast.LENGTH_LONG).show();
                }
            };
        };

    }

}
/*

    public ImageButton[] selectButtons = new ImageButton[8];
    public int SELECT_PICTURES;
    public String[] paths = new String[8];
    public SharedPreferences saveData;
    public int lastSelectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pics);
        init();
    }

    public void init() {
        saveData = getSharedPreferences("01Minute", MODE_PRIVATE);


        selectButtons[0] = (ImageButton) findViewById(R.id.pic1);
        selectButtons[1] = (ImageButton) findViewById(R.id.pic2);
        selectButtons[2] = (ImageButton) findViewById(R.id.pic3);
        selectButtons[3] = (ImageButton) findViewById(R.id.pic4);
        selectButtons[4] = (ImageButton) findViewById(R.id.pic5);
        selectButtons[5] = (ImageButton) findViewById(R.id.pic6);
        selectButtons[6] = (ImageButton) findViewById(R.id.pic7);
        selectButtons[7] = (ImageButton) findViewById(R.id.pic8);

        for (int i = 0; i<selectButtons.length;i++) {
            String uri = saveData.getString("Index" + i, null);
            if (uri != null){
                selectButtons[i].setImageURI(Uri.fromFile(new File(uri)));
            }
            final int j = i;
            selectButtons[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Intent für PickActivity
               */
/* Intent intent = new Intent(CreateActivity.this,PickActivity.class);
                startActivity(intent);*//*


                    //IMG PICKER
                    Intent intent = new Intent();
                    intent.setType("image*/
/*");
                    intent.putExtra("Index", j);
                    intent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURES);
                    lastSelectedIndex = j;
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURES) {
            if (resultCode == Activity.RESULT_OK) {
                Log.i("Image Index", "" + lastSelectedIndex);
                SharedPreferences.Editor editor = saveData.edit();
                editor.putString("Index" + lastSelectedIndex, data.getClipData().getItemAt(0).getUri().toString());
                editor.apply();
                */
/*
                if(data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();
                    int currentItem = 0;
                    // LIMIT AUF 6 fehlt (exeption oder sowas)
                    while(currentItem < count) {
                        Uri imageUri = data.getClipData().getItemAt(currentItem).getUri();
                        String imageUriPath = imageUri.getPath();
                        //do something with the image (save it to some directory or whatever you need to do with it here)
                        Log.i("PIC_URI","==== "+imageUriPath); // mglweise benutzbarer pfad?!
                        paths[currentItem] = imageUri.toString(); //gesamte URI als string
                        currentItem ++;
                    }*//*

            } else if (data.getData() != null) {
                String imagePath = data.getData().getPath();
                //do something with the image (save it to some directory or whatever you need to do with it here)
                Log.i("PIC_PATH", "====" + imagePath.toString());

                paths[0] = imagePath.toString();

            }
        }
    }

    */
/*
        for (int i = 0;i< paths.length;i++){
            Log.i("ARRAYCHECK","ARRAYNO "+i+" CONTAINS: "+paths[i]);
        }
        *//*

}
*/

