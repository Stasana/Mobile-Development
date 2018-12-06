package com.melnyk.stanislav.melnyk.RVAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.melnyk.stanislav.melnyk.Entity.Book;
import com.melnyk.stanislav.melnyk.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> books;
    private Context mContext;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.iv_poster)
        ImageView imgPoster;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public BookAdapter(Context context, List<Book> films) {
        this.books = films;
        mContext = context;
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, int position) {
        Book item = books.get(position);
        Glide.with(mContext)
                .load(item.getImage())
                .into(holder.imgPoster);
        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getSubtitle());
        holder.tvPrice.setText(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void updateBooks(List<Book> items) {
        books = items;
        notifyDataSetChanged();
    }
}
