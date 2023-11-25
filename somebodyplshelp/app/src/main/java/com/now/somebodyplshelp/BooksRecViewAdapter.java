package com.now.somebodyplshelp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BooksRecViewAdapter extends RecyclerView.Adapter<BooksRecViewAdapter.ViewHolder>{
    private static final String TAG = "BooksRecViewAdapter";
    private ArrayList<Book> books = new ArrayList<>();
    private Context mcontext;
    private String parentActivity;

    public BooksRecViewAdapter(Context mcontext, String parentActivity) {
        this.mcontext = mcontext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        return new ViewHolder(view);
    }




    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtname.setText(books.get(position).getName());
        Glide.with(mcontext)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,BookActivity.class);
                intent.putExtra("bookbyid",books.get(position).getId());
                mcontext.startActivity(intent);
            }
        });
        if(books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expanded.setVisibility(View.VISIBLE);
            holder.downarrow.setVisibility(View.GONE);
            if(parentActivity.equals("allBooks")){
                holder.txtdelete.setVisibility(View.GONE);
            } else if (parentActivity.equals("CurrentlyReading")) {
                holder.txtdelete.setVisibility(View.VISIBLE);
                holder.txtdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Utils.getInstance().removeCurrentlyReading(books.get(position))){
                            Toast.makeText(mcontext, "Book Removed", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(mcontext, "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else if (parentActivity.equals("Favorites")) {
                holder.txtdelete.setVisibility(View.VISIBLE);
                holder.txtdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Utils.getInstance().removeFavorites(books.get(position))){
                            Toast.makeText(mcontext, "book removed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else if (parentActivity.equals("Wishlist")) {
                holder.txtdelete.setVisibility(View.VISIBLE);
                holder.txtdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Utils.getInstance().removeWishlisht(books.get(position))){
                            Toast.makeText(mcontext, "book removed", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(mcontext, "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else if (parentActivity.equals("AlreadyRead")) {
                holder.txtdelete.setVisibility(View.VISIBLE);
                holder.txtdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Utils.getInstance().removeAlreadyRead(books.get(position))){
                            Toast.makeText(mcontext, "book removed", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(mcontext, "something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                
            }
        }
        else{
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expanded.setVisibility(View.GONE);
            holder.downarrow.setVisibility(View.VISIBLE);
        }
        holder.txtShortDesc.setText(books.get(position).getShortDesc());
        holder.txtAuthorName.setText(books.get(position).getAuthor());
        holder.txtShortDesc.setText(books.get(position).getShortDesc());

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imgBook;
        private TextView txtname;
        private RelativeLayout expanded;
        private ImageView uparrow,downarrow;
        private TextView  txtAuthor , txtAuthorName , txtShortDesc;
        private TextView txtdelete ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            expanded=itemView.findViewById(R.id.expandedRelLayout);
            parent=itemView.findViewById(R.id.parent);
            imgBook=itemView.findViewById(R.id.imgBook);
            txtname=itemView.findViewById(R.id.txtBookName);
            uparrow= itemView.findViewById(R.id.uparrow);
            downarrow=itemView.findViewById(R.id.downarrow);
            txtAuthorName=itemView.findViewById(R.id.txtAuthor);
            txtAuthor=itemView.findViewById(R.id.Authortext);
            txtShortDesc=itemView.findViewById(R.id.txtShortDesc);
            txtdelete= itemView.findViewById(R.id.txtdelete);
            downarrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
            uparrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

    }
}
