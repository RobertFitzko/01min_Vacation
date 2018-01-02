package fhku.a01min_vacation;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.ViewFlipper;

public class ShowActivity extends AppCompatActivity {

     ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        viewFlipper = (ViewFlipper) this.findViewById(R.id.viewflipper);
        viewFlipper.setFlipInterval(1000);



    }
}



