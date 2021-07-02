package com.prakriti.imageswitchingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    static String animals[] = new String[] {"BEAR", "BIRD", "CAT", "COW", "DOLPHIN", "FISH", "FOX", "HORSE", "LION", "TIGER"};
    static int images[] = { R.drawable.bear, R.drawable.bird, R.drawable.cat, R.drawable.cow, R.drawable.dolphin, R.drawable.fish,
            R.drawable.fox, R.drawable.horse, R.drawable.lion, R.drawable.tiger };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout llHorizontal = findViewById(R.id.llHorizontal);
        ImageSwitcher imgSwitcher = findViewById(R.id.imgSwitcher); // subclass of View Switcher
        // implement ViewFactory for image switcher -> it creates views in a view switcher

        imgSwitcher.setFactory(this);
        // set in and set out - animations
        imgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
            // AnimationUtils from view.animation package, not google.material...
        imgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
              // can use slide_in or slide_out effect as well

        for (int i = 0; i < images.length; i++) {
            // create new image view -> to put inside horizontal scrollview
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(500, 500)); // since linear layout is the parent for the image
            imageView.setPadding(50, 50, 50, 50);
            int final_index = i; // for anon inner class
            imageView.setOnClickListener(v -> {
                // on clicking image in scrollview, it should appear in image switcher outside the scrollview
                imgSwitcher.setImageResource(images[final_index]);
                imgSwitcher.setLayoutParams(new LinearLayout.LayoutParams(800, 800));
                Toast.makeText(MainActivity.this, animals[final_index], Toast.LENGTH_SHORT).show();
            });
            llHorizontal.addView(imageView);
        }
    }

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        // images will be inside image switcher
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    // above line gives error
        return imageView;
    }
}