package com.now.somebodyplshelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    ImageView avatar;
    TextView bookname, authorname, pages, txtShortDesc, txtLongDesc;
    Button btnCurrentlyReading , btnwanttoread, btnalreadyRead,btnAddToFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initvalues();
        Book book= new Book(1,"Deadly Holes","Julie Morton",160,"https://blog-cdn.reedsy.com/directories/gallery/68/large_19a00aea82e591aaa3ddb796479df0c5.jpg","Deadly Keyholes is a domestic crime drama ","Deadly Keyholes, pay for that lesson with their lives. Miranda LaVelle is an unwanted, bastard child of the 1920s Jazz Age. Without help, Miranda faces sure death. But her great aunt, Genevieve Woods, is her somewhat sinful saviorDeadly Keyholes, pay for that lesson with their lives. Miranda LaVelle is an unwanted, bastard child of the 1920s Jazz Age. Without help, Miranda faces sure death. But her great aunt, Genevieve Woods, is her somewhat sinful saviorDeadly Keyholes, pay for that lesson with their lives. Miranda LaVelle is an unwanted, bastard child of the 1920s Jazz Age. Without help, Miranda faces sure death. But her great aunt, Genevieve Woods, is her somewhat sinful saviorDeadly Keyholes, pay for that lesson with their lives. Miranda LaVelle is an unwanted, bastard child of the 1920s Jazz Age. Without help, Miranda faces sure death. But her great aunt, Genevieve Woods, is her somewhat sinful saviorDeadly Keyholes, pay for that lesson with their lives. Miranda LaVelle is an unwanted, bastard child of the 1920s Jazz Age. Without help, Miranda faces sure death. But her great aunt, Genevieve Woods, is her somewhat sinful saviorDeadly Keyholes, pay for that lesson with their lives. Miranda LaVelle is an unwanted, bastard child of the 1920s Jazz Age. Without help, Miranda faces sure death. But her great aunt, Genevieve Woods, is her somewhat sinful savior");
        Intent intent = getIntent();
        if (null != intent) {
            int bookID = intent.getIntExtra("bookbyid", -1);
            if (bookID != -1) {
                Book incomingbook = Utils.getInstance().getBookbyId(bookID);
                if (incomingbook != null) {
                    setData(incomingbook);
                    handleAlreadyRead(incomingbook);
                    handlecurrentlyReading(incomingbook);
                    handleWishlist(incomingbook);
                    handleFavorites(incomingbook);
                }
            }
        }
    }

    private void handleFavorites(final Book incomingbook) {
        ArrayList<Book> favorites = Utils.getInstance().getFavorites();

        boolean infavorites = false;

        for (Book b : favorites) {
            if (b.getId() == incomingbook.getId()) {
                infavorites= true;
                break;  // Exit the loop early since we found a match
            }
        }
        if (infavorites) {
            btnAddToFavorites.setEnabled(false);
        } else {
            btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addtoFav(incomingbook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent newintent = new Intent(BookActivity.this, fav.class);
                        startActivity(newintent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWishlist(final Book incomingbook) {
        ArrayList<Book> wishlist = Utils.getInstance().getWanttoread();

        boolean wishlistmehaikya = false;

        for (Book b : wishlist) {
            if (b.getId() == incomingbook.getId()) {
                wishlistmehaikya= true;
                break;  // Exit the loop early since we found a match
            }
        }
        if (wishlistmehaikya) {
            btnwanttoread.setEnabled(false);
        } else {
            btnwanttoread.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addtoWishlist(incomingbook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, wishlist_activity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handlecurrentlyReading(final Book incomingbook) {
        ArrayList<Book> currentlyReadBooks = Utils.getInstance().getCurrentlyreading();

        boolean existInCurrentlyReadBooks = false;

        for (Book b : currentlyReadBooks) {
            if (b.getId() == incomingbook.getId()) {
                existInCurrentlyReadBooks= true;
                break;  // Exit the loop early since we found a match
            }
        }
        if (existInCurrentlyReadBooks) {
            btnCurrentlyReading.setEnabled(false);
        } else {
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToCurrentlyRead(incomingbook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, currentlyReading.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void handleAlreadyRead(final Book incomingbook) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyRead();

        boolean existInAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks) {
            if (b.getId() == incomingbook.getId()) {
                existInAlreadyReadBooks = true;
                break;  // Exit the loop early since we found a match
            }
        }

        if (existInAlreadyReadBooks) {
            btnalreadyRead.setEnabled(false);
        } else {
            btnalreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToAlreadyRead(incomingbook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, alreadyReadBooksActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void setData(Book book) {
        bookname.setText(Utils.getInstance().getBookbyId(book.getId()).getName());
        authorname.setText(Utils.getInstance().getBookbyId(book.getId()).getAuthor());
        pages.setText(String.valueOf(book.getPages()));
        txtShortDesc.setText(book.getShortDesc());
        txtLongDesc.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(avatar);
    }


    private void initvalues() {
        avatar= findViewById(R.id.imgsubAvatar);
        bookname= findViewById(R.id.txtBookNameanother);
        authorname= findViewById(R.id.txtAuthoranother);
        pages= findViewById(R.id.txtPagesanother);
        txtShortDesc=findViewById(R.id.txtShortDescAnother);
        txtLongDesc=findViewById(R.id.txtLongDesanother);
        btnCurrentlyReading=findViewById(R.id.btnCurrentlyReading);
        btnwanttoread=findViewById(R.id.btnWantToRead);
        btnalreadyRead=findViewById(R.id.btnAlreadyReadIt);
        btnAddToFavorites=findViewById(R.id.btnAddToFavorites);
    }
}