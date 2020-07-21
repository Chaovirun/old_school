package com.android.oldschool.comm;

import android.app.Dialog;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.oldschool.BR;
import com.android.oldschool.R;
import com.android.oldschool.databinding.DlgLayoutBinding;


public class MyDialog extends BaseFragment {

    public MutableLiveData<String> stringMutableLiveData = new MutableLiveData<>();
    MyDialogViewModel myModel;
    DlgLayoutBinding binding;
    IListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myModel = new MyDialogViewModel(getActivity().getApplication());
        setDialogBinding(inflater, R.layout.dlg_layout,container, myModel, BR.dialogViewModel);
        binding = (DlgLayoutBinding) mViewDataBinding;
        return binding.getRoot();
    }

    //    private static final String KEY_MY_INFO = "KEY_MY_INFO";
//
//    private String myInfo;
//
//    private IListener listener;
//
//    public static MyDialog newInstance(String myInfo) {
//        MyDialog dialog = new MyDialog();
//        Bundle bundle = new Bundle();
//        bundle.putString(KEY_MY_INFO, myInfo);
//        dialog.setArguments(bundle);
//        return dialog;
//    }
//

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stringMutableLiveData.setValue(binding.editText.getText().toString());
        myModel.getAction().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                switch (s){
                    case "DISMISS":
//                        if (listener!=null){
//                            listener.listenData(binding.editText.getText().toString());
//                        }
                        dismiss();
                        break;
                    case "CANCEL":
                        Toast.makeText(getContext(), "Cancel", Toast.LENGTH_LONG).show();
                        dismiss();
                        break;
                    case "OK":
                        String st = binding.editText.getText().toString();
                        stringMutableLiveData.setValue(st);
                        Toast.makeText(getContext(), "Ok", Toast.LENGTH_LONG).show();
                        dismiss();
                        break;
                }
            }
        });
    }

//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        final EditText editText = view.findViewById(R.id.editText);
//
//        Button button = view.findViewById(R.id.button4);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(">>>>>>", "onClick: HI");
//                if (listener!=null){
//                    listener.listenData(editText.getText().toString());
//                }
//                dismiss();
//            }
//        });
//    }
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        FragmentActivity activity = getActivity();
//
//
//
////        binding.setVariable(myInfo);
//
//        return new AlertDialog.Builder(activity, R.style.Theme_AppCompat_DayNight_Dialog_Alert)
//                .setView(binding.getRoot())
//                .create();
//    }
//
    public void setListener(IListener listener)
    {
        this.listener = listener;
    }

    public interface IListener{
        void listenData(String d);
    }

}
