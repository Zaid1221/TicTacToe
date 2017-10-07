package com.example.zaid.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private TicTacToe game;
    private ButtonGridAndTextView tttView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        game = new TicTacToe();  //calls the constructor in the model class
        Point size = new Point(); //gets an x and y value for the screen size
        getWindowManager().getDefaultDisplay().getSize(size); //used to get the size of the screen
        int w = size.x / TicTacToe.SIDE; //x value of size of screen divided by 3
        ButtonHandler bh = new ButtonHandler();
        tttView = new ButtonGridAndTextView(this, w, TicTacToe.SIDE, bh); //calls the contructor of the class
        tttView.setStatusText(game.result()); //sets the status of the text when created
        setContentView(tttView);
    }

    public void showNewGameDialog()  //This setups the to show the pop at the end of the game
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("This is fun");
        alert.setMessage("Play again?");
        PlayDialog playAgain = new PlayDialog(); //sets up the pop up after game ends
        alert.setPositiveButton("YES", playAgain);
        alert.setNegativeButton("NO", playAgain);
        alert.show();
    }

    private class ButtonHandler implements View.OnClickListener //Handles the placing of markers when moves are made. Also where the all methods are being called
    {
        public void onClick(View v) {
            for (int row = 0; row < TicTacToe.SIDE; row++) {
                for (int column = 0; column < TicTacToe.SIDE; column++) {
                    if (tttView.isButton((Button) v, row, column)) {
                        int play = game.play(row, column);
                        if (play == 1) {
                            tttView.setButtonText(row, column, "X");
                        } else if (play == 2) {
                            tttView.setButtonText(row, column, "O");
                        }
                        if (game.isGameOver()) {
                            tttView.setStatusBackgroundColor(Color.RED);
                            tttView.enableButtons(false);
                            tttView.setStatusText(game.result());
                            showNewGameDialog();    // offer to play again
                        }
                    }
                }
            }
        }
    }

    private class PlayDialog implements DialogInterface.OnClickListener //Handles the end game popups
    {
        public void onClick(DialogInterface dialog, int id)
        {
            if (id == -1) /* YES button */
            {
                game.resetGame();
                tttView.enableButtons(true);
                tttView.resetButtons();
                tttView.setStatusBackgroundColor(Color.GREEN);
                tttView.setStatusText(game.result());
            }
            else if (id == -2) // NO button
            {
                MainActivity.this.finish();
            }
        }
    }
}
