package com.kimdora.timeline.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kimdora.timeline.R;
import com.kimdora.timeline.model.HeaderTextItem;
import com.kimdora.timeline.model.TimelineItem;

public class HeaderTextViewHolder extends BaseViewHolder {

    private TextView tvHeader;

    public HeaderTextViewHolder(@NonNull View itemView) {
        super(itemView);
        tvHeader = itemView.findViewById(R.id.header_text);
    }

    @Override
    void setData(TimelineItem item) {
        HeaderTextItem headerTextItem = item.getHeaderTextItem();
        tvHeader.setText(headerTextItem.getHeaderText());
    }
}
