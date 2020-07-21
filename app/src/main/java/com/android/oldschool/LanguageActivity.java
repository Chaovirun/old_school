package com.android.oldschool;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.oldschool.comm.DlgAlert;
import com.android.oldschool.comm.MyDialog;

public class LanguageActivity extends CoreActivity{

    MyDialog dialog;
    TextView textView;
    LanguageReciever.OnLanguageChanged onLanguageChanged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_language);
        dialog = new MyDialog();
        textView = findViewById(R.id.textView2);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
//                Intent intent = getIntent();
//                LocaleHelper.setLocale(LanguageActivity.this, "km");
//                MainActivity.IS_MAIN_RECREATE = true;
//                setResult(RESULT_OK);
//                finish();
//                Context context = LocaleHelper.setLocale(LanguageActivity.this, "km");
//                DlgAlert.DlgAlertOkCancel(LanguageActivity.this, context.getString(R.string.change_language), "Cancel", "Ok", new DlgAlert.OnDialogListener() {
//                    @Override
//                    public void onClickDlgButton(int dialogIndex, DlgAlert.DIALOG_BTN buttonType) {
//                        if (dialogIndex == 1) {
//                            LocaleHelper.setLocale(LanguageActivity.this, "km");
//                            // After change language move to SettingActivity
//                            setResult(RESULT_OK);
//
//                            /*Huo Chhunleng 08.Aug.2018
//                             * MainActivity will be on resume()*/
//                            MainActivity.IS_MAIN_RECREATE = true;
//                            finish();
//                        }else{
//                            /* set change prev iso code */
//                            LocaleHelper.setLocale(LanguageActivity.this, "ko");
//                        }
//                    }
//                },false);
                LocaleHelper.setLocale(LanguageActivity.this, "km");
                // After change language move to SettingActivity
                setResult(RESULT_OK);

                /*Huo Chhunleng 08.Aug.2018
                 * MainActivity will be on resume()*/
                MainActivity.IS_MAIN_RECREATE = true;
                finish();

            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
//                Intent intent = getIntent();
//                LocaleHelper.setLocale(LanguageActivity.this, "zh");
//                MainActivity.IS_MAIN_RECREATE = true;
//                setResult(RESULT_OK);
//                finish();
//                Context context = LocaleHelper.setLocale(LanguageActivity.this, "zh");
//                DlgAlert.DlgAlertOkCancel(LanguageActivity.this, context.getString(R.string.change_language), "Cancel", "Ok", new DlgAlert.OnDialogListener() {
//                    @Override
//                    public void onClickDlgButton(int dialogIndex, DlgAlert.DIALOG_BTN buttonType) {
//                        if (dialogIndex == 1) {
//                            LocaleHelper.setLocale(LanguageActivity.this, "zh");
//                            // After change language move to SettingActivity
//                            setResult(RESULT_OK);
//
//                            /*Huo Chhunleng 08.Aug.2018
//                             * MainActivity will be on resume()*/
//                            MainActivity.IS_MAIN_RECREATE = true;
//                            finish();
//                        }else{
//                            /* set change prev iso code */
//                            LocaleHelper.setLocale(LanguageActivity.this, "km");
//                        }
//                    }
//                },false);
//                Context context = LocaleHelper.setLocale(LanguageActivity.this, "ko");
//                DlgAlert.DlgAlertOkCancel(LanguageActivity.this, context.getString(R.string.change_language), "Cancel", "Ok", new DlgAlert.OnDialogListener() {
//                    @Override
//                    public void onClickDlgButton(int dialogIndex, DlgAlert.DIALOG_BTN buttonType) {
//                        if (dialogIndex == 1) {
//                            LocaleHelper.setLocale(LanguageActivity.this, "ko");
//                            // After change language move to SettingActivity
//                            setResult(RESULT_OK);
//
//                            /*Huo Chhunleng 08.Aug.2018
//                             * MainActivity will be on resume()*/
//                            MainActivity.IS_MAIN_RECREATE = true;
//                            finish();
//                        }else{
//                            /* set change prev iso code */
//                            LocaleHelper.setLocale(LanguageActivity.this, "km");
//                        }
//                    }
//                },false);
                LocaleHelper.setLocale(LanguageActivity.this, "ko");
                // After change language move to SettingActivity
                setResult(RESULT_OK);

                /*Huo Chhunleng 08.Aug.2018
                 * MainActivity will be on resume()*/
                MainActivity.IS_MAIN_RECREATE = true;
                finish();
            }
        });

        //dialog.setListener(this);

        dialog.stringMutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.equals("")){
                    textView.setText(s);
                }
            }
        });

    }

    //
//    @Override
//    public void listenData(String d) {
//        textView.setText(d);
//    }
}
