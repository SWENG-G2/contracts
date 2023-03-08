package com.penelope.faunafinder.presentation.elements;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.penelope.faunafinder.R;
import com.penelope.faunafinder.xml.slide.Slide;

import java.util.Timer;
import java.util.TimerTask;


public class TextElement extends PresentationElement implements ViewElement{
    private String content;
    private int fontSize;
    private int width;
    private int height;
    private long timeOnScreen;
    private int color;
    private String font;

    public TextElement(String font,int fontSize, int color,
                           int x, int y,int width, int height,
                           long timeOnScreen) {
        super(x, y);
        create(width, height, fontSize, font, color, timeOnScreen);
    }

    //Inherited methods
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

        ViewGroup.MarginLayoutParams a = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        ViewGroup.MarginLayoutParams b = setTextViewWHParams(slide, a);
        ViewGroup.MarginLayoutParams c = setTextViewXYParams(slide, b);
        textView.setLayoutParams(c);
         setTextViewFontParams(parent, textView);
        setTextViewTimer(textView);

        return textView;
    }
    //Private methods
    private ViewGroup.MarginLayoutParams setTextViewWHParams(Slide slide, ViewGroup.MarginLayoutParams mlp) {
        int w= this.width;
        int h= this.height;
        if (width > 0) {
            mlp.width = Math.round((width * slide.getCalculatedWidth()) / (float) slide.getWidth());
        } else if (width == -1) {
            mlp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        } else if (width == -2) {
            mlp.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
        } else {
            mlp.width = 0; // May need to ask Guiseppe what happens when width/height =! >0 , -1 or -2
        }
        if (height > 0) {
            mlp.height = dpToPx(height);
        } else if (height == -1) {
            mlp.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        } else if (height == -2) {
            mlp.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        } else {
            mlp.height = 0;
        }
        return mlp;
    }
    private ViewGroup.MarginLayoutParams setTextViewXYParams(Slide slide,ViewGroup.MarginLayoutParams mlp ) {
        int leftMarg = Math.round((x * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int rightMarg = leftMarg + mlp.width;
        int topMarg = dpToPx(y);
        int bottomMarg = topMarg + mlp.height;
        mlp.setMargins(leftMarg, topMarg, rightMarg, bottomMarg);
        return mlp;
    }
    private void setTextViewFontParams(View parent, TextView textView) {
        textView.setText(content);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        textView.setTextColor(color);
        Typeface type;
        if (font != null) {
            switch (font) {
                case "mono":
                    type = ResourcesCompat.getFont(parent.getContext(), R.font.chivo_mono_regular);
                    break;
                case "roberto":
                    type = ResourcesCompat.getFont(parent.getContext(), R.font.roboto_condensed_regular);
                    break;
                default:
                    type = Typeface.DEFAULT;
                    break;
            }
        } else {
            type = Typeface.DEFAULT;
        }
        textView.setTypeface(type);
    }
    private void setTextViewTimer(TextView textView) {
        if (timeOnScreen != 0) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    textView.setVisibility(View.INVISIBLE);
                }
            }, timeOnScreen);
        }
    }
    private void create(int width,int height,int fontSize,String font,int color,long timeOnScreen){
        this.width=width;
        this.height=height;
        this.fontSize=fontSize;
        this.timeOnScreen=timeOnScreen;
        this.font=font;
        this.color=color;
    }

    //Setters and getters
    public void setContent(String content){
        this.content= content;
    }
    }
