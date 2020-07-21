package com.android.oldschool.comm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.android.oldschool.R;

public class BaseFragment<T extends ViewDataBinding, V extends AndroidViewModel> extends DialogFragment {

    protected ViewDataBinding mViewDataBinding;
    protected AndroidViewModel mViewModel;

    public void setDialogBinding(LayoutInflater layout, int layoutId, ViewGroup container, AndroidViewModel viewModel, int binding){
        this.mViewDataBinding = DataBindingUtil.inflate(layout, layoutId, container, false);
        this.mViewModel = viewModel;
        this.mViewDataBinding.setVariable(binding, this.mViewModel);

    }
}
