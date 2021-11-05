package com.example.calculatorprojectv2;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button elemButton, middleButton, highButton, startButton;
    ConstraintLayout middleGame;
    ConstraintLayout introScreen;
    ConstraintLayout endGameScreen;

    TextView display, goalDisplay, constraintDisplay, levelDisplay, coinsLabel, timerDisplay, endingHighScoreLabel, endingScoreLabel, hintDisplay; //add a TextView for the number that the use has to reach
    Button bOne, bTwo, bThree, bFour, bFive, bSix, bSeven, bEight, bNine, bZero, bAdd, bSub, bMulti, bDiv, cButton, sqrtButton, expButton, clearButton, playAgainButton, hintButton1, hintButton2, hintButton3;

    private int numOpsClicked = 0;
    private String displayLabel = "";
    private int level = 1;
    private int highScore = 0;
    private int coins = 0;

    private String[] positions = {"0","1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};




    private int difficulty = 0;
    private ArrayList<String> solution = new ArrayList<>();
    private ArrayList<String> hint = new ArrayList<>();
    private int currentNumber;
    private Timer mainTimer;
    private int seconds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bOne = (Button) findViewById(R.id.buttonOne);
        bTwo = (Button) findViewById(R.id.buttonTwo);
        bThree = (Button) findViewById(R.id.buttonThree);
        bFour = (Button) findViewById(R.id.buttonFour);
        bFive = (Button) findViewById(R.id.buttonFive);
        bSix = (Button) findViewById(R.id.buttonSix);
        bSeven = (Button) findViewById(R.id.buttonSeven);
        bEight = (Button) findViewById(R.id.buttonEight);
        bNine = (Button) findViewById(R.id.buttonNine);
        bZero = (Button) findViewById(R.id.buttonZero);
        bAdd = (Button) findViewById(R.id.additionButton);
        bSub = (Button) findViewById(R.id.subtractionButton);
        bMulti = (Button) findViewById(R.id.multiplicationButton);
        bDiv = (Button) findViewById(R.id.divisionButton);
        sqrtButton = (Button) findViewById(R.id.sqrtButton);
        expButton = (Button) findViewById(R.id.buttonExp);
        cButton = (Button) findViewById(R.id.calculateButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        hintButton1 = (Button) findViewById(R.id.hintButton1);
        hintButton2 = (Button) findViewById(R.id.hintButton2);
        hintButton3 = (Button) findViewById(R.id.hintButton3);
        //^^ The numerical calculator buttons

        bOne.setOnClickListener(this);
        bTwo.setOnClickListener(this);
        bThree.setOnClickListener(this);
        bFour.setOnClickListener(this);
        bFive.setOnClickListener(this);
        bSix.setOnClickListener(this);
        bSeven.setOnClickListener(this);
        bEight.setOnClickListener(this);
        bNine.setOnClickListener(this);
        bZero.setOnClickListener(this);
        bAdd.setOnClickListener(this);
        bSub.setOnClickListener(this);
        bMulti.setOnClickListener(this);
        bDiv.setOnClickListener(this);
        sqrtButton.setOnClickListener(this);
        expButton.setOnClickListener(this);
        cButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        //^^ For the Click Listener for the Button

        elemButton = (Button) findViewById(R.id.elemButton);
        elemButton.setVisibility(VISIBLE);
        middleGame = (ConstraintLayout) findViewById(R.id.middleGame);
        middleGame.setVisibility(INVISIBLE);
        middleButton = (Button) findViewById(R.id.middleButton);
        highButton = (Button) findViewById(R.id.highButton);
        introScreen = (ConstraintLayout) findViewById(R.id.introScreen);
        startButton = (Button) findViewById(R.id.startButton);
        sqrtButton = (Button) findViewById(R.id.sqrtButton);
        expButton = (Button) findViewById(R.id.buttonExp);
        endGameScreen = (ConstraintLayout) findViewById(R.id.endGame);
        endGameScreen.setVisibility(INVISIBLE);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        display = (TextView) findViewById(R.id.display);
        goalDisplay = (TextView) findViewById((R.id.goalDisplay));
        constraintDisplay = (TextView) findViewById(R.id.constraintDisplay);
        levelDisplay = (TextView) findViewById(R.id.levelLabel);
        coinsLabel = (TextView) findViewById(R.id.coinsLabel);
        timerDisplay = (TextView) findViewById(R.id.timerDisplay);
        hintDisplay = (TextView) findViewById(R.id.hintDisplay);
        endingScoreLabel = (TextView) findViewById(R.id.textView4);
        endingHighScoreLabel = (TextView) findViewById(R.id.textView6);




        final int[] stage = {0};

        elemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = 1;
            }
        });

        middleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = 2;
            }
        });

        highButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficulty = 3;
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLevelOne();
            }
        });

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 1;
                levelDisplay.setText("Level: " + level);
                introScreen.setVisibility(VISIBLE);
                endGameScreen.setVisibility(INVISIBLE);
            }
        });

        hintButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHint(1);
            }
        });
        hintButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHint(2);
            }
        });
        hintButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHint(3);
            }
        });




    }

    protected AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
    protected AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);

    private void fadeAnimation(TextView input){
        input.startAnimation(fadeIn);
        input.startAnimation(fadeOut);
        fadeIn.setDuration(1000);
        fadeOut.setDuration(1000);
        fadeIn.setFillAfter(true);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(4200 + fadeIn.getStartOffset());
    }
    //^^ Fade animation (play around with later)

    public void openLevelOne(){
        middleGame.setVisibility(VISIBLE);
        introScreen.setVisibility(INVISIBLE);
        endGameScreen.setVisibility(INVISIBLE);
        int numOps = (int)((difficulty*.6)+(level*.4));
        generateNumbers(numOps);
    }


    public void startTimer(){
        mainTimer = new Timer();
        seconds = (int) (getNumOperations()*((3-difficulty)*2.5 + 7.5)*((level*.01)+.9));
        mainTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (seconds > 0){
                            timerDisplay.setText("Time: " + seconds);
                        } else {
                            timerDisplay.setText("Time's up");
                            mainTimer.cancel();
                            endGame();
                        }
                        seconds--;

                    }
                });

            }
        },0,1000);
    }

    public void endGame(){
        if (level > highScore){
            highScore = level;
        }
        endGameScreen.setVisibility(VISIBLE);
        middleGame.setVisibility(INVISIBLE);
        displayLabel = "";
        display.setText(displayLabel);
        endingScoreLabel.setText("Your score was: " + level);
        endingHighScoreLabel.setText("High Score: " + highScore);



    }

    public String getPosition(int num){
        num = (num+2)/2;
        return positions[num];
    }

    public void generateNumbers(int numOps)
    {
        solution.clear();
        hint.clear();
        hintDisplay.setText("");
        int clicks = 0;
        int number = randInt(1,9);
        solution.add(String.valueOf(number));
        hint.add(String.valueOf(number));
        while (clicks < numOps){
            int op = randOp();
            int number2 = randNum(op);
            switch (op){
                case 1:
                    solution.add("+");
                    hint.add("+");
                    number = number + number2;
                    hint.add(String.valueOf(number2));
                    solution.add(String.valueOf(number));
                    break;
                case 2:
                    solution.add("-");
                    hint.add("-");
                    hint.add(String.valueOf(number2));
                    number = number - number2;
                    solution.add(String.valueOf(number));
                    break;
                case 3:
                    solution.add("*");
                    hint.add("*");
                    hint.add(String.valueOf(number2));
                    number = number*number2;
                    solution.add(String.valueOf(number));
                    break;
                case 4:
                    solution.add("/");
                    hint.add("/");
                    hint.add(String.valueOf(number2));
                    number = number/number2;
                    solution.add(String.valueOf(number));
                    break;
                case 5:
                    number2 = randInt(1, 3);
                    hint.add("^");
                    hint.add(String.valueOf(number2));
                    solution.add("^");
                    number = number^number2;
                    solution.add(String.valueOf(number));
                    break;
                case 6:
                    solution.add("Sqrt");
                    hint.add("√");
                    hint.add(String.valueOf(number));
                    number = (int)(Math.sqrt(number));
                    solution.add(String.valueOf(number));
                    break;

            }
            clicks++;
        }
        currentNumber = number;
        coinsLabel.setText("Coins: " + coins);
        constraintDisplay.setText("# of Ops: " + getNumOperations());
        goalDisplay.setText("Goal: " + currentNumber);
        startTimer();

        // Generate Numbers Here

    }

    public int randInt(int low, int high){
        return (int) (Math.random()*(high-low + 1) + low);
    }

    public int randNum(int op){
        int num = randInt(1, 9);
        if (op == 5) {
            num = randInt(2, 3);
        }
        return num;
    }

    public int randOp(){
        int op = 0;
        if (difficulty == 1){
            if (Math.random() > .65){
                if (Math.random() > .50){
                    op = 1;
                } else {
                    op = 2;
                }
            } else {
                if (Math.random() > .35){
                    op = 3;
                } else {
                    op = 4;
                }
            }
        } else if (difficulty == 2) {
            double random = Math.random();
            if (random < .3){
                if (Math.random() > .50){
                    op = 1;
                } else {
                    op = 2;
                }
            } else if (random < .65) {
                if (Math.random() > .35){
                    op = 3;
                } else {
                    op = 4;
                }
            }else {
                if (Math.sqrt(Integer.parseInt(solution.get(solution.size()-1)))%1!=0){
                    op = 5;
                } else {
                    op = 6;
                }
            }
        } else if (difficulty == 3) {
            double random = Math.random();
            if (random < .25){
                if (Math.random() > .50){
                    op = 1;
                } else {
                    op = 2;
                }
            } else if (random < .60) {
                if (Math.random() > .35){
                    op = 3;
                } else {
                    op = 4;
                }
            }else {
                if (Math.sqrt(Integer.parseInt(solution.get(solution.size()-1)))%1!=0){
                    op = 5;
                } else {
                    op = 6;
                }
            }
        }
        return op;
    }

    public int getNumOperations(){
        int num = 0;
        for (int i = 0; i < solution.size(); i++){
            if (i%2== 1){
                num++;
            }
        }
        return num;
    }

    public void showHint(int hintLevel){
        if (hintLevel == 1 && coins>=20){
            String hintText = "";
            coins = coins - 20;
            int randNum = randInt(0, hint.size()-1);
            if (randNum%2 == 1){
                hintText = "The " + getPosition(randNum) + " Operation is " + hint.get(randNum);
            } else {
                hintText = "The " + getPosition(randNum) + " Number is " + hint.get(randNum);
            }
            hintDisplay.setText(hintText);
            System.out.println(hint);
        } else if (hintLevel == 2&& coins>=30){
            String hintText = "";
            coins = coins - 30;
            int randNum1 = randInt(0, hint.size()-1);
            int randNum2 = randInt(0, hint.size()-1);
            if (randNum1%2 == 1){
                hintText = "The " + getPosition(randNum1) + " Operation is " + hint.get(randNum1);
            } else {
                hintText = "The " + getPosition(randNum1) + " Number is " + hint.get(randNum1);
            }
            if (randNum2%2 == 1){
                hintText = hintText + "\nThe " + getPosition(randNum2) + " Operation is " + hint.get(randNum2);
            } else {
                hintText = hintText + "\nThe " + getPosition(randNum2) + " Number is " + hint.get(randNum2);
            }
            hintDisplay.setText(hintText);

        } else if (hintLevel == 3&& coins>=40) {
            String hintText = "";
            coins = coins - 40;
            int randNum1 = randInt(0, hint.size()-1);
            int randNum2 = randInt(0, hint.size()-1);
            int randNum3 = randInt(0, hint.size()-1);
            if (randNum1%2 == 1){
                hintText = "The " + getPosition(randNum1) + " Operation is " + hint.get(randNum1);
            } else {
                hintText = "The " + getPosition(randNum1) + " Number is " + hint.get(randNum1);
            }
            if (randNum2%2 == 1){
                hintText = hintText + "\nThe " + getPosition(randNum2) + " Operation is " + hint.get(randNum2);
            } else {
                hintText = hintText + "\nThe " + getPosition(randNum2) + " Number is " + hint.get(randNum2);
            }
            if (randNum3%2 == 1){
                hintText = hintText + "\nThe " + getPosition(randNum3) + " Operation is " + hint.get(randNum3);
            } else {
                hintText = hintText + "\nThe " + getPosition(randNum3) + " Number is " + hint.get(randNum3);
            }
            hintDisplay.setText(hintText);
        }
        coinsLabel.setText("Coins: " + coins);

    }





    // Adjusts UI components
    public void adjustDisplay(String k)
    {
        if (numDigitsInARow > 1){
            displayLabel = "";
            numDigitsInARow = 0;
            numOpsClicked = 0;
        } else {
            displayLabel = displayLabel.concat(k);
        }
        display.setText(displayLabel);

    }



    int numDigitsInARow = 0;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.clearButton:
                numDigitsInARow = 0;
                displayLabel = "";
                display.setText(displayLabel);
                numOpsClicked = 0;
                break;
            case R.id.buttonOne:
                numDigitsInARow++;
                adjustDisplay("1");
                break;
            case R.id.buttonTwo:
                numDigitsInARow++;
                adjustDisplay("2");
                break;
            case R.id.buttonThree:
                numDigitsInARow++;
                adjustDisplay("3");
                break;
            case R.id.buttonFour:
                numDigitsInARow++;
                adjustDisplay("4");
                break;
            case R.id.buttonFive:
                numDigitsInARow++;
                adjustDisplay("5");
                break;
            case R.id.buttonSix:
                numDigitsInARow++;
                adjustDisplay("6");
                break;
            case R.id.buttonSeven:
                numDigitsInARow++;
                adjustDisplay("7");
                break;
            case R.id.buttonEight:
                numDigitsInARow++;
                adjustDisplay("8");
                break;
            case R.id.buttonNine:
                numDigitsInARow++;
                adjustDisplay("9");
                break;
            case R.id.buttonZero:
                numDigitsInARow++;
                adjustDisplay("0");
                break;
            case R.id.additionButton:
                numDigitsInARow = 0;
                numOpsClicked++;
                adjustDisplay("+");
                break;
            case R.id.subtractionButton:
                numOpsClicked++;
                numDigitsInARow = 0;
                adjustDisplay("-");
                break;
            case R.id.multiplicationButton:
                numOpsClicked++;
                numDigitsInARow = 0;
                adjustDisplay("*");
                break;
            case R.id.buttonExp:
                numOpsClicked++;
                numDigitsInARow = 0;
                adjustDisplay("^");
                break;
            case R.id.sqrtButton:
                numOpsClicked++;
                numDigitsInARow = 0;
                if (display.getText().length() > 0) {
                    int num = Integer.parseInt(display.getText().toString());
                    num = (int)Math.sqrt(num);
                    displayLabel = "" + (int)num;
                    display.setText(displayLabel);
                    checkAccuracy(num);
                }
                break;
            case R.id.divisionButton:
                numOpsClicked++;
                numDigitsInARow = 0;
                adjustDisplay("/");
                break;
            case R.id.calculateButton:
                numDigitsInARow = 0;
                String expEval = display.getText().toString();


                Expression exp = new Expression(expEval);

                String resultS = String.valueOf(exp.calculate());
                double result = Double.parseDouble(resultS);
                displayLabel = "" + (int)result;
                display.setText(displayLabel);
                checkAccuracy(result);

                // For now I have kept Ian's method of evaluating the correctness, but we can always change
                // it if necessary
        }
    }

    public void checkAccuracy(double result){
        if ((int)(result) == currentNumber && numOpsClicked == getNumOperations()){
            mainTimer.cancel();
            level++;
            levelDisplay.setText("Level: " + level);
            addCoins();
            displayLabel = "";
            display.setText(displayLabel);
            numOpsClicked = 0;
            int numOps = (int)((difficulty*.6)+(level*.4));
            generateNumbers(numOps);

        }
    }

    public void addCoins(){
        if (difficulty == 1){
            coins += 5;
        } else if (difficulty == 2){
            coins += 10;
        } else if (difficulty == 3){
            coins += 15;
        }
    }

}
