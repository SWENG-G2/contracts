package com.penelope.faunafinder.presentation.elements;

import android.view.View;
import android.view.ViewGroup;

import com.penelope.faunafinder.xml.slide.Slide;

public interface ViewElement {
    View applyView(View parent, ViewGroup container, Slide slide, int id);
}
