package sweng.campusbirdsguide;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import sweng.campusbirdsguide.presentation.elements.ImageElement;
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
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setId(ELEMENT_ID);
        parent.addView(imageView);

        ImageElement imageElement = new ImageElement("url to image",
                1700, 400, 0, 0, -45,
                5L * 1000, 10L * 1000);

        imageElement.applyView(parent, (ViewGroup) parent, basicSlide, ELEMENT_ID);
    }
}