package com.android.oldschool.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;

import com.android.oldschool.R;
import com.android.oldschool.comm.PresetValueButton;

import java.util.ArrayList;


public class Usage_Adapter extends BaseAdapter {

    public OnItemClickListener listener = null;
    private ArrayList<UsageItem> data;
    private LayoutInflater inflater=null;
    private int selectedPosition;

    public Usage_Adapter(Context context, ArrayList<UsageItem> data, int selectedPosition) {
        this.data=data;
        this.selectedPosition = selectedPosition;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.selector_item, null);


        PresetValueButton presetValueButton = vi.findViewById(R.id.preset_time_value_button_30);

        presetValueButton.setChecked(position == selectedPosition);
        presetValueButton.setTag(position);
        presetValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = (Integer)view.getTag();
                listener.onItemClick(selectedPosition);
                notifyDataSetChanged();
            }
        });

        UsageItem item = data.get(position);
        Log.d(">>>>>>", "getView: " + item.getITEM_CODE() + item.getITEM_NAME());
        presetValueButton.setValue(item.getITEM_NAME());
        presetValueButton.setUnit(item.getITEM_NAME());

        return vi;
    }


    public void setOnListItemListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}