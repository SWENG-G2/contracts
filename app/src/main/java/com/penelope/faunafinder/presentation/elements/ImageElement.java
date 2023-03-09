package com.penelope.faunafinder.presentation.elements;

import android.graphics.Typeface;
import android.os.Handler;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.penelope.faunafinder.R;
import com.penelope.faunafinder.xml.slide.Slide;

import java.util.Timer;
import java.util.TimerTask;


public class ImageElement extends PresentationElement implements ViewElement{
    private final String url;
    private final int width;
    private final int height;
    private final long timeOnScreen;
    private final int rotation;
    private final long delay;

    public ImageElement(String url, int width, int height,
                       int x, int y,int rotation, long delay,
                       long timeOnScreen) {
        super(x, y);

        //ensure that the string is a valid URL
        if (!Patterns.WEB_URL.matcher(url).matches()){
            throw new IllegalArgumentException("URL is Invalid");
        }
        this.url = url;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.delay = delay;
        this.timeOnScreen = timeOnScreen;
    }

    //Inherited methods
    @Override
    public String getViewType() {
        return IMAGE_ELEMENT;
    }
    @Override
    public String getSearchableContent() {
        return null;
    }
    @Override
    public View applyView(View parent, ViewGroup container, Slide slide, int id) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setId(id);
        Glide.with(parent).load(url).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        //adding to container gives it layoutParams
        container.addView(imageView);

        //format image for scale position and rotation
        ViewGroup.MarginLayoutParams imageViewParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
        imageViewParams.width = Math.round((width * slide.getCalculatedWidth()) / (float) slide.getWidth());
        imageViewParams.height = height != MATCH_WIDTH_CLIENT_SIDE ? dpToPx(height) : imageViewParams.width;

        int leftMarg = Math.round((x * slide.getCalculatedWidth()) / (float) slide.getWidth());
        int rightMarg = leftMarg + imageViewParams.width;
        int topMarg = dpToPx(y);
        int bottomMarg = topMarg + imageViewParams.height;
        imageViewParams.setMargins(leftMarg, topMarg, rightMarg, bottomMarg);
        imageView.setLayoutParams(imageViewParams);

        imageView.setRotation((float) rotation);

        //account for delay and timeOnScreen
        imageView.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(() -> imageView.setVisibility(View.VISIBLE), delay);
        new Handler().postDelayed(() -> imageView.setVisibility(View.INVISIBLE),delay+timeOnScreen);

        return imageView;
    }
}