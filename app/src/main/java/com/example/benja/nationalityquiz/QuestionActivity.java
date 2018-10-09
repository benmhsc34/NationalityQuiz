package com.example.benja.nationalityquiz;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

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


    private boolean mEnableTouchEvents;

    String name;


    public static final String countryName = "KEY_1";
    public static final String countryFlag = "KEY_2";
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        SharedPreferences mPreferences = getSharedPreferences("country_data", MODE_PRIVATE);
        String chosenCountry = mPreferences.getString(countryName, "");
        int correspondingFlag = mPreferences.getInt(countryFlag, 0);

        ImageView imageView = findViewById(R.id.imageViewOfFlag);
        TextView textView =findViewById(R.id.textViewOfCountry);

        imageView.setImageResource(correspondingFlag);
        textView.setText(chosenCountry);

        name = getIntent().getStringExtra("name");

        System.out.println("QuestionActivity::onCreate()");

        mQuestionBank = this.generateQuestions();

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mNumberOfQuestions = 4;
        }

        mEnableTouchEvents = true;

        // Wire widgets
        mQuestionTextView = (TextView) findViewById(R.id.questionTextView);
        mAnswerButton1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        mAnswerButton2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        mAnswerButton3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        mAnswerButton4 = (Button) findViewById(R.id.activity_game_answer4_btn);

        // Use the tag property to 'name' the buttons
        mAnswerButton1.setTag(0);
        mAnswerButton2.setTag(1);
        mAnswerButton3.setTag(2);
        mAnswerButton4.setTag(3);

        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
        mAnswerButton3.setOnClickListener(this);
        mAnswerButton4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        displayQuestion(mCurrentQuestion);
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

        if (mCurrentQuestion.getAnswerIndex() == 0){
            mAnswerButton1.setBackgroundResource(R.drawable.rounder_cornes_questions_green);
            mAnswerButton1.setTextColor(Color.WHITE);
        }else if (mCurrentQuestion.getAnswerIndex() == 1){
            mAnswerButton2.setBackgroundResource(R.drawable.rounder_cornes_questions_green);
            mAnswerButton2.setTextColor(Color.WHITE);
        }else if (mCurrentQuestion.getAnswerIndex() == 2){
            mAnswerButton3.setBackgroundResource(R.drawable.rounder_cornes_questions_green);
            mAnswerButton3.setTextColor(Color.WHITE);
        }else if (mCurrentQuestion.getAnswerIndex() == 3){
            mAnswerButton4.setBackgroundResource(R.drawable.rounder_cornes_questions_green);
            mAnswerButton4.setTextColor(Color.WHITE);
        }

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good answer
            mScore++;
        } else {


            if (responseIndex == 0){
                mAnswerButton1.setBackgroundResource(R.drawable.rounder_cornes_questions_red);
                mAnswerButton1.setTextColor(Color.WHITE);

            }else if (responseIndex == 1){
                mAnswerButton2.setBackgroundResource(R.drawable.rounder_cornes_questions_red);
                mAnswerButton2.setTextColor(Color.WHITE);

            }else if (responseIndex == 2){
                mAnswerButton3.setBackgroundResource(R.drawable.rounder_cornes_questions_red);
                mAnswerButton3.setTextColor(Color.WHITE);

            }else if (responseIndex == 3){
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
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                    mAnswerButton4.setBackgroundResource(R.drawable.rounder_cornes_questions1);
                    mAnswerButton3.setBackgroundResource(R.drawable.rounder_cornes_questions1);
                    mAnswerButton2.setBackgroundResource(R.drawable.rounder_cornes_questions1);
                    mAnswerButton1.setBackgroundResource(R.drawable.rounder_cornes_questions1);
                    mAnswerButton4.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mAnswerButton3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mAnswerButton2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mAnswerButton1.setTextColor(getResources().getColor(R.color.colorPrimary));


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

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setMessage("Your score is " + mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End the activity
                        Intent data = new Intent();
                        data.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        data.putExtra(BUNDLE_EXTRA_NAME, name);
                        setResult(RESULT_OK, data);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    private void displayQuestion(final Question question) {
        mQuestionTextView.setText(question.getQuestion());
        mAnswerButton1.setText(question.getChoiceList().get(0));
        mAnswerButton2.setText(question.getChoiceList().get(1));
        mAnswerButton3.setText(question.getChoiceList().get(2));
        mAnswerButton4.setText(question.getChoiceList().get(3));
    }

    private QuestionBank generateQuestions() {
        Question question1 = new Question("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Question question2 = new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question3 = new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who painted the Mona Lisa?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
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