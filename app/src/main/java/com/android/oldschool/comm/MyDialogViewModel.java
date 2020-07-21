package com.android.oldschool.comm;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MyDialogViewModel extends AndroidViewModel {

    public MutableLiveData<String> action;

    private Context context;
    public MyDialogViewModel(@NonNull Application application) {
        super(application);
        context = application;
        action = new MutableLiveData<>();
    }

    public LiveData<String> getAction(){
        return action;
    }

    public void msg(){

        action.setValue("DISMISS");

    }

    public void btnOk(){
        action.setValue("OK");
    }

    public void btnCanccel(){
        action.setValue("CANCEL");
    }

}
