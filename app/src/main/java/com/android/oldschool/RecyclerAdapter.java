package com.android.oldschool;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    int imgs[] = {R.drawable.card_bs, R.drawable.card_ct, R.drawable.card_hd};
    public final int[] mPosition;
    private int mItemsCount = 100;
    RecyclerAdapter(){
        mPosition = new int[mItemsCount];
        for (int i = 0; mItemsCount > i; ++i) {
            //noinspection MagicNumber
            mPosition[i] = i;
        }
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_holder, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        holder.image_slide.setImageResource(imgs[mPosition[position]]);
    }

    @Override
    public int getItemCount() {
        return imgs.length;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        ImageView image_slide = itemView.findViewById(R.id.image_slide);
    }
}
