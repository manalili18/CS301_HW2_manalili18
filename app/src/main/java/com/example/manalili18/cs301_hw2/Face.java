package com.example.manalili18.cs301_hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Face.java
 *
 * This class models and draws the face.
 *
 * @author Phillip Manalili-Simeon
 */

public class Face extends SurfaceView {

    private final static int OPAQUE = 0xFF000000;
    private final static int MAX_COLOR = 0x00FFFFFF;
    private final static int MAX_HAIR_STYLE = HAIR_STYLE.values().length;

    protected enum HAIR_STYLE {
        BALD,
        AFRO,
        MOHAWK
    }

    protected int skinColor, eyeColor, hairColor;
    protected HAIR_STYLE hairStyle;
    private Paint skinPaint, eyePaint, hairPaint;
    public static Random random;

    public Face(Context context) {
        super(context);
        ctorInit();
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctorInit();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctorInit();
    }

    public void ctorInit() {

        //initialize default values with empty objects
        this.setWillNotDraw(false);

        skinColor = eyeColor = hairColor = 0;

        hairStyle = HAIR_STYLE.BALD;

        skinPaint = new Paint();
        eyePaint = new Paint();
        hairPaint = new Paint();

        random = new Random();

        //then randomize features
        randomize();
    }

    public void randomize() {

        //random int color values
        skinColor = OPAQUE + random.nextInt(MAX_COLOR + 1);
        eyeColor = OPAQUE + random.nextInt(MAX_COLOR + 1);
        hairColor = OPAQUE + random.nextInt(MAX_COLOR + 1);


        /**
         External Citation
         Date: 16 February 2018
         Problem: Couldn't coerce an enum into an int and vice-versa
         Resource:
         https://stackoverflow.com/questions/1972392/java-pick-a-random-value-from-an-enum
         Solution: I used the example code from this post.
         */

        hairStyle = HAIR_STYLE.values()[random.nextInt(MAX_HAIR_STYLE)];

        //apply those colors to paints
        skinPaint.setColor(skinColor);
        eyePaint.setColor(eyeColor);
        hairPaint.setColor(hairColor);

        invalidate();

    }

    public void setSkinColor(int colorVal, int rgb){

        //disas the color
        int red = Color.red(skinColor);
        int green = Color.green(skinColor);
        int blue = Color.blue(skinColor);

        //depending on the rgb index passed by MainActivity.java, change only that color value
        switch(rgb){
            case 0: //red
                red = colorVal;
                break;
            case 1: //green
                green = colorVal;
                break;
            case 2: //blue
                blue = colorVal;
                break;

        }

        //reas the color
        skinColor = Color.argb(0xFF,red,green,blue);
        skinPaint.setColor(skinColor);

        invalidate();
    }

    public void setEyeColor(int colorVal, int rgb){
        int red = Color.red(eyeColor);
        int green = Color.green(eyeColor);
        int blue = Color.blue(eyeColor);

        switch(rgb){
            case 0: //red
                red = colorVal;
                break;
            case 1: //green
                green = colorVal;
                break;
            case 2: //blue
                blue = colorVal;
                break;

        }

        eyeColor = Color.argb(0xFF,red,green,blue);
        eyePaint.setColor(eyeColor);

        invalidate();
    }

    public void setHairColor(int colorVal, int rgb){
        int red = Color.red(hairColor);
        int green = Color.green(hairColor);
        int blue = Color.blue(hairColor);

        switch(rgb){
            case 0: //red
                red = colorVal;
                break;
            case 1: //green
                green = colorVal;
                break;
            case 2: //blue
                blue = colorVal;
                break;

        }

        hairColor = Color.argb(0xFF,red,green,blue);
        hairPaint.setColor(hairColor);

        invalidate();
    }

    public void setHairStyle(int pos){

        hairStyle = HAIR_STYLE.values()[pos];

        invalidate();
    }

    public void onDraw(Canvas c) {
        //paint for eye whites
        Paint white = new Paint();
        white.setColor(Color.WHITE);
        //paint for mouth
        Paint black = new Paint();
        black.setColor(Color.BLACK);

        //get some standardized values based on the surface view size
        int xCtr = this.getWidth() / 2;
        int yCtr = this.getHeight() * 3 / 5;
        int eyeDist = 160;

        //draw hair
        switch(hairStyle){
            case BALD:
                break;
            case AFRO:

                // 3rd line down is repeated a few times
                // essentially, it sizes the drawing appropriately
                // works best in portrait

                c.drawCircle(xCtr,
                        yCtr - 250,
                        (this.getWidth() > this.getHeight()) ? this.getHeight() * 2 / 5 : this.getWidth()* 2 / 5,
                        hairPaint);
                break;
            case MOHAWK:
                c.drawRect( xCtr - 100,
                        yCtr - 800,
                        xCtr + 100,
                        yCtr,
                        hairPaint);
                break;
            default:
                break;

        }

        //face
        c.drawCircle(xCtr,
                yCtr,
                (this.getWidth() > this.getHeight()) ? this.getHeight() * 2 / 6 : this.getWidth() * 2 / 6,
                skinPaint);

        //eyes
        //whites
        c.drawCircle(xCtr-eyeDist,yCtr-80,80,white);
        c.drawCircle(xCtr+eyeDist,yCtr-80,80,white);

        //iris
        c.drawCircle(xCtr-eyeDist,yCtr-80,50,eyePaint);
        c.drawCircle(xCtr+eyeDist,yCtr-80,50,eyePaint);

        //mouth
        c.drawRect(xCtr-200,yCtr+190,xCtr+200,yCtr+200,black);

    }

}

