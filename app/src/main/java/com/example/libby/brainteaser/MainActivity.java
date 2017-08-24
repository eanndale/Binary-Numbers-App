package com.example.libby.brainteaser;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> answers = new ArrayList<String>();
    Button startButton;
    int locationOfCorrectAnswer;
    int score = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView resultTextView;
    TextView pointsTextView;
    TextView timerTextView;
    int numberOfQuestions = 0;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;


    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        generateQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your score is " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions) + ".");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();
    }
    public void generateQuestion() {

        Random rand = new Random();
        int a = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for(int i = 0; i < 4; ++i) {
            if (i == locationOfCorrectAnswer) {
                answers.add(Integer.toBinaryString(a));
            } else {
                incorrectAnswer = rand.nextInt(40);
                while(incorrectAnswer == a) {
                    incorrectAnswer = rand.nextInt(40);
                }
                answers.add(Integer.toBinaryString(incorrectAnswer));

            }
        }

        button0.setText(answers.get(0));
        button1.setText(answers.get(1));
        button2.setText(answers.get(2));
        button3.setText(answers.get(3));

    }
    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            resultTextView.setText("Correct!");

        } else {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        generateQuestion();
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
    }
    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility((View.VISIBLE));
        playAgain(findViewById(R.id.playAgainButton));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Binary Numbers Test");
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        startButton = (Button) findViewById(R.id.goButton);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);





    }
}
