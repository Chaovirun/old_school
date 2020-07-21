//package com.android.oldschool.adapter;
//
//import java.util.ArrayList;
//import java.util.List;
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentStatePagerAdapter;
//import androidx.transition.Slide;
//
///**
// * Slide ViewPager Adapter
// *
// * @author KOSIGN-SMART
// * @since 2019.12.03
// *
// */
//public class SlideAdapter extends FragmentStatePagerAdapter {
//    private List<SlideFragment> fragments;
//    private List<Slide> slides;
//
//    public SlideAdapter(FragmentManager fm, List<Slide> slides){
//        super(fm);
//        this.fragments = new ArrayList<>();
//        this.slides = slides;
//
//        for(Slide slide : slides){
//            fragments.add(new SlideFragment(slide));
//        }
//    }
//
//    @NonNull
//    @Override
//    public Fragment getItem(int position) {
//        return fragments.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return fragments.size();
//    }
//}
