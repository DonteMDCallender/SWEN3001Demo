package com.maxst.mediaapp;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Images> pic = new ArrayList<>();
        Images Images;

        Images = new Images("Image1",R.drawable.image1);
        pic.add(Images);
        Images = new Images("Image2",R.drawable.image2);
        pic.add(Images);
        Images = new Images("Image3",R.drawable.image3);
        pic.add(Images);
        Images = new Images("Image4",R.drawable.image4);
        pic.add(Images);
        Images = new Images("Image5",R.drawable.image5);
        pic.add(Images);
        Images = new Images("Image6",R.drawable.image6);
        pic.add(Images);
        Images = new Images("Image7",R.drawable.image7);
        pic.add(Images);
        Images = new Images("Image8",R.drawable.image8);
        pic.add(Images);
        Images = new Images("Image9",R.drawable.image9);
        pic.add(Images);
        Images = new Images("Image10",R.drawable.image10);
        pic.add(Images);
        Images = new Images("Image11",R.drawable.image11);
        pic.add(Images);

        RecyclerView rView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, pic);
        rView.setLayoutManager(new GridLayoutManager(this, 1));
        rView.setAdapter(adapter);
    }

}