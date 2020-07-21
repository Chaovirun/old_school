package com.android.oldschool.comm;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.oldschool.R;
/**
 * @author chanyouvita
 * @since 2019. 12. 11.
 */
public class DlgAlert {

    /**
     * listener
     */
    private static OnDialogListener mlistener;

    /**
     * alert window
     */
    private static DlgWindow dialogWindow;

    /**
     * option button type
     */
    public enum DIALOG_BTN {
        LEFT_BTN, RIGHT_BTN
    }

    /**
     * alert only one button
     * @param context activity context
     * @param alert_message alert message
     * @param alert_right alert right button
     * @param listener alert button listener
     * @param cancel_able alert disable, enable back key & touch outside screen
     */
    public static DlgWindow DlgAlertOk(Context context, String alert_message, String alert_right, OnDialogListener listener, boolean cancel_able) {
        mlistener = listener;

        View mLayout = View.inflate(context, R.layout.dlg_voice_search, null);
        //dialogWindow = new DlgWindow(context, mLayout);
        dialogWindow.setCancelable(cancel_able);
        dialogWindow.setCanceledOnTouchOutside(cancel_able);
        dialogWindow.getWindow().setDimAmount(0.9f);
        //TextView tv_message  = mLayout.findViewById(R.id.alert_message);

        //Button mOk     = mLayout.findViewById(R.id.btn_right);
        //Button mCancel = mLayout.findViewById(R.id.btn_left);
        ImageView imgClose = mLayout.findViewById(R.id.imgClose);

        //tv_message.setText(alert_message);

        //mCancel.setVisibility(View.GONE);

        //mOk.setText(alert_right);
//        mOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialogWindow.dismiss();
//                if (mlistener != null)
//                    mlistener.onClickDlgButton(1, DIALOG_BTN.RIGHT_BTN);
//            }
//        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogWindow.dismiss();
                if (mlistener != null){
                    mlistener.onClickDlgButton(1, DIALOG_BTN.RIGHT_BTN);
                }
            }
        });

        return dialogWindow;
    }

    /**
     * alert two button
     * @param context activity context
     * @param alert_message alert message
     * @param alert_right alert right button
     * @param alert_left alert left button
     * @param listener alert button listener
     * @param cancel_able alert disable, enable back key & touch outside screen
     */
    public static DlgWindow DlgAlertOkCancel(Context context, String alert_message, String alert_left, String alert_right, OnDialogListener listener, boolean cancel_able) {
        mlistener = listener;

        View mLayout = View.inflate(context, R.layout.comm_alert_dialog, null);
        //dialogWindow = new DlgWindow(context, mLayout, this);
//        dialogWindow.setCancelable(cancel_able);
//        dialogWindow.setCanceledOnTouchOutside(cancel_able);
//        dialogWindow.getWindow().setDimAmount(0.8f);
        TextView tv_message  = mLayout.findViewById(R.id.alert_message);

        Button mOk     = mLayout.findViewById(R.id.btn_right);
        Button mCancel = mLayout.findViewById(R.id.btn_left);

        tv_message.setText(alert_message);

        mCancel.setText(alert_left);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogWindow.dismiss();
                if (mlistener != null)
                    mlistener.onClickDlgButton(2, DIALOG_BTN.LEFT_BTN);
            }
        });

        mOk.setText(alert_right);
        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogWindow.dismiss();
                if (mlistener != null)
                    mlistener.onClickDlgButton(1, DIALOG_BTN.RIGHT_BTN);
            }
        });

        return dialogWindow;
    }

    public interface OnDialogListener {
        void onClickDlgButton(int dialogIndex, DIALOG_BTN buttonType);
    }
}
