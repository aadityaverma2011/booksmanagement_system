package com.now.somebodyplshelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class wishlist_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        RecyclerView recyclerView= findViewById(R.id.wishlistrecview);
        BooksRecViewAdapter recViewAdapter = new BooksRecViewAdapter(this,"Wishlist");
        recyclerView.setAdapter(recViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recViewAdapter.setBooks(Utils.getInstance().getWanttoread());

    }
}