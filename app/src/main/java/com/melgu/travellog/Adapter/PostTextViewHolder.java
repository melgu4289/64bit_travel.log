package com.kimdora.timeline.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.kimdora.timeline.R;
import com.kimdora.timeline.model.PostTextItem;
import com.kimdora.timeline.model.TimelineItem;

public class PostTextViewHolder extends BaseViewHolder {

    private TextView txtPost, txtTime;
    private ImageView imgUser;

    public PostTextViewHolder(@NonNull View itemView) {
        super(itemView);
        txtPost = itemView.findViewById(R.id.post_text_content);
        txtTime = itemView.findViewById(R.id.post_video_time);
        imgUser = itemView.findViewById(R.id.post_video_img);
    }

    @Override
    void setData(TimelineItem item) {
        PostTextItem post = item.getPostTextItem();
        txtPost.setText(post.getPostText());
        txtTime.setText(post.getTime());
        Glide.with(itemView.getContext()).load(post.getImgUser()).into(imgUser);
    }
}
