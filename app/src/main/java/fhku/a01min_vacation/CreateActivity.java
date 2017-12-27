package fhku.a01min_vacation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonpics = (Button) findViewById(R.id.pics);
        buttonpics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        Button buttonmusic = (Button) findViewById(R.id.music);
        buttonmusic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
    }
}
