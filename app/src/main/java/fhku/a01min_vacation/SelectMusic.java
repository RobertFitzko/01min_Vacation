package fhku.a01min_vacation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectMusic extends AppCompatActivity {

    public Button backSelectMusicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_music);

        backStepMusic();
    }

    public void backStepMusic(){
        backSelectMusicButton=findViewById(R.id.back_select_music);
        backSelectMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectMusic.this, CreateActivity.class);
                startActivity(intent);
            }
        });
    }
}
