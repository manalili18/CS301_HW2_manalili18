package com.example.manalili18.cs301_hw2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * MainActivity.java
 *
 * This class controls the functionality of the app. Nested inside is a listener that can see
 * all the views that the activity sees.
 *
 * @author Phillip Manalili-Simeon
 *
 */

public class MainActivity extends AppCompatActivity {

    Face face;
    MyListener listener;

    Button randomBut;
    Spinner hairSpin;

    SeekBar redSb;
    SeekBar greenSb;
    SeekBar blueSb;

    TextView redTv;
    TextView blueTv;
    TextView greenTv;

    RadioButton hair;
    RadioButton eyes;
    RadioButton skin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setup all views with corresponding listeners
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        face = (Face) findViewById(R.id.surfaceView);
        listener = new MyListener();

        randomBut = (Button) findViewById(R.id.button);
        randomBut.setOnClickListener(listener);

        hairSpin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hairstyles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSpin.setAdapter(adapter);
        hairSpin.setOnItemSelectedListener(listener);

        redSb = (SeekBar) findViewById(R.id.red);
        redSb.setOnSeekBarChangeListener(listener);
        greenSb = (SeekBar) findViewById(R.id.green);
        greenSb.setOnSeekBarChangeListener(listener);
        blueSb = (SeekBar) findViewById(R.id.blue);
        blueSb.setOnSeekBarChangeListener(listener);

        redTv = (TextView) findViewById(R.id.redVal);
        greenTv = (TextView) findViewById(R.id.greenVal);
        blueTv = (TextView) findViewById(R.id.blueVal);

        hair = (RadioButton) findViewById(R.id.hair);
        hair.setOnClickListener(listener);
        eyes = (RadioButton) findViewById(R.id.eyes);
        eyes.setOnClickListener(listener);
        skin = (RadioButton) findViewById(R.id.skin);
        skin.setOnClickListener(listener);

        hair.performClick();
    }

    //nested class so it has visibility of all the views declared above
    class MyListener implements SeekBar.OnSeekBarChangeListener, View.OnClickListener,
            Spinner.OnItemSelectedListener{

        public MyListener(){}

        @Override
        public void onProgressChanged(SeekBar sb, int progress, boolean b) {
            //update color attributes of hair face or eyes.
            switch (sb.getId()){
                case R.id.red:
                    redTv.setText(""+progress);

                    if(hair.isChecked()){
                        face.setHairColor(progress,0);
                    } else if(eyes.isChecked()){
                        face.setEyeColor(progress,0);
                    } else if(skin.isChecked()){
                        face.setSkinColor(progress,0);
                    }
                    break;

                case R.id.green:
                    greenTv.setText(""+progress);

                    if(hair.isChecked()){
                        face.setHairColor(progress,1);
                    } else if(eyes.isChecked()){
                        face.setEyeColor(progress,1);
                    } else if(skin.isChecked()){
                        face.setSkinColor(progress,1);
                    }
                    break;

                case R.id.blue:
                    blueTv.setText(""+progress);

                    if(hair.isChecked()){
                        face.setHairColor(progress,2);
                    } else if(eyes.isChecked()){
                        face.setEyeColor(progress,2);
                    } else if(skin.isChecked()){
                        face.setSkinColor(progress,2);
                    }
                    break;

                default:
                    Log.i("sb progress change", "this should never print");
                    break;
            }
        }

        @Override
        public void onStopTrackingTouch(SeekBar sb){}

        @Override
        public void onStartTrackingTouch(SeekBar sb){}

        @Override
        public void onClick(View v){

            //depending on view, either update the seekbars or randomize features
            switch(v.getId()){
                case R.id.skin:
                    redSb.setProgress(Color.red(face.skinColor));
                    blueSb.setProgress(Color.blue(face.skinColor));
                    greenSb.setProgress(Color.green(face.skinColor));
                    break;
                case R.id.eyes:
                    redSb.setProgress(Color.red(face.eyeColor));
                    blueSb.setProgress(Color.blue(face.eyeColor));
                    greenSb.setProgress(Color.green(face.eyeColor));
                    break;
                case R.id.hair:
                    redSb.setProgress(Color.red(face.hairColor));
                    blueSb.setProgress(Color.blue(face.hairColor));
                    greenSb.setProgress(Color.green(face.hairColor));
                    break;
                case R.id.button:
                    face.randomize();

                    if(hair.isChecked()){
                        hair.performClick();
                    } else if(skin.isChecked()){
                        skin.performClick();
                    } else if(eyes.isChecked()){
                        eyes.performClick();
                    } else {
                        hair.performClick();
                    }

                    switch(face.hairStyle){
                        case BALD:
                            hairSpin.setSelection(0);
                            break;
                        case AFRO:
                            hairSpin.setSelection(1);
                            break;
                        case MOHAWK:
                            hairSpin.setSelection(2);
                            break;
                    }

                break;
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // based on position, update the hairstyle
            face.setHairStyle(pos);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
}


