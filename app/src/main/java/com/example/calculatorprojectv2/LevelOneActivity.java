package com.example.calculatorprojectv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LevelOneActivity extends AppCompatActivity{
    TextView display, goalDisplay, constraintDisplay, levelDisplay; //add a TextView for the number that the use has to reach
    Button bOne, bTwo, bThree, bFour, bFive, bSix, bSeven, bEight, bNine, bAdd, bSub, bMulti, bDiv, cButton;

    private int clickCounter = 0;
    private String displayLabel = "";
    private int level = 1;
    ConstraintLayout middleGame;

    private double goalOne = 64.0;
    private double goalTwo = 55.0;
    private double goalThree = 169.0;
    private double goalFour = 267.0;
    private double goalFive = 12.0;

    ArrayList viableNumbers = new ArrayList();
    private boolean goalOneA = false;
    private boolean goalTwoA = false;
    private boolean goalThreeA = false;
    private boolean goalFourA = false;
    private boolean goalFiveA = false;

    private int goalOneClick = 3;
    private int goalTwoClick = 4;
    private int goalThreeClick = 5;
    private int goalFourClick = 6;
    private int difficulty = 0;
    private int goalFiveClick = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);

        bOne = (Button) findViewById(R.id.buttonOne);
        bTwo = (Button) findViewById(R.id.buttonTwo);
        bThree = (Button) findViewById(R.id.buttonThree);
        bFour = (Button) findViewById(R.id.buttonFour);
        bFive = (Button) findViewById(R.id.buttonFive);
        bSix = (Button) findViewById(R.id.buttonSix);
        bSeven = (Button) findViewById(R.id.buttonSeven);
        bEight = (Button) findViewById(R.id.buttonEight);
        bNine = (Button) findViewById(R.id.buttonNine);
        bAdd = (Button) findViewById(R.id.additionButton);
        bSub = (Button) findViewById(R.id.subtractionButton);
        bMulti = (Button) findViewById(R.id.multiplicationButton);
        bDiv = (Button) findViewById(R.id.divisionButton);
        cButton = (Button) findViewById(R.id.calculateButton);
        //^^ The numerical calculator buttons

//        bOne.setOnClickListener(this);
//        bTwo.setOnClickListener(this);
//        bThree.setOnClickListener(this);
//        bFour.setOnClickListener(this);
//        bFive.setOnClickListener(this);
//        bSix.setOnClickListener(this);
//        bSeven.setOnClickListener(this);
//        bEight.setOnClickListener(this);
//        bNine.setOnClickListener(this);
//        bAdd.setOnClickListener(this);
//        bSub.setOnClickListener(this);
//        bMulti.setOnClickListener(this);
//        bDiv.setOnClickListener(this);
//        cButton.setOnClickListener(this);
//        //^^ For the Click Listener for the Button

        display = (TextView) findViewById(R.id.display);
        goalDisplay = (TextView) findViewById((R.id.goalDisplay));
        constraintDisplay = (TextView) findViewById(R.id.constraintDisplay);
        levelDisplay = (TextView) findViewById(R.id.levelLabel);
        //^^ Sets the Displays

        goalOneRun();
    }


    private void goalOneRun(){
        clickCounter = 0;
        goalDisplay.setText("Goal: " + goalOne);
        constraintDisplay.setText("Three Buttons");
    }

    private void goalTwoRun(){
        clickCounter = 0;
        displayLabel = "";
        goalDisplay.setText("Goal: " + goalTwo);
        constraintDisplay.setText("Four Buttons");
        level++;
        levelDisplay.setText("Level: " + level);
    }

    private void goalThreeRun(){
        clickCounter = 0;
        displayLabel = "";
        goalDisplay.setText("Goal: " + goalThree);
        constraintDisplay.setText("5 Buttons");
        level++;
        levelDisplay.setText("Level: " + level);
    }

    private void goalFourRun(){
        clickCounter = 0;
        displayLabel = "";
        goalDisplay.setText("Goal: " + goalFour);
        constraintDisplay.setText("Use Addition with 5 Button Presses");
        level++;
        levelDisplay.setText("Level: " + level);
    }

    private void goalFiveRun(){
        clickCounter = 0;
        displayLabel = "";
        goalDisplay.setText("Goal: " + goalFive);
        constraintDisplay.setText("3 Buttons");
        level++;
        levelDisplay.setText("Level: " + level);
    }

    private void generateNumbers()
    {
        // Generate Numbers Here
    }

    private void finishScreen(){
        display.setText("You Beat the Game!");
        constraintDisplay.setVisibility(View.GONE);
        goalDisplay.setVisibility(View.GONE);
        levelDisplay.setVisibility(View.GONE);


        // Adjusts visibility of buttons based on the game
        bOne.setVisibility(View.GONE);
        bTwo.setVisibility(View.GONE);
        bThree.setVisibility(View.GONE);
        bFour.setVisibility(View.GONE);
        bFive.setVisibility(View.GONE);
        bSix.setVisibility(View.GONE);
        bSeven.setVisibility(View.GONE);
        bEight.setVisibility(View.GONE);
        bNine.setVisibility(View.GONE);
        bAdd.setVisibility(View.GONE);
        bSub.setVisibility(View.GONE);
        bMulti.setVisibility(View.GONE);
        bDiv.setVisibility(View.GONE);
        cButton.setVisibility(View.GONE);

    }

    // Erases display if user uses too many button
    private void tooManyActions()
    {
        Context context = getApplicationContext();
        CharSequence keystrokeOver = "Too many Button Presses!";
        CharSequence sillyGoose = "I said Addition you silly goose :)";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, keystrokeOver, duration);
        Toast fgd = Toast.makeText(context, sillyGoose, duration);

        Context contextTwo = getApplicationContext();
        CharSequence textOver = "Goal Overshot!";
        CharSequence textUnder = "Goal Undershot!";
        int durationTwo = Toast.LENGTH_SHORT;

        Toast underShot = Toast.makeText(contextTwo, textUnder, durationTwo);
        Toast overShot = Toast.makeText(contextTwo, textOver, durationTwo);
        toast.show();
        displayLabel = "";
        display.setText(displayLabel);
        clickCounter = 0;
    }

    // Adjusts UI components
    public void adjustDisplay(String k)
    {
        clickCounter++;
        displayLabel = displayLabel.concat(k);
        display.setText(displayLabel);
    }

    // His whole functionality for the project is centralized around the five numbers
    // The five numbers and their corresponding booleans are goalOneA and goalOne, goalTwo and goalTwoA, etc.
    public void compare()
    {
        if (!goalOneA){
            if (clickCounter > goalOneClick){
                tooManyActions();
            }
        } else if (!goalTwoA){
            if (clickCounter > goalTwoClick){
                tooManyActions();
            }
        } else if (!goalThreeA){
            if (clickCounter > goalThreeClick){
                tooManyActions();
            }
        } else if (!goalFourA){
            if (clickCounter > goalFourClick){
                tooManyActions();
            }
        } else if (!goalFiveA){
            if (clickCounter > goalFiveClick){
                tooManyActions();
            }
        }
    }

//    @Override
//    public void onClick(View view) {
//
//
//        switch (view.getId()){
//            case R.id.buttonOne:
//                adjustDisplay("1");
//                compare();
//                break;
//            case R.id.buttonTwo:
//                adjustDisplay("2");
//                compare();
//                break;
//            case R.id.buttonThree:
//                adjustDisplay("3");
//                compare();
//                break;
//            case R.id.buttonFour:
//                adjustDisplay("4");
//                compare();
//                break;
//            case R.id.buttonFive:
//                adjustDisplay("5");
//                compare();
//                break;
//            case R.id.buttonSix:
//                adjustDisplay("6");
//                compare();
//                break;
//            case R.id.buttonSeven:
//                adjustDisplay("7");
//                compare();
//                break;
//            case R.id.buttonEight:
//                adjustDisplay("8");
//                compare();
//                break;
//            case R.id.buttonNine:
//                adjustDisplay("9");
//                compare();
//                break;
//            case R.id.additionButton:
//                adjustDisplay("+");
//                compare();
//                break;
//            case R.id.subtractionButton:
//                adjustDisplay("-");
//                compare();
//                break;
//            case R.id.multiplicationButton:
//                adjustDisplay("*");
//                compare();
//                break;
//            case R.id.divisionButton:
//                adjustDisplay("Ã·");
//                compare();
//                break;
//            case R.id.calculateButton:
//                String expEval = display.getText().toString();
//
//
//                Expression exp = new Expression(expEval);
//
//                String resultS = String.valueOf(exp.calculate());
//                double result = Double.parseDouble(resultS);
//
//                // For now I have kept Ian's method of evaluating the correctness, but we can always change
//                // it if necessary
//        }
//    }
}


