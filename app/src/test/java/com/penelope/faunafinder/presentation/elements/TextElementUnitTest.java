package com.penelope.faunafinder.presentation.elements;

import static org.junit.Assert.assertEquals;

import android.graphics.Color;
import android.graphics.fonts.Font;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.penelope.faunafinder.xml.slide.BasicSlide;

import org.junit.Before;
import org.junit.Test;

/*
no idea how to get "RelativeLayout parent = findViewById(R.id.activity_main)" in this
 */

public class TextElementUnitTest{
    RelativeLayout parent;
    BasicSlide basicSlide;
    TextView textView;
    TextElement textElementTest;

    int x;
    int y;
    int width;
    int height;
    long timeOnScreen;
    int fontSize;
    Font font;
    String fontName;
    int slideWidth=1920;
    int slideHeight=500;
    int color;
    private static final int ELEMENT_ID = 8008135;

    @Before
    public void setUpTest(){

       // RelativeLayout parent = findViewById(R.id.activity_main);
       // androidx.
        //parent= Activity.(R.layout.activity_main);
       // basicSlide = new BasicSlide(slideWidth,slideHeight, "Test slide");

    }
    @Test
    public void positiveWidthAndHeightSetCorrectly() {
        fontName=null;
        color=Color.BLACK;
        x=100;
        y=500;
        width=1800;
        height= 25;
        fontSize=32;
        textElementTest = new TextElement(fontName, fontSize, Color.BLACK, x, y, width, height, timeOnScreen);
       View test= textElementTest.applyView(parent, (ViewGroup) parent, basicSlide, ELEMENT_ID);
       float expectedWidth= width* basicSlide.getCalculatedWidth()/(float) basicSlide.getWidth();
       float delta= 1.0f;
        Log.d("TEXTELEMENTTEST","Expected:"+expectedWidth);
        Log.d("TEXTELEMENTTEST","Expected:"+ test.getWidth());
        assertEquals(expectedWidth,test.getWidth(),delta);
    }
}
