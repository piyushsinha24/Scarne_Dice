package com.example.piyush.scarnedice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int user_turn_score;
    private int computer_turn_score;
    private int user_overall_score;
    private int computer_overall_score;
    private int dice_value;
    Random random=new Random();
    TextView useroverallscore;
    TextView turnscore;
    TextView compoverallscore;
    Button roll;
    Button hold;
    Button reset;
    ImageView dice_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_turn_score=0;
        user_overall_score=0;
        computer_overall_score=0;
        dice_value=0;
        computer_turn_score=0;
        useroverallscore=(TextView)findViewById(R.id.userscore_textview);
        turnscore=(TextView)findViewById(R.id.turn_textview);
        compoverallscore=(TextView)findViewById(R.id.compscore_textview);
        roll=(Button)findViewById(R.id.rollbtn);
        hold=(Button)findViewById(R.id.holdbtn);
        reset=(Button)findViewById(R.id.resetbtn);
        dice_view = (ImageView) findViewById(R.id.dice_img);


    }
    public int RandomDiceNumber() {
        final int diceArr[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4,
                R.drawable.dice5, R.drawable.dice6};
        int random = (int)(Math.random() * 6 + 1);
        dice_view.setImageResource(diceArr[random-1]);
        return random;
    }
    public void rollbtn(View view) {

        dice_value= RandomDiceNumber();
        if(dice_value==1) {
            Toast.makeText(getApplicationContext(), "User rolled 1", Toast.LENGTH_SHORT).show();
            user_turn_score = 0;
            setUser_score(0);
            user_overall_score = 0;
            useroverallscore.setText("Your Score : " + " " + String.valueOf(user_overall_score));
            Toast.makeText(getApplicationContext(), "Computer's Turn", Toast.LENGTH_SHORT).show();
            computerTurn();
        }
        else{
            user_turn_score=user_turn_score+dice_value;
            setUser_score(user_turn_score);

        }

    }
    public void setUser_score(int user_turn_score){
        turnscore.setText("Turn Score : "+" "+String.valueOf(user_turn_score));


    }


    public void holdbtn(View view) {
        user_overall_score += user_turn_score;
        user_turn_score=0;
        useroverallscore.setText("Your Score : "+" "+String.valueOf(user_overall_score));
        setUser_score(user_turn_score);
        if(user_overall_score>=50)
        {
            Toast.makeText(getApplicationContext(), "User wins!", Toast.LENGTH_SHORT).show();
            reset();

        }
        else {
            Toast.makeText(getApplicationContext(), "Computer's Turn", Toast.LENGTH_SHORT).show();
            computerTurn();
        }


    }
    public void computerTurn()
    {
        roll.setEnabled(false);
        hold.setEnabled(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run() {
            dice_value =  RandomDiceNumber();
            if (dice_value == 1) {
                Toast.makeText(getApplicationContext(), "CPU got 1!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "User's Turn", Toast.LENGTH_SHORT).show();
                roll.setEnabled(true);
                hold.setEnabled(true);
                computer_turn_score = 0;
                setcompcore(computer_turn_score);
                computer_overall_score+=computer_turn_score;
                compoverallscore.setText("Computer's Score : " + " " + String.valueOf(computer_overall_score));
                return;
            }
            else
            {
                computer_turn_score += dice_value;
                if(computer_turn_score>20)
                {
                    Toast.makeText(getApplicationContext(), "CPU hold!", Toast.LENGTH_SHORT).show();
                    computer_overall_score+=computer_turn_score;
                    computer_turn_score=0;
                    setcompcore(computer_turn_score);
                    compoverallscore.setText("Computer's Score : " + " " + String.valueOf(computer_overall_score));
                    roll.setEnabled(true);
                    hold.setEnabled(true);
                    Toast.makeText(getApplicationContext(), "User's Turn", Toast.LENGTH_SHORT).show();
                    if (computer_overall_score>50)
                    {
                        Toast.makeText(getApplicationContext(), "Computer has won!", Toast.LENGTH_SHORT).show();
                        reset();
                    }
                    return;
                }
                else
                {
                    setcompcore(computer_turn_score);
                    computerTurn();
                }

            }


    }
        },1000);

    }
    public void setcompcore(int computer_turn_score){
        turnscore.setText("Turn Score : "+" "+String.valueOf(computer_turn_score));


    }


    public void resetbtn(View view) {
        reset();
    }
    public void reset()
    {
        user_turn_score=0;
        user_overall_score=0;
        computer_overall_score=0;
        computer_turn_score=0;
        turnscore.setText("Turn Score : "+" "+String.valueOf(computer_turn_score));
        useroverallscore.setText("Your Score : "+" "+String.valueOf(user_overall_score));
        compoverallscore.setText("Computer Score : "+" "+String.valueOf(computer_overall_score));
    }
}
