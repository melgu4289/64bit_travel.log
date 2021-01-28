package com.melgu.travellog;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.irozon.justbar.BarItem;
import com.irozon.justbar.JustBar;
import com.irozon.justbar.interfaces.OnBarItemClickListener;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;

    JustBar justBar;
    TextView txt;


    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                case 2:
                    return new ThirdFragment();
                case 3:
                    return new FourthFragment();
                case 4:
                    return new FifthFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(0);

        txt = findViewById(R.id.txt);
        justBar = findViewById(R.id.justBar);
        justBar.setOnBarItemClickListener(new OnBarItemClickListener() {
            @Override
            public void onBarItemClick(BarItem barItem, int position) {
                txt.setText(barItem + "," +barItem  + " , " + position);
                int tag = (int) barItem.getTag();
                pager.setCurrentItem(tag);
            }
        });


    }
}

