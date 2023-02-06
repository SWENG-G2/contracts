package sweng.campusbirdsguide;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sweng.campusbirdsguide.presentation.elements.TextElement;
import sweng.campusbirdsguide.xml.slide.BasicSlide;

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

        TextElement textElement = new TextElement(null, 32, Color.BLACK, 1800, 500, -1);
        textElement.setContent("Test input");

        textElement.applyView(parent, (ViewGroup) parent, basicSlide, ELEMENT_ID);
    }
}