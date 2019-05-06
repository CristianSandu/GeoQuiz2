package com.example.geoquiz2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    // question Text View
    private TextView mQuestionTextView;
    // the array of questions  with their answers
    private Question[] mQuestionBank = MethodsHelper.getQuest();

    // can't press twice the answer for the same Question
    private boolean [] isQuestionAnswered = new boolean []{true, true, true, true, true, true} ;

    // index on array of questions
    private int mCurrentIndex = 0;

    // adding a tag
    private static final String TAG = "QuizActivity";









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mQuestionTextView = MethodsHelper.updateQuestion(  mQuestionBank,  mCurrentIndex,  mQuestionTextView);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
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
        if (userPressedTrue == answerIsTrue) messageResId = R.string.correct_toast;
        else messageResId = R.string.incorrect_toast;

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
}
