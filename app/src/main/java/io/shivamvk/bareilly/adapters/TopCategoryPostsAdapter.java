package io.shivamvk.bareilly.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
//        Picasso.get()
//                .load(R.drawable.dummy_post)
//                .fit()
//                .into(holder.postImageView);
        if (position%2==0){
            holder.postImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView postText;
        ImageView postImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postText = itemView.findViewById(R.id.post_text);
            postImageView = itemView.findViewById(R.id.post_image);
        }
    }

}
