package fhku.a01min_vacation;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowActivity extends AppCompatActivity {

    ImageView animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        animation = findViewById(R.id.imageanimation);
        animation.setBackgroundResource(R.drawable.animation);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        AnimationDrawable frameAnimation =
                (AnimationDrawable) animation.getBackground();

        if(hasFocus){
            frameAnimation.start();
        }else {
            frameAnimation.stop();
        }
    }
}
