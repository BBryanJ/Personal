package com.wordscramble;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameScreen extends Activity implements OnClickListener {

    private Button resetButton;
    private TextView letter1TextView;
    private TextView letter2TextView;
    private TextView letter3TextView;
    private TextView letter4TextView;
    private TextView letter5TextView;
    private Button letter1Button;
    private Button letter2Button;
    private Button letter3Button;
    private Button letter4Button;
    private Button letter5Button;

    private TextView[] textArray;
    private Button[] buttonArray;

    private String[] words = new String[] {"OUTER", "CHEAP", "JUICE", "ATLAS", "NURSE",
        "ALGAE", "STAMP", "ANGEL", "ELBOW", "FRAME"};
    private String wordToScramble, scrambledWord;
    private boolean filled = false;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        resetButton = (Button) findViewById(R.id.resetButton);
        letter1TextView = (TextView) findViewById(R.id.letter1TextView);
        letter2TextView = (TextView) findViewById(R.id.letter2TextView);
        letter3TextView = (TextView) findViewById(R.id.letter3TextView);
        letter4TextView = (TextView) findViewById(R.id.letter4TextView);
        letter5TextView = (TextView) findViewById(R.id.letter5TextView);
        letter1Button = (Button) findViewById(R.id.letter1Button);
        letter2Button = (Button) findViewById(R.id.letter2Button);
        letter3Button = (Button) findViewById(R.id.letter3Button);
        letter4Button = (Button) findViewById(R.id.letter4Button);
        letter5Button = (Button) findViewById(R.id.letter5Button);

        textArray = new TextView[] {letter1TextView, letter2TextView,
        letter3TextView, letter4TextView, letter5TextView};
        buttonArray = new Button[] {letter1Button, letter2Button, letter3Button,
        letter4Button, letter5Button};

        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        for (TextView tv : textArray) {
            tv.setText("_");
        }
        for (Button b : buttonArray) {
            b.setOnClickListener(this);
        }

        playGame();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(this, "Game reset", Toast.LENGTH_SHORT).show();
        reset();
    }

    public void playGame() {

        wordToScramble = words[r.nextInt(words.length)];
        char[] splitWord = wordToScramble.toCharArray();
        scrambledWord = splitWord.toString();

        for (int i = 0; i < 5; i++) {
            buttonArray[i].setText(splitWord[i]);
        }

        String answer;
        if (filled) {
            answer = "";
            for (Button b : buttonArray) {
                answer += b.getText();
            }
            if (answer.equals(wordToScramble)) {
                Toast.makeText(this, "You got the word right!", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Better luck next time", Toast.LENGTH_SHORT).show();
        }
    }

    public void reset() {
        for (TextView tv : textArray) {
            tv.setText("_");
        }
        for (Button b : buttonArray) {
            b.setText("");
            b.setClickable(true);
        }
        wordToScramble = "";
        playGame();
    }

    @Override
    public void onClick(View v) {
        Button pressed = (Button) v;
        if (letter1TextView.getText() == "_") {
            letter1TextView.setText(pressed.getText());
        }
        else if (letter2TextView.getText() == "_") {
            letter2TextView.setText(pressed.getText());
        }
        else if (letter3TextView.getText() == "_") {
            letter3TextView.setText(pressed.getText());
        }
        else if (letter4TextView.getText() == "_") {
            letter4TextView.setText(pressed.getText());
        }
        else if (letter5TextView.getText() == "_") {
            letter5TextView.setText(pressed.getText());
            filled = true;
        }
        pressed.setClickable(false);
    }
}
