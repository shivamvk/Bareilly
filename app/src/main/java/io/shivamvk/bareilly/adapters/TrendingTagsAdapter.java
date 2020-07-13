package io.shivamvk.bareilly.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hendraanggrian.widget.SocialAutoCompleteTextView;

import java.util.List;

import io.shivamvk.bareilly.R;

public class TrendingTagsAdapter extends RecyclerView.Adapter<TrendingTagsAdapter.ViewHolder> {

    private Context context;
    private List<String> tags;
    private SocialAutoCompleteTextView postContentView;

    public TrendingTagsAdapter(Context context, List<String> tags, SocialAutoCompleteTextView postContentView){
        this.context = context;
        this.tags = tags;
        this.postContentView = postContentView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_trending_tags_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tag.setText("#" + tags.get(position));
        holder.tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postContentView.setText(postContentView.getText().append(" #" + tags.get(position)));
                postContentView.setSelection(postContentView.getText().length());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tag);
        }
    }

}
