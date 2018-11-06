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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;



import com.example.benja.nationalityquiz.Utils.Question;
import com.example.benja.nationalityquiz.Utils.QuestionBank;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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


    private boolean mEnableTouchEvents;
    List<Question> questionList = new ArrayList<>();
    String name;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);


        Intent intent = getIntent();
        int number = intent.getIntExtra("countryFlag", 0);
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

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String language = prefs.getString("language", "English");

        imageView.setImageResource(number);
        textView.setText(str);

        System.out.println("QuestionActivity::onCreate()");
        // switch if extra intent .equals"China" then get_json_china...
        if (str.equals("France") && language.equals("Français")) {
            mQuestionBank = this.get_json_france();
        }if (str.equals("France") && language.equals("English")) {
            mQuestionBank = this.get_json_france_en();
        }  if (str.equals("England") && language.equals("Français")) {
            mQuestionBank = this.get_json_england_fr();
        }if (str.equals("England") && language.equals("English")) {
            mQuestionBank = this.get_json_england();
        }  if (str.equals("USA") && language.equals("Français")) {
            mQuestionBank = this.get_json_usa();
        }if (str.equals("USA") && language.equals("English")) {
            mQuestionBank = this.get_json_usa_en();
        }  if (str.equals("Spain") && language.equals("Français")) {
            mQuestionBank = this.get_json_france();
        }if (str.equals("Spain") && language.equals("English")) {
            mQuestionBank = this.get_json_france();
        }



        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mNumberOfQuestions = 10;
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
                    mCurrentQuestion = mQuestionBank.getQuestion();
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
        String str = intent.getStringExtra("countryName");
        if (str.equals("England")) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            int games = prefs.getInt("england_games", 0);
            int score = prefs.getInt("england_score", 0);


            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("england_score", score + mScore);
            editor.putInt("england_games", games + 1);
            editor.apply();
        }   if (str.equals("USA")) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            int games = prefs.getInt("usa_games", 0);
            int score = prefs.getInt("usa_score", 0);


            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("usa_score", score + mScore);
            editor.putInt("usa_games", games + 1);
            editor.apply();
        }if (str.equals("France")) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            int games = prefs.getInt("france_games", 0);
            int score = prefs.getInt("france_score", 0);


            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("france_score", score + mScore);
            editor.putInt("france_games", games + 1);
            editor.apply();
        }if (str.equals("Spain")) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            int games = prefs.getInt("spain_games", 0);
            int score = prefs.getInt("spain_score", 0);


            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("spain_score", score + mScore);
            editor.putInt("spain_games", games + 1);
            editor.apply();
        }if (str.equals("Germany")) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            int games = prefs.getInt("germany_games", 0);
            int score = prefs.getInt("germany_score", 0);

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("germany_score", score + mScore);
            editor.putInt("germany_games", games + 1);
            editor.apply();
        }if (str.equals("Japan")) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            int games = prefs.getInt("japan_games", 0);
            int score = prefs.getInt("japan_score", 0);

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("japan_score", score + mScore);
            editor.putInt("japan_games", games + 1);
            editor.apply();
        }if (str.equals("China")) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            int games = prefs.getInt("china_games", 0);
            int score = prefs.getInt("china_score", 0);

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("china_score", score + mScore);
            editor.putInt("china_games", games + 1);
            editor.apply();
        }if (str.equals("Australia")) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            int games = prefs.getInt("australia_games", 0);
            int score = prefs.getInt("australia_score", 0);

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("australia_score", score + mScore);
            editor.putInt("australia_games", games + 1);
            editor.apply();
        }

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

    public QuestionBank get_json_france() {
        String json;
        try {
            InputStream is = getAssets().open("france.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Question theQuestion = new Question(obj.getString("question"), Arrays.asList(obj.getString("answer0"), obj.getString("answer1"), obj.getString("answer2"), obj.getString("answer3")), obj.getInt("answerIndex"));
                questionList.add(theQuestion);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new QuestionBank(questionList);
    }
    public QuestionBank get_json_france_en() {
        String json;
        try {
            InputStream is = getAssets().open("france_en.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Question theQuestion = new Question(obj.getString("question"), Arrays.asList(obj.getString("answer0"), obj.getString("answer1"), obj.getString("answer2"), obj.getString("answer3")), obj.getInt("answerIndex"));
                questionList.add(theQuestion);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new QuestionBank(questionList);
    }
    public QuestionBank get_json_england() {
        String json;
        try {
            InputStream is = getAssets().open("england.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Question theQuestion = new Question(obj.getString("question"), Arrays.asList(obj.getString("answer0"), obj.getString("answer1"), obj.getString("answer2"), obj.getString("answer3")), obj.getInt("answerIndex"));
                questionList.add(theQuestion);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new QuestionBank(questionList);
    }
    public QuestionBank get_json_england_fr() {
        String json;
        try {
            InputStream is = getAssets().open("england_fr.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Question theQuestion = new Question(obj.getString("question"), Arrays.asList(obj.getString("answer0"), obj.getString("answer1"), obj.getString("answer2"), obj.getString("answer3")), obj.getInt("answerIndex"));
                questionList.add(theQuestion);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new QuestionBank(questionList);
    }
    public QuestionBank get_json_usa() {
        String json;
        try {
            InputStream is = getAssets().open("usa.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Question theQuestion = new Question(obj.getString("question"), Arrays.asList(obj.getString("answer0"), obj.getString("answer1"), obj.getString("answer2"), obj.getString("answer3")), obj.getInt("answerIndex"));
                questionList.add(theQuestion);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new QuestionBank(questionList);
    }
    public QuestionBank get_json_usa_en() {
        String json;
        try {
            InputStream is = getAssets().open("usa_en.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Question theQuestion = new Question(obj.getString("question"), Arrays.asList(obj.getString("answer0"), obj.getString("answer1"), obj.getString("answer2"), obj.getString("answer3")), obj.getInt("answerIndex"));
                questionList.add(theQuestion);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new QuestionBank(questionList);
    }
    public QuestionBank get_json_spain() {
        String json;
        try {
            InputStream is = getAssets().open("france.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Question theQuestion = new Question(obj.getString("question"), Arrays.asList(obj.getString("answer0"), obj.getString("answer1"), obj.getString("answer2"), obj.getString("answer3")), obj.getInt("answerIndex"));
                questionList.add(theQuestion);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new QuestionBank(questionList);
    }
}