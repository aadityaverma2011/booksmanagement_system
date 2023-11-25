package com.now.somebodyplshelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class fav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        RecyclerView recyclerView= findViewById(R.id.recviewfav);
        BooksRecViewAdapter recViewAdapter = new BooksRecViewAdapter(this,"Favorites");
        recyclerView.setAdapter(recViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recViewAdapter.setBooks(Utils.getInstance().getFavorites());
    }
}