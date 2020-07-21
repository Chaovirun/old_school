//package com.android.oldschool.adapter;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.databinding.DataBindingUtil;
//import androidx.fragment.app.Fragment;
//import androidx.transition.Slide;
//
///**
// * Slide Fragment
// *
// * @author KOSIGN-SMART
// * @since 2019.12.03
// */
//public class SlideFragment extends Fragment {
//    private Slide slide;
//
//    public SlideFragment(Slide slide){
//        this.slide = slide;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = null;
//        if(slide.getSlideType().equals(getString(R.string.image))){
//            View v = inflater.inflate(LayoutInflater.from(container.getContext()), R.layout.slide_item_image_layout, container, false);
//            slideItemImageLayoutBinding.setSlideItem(slide);
//            view = slideItemImageLayoutBinding.getRoot();
//        }else {
//            SlideItemVideoLayoutBinding slideItemVideoLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.slide_item_video_layout, container, false);
//            slideItemVideoLayoutBinding.setSlideItem(slide);
//            view = slideItemVideoLayoutBinding.getRoot();
//        }
//        return view;
//    }
//}
