package com.example.geoquiz2;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {

    // Buttons
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;

    // question Text View
    private TextView mQuestionTextView;
    // the array of questions  with their answers
    private Question[] mQuestionBank = MethodsHelper.getQuest();

    // can't press twice the answer for the same Question
    private boolean [] isQuestionAnswered = MethodsHelper.getIsQuestionAnswered();
    // index on array of questions
    private int mCurrentIndex = 0;

    // adding a tag
    private static final String TAG = "QuizActivity";
    // the cheat code activity constant
    private static final int REQUEST_CODE_CHEAT = 0;
    // decode if user saw the answer
    private boolean mIsCheater;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // The d stands for “debug” and refers to the level of the log message.
        Log.d(TAG, "onCreate(Bundle) called");

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }


        // get the int ref to buttons from R using findViewById()
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mPrevButton = findViewById(R.id.prev_button);
        mCheatButton = findViewById(R.id.cheat_button);

        // setting text to question
        mQuestionTextView = findViewById(R.id.question_text);


        // setOnClickListener to buttons
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    checkAnswer(true);
            }
        });
        // added toasts when button clicked
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    checkAnswer(false);
            }
        });



        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mQuestionTextView = MethodsHelper.updateQuestion(  mQuestionBank,  mCurrentIndex,  mQuestionTextView);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                if (isQuestionAnswered[mCurrentIndex]){
                    mTrueButton.setEnabled(true);
                    mFalseButton.setEnabled(true);
                } else {
                    mTrueButton.setEnabled(false);
                    mFalseButton.setEnabled(false);
                }

                mQuestionTextView = MethodsHelper.updateQuestion(  mQuestionBank,  mCurrentIndex,  mQuestionTextView);
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mCurrentIndex = abs((mCurrentIndex - 1) % mQuestionBank.length);
                if (isQuestionAnswered[mCurrentIndex]){
                    mTrueButton.setEnabled(true);
                    mFalseButton.setEnabled(true);
                } else {
                    mTrueButton.setEnabled(false);
                    mFalseButton.setEnabled(false);
                }
                mQuestionTextView = MethodsHelper.updateQuestion(mQuestionBank, mCurrentIndex, mQuestionTextView);
            }
        });


        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 When an activity calls startActivity(Intent), this call
//                 is sent to the OS.In particular, it is sent to a part of the OS called the ActivityManager. The ActivityManager then
//                 creates the Activity instance and calls its onCreate(Bundle) method
//                 An intent is an object that a component can use to communicate with the OS. The only components
//                you have seen so far are activities, but there are also services, broadcast receivers, and content
//                providers.
//                Intents are multipurpose communication tools, and the Intent class provides different constructors
//                depending on what you are using the intent to do.
//                Before starting the activity, the ActivityManager checks the package’s manifest for a declaration with
//                the same name as the specified Class. If it finds a declaration, it starts the activity, and all is well. If it
//                does not, you get a nasty ActivityNotFoundException, which will crash your app. This is why all of
//                your activities must be declared in the manifest.
//                When you create an Intent with a Context and a Class object, you are creating an explicit intent. You
//                use explicit intents to start activities within your application.
//                        It may seem strange that two activities within your application must communicate via the
//                ActivityManager, which is outside of your application. However, this pattern makes it easy for an
//                activity in one application to work with an activity in another application.
//                When an activity in your application wants to start an activity in another application, you create an
//                implicit intent.
//                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
//                Intent.putExtra();
//                To inform the CheatActivity of the answer to the current question, you will pass it the value of
//                mQuestionBank[mCurrentIndex].isAnswerTrue()
//                You will send this value as an extra on the Intent that is passed into startActivity(Intent).
//                        Extras are arbitrary data that the calling activity can include with an intent.
//                The OS forwards the intent to
//                the recipient activity, which can then access the extras and retrieve the data
//                An extra is structured as a key-value pair, like the one you used to save out the value of mCurrentIndex
//                in QuizActivity.onSaveInstanceState(Bundle).
//                        To add an extra to an intent, you use Intent.putExtra(…)
//                The first argument is
//                always a String key, and the second argument is the value, whose type will vary. It returns the Intent
//                itself, so you can chain multiple calls if you need to.
//                        Now you could return to QuizActivity and put the extra on the intent, but there is a better approach.
//                        There is no reason for QuizActivity, or any other code in your app, to know the implementation
//                details of what CheatActivity expects as extras on its Intent. Instead, you can encapsulate that work
//                into a newIntent(…) method.
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
//                startActivity(intent);


//                When you want to hear back from the child activity, you call startActivityForResult()
//                The first parameter is the same intent as before. The second parameter is the request code. The request
//                code is a user-defined integer that is sent to the child activity and then received back by the parent. It
//                is used when an activity starts more than one type of child activity and needs to know who is reporting
//                back. QuizActivity will only ever start one type of child activity, but using a constant for the request
//                code is a best practice that will set you up well for future changes.
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });




        //  Encapsulating with updateQuestion()
        mQuestionTextView = MethodsHelper.updateQuestion(  mQuestionBank,  mCurrentIndex,  mQuestionTextView);
    }




//    This method will accept a boolean variable that identifies whether the user pressed TRUE or FALSE.
//    Then, it will check the user’s answer against the answer in the current Question object. Finally, after
//    determining whether the user answered correctly, it will make a Toast that displays the appropriate
//    message to the user.
    private void checkAnswer (boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        isQuestionAnswered[mCurrentIndex] = false;

        int messageResId = 0;
        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) messageResId = R.string.correct_toast;
            else messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    //
    private static final String KEY_INDEX = "index";
//    The post-rotation QuizActivity instance needs to know the old value of
//    mCurrentIndex. You need a way to save this data across a runtime configuration change, like rotation.
//    One way to do this is to override the onSaveInstanceState()
//     This method is called before onStop(), except when the user presses the Back button
//    The default implementation of onSaveInstanceState(Bundle) directs all of the activity’s views to
//    save their state as data in the Bundle object. A Bundle is a structure that maps string keys to values of
//    certain limited types.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }
}
