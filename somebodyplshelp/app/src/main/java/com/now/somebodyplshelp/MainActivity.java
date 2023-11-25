package com.now.somebodyplshelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView avatar;
    TextView title;
    Button seeAllbooks,currentlyReading,alreadyRead,yourWishlist,seeYourFavorites,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initValues();
        seeAllbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,AllBooksActivity.class);
                startActivity(intent);
            }
        });
        alreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , alreadyReadBooksActivity.class);
                startActivity(intent);
            }
        });
        currentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , currentlyReading.class);
                startActivity(intent);
            }
        });
        yourWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, wishlist_activity.class);
                startActivity(intent);
            }
        });
        seeYourFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, fav.class);
                startActivity(intent);
            }
        });

        Utils.getInstance();
    }

    private void initValues() {
        avatar= findViewById(R.id.imgAvatar);
        title=findViewById(R.id.txtTitle);
        seeAllbooks=findViewById(R.id.btnSeeAllBooks);
        currentlyReading=findViewById(R.id.btnCurrentlyReadingBooks);
        alreadyRead= findViewById(R.id.btnAlreadyRead);
        yourWishlist=findViewById(R.id.btnYourWishlist);
        seeYourFavorites=findViewById(R.id.btnYourFavorites);
        about=findViewById(R.id.btnAbout);
        about.setVisibility(View.GONE);
    }
}