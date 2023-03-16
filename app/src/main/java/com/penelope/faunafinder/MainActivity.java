package com.penelope.faunafinder;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.penelope.faunafinder.presentation.elements.TextElement;
import com.penelope.faunafinder.xml.slide.BasicSlide;

public class MainActivity extends AppCompatActivity {
    // Item identifier
    private static final int ELEMENT_ID = 8008135;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Most of the code here is to mimic the behaviour in our project.

        RelativeLayout parent = findViewById(R.id.activity_main);

        // Create basic container slide
        BasicSlide basicSlide = new BasicSlide(1920, 500, "Test slide");

        // Item must be already existing on screen for view re-usability in our project
        TextView textView = new TextView(parent.getContext());
        textView.setId(ELEMENT_ID);
        parent.addView(textView);

        TextElement textElement = new TextElement("roboto", 32, Color.BLACK, 100, 500, 1800, 25, 5000L);
        textElement.setContent("Test input");

        textElement.applyView(parent, (ViewGroup) parent, basicSlide, ELEMENT_ID);
        // The example text element should appear cropped in about half and disappear after 5 seconds

    }
}