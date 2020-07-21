package com.android.oldschool.localize_image;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.oldschool.MainActivity;
import com.android.oldschool.R;

import static android.content.Context.MODE_PRIVATE;

public class ScreenSlideThreePageFragment extends Fragment {
    ViewPager mPager;

    public ScreenSlideThreePageFragment(){

    }

    @SuppressLint("ValidFragment")
    public ScreenSlideThreePageFragment(ViewPager mPager){
        this.mPager = mPager;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_three_page, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout next = view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save data to Shared Reference
                SharedPreferences.Editor editor = getContext().getSharedPreferences("oldSchool", MODE_PRIVATE).edit();
                editor.putBoolean("finishedTutorial", true);
                editor.apply();
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });
    }
}
