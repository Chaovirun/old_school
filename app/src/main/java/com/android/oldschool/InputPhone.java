package com.android.oldschool;

import android.content.Context;
import androidx.appcompat.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Toast;

public class InputPhone extends AppCompatEditText implements TextWatcher {

    private boolean isDelete = true;
    Context context;
    public InputPhone(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public InputPhone(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr();
    }

    private void initView() {

    }

    public void initAttr(){

    }

    public void setOnTextChangedListener(){

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
        if (before > count){
            isDelete = true;
        }else{
            isDelete = false;
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // +855 17 123-433
        if (!isDelete){
            changeToPhoneFormat(getEditableText(),charSequence, i1);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!isDelete){
            if (editable.toString().length() == 2) {
                editable.append("");
            }
            if (editable.toString().length() == 6){
                editable.equals("");
            }
        }else{
            Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
        }
    }

    private void changeToPhoneFormat(Editable editable,CharSequence c , int a){
        if (editable.toString().length()<12){
            if (editable.toString().length()== 2) {
                editable.append(" ");
            }
            if (editable.toString().length() == 6){
                editable.append("-");
            }
        }else{
            //Toast.makeText(this, "Phone number must lenght low than 10 digit",Toast.LENGTH_LONG).show();
        }

        //Log.d(">>>>>>>", "changeToPhoneFormat: Text:" + inputPhone.getText().toString() + " --- " + c);

    }
}
