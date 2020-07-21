package com.android.oldschool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.oldschool.comm.PresetValueButton;

public class SelectorAdapter extends RecyclerView.Adapter<SelectorAdapter.ItemViewHolder> {

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ItemViewHolder(inflater.inflate(R.layout.selector_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.preset_time_value_button_30.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        PresetValueButton preset_time_value_button_30;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            preset_time_value_button_30 = itemView.findViewById(R.id.preset_time_value_button_30);

            preset_time_value_button_30.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
