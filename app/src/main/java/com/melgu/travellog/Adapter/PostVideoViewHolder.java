package com.melgu.travellog.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.melgu.travellog.R;
import com.melgu.travellog.model.PostVideoItem;
import com.melgu.travellog.model.TimelineItem;

public class PostVideoViewHolder extends BaseViewHolder {

    private TextView txtTime;
    private ImageView imgUser;

    public PostVideoViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTime = itemView.findViewById(R.id.post_video_time);
        imgUser = itemView.findViewById(R.id.post_video_img);
    }

    @Override
    void setData(TimelineItem item) {
        PostVideoItem post = item.getPostVideoItem();
        txtTime.setText(post.getTime());
        Glide.with(super.itemView.getContext()).load(post.getUserImg()).into(imgUser);
    }
}
