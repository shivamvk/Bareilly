package io.shivamvk.bareilly.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hendraanggrian.socialview.commons.Mention;
import com.hendraanggrian.socialview.commons.SocialAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

import io.shivamvk.bareilly.R;

public class CreatePostMentionAdapter extends SocialAdapter<Mention> {

    @DrawableRes
    private final int defaultAvatar;
    @Nullable
    private Filter filter;

    public CreatePostMentionAdapter(@NonNull Context context) {
        this(context, R.drawable.profile_pic_placeholder);
    }

    public CreatePostMentionAdapter(@NonNull Context context, @DrawableRes int defaultAvatar) {
        super(context, R.layout.item_mention, R.id.textview_mention_username);
        this.defaultAvatar = defaultAvatar;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_mention, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Mention item = getItem(position);
        if (item != null) {
            Picasso picasso = Picasso.get();
            RequestCreator request = null;
            if (item.getAvatar() == null)
                request = picasso.load(defaultAvatar);
            else if (item.getAvatar() instanceof Integer)
                request = picasso.load((Integer) item.getAvatar());
            else if (item.getAvatar() instanceof String)
                request = picasso.load((String) item.getAvatar());
            else if (item.getAvatar() instanceof Uri)
                request = picasso.load((Uri) item.getAvatar());
            else if (item.getAvatar() instanceof File)
                request = picasso.load((File) item.getAvatar());

            if (request != null) {
                Resources res = getContext().getResources();
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(res.getDimensionPixelSize(R.dimen.mention_progressbar_size), res.getDimensionPixelSize(R.dimen.mention_progressbar_size));
                lp.gravity = Gravity.CENTER;
                ProgressBar progressBar = new ProgressBar(getContext());
                progressBar.setLayoutParams(lp);
                request.error(defaultAvatar)
                        .into(holder.imageView);
            }

            holder.textViewUsername.setText(item.getUsername());
            holder.textViewDisplayname.setText(item.getDisplayname());
        }
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new SuggestionFilter() {
                @Override
                public String getString(Mention item) {
                    return item.getUsername();
                }
            };
        return filter;
    }

    private static class ViewHolder {
        @NonNull private final ImageView imageView;
        @NonNull private final TextView textViewUsername;
        @NonNull private final TextView textViewDisplayname;

        private ViewHolder(@NonNull View view) {
            imageView = (ImageView) view.findViewById(R.id.imageview_mention_username);
            textViewUsername = (TextView) view.findViewById(R.id.textview_mention_username);
            textViewDisplayname = (TextView) view.findViewById(R.id.textview_mention_displayname);
        }
    }
}