package com.melgu.travellog.utils;

import com.melgu.travellog.R;
import com.melgu.travellog.model.HeaderTextItem;
import com.melgu.travellog.model.PostTextItem;
import com.melgu.travellog.model.PostVideoItem;
import com.melgu.travellog.model.TimelineItem;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<TimelineItem> getTimelineData() {

        List<TimelineItem> mdata = new ArrayList<>();

        // create header item
        HeaderTextItem itemHeader = new HeaderTextItem("어제");
        TimelineItem headerTimelineItem = new TimelineItem(itemHeader);

        // create Post item
        PostTextItem postTextItem = new PostTextItem("오늘은 여행은 첫 날! 너무 신난다. ㅎㅎ", R.drawable.ic_userone, "10:14");
        TimelineItem posttextTimelineItem = new TimelineItem(postTextItem);

        // create post video item
        PostVideoItem postVideoItem = new PostVideoItem("", R.drawable.ic_usertwo, "9:14");
        TimelineItem postvideoTimelineItem = new TimelineItem(postVideoItem);

        mdata.add(headerTimelineItem);
        mdata.add(posttextTimelineItem);
        mdata.add(postvideoTimelineItem);

        // create header item
        HeaderTextItem itemHeader2 = new HeaderTextItem("3시간 전");
        TimelineItem headerTimelineItem2 = new TimelineItem(itemHeader2);

        // create Post item
        PostTextItem postTextItem2 = new PostTextItem("방금 맛있는 음식을 먹었다.", R.drawable.ic_userone, "12:14");
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
