package com.melgu.travellog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.melgu.travellog.ui.TimelineActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(), 2000); // 2초 후에 실행
    }

    private class splashHandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), LoginActivity.class));
            overridePendingTransition(0, R.anim.fadeout); // 애니메이션
            SplashActivity.this.finish(); // Activity stack에서 제거
        }
    }

    @Override
    public void onBackPressed() {
        // 초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함.
    }
}