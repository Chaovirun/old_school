package com.android.oldschool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

public class LanguageReciever extends BroadcastReceiver{

    private OnLanguageChanged mListener;
    private static boolean b;

    public void setListener(OnLanguageChanged mListener){
        this.mListener = mListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (b){
            mListener.changed();
        }

    }

    class Change extends CoreActivity{
        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            b = true;
        }
    }

    public interface OnLanguageChanged{
        void changed();
    }
}
