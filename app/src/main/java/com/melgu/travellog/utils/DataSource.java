package com.kimdora.timeline.utils;

import com.kimdora.timeline.R;
import com.kimdora.timeline.model.HeaderTextItem;
import com.kimdora.timeline.model.PostTextItem;
import com.kimdora.timeline.model.PostVideoItem;
import com.kimdora.timeline.model.TimelineItem;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<TimelineItem> getTimelineData() {

        List<TimelineItem> mdata = new ArrayList<>();

        // create header item
        HeaderTextItem itemHeader = new HeaderTextItem("Yesterday");
        TimelineItem headerTimelineItem = new TimelineItem(itemHeader);

        // create Post item
        PostTextItem postTextItem = new PostTextItem("This is a simple post text item", R.drawable.ic_userone, "10:14");
        TimelineItem posttextTimelineItem = new TimelineItem(postTextItem);

        // create post video item
        PostVideoItem postVideoItem = new PostVideoItem("", R.drawable.ic_usertwo, "9:143");
        TimelineItem postvideoTimelineItem = new TimelineItem(postVideoItem);

        mdata.add(headerTimelineItem);
        mdata.add(posttextTimelineItem);
        mdata.add(postvideoTimelineItem);

        // create header item
        HeaderTextItem itemHeader2 = new HeaderTextItem("4 Years Ago");
        TimelineItem headerTimelineItem2 = new TimelineItem(itemHeader2);

        // create Post item
        PostTextItem postTextItem2 = new PostTextItem("This is a simple post text item", R.drawable.ic_userone, "12:14");
        TimelineItem posttextTimelineItem2 = new TimelineItem(postTextItem2);

        // create post video item
        PostVideoItem postVideoItem2 = new PostVideoItem("", R.drawable.ic_usertwo, "9:14");
        TimelineItem postvideoTimelineItem2 = new TimelineItem(postVideoItem2);

        mdata.add(headerTimelineItem2);
        mdata.add(posttextTimelineItem2);
        mdata.add(postvideoTimelineItem2);

        return mdata;

    }

}
