package com.example.geoquiz2;

import android.widget.TextView;

public class MethodsHelper {

    private static Question [] q = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };


    //  Encapsulating with updateQuestion()
    public static TextView updateQuestion( Question[] mQuestionBank, int mCurrentIndex, TextView mQuestionTextView) {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        return mQuestionTextView;
    }


    public static Question [] getQuest (){
        return q;
    }

    public static void checkIfAlreadyAnswered(){
        return;
    }
}
