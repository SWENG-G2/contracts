package com.penelope.faunafinder.xml.slide;

import android.content.Context;
import android.view.View;

import com.penelope.faunafinder.presentation.elements.PresentationElement;

import java.util.List;

public interface Slide {
    int HORIZONTAL_MARGIN = 10; // 5 DP each side
    int HORIZONTAL_PADDING = 20; // 10 dp each side
    int STANDARD_TYPE = 0;
    int EXPANDABLE_TYPE = 1;

    // Getters
    int getWidth();

    int getHeight();

    int getCalculatedWidth();

    int getCalculatedHeight();

    int getType();

    int getId();

    String getTitle();

    List<PresentationElement> getElements();

    // Adder
    void addElement(PresentationElement presentationElement);

    // Customisation
    void slideSpecifics(View containerView, Context context);
}
