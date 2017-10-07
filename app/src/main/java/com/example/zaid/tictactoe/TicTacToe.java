package com.example.zaid.tictactoe;

public class TicTacToe
{
    public static final int SIDE = 3;
    private int turn; //the player whos turn it is
    private int [][] game;

    public TicTacToe( )
    {
        game = new int[SIDE][SIDE];  //creates the tic tac toe dimensions
        resetGame( );
    }

    public int play( int row, int col ) //This is the turn of the player
    {
        int currentTurn = turn;
        if( row >= 0 && col >= 0 && row < SIDE && col < SIDE
                && game[row][col] == 0 ) {
            game[row][col] = turn;    //checks if it is in the playable area and spot is empty
            if( turn == 1 ) //change turn to the other player
                turn = 2;
            else
                turn = 1;
            return currentTurn;
        }
        else
            return 0;
    }

    public int whoWon( ) //Checks who won
    {
        int rows = checkRows( ); //calls a function to check the rows
        if ( rows > 0 )
            return rows;
        int columns = checkColumns( );
        if( columns > 0 )
            return columns;
        int diagonals = checkDiagonals( );
        if( diagonals > 0 )
            return diagonals;
        return 0;
    }

    protected int checkRows( ) //checks if someone has 3 consecutive in a row
    {
        for( int row = 0; row < SIDE; row++ )
            if ( game[row][0] != 0 && game[row][0] == game[row][1]
                    && game[row][1] == game[row][2] )
                return game[row][0];
        return 0;
    }

    protected int checkColumns( ) //check if someone has 3 consecutive in a column
    {
        for( int col = 0; col < SIDE; col++ )
            if ( game[0][col] != 0 && game[0][col] == game[1][col]
                    && game[1][col] == game[2][col] )
                return game[0][col];
        return 0;
    }

    protected int checkDiagonals( ) // check is someone has 3 consecutive in a diaganol
    {
        if ( game[0][0] != 0 && game[0][0] == game[1][1]
                && game[1][1] == game[2][2] )
            return game[0][0];
        if ( game[0][2] != 0 && game[0][2] == game[1][1]
                && game[1][1] == game[2][0] )
            return game[2][0];
        return 0;
    }

    public boolean canNotPlay( ) //Tests if there are any playable spots left (draw)
    {
        boolean result = true;
        for (int row = 0; row < SIDE; row++)
            for( int col = 0; col < SIDE; col++ )
                if ( game[row][col] == 0 )
                    result = false;
        return result;
    }

    public boolean isGameOver( ) //Test if game is still playable by checking for empty spots or a winner
    {
        return canNotPlay( ) || ( whoWon( ) > 0 );
    }

    public void resetGame( ) //empties the entire board to play again
    {
        for (int row = 0; row < SIDE; row++)
            for( int col = 0; col < SIDE; col++ )
                game[row][col] = 0;
        turn = 1;
    }

    public String result( ) //Results screen on the bottom values
    {
        if( whoWon( ) > 0 )
            return "Player " + whoWon( ) + " won";
        else if( canNotPlay( ) )
            return "Tie Game";
        else
            return "PLAY !!";
    }
}
