package com.example.geoquiz2;
import android.util.Log;

import android.widget.TextView;

public class MethodsHelper {

    // adding a tag
    private static final String TAG = "QuizActivity";

    private static Question [] q = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_nevada, true),
            new Question(R.string.question_London, true),
            new Question(R.string.question_Matherhorn, false),
            new Question(R.string.question_Cuba, true),
            new Question(R.string.question_Equatorial_Guinea, true),
            new Question(R.string.question_Iceland, true),
            new Question(R.string.question_tigers, false),
            new Question(R.string.question_Nicaragua, true),
            new Question(R.string.question_Brazil, true),
            new Question(R.string.question_Ulaanbaatar, true),
            new Question(R.string.question_Bermuda, false),
            new Question(R.string.question_Sultan_of_Brunei, false),
            new Question(R.string.question_Ural_Mountains, false),
            new Question(R.string.question_Kauai, false),
            new Question(R.string.question_Peru, false),
            new Question(R.string.question_Montana, false),
            new Question(R.string.question_equator, false),
            new Question(R.string.question_tibet, false),
            new Question(R.string.question_flat_australia, true),
            new Question(R.string.question_Kilimanjaro, false),
            new Question(R.string.question_Cotopaxi, false),
            new Question(R.string.question_Maine, false),
            new Question(R.string.question_Mexico, true),
            new Question(R.string.question_glaciers, false),
            new Question(R.string.question_asia_timbuktu, false)


    };


    //  Encapsulating with updateQuestion()
    public static TextView updateQuestion( Question[] mQuestionBank, int mCurrentIndex, TextView mQuestionTextView) {
//        Added log to see where updateQuestion has been called.
//        Log.d(TAG, "Updating question text", new Exception());
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

    public static boolean[] getIsQuestionAnswered() {
        boolean[] booleans = new boolean[getQuest().length];
        for (int i = 0; i < booleans.length; i++) {
            booleans[i] = true;
        }
        return booleans;

    }
}
