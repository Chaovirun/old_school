package com.android.oldschool;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.oldschool.adapter.UsageItem;
import com.android.oldschool.adapter.Usage_Adapter;
import com.android.oldschool.blure_builder.BlurBehind;
import com.android.oldschool.comm.PresetRadioGroup;
import com.android.oldschool.comm.PresetValueButton;

import java.util.ArrayList;

public class SettingActivity extends CoreActivity{

    private static final int CHOOSE_LANGUAGE_CODE = 1;
    private static boolean is_recreate;
    public static boolean b;
    PresetRadioGroup mSetDurationPresetRadioGroup;
    RecyclerView recyclerView;
    ArrayList<UsageItem> items;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);

        recyclerView = findViewById(R.id.recycler_view);
//        SelectorAdapter adapter = new SelectorAdapter();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
//        recyclerView.setAdapter(adapter);

        final ListView mListView = findViewById(R.id.lvdata);
        final Usage_Adapter mAdapter = new Usage_Adapter(this, items(), 2);

        mAdapter.setOnListItemListener(new Usage_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(SettingActivity.this, "" + items.get(position).getITEM_NAME(), Toast.LENGTH_SHORT).show();
            }
        });
        mListView.setAdapter(mAdapter);
//        mSetDurationPresetRadioGroup = (PresetRadioGroup) findViewById(R.id.preset_time_radio_group);
//        mSetDurationPresetRadioGroup.setOnCheckedChangeListener(new PresetRadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(View radioGroup, View radioButton, boolean isChecked, int checkedId) {
//                switch (checkedId){
//                    case R.id.preset_time_value_button_30:
//                        Toast.makeText(getApplicationContext(), "30", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.preset_time_value_button_60:
//                        Toast.makeText(getApplicationContext(), "60", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.preset_time_value_button_120:
//                        Toast.makeText(getApplicationContext(), "120" , Toast.LENGTH_SHORT).show();
//                        break;
//                }
//                Toast.makeText(getApplicationContext(), "" + checkedId, Toast.LENGTH_SHORT).show();
            }

            private ArrayList<UsageItem> items(){
                items = new ArrayList<>();
                UsageItem item = new UsageItem();
                item.setITEM_CODE("200");
                item.setITEM_NAME("COCA");

                UsageItem item1 = new UsageItem();
                item1.setITEM_CODE("205");
                item1.setITEM_NAME("CARABOA");

                UsageItem item2 = new UsageItem();
                item2.setITEM_CODE("201");
                item2.setITEM_NAME("PEPSI");

                items.add(item);
                items.add(item1);
                items.add(item2);
                return items;
            }
//        });

//
//        BlurBehind.getInstance()
//                .withAlpha(80)
//                .withFilterColor(Color.parseColor("#50000000"))
//                .setBackground(this);
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivityForResult(new Intent(getApplicationContext(), LanguageActivity.class),CHOOSE_LANGUAGE_CODE);
//            }
//        });
//
//        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });

    }

//    @Override
//    public void onBackPressed() {
//        setResult(RESULT_OK);
//
//        finish();
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(0, 0);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == CHOOSE_LANGUAGE_CODE) {
//            Log.d(">>>>>>", "onActivityResult: Callback");
//            is_recreate = true;
//            this.recreate();
//        }
//    }

