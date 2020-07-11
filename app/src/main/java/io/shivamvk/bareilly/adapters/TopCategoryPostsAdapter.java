package io.shivamvk.bareilly.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import io.shivamvk.bareilly.R;

public class TopCategoryPostsAdapter extends RecyclerView.Adapter<TopCategoryPostsAdapter.ViewHolder> {

    private Context context;


    public TopCategoryPostsAdapter(
            Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.adapter_top_category_posts_item,
                                parent,
                                false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Picasso.get()
//                .load(R.drawable.dummy_post)
//                .fit()
//                .into(holder.postImageView);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView postImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postImageView = itemView.findViewById(R.id.post_image);
        }
    }

}
