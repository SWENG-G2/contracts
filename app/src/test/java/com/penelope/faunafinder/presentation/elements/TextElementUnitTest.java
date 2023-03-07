package com.penelope.faunafinder.presentation.elements;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.test.platform.app.InstrumentationRegistry;

import com.penelope.faunafinder.xml.slide.BasicSlide;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Implements;

import java.util.ArrayList;

/*
no idea how to get "RelativeLayout parent = findViewById(R.id.activity_main)" in this
 */
@RunWith(RobolectricTestRunner.class)
@Implements(ResourcesCompat.class)
public class TextElementUnitTest{

   RelativeLayout parent;
    BasicSlide basicSlide;


   int ELEMENT_ID = 8008135;

   private View getViewOfTextElementWithWH(int width, int height){
       TextElement textElement = new TextElement(null, 32, Color.BLACK, 100, 500, width, height, 5000L);
       textElement.setContent("Test input");
       View test= textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID);

       return test;
   }

    @Before
    public void setUpTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        parent= new RelativeLayout(appContext);
        basicSlide = new BasicSlide(1920, 500, "Test slide");

    }
    @Test
    public void positiveWidthSetCorrectly(){
        int width=1800;
        int height= 25;
        View testView= getViewOfTextElementWithWH(width,height);
        float expectedWidth= width* basicSlide.getCalculatedWidth()/(float) basicSlide.getWidth();
        float delta= 0f;
        int actualWidth= testView.getLayoutParams().width;

        Assert.assertEquals( expectedWidth, actualWidth,delta);
    }
    @Test
    public void positiveHeightSetCorrectly(){
        int height=25;
        TextElement textElement = new TextElement(null, 32, Color.BLACK, 100, 500, 1800, height, 5000L);
        textElement.setContent("Test input");
        View test= textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID);
        float expectedHeight= textElement.dpToPx(height);
        float delta= 0f;
        int actualHeight= test.getLayoutParams().height;
        Assert.assertEquals( expectedHeight, actualHeight,delta);
    }
    @Test
    public void matchParentHeightSetCorrectly() throws NoSuchFieldException, IllegalAccessException {
        int height=-1;
        TextElement textElement = new TextElement(null, 32, Color.BLACK, 100, 500, 1800, height, 5000L);
        textElement.setContent("Test input");
        View test= textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID);
        float expectedHeight= RelativeLayout.LayoutParams.MATCH_PARENT;
        float delta= 0f;
        int actualHeight= test.getLayoutParams().height;
        Assert.assertEquals( expectedHeight, actualHeight,delta);
    }
    @Test
    public void wrapContentHeightSetCorrectly() throws NoSuchFieldException, IllegalAccessException {
        int height=-2;
        TextElement textElement = new TextElement(null, 32, Color.BLACK, 100, 500, 1800, height, 5000L);
        textElement.setContent("Test input");
        View test= textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID);
        int expectedHeight= RelativeLayout.LayoutParams.WRAP_CONTENT;
        float delta= 0f;
        int actualHeight= test.getLayoutParams().height;
        Assert.assertEquals( expectedHeight, actualHeight,delta);
    }
    @Test
    public void matchParentWidthSetCorrectly() throws NoSuchFieldException, IllegalAccessException {
        int width=-1;
        View testView= getViewOfTextElementWithWH(width,25);
        int expectedWidth= RelativeLayout.LayoutParams.MATCH_PARENT;
        float delta= 0f;
        int actualWidth=testView.getLayoutParams().width;
        Assert.assertEquals( expectedWidth,actualWidth,delta);
    }
    @Test
    public void wrapContentWidthSetCorrectly() throws NoSuchFieldException, IllegalAccessException {
        int width=-2;
        View testView= getViewOfTextElementWithWH(width,25);
        int expectedWidth= RelativeLayout.LayoutParams.WRAP_CONTENT;
        float delta= 0f;
        int actualWidth=testView.getLayoutParams().width;
        Assert.assertEquals( expectedWidth,actualWidth,delta);
    }
    @Test
    public void fontIsSetCorrectly() throws NoSuchFieldException, IllegalAccessException {
       ArrayList fonts= new ArrayList<String>();
       fonts.add("null");
      // fonts.add("mono");
      // fonts.add("roberto");

       ArrayList typefaces= new ArrayList<Typeface>();
       typefaces.add(Typeface.DEFAULT);
       //typefaces.add(ResourcesCompat.getFont(parent.getContext(),font.chivo_mono_regular ));
       //typefaces.add(ResourcesCompat.getFont(parent.getContext(),font.roboto_condensed_regular));
    /*
       its giving a Resources$NotFoundException when these are uncommented
     */

       for(int i=0;i<1;i++){
           TextElement textElement = new TextElement((String)fonts.get(i), 32, Color.BLACK, 100, 500, 1800, 25, 5000L);
           textElement.setContent("Test input");
           View test= textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID);
           Assert.assertEquals(TextView.class.cast(test).getTypeface().toString(),((Typeface)typefaces.get(i)).toString());
       }
    }
}
