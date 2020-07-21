package com.android.oldschool;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleHelper.onAttach(this, Locale.getDefault().getLanguage());
    }

}
