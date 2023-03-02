package com.penelope.faunafinder.presentation.elements;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.penelope.faunafinder.xml.slide.Slide;

import java.util.Timer;
import java.util.TimerTask;
/*
SORT OUT FONTS
ApplyVIew XYWH
*/

public class TextElement extends PresentationElement implements ViewElement{

        private String content;
        private int fontSize;
        private int width;
        private int height;
        private int x;
        private int y;
        private long timeOnScreen;
       // private Color color;
        private int color;
        private String font;
        public TextElement(String font,int fontSize, int color,
                           int x, int y,int width, int height,
                           long timeOnScreen){
          super(x,y);
          create(x,y,width,height,fontSize,font,color,timeOnScreen);
        }
        //Private methods
        private void create(int x,int y, int width,int height,int fontSize,String font,int color,long timeOnScreen){
            this.x= x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.fontSize=fontSize;
            this.timeOnScreen=timeOnScreen;
            //Sets FONT
            if(font!=null) {
                switch (font) {
                    case "mono":
                       this.font= "R.font.chivo_mono_regular";
                        break;
                    case "roberto":
                        this.font= "R.font.roboto_condensed_regular";
                        break;
                    default:
                        this.font= "android.widget.RelativeLayout";
                        break;
                }
            }else{
                this.font= "R.font.chivo_mono_regular";

            }
            //Set COLOR field to hex value of costructor 'color'
           // this.color= new Color();
            //this.color.parseColor(Integer.toHexString(color));
            this.color=color;
        }
        //Setters and getters
        public void setContent(String content){
            this.content= content;
        }

        @Override
        public String getViewType() {
            return TEXT_ELEMENT;
        }

        @Override
        public String getSearchableContent() {
            return content.toLowerCase();
        }

        @Override
        public View applyView(View parent, ViewGroup container, Slide slide, int id) {
            TextView textView = new TextView(parent.getContext());
            textView.setId(id);
            container.addView(textView);

            ///ROUGH
            int nX = Math.round((x * slide.getCalculatedWidth()) / (float) slide.getWidth());
            int nY = dpToPx(y);
            Log.d("Elements:", "Initial Y:" + y);
            Log.d("Elements:", "New Y:" + nY);
            Log.d("Elements:", "Initial X:" + x);
            Log.d("Elements:", "New X:" + nX);
            int nWidth = Math.round((width * slide.getCalculatedWidth()) / (float) slide.getWidth());
            int nHeight = dpToPx(height);

          //  textView.getLayoutParams().height = nHeight;
            //textView.getLayoutParams().width = nWidth;

            ViewGroup.MarginLayoutParams mlp= (ViewGroup.MarginLayoutParams)
                    textView.getLayoutParams();
            mlp.height=nHeight;
            mlp.width=nWidth;
            mlp.setMargins(nX,nY,0,0);

            //textView.setX(nX);
            //textView.setY(nY);
            //textView.layout(nX,nY,slide.getWidth()-nWidth,slide.getHeight()-nHeight);



            Log.d("Elements:", "TextView Width:" + textView.getLayoutParams().width);
            Log.d("Elements:", "TextView height:" + textView.getLayoutParams().height);
            Log.d("Elements:", "TextView xPos:" + textView.getX());
            Log.d("Elements:", "TextView yPos:" + textView.getY());





            textView.setText(content);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
            textView.setTextColor(color);
            textView.setFontFeatureSettings(font);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    textView.setVisibility(View.INVISIBLE);
                }
            }, timeOnScreen);


            return null;
        }
    }
