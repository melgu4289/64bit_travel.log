package com.melgu.travellog.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.melgu.travellog.R;
import com.melgu.travellog.model.HeaderTextItem;
import com.melgu.travellog.model.TimelineItem;

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
