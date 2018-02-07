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
    public String[] paths = new String[6];

    public void init() {
        picButton = (Button) findViewById(R.id.pics);
        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IMG PICKER
                Intent intent = new Intent(CreateActivity.this, SelectPicsActivity.class);
                startActivity(intent);
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
}