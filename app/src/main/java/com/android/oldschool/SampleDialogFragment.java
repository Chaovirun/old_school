package com.android.oldschool;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.android.oldschool.animate.RippleBackground;
import com.android.oldschool.blure_builder.BlurDialogFragment;
import com.android.oldschool.comm.DlgAlert;
import com.android.oldschool.comm.DlgWindow;
import com.android.oldschool.comm.VoiceSearchDialogFragment;

import java.util.ArrayList;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;


/**
 * Simple fragment with blur effect behind.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SampleDialogFragment extends BlurDialogFragment{

    public MutableLiveData<Boolean> isPermissionGranted;

    /**
     * Bundle key used to start the blur dialog with a given scale factor (float).
     */
    private static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";

    /**
     * Bundle key used to start the blur dialog with a given blur radius (int).
     */
    private static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";

    /**
     * Bundle key used to start the blur dialog with a given dimming effect policy.
     */
    private static final String BUNDLE_KEY_DIMMING = "bundle_key_dimming_effect";

    /**
     * Bundle key used to start the blur dialog with a given debug policy.
     */
    private static final String BUNDLE_KEY_DEBUG = "bundle_key_debug_effect";

    private int mRadius;
    private float mDownScaleFactor;
    private boolean mDimming;
    private boolean mDebug;
    private View mLayout;
    private DlgWindow dialogWindow;
    private static final int REQUEST_RECORD_PERMISSION = 100;
    private TextView returnedText,searchTitle;
    private ImageView imgRecorder, imgClose;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";
    private boolean isListening = false;
    RippleBackground rippleBackground;
    Handler handler;


    /**
     * Retrieve a new instance of the sample fragment.
     *
     * @param radius          blur radius.
     * @param downScaleFactor down scale factor.
     * @param dimming         dimming effect.
     * @param debug           debug policy.
     * @return well instantiated fragment.
     */
    public static SampleDialogFragment newInstance(int radius,
                                                   float downScaleFactor,
                                                   boolean dimming,
                                                   boolean debug) {
        SampleDialogFragment fragment = new SampleDialogFragment();
        Bundle args = new Bundle();
        args.putInt(
                BUNDLE_KEY_BLUR_RADIUS,
                radius
        );
        args.putFloat(
                BUNDLE_KEY_DOWN_SCALE_FACTOR,
                downScaleFactor
        );
        args.putBoolean(
                BUNDLE_KEY_DIMMING,
                dimming
        );
        args.putBoolean(
                BUNDLE_KEY_DEBUG,
                debug
        );

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Bundle args = getArguments();
        mRadius = args.getInt(BUNDLE_KEY_BLUR_RADIUS);
        mDownScaleFactor = args.getFloat(BUNDLE_KEY_DOWN_SCALE_FACTOR);
        mDimming = args.getBoolean(BUNDLE_KEY_DIMMING);
        mDebug = args.getBoolean(BUNDLE_KEY_DEBUG);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isPermissionGranted =new MutableLiveData<>();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

//        return DlgAlert.DlgAlertOkCancel(getContext(), getString(R.string.change_language), "Cancel", "Ok", new DlgAlert.OnDialogListener() {
//            @Override
//            public void onClickDlgButton(int dialogIndex, DlgAlert.DIALOG_BTN buttonType) {
//                if (dialogIndex == 1) {
////                    LocaleHelper.setLocale(LanguageActivity.this, "km");
////                    // After change language move to SettingActivity
////                    setResult(RESULT_OK);
////
////                    /*Huo Chhunleng 08.Aug.2018
////                     * MainActivity will be on resume()*/
////                    MainActivity.IS_MAIN_RECREATE = true;
//                    dismiss();
//                }else{
//                    /* set change prev iso code */
//                    //LocaleHelper.setLocale(LanguageActivity.this, "ko");
//                }
//            }
//        },false);
        //return super.onCreateDialog(savedInstanceState);

//        return DlgAlert.DlgAlertOk(getContext(), "a", "back", new DlgAlert.OnDialogListener() {
//            @Override
//            public void onClickDlgButton(int dialogIndex, DlgAlert.DIALOG_BTN buttonType) {
//
//            }
//        }, false);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()),
//                    new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_PERMISSION);
//        }
//        Log.d(">>>>>>", "onViewCreated ####################################: running");
        mLayout = View.inflate(getContext(), R.layout.dlg_voice_search, null);
        dialogWindow = new DlgWindow(getContext(), mLayout, getActivity());
        dialogWindow.setCancelable(false);
        dialogWindow.setCanceledOnTouchOutside(false);
        dialogWindow.getWindow().setDimAmount(0.9f);
        return dialogWindow;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(">>>>>>", "onViewCreated ####################################: running");

//        returnedText = mLayout.findViewById(R.id.returnText);
//        searchTitle = mLayout.findViewById(R.id.textView);
//        imgRecorder = mLayout.findViewById(R.id.imgRecorder);
//        imgClose    = mLayout.findViewById(R.id.imgClose);
//
//        rippleBackground=mLayout.findViewById(R.id.content);
//
//        handler = new Handler();
//        returnedText.setText("abcdalsdjkga");

//        speech = SpeechRecognizer.createSpeechRecognizer(getContext());
//        speech.setRecognitionListener(this);
//        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
//        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()),
//                    new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_PERMISSION);
//        }

//        imgRecorder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isListening){
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                        ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()),
//                                new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_PERMISSION);
//                    }
//                    isListening = false;
//                }else{
//
//                    Toast.makeText(getContext(), "Stop listening", Toast.LENGTH_SHORT).show();
//                    speech.stopListening();
//                    isListening = true;
//                }
//            }
//        });
    }

    //    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        View view = getActivity().getLayoutInflater().inflate(R.layout.dlg_layout, null);
//        builder.setView(view);
//        return builder.create();
//    }

    @Override
    protected boolean isDebugEnable() {
        return mDebug;
    }

    @Override
    protected boolean isDimmingEnable() {
        return mDimming;
    }

    @Override
    protected boolean isActionBarBlurred() {
        return true;
    }

    @Override
    protected float getDownScaleFactor() {
        return mDownScaleFactor;
    }

    @Override
    protected int getBlurRadius() {
        return mRadius;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isPermissionGranted.setValue(true);
                } else {
                    Toast.makeText(getContext(), "Permission Denied!", Toast
                            .LENGTH_SHORT).show();
                }

        }
    }
//
//    private void foundDevice(){
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setDuration(400);
//        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
//        ArrayList<Animator> animatorList=new ArrayList<Animator>();
//        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imgRecorder, "ScaleX", 0f, 1.2f, 1f);
//        animatorList.add(scaleXAnimator);
//        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imgRecorder, "ScaleY", 0f, 1.2f, 1f);
//        animatorList.add(scaleYAnimator);
//        animatorSet.playTogether(animatorList);
//        imgRecorder.setVisibility(View.VISIBLE);
//        animatorSet.start();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (speech != null) {
//            speech.destroy();
//            Log.i(LOG_TAG, "destroy");
//        }
//    }
//
//    @Override
//    public void onReadyForSpeech(Bundle params) {
//        Log.i(LOG_TAG, "onReadyForSpeech");
//    }
//
//    @Override
//    public void onBeginningOfSpeech() {
//        Log.i(LOG_TAG, "onBeginningOfSpeech");
////        progressBar.setIndeterminate(false);
////        progressBar.setMax(10);
//        searchTitle.setText("Searching for:");
//        returnedText.setText("...");
//        rippleBackground.startRippleAnimation();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                foundDevice();
//            }
//        },1500);
//    }
//
//    @Override
//    public void onRmsChanged(float rmsdB) {
//        Log.i(LOG_TAG, "onRmsChanged");
//    }
//
//    @Override
//    public void onBufferReceived(byte[] buffer) {
//        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
//    }
//
//    @Override
//    public void onEndOfSpeech() {
//        Log.i(LOG_TAG, "onEndOfSpeech");
//    }
//
//    @Override
//    public void onError(int error) {
//        rippleBackground.stopRippleAnimation();
//        String errorMessage = getErrorText(error);
//        Log.d(LOG_TAG, "FAILED " + errorMessage);
//        returnedText.setText(errorMessage);
//
//    }
//
//    @Override
//    public void onResults(Bundle results) {
//        rippleBackground.stopRippleAnimation();
//        Log.i(LOG_TAG, "onResults");
//        ArrayList<String> matches = results
//                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//        String text = "";
//        for (String result : matches)
//            text += result + "\n";
//        returnedText.setText(matches.get(0));
//        searchTitle.setText(matches.get(0));
////        Intent intent = getActivity().getIntent();
////        intent.putExtra("searchKey", matches.get(0));
////        getActivity().setResult(RESULT_OK, intent);
////        getActivity().finish();
////        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, getActivity().getIntent());
////        dismiss();
//
//    }
//
//    @Override
//    public void onPartialResults(Bundle partialResults) {
//        Log.i(LOG_TAG, "onPartialResults");
//
//    }
//
//    @Override
//    public void onEvent(int eventType, Bundle params) {
//        Log.i(LOG_TAG, "onEvent");
//
//    }
//
//    public static String getErrorText(int errorCode) {
//        String message;
//        switch (errorCode) {
//            case SpeechRecognizer.ERROR_AUDIO:
//                message = "Audio recording error";
//                break;
//            case SpeechRecognizer.ERROR_CLIENT:
//                message = "Client side error";
//                break;
//            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
//                message = "Insufficient permissions";
//                break;
//            case SpeechRecognizer.ERROR_NETWORK:
//                message = "Network error";
//                break;
//            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
//                message = "Network timeout";
//                break;
//            case SpeechRecognizer.ERROR_NO_MATCH:
//                message = "No match";
//                break;
//            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
//                message = "RecognitionService busy";
//                break;
//            case SpeechRecognizer.ERROR_SERVER:
//                message = "error from server";
//                break;
//            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
//                message = "No speech input";
//                break;
//            default:
//                message = "Didn't understand, please try again.";
//                break;
//        }
//        return message;
//    }

}
