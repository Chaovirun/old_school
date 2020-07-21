package com.android.oldschool;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.oldschool.blure_builder.BlurBehind;
import com.android.oldschool.blure_builder.BlurBuilder;
import com.android.oldschool.blure_builder.OnBlurCompleteListener;
import com.android.oldschool.carousellayoutmanager.CarouselLayoutManager;
import com.android.oldschool.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.android.oldschool.carousellayoutmanager.CenterScrollListener;

import java.lang.ref.WeakReference;


@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends CoreActivity implements View.OnScrollChangeListener {
    SharedPreferences preferences;
    private static int SETTING_RESULT = 101;
    public static boolean IS_MAIN_RECREATE;
    EditText editText,inputPhone;
    InputPhone inputPhoneFormat;
    Button btnShow;
    RadioGroup rg;
    boolean isDelete = true;
    RecyclerView recyclerView,recyclerView1;
    //ImageView img1,img2,img3;
    LinearLayout linearLayout1,linearLayout2,linearLayout3,ly1;
    TextView textView;
    TabGroup tabGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_main);

        RadioButton r = findViewById(R.id.radio);
        RadioButton r1 = findViewById(R.id.radio1);
        rg = findViewById(R.id.rg);

//        tabGroup = findViewById(R.id.tapGroup);

        tabGroup.setOnTabGroupChildListener(new TabGroup.setChildListener() {
            @Override
            public void onPressChanged(int index) {
                switch (index){
                    case 0:
                        Toast.makeText(getApplicationContext(), "" + index, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "" + index, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "" + index, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        textView = findViewById(R.id.text1);
        r.setChecked(true);

        inputPhoneFormat = findViewById(R.id.inputPhoneFormat);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView1 = findViewById(R.id.recycler_view1);

        recyclerView1.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        recyclerView.addOnScrollListener(new CenterScrollListener());
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerAdapter adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (CarouselLayoutManager.INVALID_POSITION != adapterPosition) {
                    final int value = adapter.mPosition[adapterPosition];

                    initIndicator(value);
                    Log.d(">>>>>>>", "onCenterItemChanged: " + value);

                }
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int childRadio = radioGroup.getCheckedRadioButtonId();

                switch (childRadio){
                    case R.id.radio:
                        recyclerView1.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);
                        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new RecyclerAdapter());
                        recyclerView.setHasFixedSize(true);

                        Toast.makeText(MainActivity.this, "CarouselManager", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio1:
//                        recyclerView.setVisibility(View.GONE);
//                        recyclerView1.setVisibility(View.VISIBLE);
//                        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//                        recyclerView1.setLayoutManager(layoutManager1);
//                        recyclerView1.setAdapter(new RecyclerAdapter());
//                        recyclerView1.setHasFixedSize(true);
//                        recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                            @Override
//                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                                super.onScrollStateChanged(recyclerView, newState);
//                                if (newState == RecyclerView.SCROLL_STATE_IDLE){
//                                    int position = ((LinearLayoutManager)recyclerView.getLayoutManager())
//                                            .findFirstVisibleItemPosition();
//                                    matchPager(position);
//                                    Log.d(">>>>>>>", "addOnScrollListener: " + position);
//
//                                }
//                            }
//                        });
                        Toast.makeText(MainActivity.this, "LinearManager", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        editText = findViewById(R.id.edit_phone);
        UsPhoneNumberFormatter addLineNumberFormatter = new UsPhoneNumberFormatter(
                new WeakReference<>(editText));
        editText.addTextChangedListener(addLineNumberFormatter);

        inputPhoneFormat.setOnTextChangedListener();

        inputPhone = findViewById(R.id.inputPhone);
        inputPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (before > count){
                    isDelete = true;
                }else{
                    isDelete = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // +855 17 123-433
                if (!isDelete){
                    changeToPhoneFormat(charSequence, i1);
                }
                Log.d(">>>>>>>", "onTextChanged: " + charSequence + i + " --- " + i1);
            }

            @Override
            public void afterTextChanged(Editable e) {
                if (!isDelete){
                    if (inputPhone.getText().toString().length()== 2) {
                        inputPhone.getText().append("");
                    }
                    if (inputPhone.getText().toString().length() == 6){
                        inputPhone.getText().equals("");
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button go = findViewById(R.id.btnGo);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset data in Shared Reference
//                SharedPreferences.Editor editor = getSharedPreferences("oldSchool", MODE_PRIVATE).edit();
//                editor.putBoolean("finishedTutorial", false);
//                editor.apply();

//                SampleDialogFragment fragment
//                        = SampleDialogFragment.newInstance(
//                        12,
//                        6,
//                        true,
//                        true
//                );
//                //fragment.setTargetFragment(this, 1);
//                fragment.show(getSupportFragmentManager(), "blur_sample");
//                //startActivityForResult(new Intent(getApplicationContext(), SettingActivity.class),SETTING_RESULT);

                BlurBehind.getInstance().execute(MainActivity.this, new OnBlurCompleteListener() {
                    @Override
                    public void onBlurComplete() {
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivityForResult(intent, SETTING_RESULT);
                    }
                });

            }
        });


        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //changeLanguage();

            }
        });

//        img1 = findViewById(R.id.img1);
//        img2 = findViewById(R.id.img2);
//        img3 = findViewById(R.id.img3);

        ly1 = findViewById(R.id.ly1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTING_RESULT && resultCode == RESULT_OK){
            this.recreate();
        }
    }


    //    private void matchPager(int position) {
//        switch (position){
//            case 0:{
//                img1.setImageResource(R.drawable.main_pgn_on_icon);
//                img2.setImageResource(R.drawable.main_pgn_off_icon);
//                img3.setImageResource(R.drawable.main_pgn_off_icon);
//                break;
//            }
//            case 1:{
//                img2.setImageResource(R.drawable.main_pgn_on_icon);
//                img1.setImageResource(R.drawable.main_pgn_off_icon);
//                img3.setImageResource(R.drawable.main_pgn_off_icon);
//                break;
//            }
//            case 2:{
//                img3.setImageResource(R.drawable.main_pgn_on_icon);
//                img1.setImageResource(R.drawable.main_pgn_off_icon);
//                img2.setImageResource(R.drawable.main_pgn_off_icon);
//                break;
//            }
//        }
//    }

    /*
     * init indicator
     */
    private void initIndicator(int currentPage){
        ImageView[] docts = new ImageView[3];

        ly1.removeAllViews();
        for(int i = 0; i < docts.length; i++){
            docts[i] = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,0, getResources().getDimensionPixelSize(R.dimen.indicator_space),0);

            if (i != docts.length - 1) {
                docts[i].setLayoutParams(layoutParams);
            }
            docts[i].setBackgroundResource(R.drawable.main_pgn_off_icon);
            ly1.addView(docts[i]);
        }

        if(docts.length > 0){
            docts[currentPage].setBackgroundResource(R.drawable.main_pgn_on_icon);
        }
    }


    private void changeToPhoneFormat(CharSequence c , int a){
        if (inputPhone.getText().toString().length()<12){
            if (inputPhone.getText().toString().length()== 2) {
                inputPhone.getText().append(" ");
            }
            if (inputPhone.getText().toString().length() == 6){
                inputPhone.getText().append("-");
            }
        }else{
            Toast.makeText(this, "Phone number must lenght low than 10 digit",Toast.LENGTH_LONG).show();
        }

        Log.d(">>>>>>>", "changeToPhoneFormat: Text:" + inputPhone.getText().toString() + " --- " + c);

    }
    public String GetCountryZipCode(){
        String CountryID="";
        String CountryZipCode="";

        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        //getNetworkCountryIso
        CountryID= manager.getSimCountryIso().toUpperCase();
        String[] rl=this.getResources().getStringArray(R.array.CountryCodes);
        for(int i=0;i<rl.length;i++){
            String[] g=rl[i].split(",");
            if(g[1].trim().equals(CountryID.trim())){
                CountryZipCode=g[0];
                break;
            }
        }
        return CountryZipCode;
    }

    private PopupWindow showOptions(Context mcon){
        try{
            LayoutInflater inflater = (LayoutInflater) mcon.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_option_documents_type,null);
            PopupWindow optionspu = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            optionspu.setAnimationStyle(R.style.popup_window_animation);
            optionspu.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            optionspu.setFocusable(true);
            optionspu.setOutsideTouchable(true);
            optionspu.update(0, 0, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            optionspu.showAtLocation(layout, Gravity.CENTER, 0, 0);

            return optionspu;
        }
        catch (Exception e){e.printStackTrace();
            return null;}
    }

//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radio:
//                if (checked)
//                    // Pirates are the best
//                    break;
//            case R.id.radio1:
//                if (checked)
//                    // Ninjas rule
//                    break;
//        }
//    }


    @Override
    public void onScrollChange(View view, int i, int i1, int i2, int i3) {

    }
//    public boolean hasPreview() {
//        return getCurrentItem() > 0;
//    }
//
//    public boolean hasNext() {
//        return recyclerView.getAdapter() != null &&
//                getCurrentItem() < (recyclerView.getAdapter().getItemCount()- 1);
//    }
//
//    public void preview() {
//        int position = getCurrentItem();
//        if (position > 0)
//            setCurrentItem(position -1, true);
//    }
//
//    public void next() {
//        RecyclerView.Adapter adapter = recyclerView.getAdapter();
//        if (adapter == null)
//            return;
//
//        int position = getCurrentItem();
//        int count = adapter.getItemCount();
//        if (position < (count -1))
//            setCurrentItem(position + 1, true);
//    }
//
//    private int getCurrentItem(){
//        return ((LinearLayoutManager)recyclerView.getLayoutManager())
//                .findFirstVisibleItemPosition();
//    }
//
//    private void setCurrentItem(int position, boolean smooth){
//        if (smooth)
//            recyclerView.smoothScrollToPosition(position);
//        else
//            recyclerView.scrollToPosition(position);
//    }
//    private void changeLanguage() {
//        final String[] listItems = {"English(Defualt)", "Korea", "Chinese", "Japanese"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Choose a language").setCancelable(true);
//        builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which == 0) {
//                    setLocale("en");
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    finish();
//                }
//                if (which == 1) {
//                    setLocale("ko");
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    finish();
//
//                }
//                if (which == 2) {
//                    setLocale("zh");
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    finish();
//                }
//                if (which == 3) {
//                    setLocale("ja");
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    finish();
//                }
//                dialog.dismiss();
//            }
//        });
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
//    private void setLocale(String lang) {
//        Locale locale = new Locale(lang);
//        Configuration config = new Configuration();
//        Resources resources = getBaseContext().getResources();
//
//        if (lang.equals("en")){
//            Locale.setDefault(locale);
//            config.locale = locale;
//        }
//        if (lang.equals("ko")){
//            Locale.setDefault(locale);
//            config.locale = locale;
//        }
//        if (lang.equals("ja")){
//            Locale.setDefault(locale);
//            config.locale = locale;
//        }
//        if (lang.equals("zh")){
//            Locale.setDefault(locale);
//            config.locale = locale;
//        }
//        Log.d(">>>>>>>", "setLocale: " + locale);
//        resources.updateConfiguration(config, resources.getDisplayMetrics());
//        // Save data to Shared Reference
//        SharedPreferences.Editor editor = getSharedPreferences("oldSchool", MODE_PRIVATE).edit();
//        editor.putString("My_lang", lang);
//        editor.apply();
//
//    }
//    private void loadLocale() {
//        SharedPreferences pref = getSharedPreferences("oldSchool", MODE_PRIVATE);
//        String language = pref.getString("My_lang", "");
//        Locale locale = new Locale(language);
//        Configuration config = new Configuration();
//        Locale.setDefault(locale);
//        config.locale = locale;
//        Resources resources = getBaseContext().getResources();
//        resources.updateConfiguration(config, resources.getDisplayMetrics());
//    }


}
