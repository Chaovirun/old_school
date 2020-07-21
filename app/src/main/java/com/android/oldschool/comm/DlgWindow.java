package com.android.oldschool.comm;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.android.oldschool.R;
import com.android.oldschool.SampleDialogFragment;
import com.android.oldschool.animate.RippleBackground;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author chanyouvita
 * @since 2019. 12. 11
 */
public class DlgWindow extends Dialog implements RecognitionListener{

    View layout;
    private TextView returnedText,searchTitle;
    private ImageView imgRecorder, imgClose;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    private boolean isListening = false;
    RippleBackground rippleBackground;
    Handler handler;
    Activity activity;
    private static final int REQUEST_RECORD_PERMISSION = 100;
    public DlgWindow(final Context context, final View layout, final Activity activity){
        super(context, R.style.Theme_AppCompat_Dialog_Alert);
        this.layout = layout;
        this.activity = activity;
        try {
            if (super.getWindow() != null) {
                super.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                super.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                super.setContentView(layout);
                super.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                super.show();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        rippleBackground=layout.findViewById(R.id.content);

        handler = new Handler();

        speech = SpeechRecognizer.createSpeechRecognizer(getContext());
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        returnedText = layout.findViewById(R.id.returnText);
        searchTitle = layout.findViewById(R.id.searchTitle);
        imgRecorder = layout.findViewById(R.id.imgRecorder);
        imgRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!isListening){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        ActivityCompat.requestPermissions(activity,
                                new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_PERMISSION);
                    }

                Toast.makeText(getContext(), "Record listening", Toast.LENGTH_SHORT).show();
//                    isListening = false;
//                }else{
//
//                    Toast.makeText(getContext(), "Stop listening", Toast.LENGTH_SHORT).show();
//                    speech.stopListening();
//                    isListening = true;
//                }
            }
        });
        imgClose = layout.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



    }

    private void foundDevice(){
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList=new ArrayList<Animator>();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imgRecorder, "ScaleX", 0f, 1.2f, 1f);
        animatorList.add(scaleXAnimator);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imgRecorder, "ScaleY", 0f, 1.2f, 1f);
        animatorList.add(scaleYAnimator);
        animatorSet.playTogether(animatorList);
        imgRecorder.setVisibility(View.VISIBLE);
        animatorSet.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (speech != null) {
            speech.destroy();
            Log.i(LOG_TAG, "destroy");
        }
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
//        progressBar.setIndeterminate(false);
//        progressBar.setMax(10);
        searchTitle.setText("Searching for:");
        returnedText.setText("...");
        rippleBackground.startRippleAnimation();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                foundDevice();
            }
        },1500);
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged");
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
    }

    @Override
    public void onError(int error) {
        rippleBackground.stopRippleAnimation();
        String errorMessage = getErrorText(error);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        returnedText.setText(errorMessage);

    }

    @Override
    public void onResults(Bundle results) {
        rippleBackground.stopRippleAnimation();
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        for (String result : matches)
            text += result + "\n";
        returnedText.setText(matches.get(0));
        searchTitle.setText(matches.get(0));
//        Intent intent = getActivity().getIntent();
//        intent.putExtra("searchKey", matches.get(0));
//        getActivity().setResult(RESULT_OK, intent);
//        getActivity().finish();
//        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, getActivity().getIntent());
//        dismiss();

    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        Log.i(LOG_TAG, "onPartialResults");

    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        Log.i(LOG_TAG, "onEvent");

    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

}
