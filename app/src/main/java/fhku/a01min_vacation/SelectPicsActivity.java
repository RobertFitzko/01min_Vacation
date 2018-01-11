package fhku.a01min_vacation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;

public class SelectPicsActivity extends AppCompatActivity {

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
               /* Intent intent = new Intent(CreateActivity.this,PickActivity.class);
                startActivity(intent);*/

                    //IMG PICKER
                    Intent intent = new Intent();
                    intent.setType("image/*");
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
                    }*/
            } else if (data.getData() != null) {
                String imagePath = data.getData().getPath();
                //do something with the image (save it to some directory or whatever you need to do with it here)
                Log.i("PIC_PATH", "====" + imagePath.toString());

                paths[0] = imagePath.toString();

            }
        }
    }

    /*
        for (int i = 0;i< paths.length;i++){
            Log.i("ARRAYCHECK","ARRAYNO "+i+" CONTAINS: "+paths[i]);
        }
        */
}

