package com.example.geoquiz2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//          What you are seeing here is that the ActivityManager maintains a back stack and that this back stack
//        is not just for your application’s activities. Activities for all applications share the back stack, which is
//        one reason the ActivityManager is involved in starting your activities and lives with the OS and not
//        your application. The stack represents the use of the OS and device as a whole rather than the use of a
//        single application.
public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
//        To retrieve the value from the extra, you will use: getBooleanExtra()
//        The first argument is the name of the extra. The second argument of getBooleanExtra(…) is a default
//        answer if the key is not found.
//        Note that Activity.getIntent() always returns the Intent that started the activity. This is what you
//        sent when calling startActivity(Intent).
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswerButton = findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });

//        There are two methods you can call in the child activity to send data back to the parent:
//        setResult(int resultCode, Intent data)
//        Typically, the result code is one of two predefined constants: Activity.RESULT_OK or
//        Activity.RESULT_CANCELED. (You can use another constant, RESULT_FIRST_USER, as an offset when
//        defining your own result codes.)
//        Setting result codes is useful when the parent needs to take different action depending on how the child
//        activity finished.
//        For example, if a child activity had an OK button and a Cancel button, the child activity would set a
//        different result code depending on which button was pressed. Then the parent activity would take a
//        different action depending on the result code.
//                Calling setResult(…) is not required of the child activity. If you do not need to distinguish
//        between results or receive arbitrary data on an intent, then you can let the OS send a default
//        result code. A result code is always returned to the parent if the child activity was started with
//        startActivityForResult(…). If setResult(…) is not called,d, then when the user presses the Back
//        button, the parent will receive Activity.RESULT_CANCELED.

    }


//    This static method allows you to create an Intent properly configured with the extras CheatActivity
//    will need. The answerIsTrue argument, a boolean, is put into the intent with a private name using
//    the EXTRA_ANSWER_IS_TRUE constant. You will extract this value momentarily. Using a newIntent(…)
//    method like this for your activity subclasses will make it easy for other code to properly configure their
//    launching intents.
//    You only need one extra, but you can put multiple extras on an Intent if you need to. If you do, add
//    more arguments to your newIntent(…) method to stay consistent with the pattern

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }


//     in passing some specific data back to QuizActivity. So you
//    are going to create an Intent, put an extra on it, and then call Activity.setResult(int, Intent) to
//    get that data into QuizActivity’s hands.
//    In CheatActivity, add a constant for the extra’s key and a private method that does this work. Then
//    call this method in the SHOW ANSWER button’s listener.
    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }


    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }



}
