package com.android.oldschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    LinearLayout ly_en,ly_ko,ly_zh,ly_ja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (checkTutorialComplete()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        ly_en = findViewById(R.id.ly_en);
        ly_ko = findViewById(R.id.ly_ko);
        ly_zh = findViewById(R.id.ly_zh);
        ly_ja = findViewById(R.id.ly_ja);
        loadLocale();
        ly_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("en");
                startActivity(new Intent(getApplicationContext(), TwoActivity.class));
                finish();
            }
        });
        ly_ko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("ko");
                startActivity(new Intent(getApplicationContext(), TwoActivity.class));
                finish();
            }
        });
        ly_ja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("ja");
                startActivity(new Intent(getApplicationContext(), TwoActivity.class));
                finish();
            }
        });
        ly_zh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("zh");
                startActivity(new Intent(getApplicationContext(), TwoActivity.class));
                finish();
            }
        });

    }

    private boolean checkTutorialComplete() {
        SharedPreferences pref = getSharedPreferences("oldSchool", MODE_PRIVATE);
        boolean iSFinishedTutorial = pref.getBoolean("finishedTutorial", false);
        if (iSFinishedTutorial){
            return true;
        }
        return false;
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Configuration config = new Configuration();
        Resources resources = getBaseContext().getResources();

        if (lang.equals("en")){
            Locale.setDefault(locale);
            config.locale = locale;
        }
        if (lang.equals("ko")){
            Locale.setDefault(locale);
            config.locale = locale;
        }
        if (lang.equals("ja")){
            Locale.setDefault(locale);
            config.locale = locale;
        }
        if (lang.equals("zh")){
            Locale.setDefault(locale);
            config.locale = locale;
        }
        Log.d(">>>>>>>", "setLocale: " + locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        // Save data to Shared Reference
        SharedPreferences.Editor editor = getSharedPreferences("oldSchool", MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();

    }
    private void loadLocale() {
        SharedPreferences pref = getSharedPreferences("oldSchool", MODE_PRIVATE);
        String language = pref.getString("My_lang", "");
        setLocale(language);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadLocale();
    }

}
