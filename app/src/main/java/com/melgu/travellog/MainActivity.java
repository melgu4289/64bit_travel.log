package com.melgu.travellog;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.irozon.justbar.BarItem;
import com.irozon.justbar.JustBar;
import com.irozon.justbar.interfaces.OnBarItemClickListener;

public class MainActivity extends AppCompatActivity {

    JustBar justBar;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt);
        justBar = findViewById(R.id.justBar);
        justBar.setOnBarItemClickListener(new OnBarItemClickListener() {
            @Override
            public void onBarItemClick(BarItem barItem, int position) {
                int tag = (int) barItem.getTag();
            }
        });


    }
}

