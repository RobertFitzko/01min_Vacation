package fhku.a01min_vacation;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    public Button picButton;
    public Button musicButton;
    public Button backCreateButton;
    public int SELECT_PICTURES;
    public String[] paths = new String[6];


    public void init() {
        picButton = (Button) findViewById(R.id.pics);
        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent für PickActivity
               /* Intent intent = new Intent(CreateActivity.this,PickActivity.class);
                startActivity(intent);*/

                //IMG PICKER
                Intent intent = new Intent(CreateActivity.this, SelectPicsActivity.class);
                startActivity(intent);
/*
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURES);
                */
            }
        });
        musicButton = (Button) findViewById(R.id.music);
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this, SelectMusic.class);
                startActivity(intent);
            }
        });
        backCreateButton = (Button) findViewById(R.id.back_create);
        backCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        init();

    }


/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PICTURES) {
            if(resultCode == Activity.RESULT_OK) {
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
                    }
                } else if(data.getData() != null) {
                    String imagePath = data.getData().getPath();
                    //do something with the image (save it to some directory or whatever you need to do with it here)
                    Log.i("PIC_PATH","===="+imagePath.toString());

                    paths[0]=imagePath.toString();

                }
            }
        }
        for (int i = 0;i< paths.length;i++){
            Log.i("ARRAYCHECK","ARRAYNO "+i+" CONTAINS: "+paths[i]);
        }
    }*/
}