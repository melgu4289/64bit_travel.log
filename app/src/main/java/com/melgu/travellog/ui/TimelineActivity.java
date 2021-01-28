package com.melgu.travellog.ui;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.melgu.travellog.Adapter.TimelineAdapter;
import com.melgu.travellog.R;
import com.melgu.travellog.model.TimelineItem;
import com.melgu.travellog.utils.DataSource;

import java.util.List;

public class TimelineActivity extends AppCompatActivity {

    private RecyclerView timelineRv;
    private TimelineAdapter adapter;
    private List<TimelineItem> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullscreen();
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        iniRV();
        getListData();
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new TimelineAdapter(this, mData);
        timelineRv.setAdapter(adapter);
    }


    private void getListData() {
        mData = DataSource.getTimelineData();
    }

    private void iniRV() {
        timelineRv = findViewById(R.id.timeline_rv);
        timelineRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setFullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}