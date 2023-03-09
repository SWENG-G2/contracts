package com.penelope.faunafinder.presentation.elements;

import static com.penelope.faunafinder.presentation.elements.PresentationElement.displayMetrics;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.test.platform.app.InstrumentationRegistry;

import com.penelope.faunafinder.R;
import com.penelope.faunafinder.xml.slide.BasicSlide;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Implements;

import java.util.ArrayList;

/*
Tests I need:
size: width(done), height(done), font size(done), margin params (x and y pos)(done)
font(done)
font color(done)
time on screen(not done)
text on view(done)
setContent

 */
@RunWith(RobolectricTestRunner.class)
@Implements(ResourcesCompat.class)
public class TextElementUnitTest{

   RelativeLayout parent;
    BasicSlide basicSlide;
   int ELEMENT_ID = 8008135;

    @Before
    public void setUpTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        parent= new RelativeLayout(appContext);
        basicSlide = new BasicSlide(1920, 500, "Test slide");
    }
    @Test
    public void widthIsSetCorrectly(){
       int height= 100;
       ArrayList testWidths= new ArrayList<Integer>();
       testWidths.add(100);
       testWidths.add(-1);
       testWidths.add(-2);

        ArrayList expectedWidths= new ArrayList<Integer>();
        expectedWidths.add(Math.round(100* basicSlide.getCalculatedWidth()/(float) basicSlide.getWidth()));
        expectedWidths.add(RelativeLayout.LayoutParams.MATCH_PARENT);
        expectedWidths.add(RelativeLayout.LayoutParams.WRAP_CONTENT);

        for(int i=0;i<3;i++){
            TextElement textElement = new TextElement(null, 32, Color.BLACK, 100, 500, (int)testWidths.get(i), height, 5000L);
            textElement.setContent("Test input");
            View testView= textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID);
            int actualWidth= testView.getLayoutParams().width;
            Assert.assertEquals(expectedWidths.get(i), actualWidth);
        }
    }
    @Test
    public void heightIsSetCorrectly(){
        int width= 100;
        ArrayList testHeights= new ArrayList<Integer>();
        testHeights.add(100);
        testHeights.add(-1);
        testHeights.add(-2);

        ArrayList expectedHeights= new ArrayList<Integer>();
        expectedHeights.add(Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, displayMetrics)));
        expectedHeights.add(RelativeLayout.LayoutParams.MATCH_PARENT);
        expectedHeights.add(RelativeLayout.LayoutParams.WRAP_CONTENT);

        for(int i=0;i<3;i++){
            TextElement textElement = new TextElement(null, 32, Color.BLACK, 100, 500,width, (int)testHeights.get(i), 5000L);
            textElement.setContent("Test input");
            View testView= textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID);
            int actualHeight= testView.getLayoutParams().height;
            Assert.assertEquals(expectedHeights.get(i), actualHeight);
        }
    }
    @Test
    public void fontIsSetCorrectly() {
       ArrayList fonts= new ArrayList<String>();
       fonts.add("null");
       fonts.add("mono");
       fonts.add("roberto");

       ArrayList typefaces= new ArrayList<Typeface>();
       typefaces.add(Typeface.DEFAULT);
       typefaces.add(ResourcesCompat.getFont(parent.getContext(), R.font.chivo_mono_regular));
       typefaces.add(ResourcesCompat.getFont(parent.getContext(),  R.font.chivo_mono_regular));

       for(int i=0;i<1;i++){
           TextElement textElement = new TextElement((String)fonts.get(i), 32, Color.BLACK, 100, 500, 1800, 25, 5000L);
           textElement.setContent("Test input");
           View test= textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID);
           Assert.assertEquals(TextView.class.cast(test).getTypeface().toString(),((Typeface)typefaces.get(i)).toString());
       }
    }
    @Test
    public void fontColorIsCorrect(){
        ArrayList expectedColors= new ArrayList<Integer>();
        expectedColors.add(Color.BLACK);
        expectedColors.add(Color.WHITE);
        expectedColors.add(Color.rgb(200,200,200));
        for(int i=0;i<expectedColors.size();i++){
            TextElement textElement = new TextElement(null, 32, (int)expectedColors.get(i), 100, 500, 500, 25, 5000L);
            textElement.setContent("Test input");
            TextView testView= TextView.class.cast(textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID));
            int actualColor= testView.getCurrentTextColor();
            Assert.assertEquals(expectedColors.get(i), actualColor);
        }
    }
   @Test
   public void yParamsSetCorrectly(){
        int x= 200;
       ArrayList testYPositions= new ArrayList<Integer>();
       testYPositions.add(5); //x
       testYPositions.add(10); //y
       testYPositions.add(300); //x

       for(int i=0;i<testYPositions.size();i++){
           TextElement textElement = new TextElement(null, 32, Color.BLACK, x,(int)testYPositions.get(i), 1000, 25, 5000L);
           textElement.setContent("Test input");
           TextView testView= TextView.class.cast(textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID));
           ViewGroup.MarginLayoutParams mlp= (ViewGroup.MarginLayoutParams) testView.getLayoutParams();
           int actualY= mlp.topMargin;
           int expectedY= Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (int)testYPositions.get(i), displayMetrics));
           Assert.assertEquals(expectedY, actualY);
       }
   }
    @Test
    public void xParamSetCorrectly(){
        int y= 200;
        ArrayList testXPositions= new ArrayList<Integer>();
        testXPositions.add(5); //x
        testXPositions.add(2); //x
        testXPositions.add(100); //x

        for(int i=0;i<testXPositions.size();i++){
            TextElement textElement = new TextElement(null, 32, Color.BLACK, (int)testXPositions.get(i),y, 1000, 25, 5000L);
            textElement.setContent("Test input");
            TextView testView= TextView.class.cast(textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID));
            ViewGroup.MarginLayoutParams mlp= (ViewGroup.MarginLayoutParams) testView.getLayoutParams();
            int actualX= mlp.leftMargin;
            int expectedX=  Math.round((((int)testXPositions.get(i)) * basicSlide.getCalculatedWidth()) / (float) basicSlide.getWidth());
            Assert.assertEquals(expectedX, actualX);
        }
    }
    @Test
    public void fontSizeIsSetCorrectly(){
        ArrayList testFontSizes= new ArrayList<Integer>();
        testFontSizes.add(1);
        testFontSizes.add(5);
        testFontSizes.add(10);
        for(int i=0;i<testFontSizes.size();i++) {
            TextElement textElement = new TextElement(null, (int) testFontSizes.get(i), Color.BLACK, 100, 100, 1000, 25, 5000L);
            textElement.setContent("Test input");
            TextView testView = TextView.class.cast(textElement.applyView(parent, (ViewGroup) parent, basicSlide, ELEMENT_ID));
            int expectedFontSize = View.getDefaultSize((int) testFontSizes.get(i), TypedValue.COMPLEX_UNIT_SP);
            int actualFontSize = Math.round(testView.getTextSize());
            Assert.assertEquals(expectedFontSize, actualFontSize);
        }
    }
    @Test
    public void textContentIsSetCorrectly(){
        ArrayList expectedContent= new ArrayList<String>();
        expectedContent.add("Test Input");
        expectedContent.add("Greaylag Goose");
        expectedContent.add("1234567890-=,.<>/?'[]{}|+_)(*&^%$£@!~`§±");

        for(int i=0;i<expectedContent.size();i++){
            TextElement textElement = new TextElement(null, 32, Color.BLACK, 100,100, 1000, 25, 5000L);
            textElement.setContent(expectedContent.get(i).toString());
            TextView testView= TextView.class.cast(textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID));
            String actualContent= testView.getText().toString();
            Assert.assertEquals(actualContent, expectedContent.get(i));
        }
    }
    @Test
    public void timeOnScreenIsSetCorrectly(){
        ArrayList testTimes= new ArrayList<Long>();
        testTimes.add(10L);
        testTimes.add(100L);
        testTimes.add(500L);

        for(int i=0;i<testTimes.size();i++) {
            TextElement textElement = new TextElement(null, 32,
                    Color.BLACK, 100, 100, 1000, 25, (Long)testTimes.get(i));
            textElement.setContent("Test input");
            View testView= textElement.applyView(parent,(ViewGroup) parent, basicSlide,ELEMENT_ID);
            long b= System.currentTimeMillis();
            while(testView.getVisibility()==0){
                //System.out.println("A");
            }
            long c= System.currentTimeMillis();
            long actualTimeDelta= c-b;
            long delta= 5; // +5ms for testView to be returned
            Assert.assertEquals((long )testTimes.get(i),actualTimeDelta,delta);
        }
    }
    @Test
    public void getViewTypeReturnsCorrect() {
        TextElement textElement = new TextElement(null, 32,
                Color.BLACK, 100,100, 1000, 25, 500L);
        textElement.setContent("Test input");
        textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID);
        String actualReturn= textElement.getViewType();
        String expectedReturn= "text";
        Assert.assertEquals(expectedReturn,actualReturn);
    }
    @Test
    public void getSearchableContentReturnsCorrect(){
        TextElement textElement = new TextElement(null, 32,
                Color.BLACK, 100,100, 1000, 25, 500L);
        textElement.setContent("TEST INPUT");
        textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID);
        String actualReturn= textElement.getSearchableContent();
        String expectedReturn= "test input";
        Assert.assertEquals(expectedReturn,actualReturn);
    }
}

