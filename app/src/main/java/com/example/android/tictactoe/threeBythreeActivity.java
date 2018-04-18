package com.example.android.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class threeBythreeActivity extends AppCompatActivity implements View.OnClickListener {
    TextView recieve;
    TextView recieve2;

    //this is instantiated for the button (a three by three array)
    private Button[][] buttons = new Button[3][3];
    //this makes the player one the player that will play immediately the game start
    private boolean player1Turn = true;
    //this counts our round of plays, so if we have 9 rounds and there is  no winner we know that there is a draw
    private int roundCount;
    // this helps to show the player scores
    private int player1Points;
    private int player2Points;
    // this helps to show the text view of players
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_bythree);
        recieve= (TextView)findViewById(R.id.player_1);
        recieve.setText(getIntent().getStringExtra("EdiTtEXTvALUE"));
        recieve2= (TextView)findViewById(R.id.player_2);
        recieve2.setText(getIntent().getStringExtra("EdiTtEXTvALUE2"));
        Bundle extras = getIntent().getExtras();

// find view by id for the players
        textViewPlayer1 = findViewById(R.id.score1);
        textViewPlayer2 = findViewById(R.id.score2);
// this helps to declare and loop through the button array which is a three by three array.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // this helps us to get the button id and the i and j loops through our button array.
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                //we pass the res id created into the buttons object instatiated in the beginning pf the main activity class
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
// here we pass a resource id into our button reset to make the reset button in our xml layout work.
        // set an on click listener to the button and cast the method reset game into it to reset all the games parameters
        ImageButton buttonReset = findViewById(R.id.reset);
       buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
         public void onClick(View v) {
                resetGame();
            }
       });
    }

    @Override
    //this helps to find strings x and 0 into the boxes.
    public void onClick(View v) {
        // this checked if the box that was clicked contains an empty string
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
//this gives player the value of x and if player 1 has clicked it gives player 2 the value of 0
        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }
// this check the number of rounds played already in the game
        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }
    //this methods checks if anybody has win
    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
// create a for loop to check through your boxes if the strings in them are identical to check fot a winner
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }
    public void displayScore1(int score){
        textViewPlayer1 .setText(String . valueOf(score));
    }

    public void displayScore2(int score){
        textViewPlayer2 .setText(String . valueOf(score));

    }

    private void player1Wins() {
        player1Points++;
        displayScore1(player1Points);
        Toast.makeText(this, "X wins!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        displayScore2(player2Points);
        Toast.makeText(this, " O wins!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }


    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }
    // this method reset all the games parameters to default or zero, the method is called when the reset button is being clicked
    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
       // updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}

