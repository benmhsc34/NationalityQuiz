package com.internationalknowledgequiz.benja.international;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.internationalknowledgequiz.benja.international.Utils.Question;
import com.internationalknowledgequiz.benja.international.Utils.QuestionBank;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionTextView;
    private Button mAnswerButton1;
    private Button mAnswerButton2;
    private Button mAnswerButton3;
    private Button mAnswerButton4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestions;

    public static final String BUNDLE_EXTRA_SCORE = QuestionActivity.class.getCanonicalName().concat("BUNDLE_EXTRA_SCORE");
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";
    public static final String BUNDLE_EXTRA_NAME = "currentName";
    private CollectionReference mCollectionReference = FirebaseFirestore.getInstance().collection("Questions");

    private boolean mEnableTouchEvents;
    List<Question> questionList = new ArrayList<>();
    String name;

    TextView questionNumber;
    int questionNumero = 1;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);


        questionNumber = findViewById(R.id.questionNumber);

        Intent intent = getIntent();
        String number = intent.getStringExtra("countryFlag");
        String str = intent.getStringExtra("countryName");

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        ImageView imageView = findViewById(R.id.imageViewOfFlag);
        TextView textView = findViewById(R.id.textViewOfCountry);

        Picasso.get().load(number).into(imageView);
        textView.setText(str);

        System.out.println("QuestionActivity::onCreate()");
        // switch if extra intent .equals"China" then get_json_china...
        if (str != null) {
            mCollectionReference.document(str).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    ArrayList<String> aIArray = (ArrayList) task.getResult().get("answerIndex");
                    ArrayList<String> answer0Array = (ArrayList) task.getResult().get("answer0");
                    ArrayList<String> answer1Array = (ArrayList) task.getResult().get("answer1");
                    ArrayList<String> answer2Array = (ArrayList) task.getResult().get("answer2");
                    ArrayList<String> answer3Array = (ArrayList) task.getResult().get("answer3");
                    ArrayList<String> questionArray = (ArrayList) task.getResult().get("question");
                    for (int i = 0; i < answer0Array.size(); i++) {
                        int aIINT = Integer.valueOf(aIArray.get(i));
                        Question question = new Question(questionArray.get(i), Arrays.asList(answer0Array.get(i), answer1Array.get(i), answer2Array.get(i), answer3Array.get(i)), aIINT);
                        questionList.add(question);
                    }
                    mQuestionBank = new QuestionBank(questionList);

                    if (savedInstanceState != null) {
                        mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
                        mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
                    } else {
                        mScore = 0;
                        mNumberOfQuestions = 10;
                    }

                    mEnableTouchEvents = true;

                    // Wire widgets
                    mQuestionTextView = findViewById(R.id.questionTextView);
                    mAnswerButton1 = findViewById(R.id.activity_game_answer1_btn);
                    mAnswerButton2 = findViewById(R.id.activity_game_answer2_btn);
                    mAnswerButton3 = findViewById(R.id.activity_game_answer3_btn);
                    mAnswerButton4 = findViewById(R.id.activity_game_answer4_btn);

                    // Use the tag property to 'name' the buttons
                    mAnswerButton1.setTag(0);
                    mAnswerButton2.setTag(1);
                    mAnswerButton3.setTag(2);
                    mAnswerButton4.setTag(3);

                    mAnswerButton1.setOnClickListener(QuestionActivity.this);
                    mAnswerButton2.setOnClickListener(QuestionActivity.this);
                    mAnswerButton3.setOnClickListener(QuestionActivity.this);
                    mAnswerButton4.setOnClickListener(QuestionActivity.this);

                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            });
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);

        super.onSaveInstanceState(outState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onClick(View v) {
        final int responseIndex = (int) v.getTag();

        if (mCurrentQuestion.getAnswerIndex() == 0) {
            mAnswerButton1.setBackgroundResource(R.drawable.rounder_cornes_questions_green);
            mAnswerButton1.setTextColor(Color.WHITE);
        } else if (mCurrentQuestion.getAnswerIndex() == 1) {
            mAnswerButton2.setBackgroundResource(R.drawable.rounder_cornes_questions_green);
            mAnswerButton2.setTextColor(Color.WHITE);
        } else if (mCurrentQuestion.getAnswerIndex() == 2) {
            mAnswerButton3.setBackgroundResource(R.drawable.rounder_cornes_questions_green);
            mAnswerButton3.setTextColor(Color.WHITE);
        } else if (mCurrentQuestion.getAnswerIndex() == 3) {
            mAnswerButton4.setBackgroundResource(R.drawable.rounder_cornes_questions_green);
            mAnswerButton4.setTextColor(Color.WHITE);
        }

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good answer
            mScore++;
        } else {


            if (responseIndex == 0) {
                mAnswerButton1.setBackgroundResource(R.drawable.rounder_cornes_questions_red);
                mAnswerButton1.setTextColor(Color.WHITE);

            } else if (responseIndex == 1) {
                mAnswerButton2.setBackgroundResource(R.drawable.rounder_cornes_questions_red);
                mAnswerButton2.setTextColor(Color.WHITE);

            } else if (responseIndex == 2) {
                mAnswerButton3.setBackgroundResource(R.drawable.rounder_cornes_questions_red);
                mAnswerButton3.setTextColor(Color.WHITE);

            } else if (responseIndex == 3) {
                mAnswerButton4.setBackgroundResource(R.drawable.rounder_cornes_questions_red);
                mAnswerButton4.setTextColor(Color.WHITE);

            }

            // Wrong answer
        }

        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                mEnableTouchEvents = true;

                // If this is the last question, ends the game.
                // Else, display the next question.
                if (--mNumberOfQuestions == 0) {
                    // End the game
                    endGame();
                } else {
                    questionNumero++;
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    questionNumber.setText(questionNumero + "/10");
                    displayQuestion(mCurrentQuestion);
                    mAnswerButton4.setBackgroundResource(R.drawable.rounder_cornes_questions1);
                    mAnswerButton3.setBackgroundResource(R.drawable.rounder_cornes_questions1);
                    mAnswerButton2.setBackgroundResource(R.drawable.rounder_cornes_questions1);
                    mAnswerButton1.setBackgroundResource(R.drawable.rounder_cornes_questions1);
                    mAnswerButton4.setTextColor(Color.parseColor("#696969"));
                    mAnswerButton3.setTextColor(Color.parseColor("#696969"));
                    mAnswerButton2.setTextColor(Color.parseColor("#696969"));
                    mAnswerButton1.setTextColor(Color.parseColor("#696969"));


                }
            }
        }, 2000); // LENGTH_SHORT is usually 2 second long
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    private void endGame() {
        Intent intent = getIntent();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String str = intent.getStringExtra("countryName");
        String number = intent.getStringExtra("countryFlag");


        int games = prefs.getInt(str + "_games", 0);
        int score = prefs.getInt(str + "_score", 0);


        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(str + "_score", score + mScore);
        editor.putInt(str + "_games", games + 1);
        editor.apply();


        Intent myIntent = new Intent(QuestionActivity.this, ScoreActivity.class);
        myIntent.putExtra("score", mScore);
        myIntent.putExtra("country", str);
        myIntent.putExtra("image", number);
        QuestionActivity.this.startActivityForResult(myIntent, 0);
        finish();


    }


    private void displayQuestion(final Question question) {
        mQuestionTextView.setText(question.getQuestion());
        mAnswerButton1.setText(question.getChoiceList().get(0).trim());
        mAnswerButton2.setText(question.getChoiceList().get(1).trim());
        mAnswerButton3.setText(question.getChoiceList().get(2).trim());
        mAnswerButton4.setText(question.getChoiceList().get(3).trim());
    }


    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {

        super.onResume();

        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        System.out.println("GameActivity::onDestroy()");
    }
}