package com.android.oldschool;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextThemeWrapper;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.android.oldschool.TimePicker.RageTimePickerDialog;

import java.util.Calendar;

public class TimePickerDialogFragment extends DialogFragment {

    Context context;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new RageTimePickerDialog(getActivity(), android.R.style.Theme_Holo_Dialog_NoActionBar,timeSetListener, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    private TimePickerDialog.OnTimeSetListener timeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        Toast.makeText(getActivity(), "selected time is "
                                        + view.getHour() +
                                        " / " + view.getMinute()
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            };
}
