package com.emefilefrancis.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 for red and 1 for yellow
    int activePlayer = 0;

    //Store the state of the game. Position with 2 means the square corresponding to that position is empty
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    boolean activeGame = true;

    //Win Positions{{3 Horizontals}, {3 Verticals}, {2 Diagonals}}
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedSquare = Integer.parseInt(counter.getTag().toString());

        //Check if square is empty and game is still active
        if(gameState[tappedSquare] == 2 && activeGame){
            counter.setTranslationY(-1000f);
            //check if player is red
            if(activePlayer == 0){
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1000f).rotation(360f).setDuration(800);
                //set the next active player
                activePlayer = 1;
                //set the square to the player's color
                gameState[tappedSquare] = 0;
            }else{ //player is yellow
                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1000f).rotation(360f).setDuration(800);
                //set the next active player
                activePlayer = 0;
                //set the square to the player's color
                gameState[tappedSquare] = 1;
            }
        }

        for(int[]winningPosition : winningPositions){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                    gameState[winningPosition[0]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]]!= 2){

                //Someone has won

                String winner = "Yellow";
                if(gameState[winningPosition[0]] == 0){
                    winner = "Red";
                }
                TextView winMessage = findViewById(R.id.youWin);
                winMessage.setText(winner + " has won.");

                LinearLayout gameMessage = findViewById(R.id.gameMessage);
                gameMessage.setVisibility(View.VISIBLE);
                gameMessage.animate().rotation(360f).setDuration(500);

                //Deactivate game
                activeGame = false;
            }
        }

    }

    public void playAgain(View view){
        //Hide gameMessage Linear Layout
        LinearLayout gameMessage = findViewById(R.id.gameMessage);
        gameMessage.animate().alpha(0f).rotation(-360f).setDuration(500);
        gameMessage.setVisibility(View.INVISIBLE);

        //Reset activePlayer
        activePlayer = 0;

        //Reset gameState
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        //Reset activeGame
        activeGame = true;

        //Reset every square to empty
        GridLayout gridLayout = findViewById(R.id.gridLayoutID);
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
