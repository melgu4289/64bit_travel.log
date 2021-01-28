package com.kimdora.timeline.Adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kimdora.timeline.model.TimelineItem;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    abstract void setData(TimelineItem item);

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
