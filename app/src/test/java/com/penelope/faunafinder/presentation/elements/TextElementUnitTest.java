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
time on screen
text on view
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
    public void xyParamsSetCorrectly(){
        ArrayList testXYPositions= new ArrayList<Integer>();
        testXYPositions.add(5); //x
        testXYPositions.add(5); //y
        testXYPositions.add(2); //x
        testXYPositions.add(8); //y
        testXYPositions.add(100); //x
        testXYPositions.add(200); //y

        for(int i=0;i<testXYPositions.size();i=i+2){
            TextElement textElement = new TextElement(null, 32, Color.BLACK, (int)testXYPositions.get(i),(int)testXYPositions.get(i+1), 1000, 25, 5000L);
            textElement.setContent("Test input");
            TextView testView= TextView.class.cast(textElement.applyView(parent,(ViewGroup) parent,basicSlide,ELEMENT_ID));
            ViewGroup.MarginLayoutParams mlp= (ViewGroup.MarginLayoutParams) testView.getLayoutParams();
            int actualX= mlp.leftMargin;
            int actualY= mlp.topMargin;
            int expectedX=  Math.round((((int)testXYPositions.get(i)) * basicSlide.getCalculatedWidth()) / (float) basicSlide.getWidth());
            int expectedY= Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (int)testXYPositions.get(i+1), displayMetrics));
            Assert.assertEquals(expectedX, actualX);
            Assert.assertEquals(expectedY, actualY);
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
}
