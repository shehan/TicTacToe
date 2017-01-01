package lk.peruma.tictactoe;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.stream.IntStream;

public class GameActivity extends AppCompatActivity {

    private State currentPlayer;
    private int colorCurrentPlayer,colorWinningBackground,colorDefaultBackground;
    private boolean gameCompleted, vsComputer;
    private int availableSquares;

    private State[][] squares;

    TextView textGameStatus;

    Button btn00,btn01,btn02;
    Button btn10,btn11,btn12;
    Button btn20,btn21,btn22;
    Button btnNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btn00 = (Button) findViewById(R.id.button00);
        btn01 = (Button) findViewById(R.id.button01);
        btn02 = (Button) findViewById(R.id.button02);

        btn10 = (Button) findViewById(R.id.button10);
        btn11 = (Button) findViewById(R.id.button11);
        btn12 = (Button) findViewById(R.id.button12);

        btn20 = (Button) findViewById(R.id.button20);
        btn21 = (Button) findViewById(R.id.button21);
        btn22 = (Button) findViewById(R.id.button22);

        textGameStatus = (TextView) findViewById(R.id.textGameStatus);
        btnNewGame = (Button) findViewById(R.id.buttonNewGame);

        btnNewGame.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initializeGame();
                    }
                }
        );

        initializeGame();
    }

    private void systemMove(){
        int row,column;

        while (true) {
            row = (int) (Math.random() * 3 + 0);
            column = (int) (Math.random() * 3 + 0);
            if(squares[row][column] == State.Blank)
            {
                String buttonTag = "["+row+"]["+column+"]";

                GridLayout grid = (GridLayout) findViewById(R.id.activity_game);
                int count = grid.getChildCount();
                for(int i=0;i<count;i++){
                    View v = grid.getChildAt(i);
                    if (v instanceof Button){
                        if(v.getTag()!=null && v.getTag().equals(buttonTag)) {
                            onClick(v);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void onClick(View v) {

        if (!gameCompleted) {

            Button button = (Button) v;

            button.setText(currentPlayer.toString());
            button.setTextColor(colorCurrentPlayer);
            button.setClickable(false);

            if(btn00.getId() == v.getId()){
                squares[0][0] = currentPlayer;
                availableSquares--;
            }
            else if(btn01.getId() == v.getId()){
                squares[0][1] = currentPlayer;
                availableSquares--;
            }
            else if(btn02.getId() == v.getId()){
                squares[0][2] = currentPlayer;
                availableSquares--;
            }
            else if(btn10.getId() == v.getId()){
                squares[1][0] = currentPlayer;
                availableSquares--;
            }
            else if(btn11.getId() == v.getId()){
                squares[1][1] = currentPlayer;
                availableSquares--;
            }
            else if(btn12.getId() == v.getId()){
                squares[1][2] = currentPlayer;
                availableSquares--;
            }
            else if(btn20.getId() == v.getId()){
                squares[2][0] = currentPlayer;
                availableSquares--;
            }
            else if(btn21.getId() == v.getId()){
                squares[2][1] = currentPlayer;
                availableSquares--;
            }
            else if(btn22.getId() == v.getId()){
                squares[2][2] = currentPlayer;
                availableSquares--;
            }

            updateScore();

            if (!gameCompleted) {
                if (availableSquares == 0){
                    gameCompleted = true;
                    textGameStatus.setText("Game Drawn!");
                    return;
                }
                else {
                    changePlayer();
                    textGameStatus.setText("Current Player: "+currentPlayer.name());
                    if(currentPlayer == State.O && vsComputer)
                        systemMove();
                }
            }
            else{
                textGameStatus.setText("Winner: "+currentPlayer.name());
            }

        }
    }


    private void updateScore(){
        if(squares[0][0] == currentPlayer && squares[0][1] == currentPlayer && squares[0][2] == currentPlayer){
            btn00.setBackgroundColor(colorWinningBackground);
            btn01.setBackgroundColor(colorWinningBackground);
            btn02.setBackgroundColor(colorWinningBackground);
            gameCompleted = true;
            return;
        }

        if(squares[1][0] == currentPlayer && squares[1][1] == currentPlayer && squares[1][2] == currentPlayer){
            btn10.setBackgroundColor(colorWinningBackground);
            btn11.setBackgroundColor(colorWinningBackground);
            btn12.setBackgroundColor(colorWinningBackground);
            gameCompleted = true;
            return;
        }

        if(squares[2][0] == currentPlayer && squares[2][1] == currentPlayer && squares[2][2] == currentPlayer){
            btn20.setBackgroundColor(colorWinningBackground);
            btn21.setBackgroundColor(colorWinningBackground);
            btn22.setBackgroundColor(colorWinningBackground);
            gameCompleted = true;
            return;
        }

        if(squares[0][0] == currentPlayer && squares[1][0] == currentPlayer && squares[2][0] == currentPlayer){
            btn00.setBackgroundColor(colorWinningBackground);
            btn10.setBackgroundColor(colorWinningBackground);
            btn20.setBackgroundColor(colorWinningBackground);
            gameCompleted = true;
            return;
        }

        if(squares[0][1] == currentPlayer && squares[1][1] == currentPlayer && squares[2][1] == currentPlayer){
            btn01.setBackgroundColor(colorWinningBackground);
            btn11.setBackgroundColor(colorWinningBackground);
            btn21.setBackgroundColor(colorWinningBackground);
            gameCompleted = true;
            return;
        }

        if(squares[0][2] == currentPlayer && squares[1][2] == currentPlayer && squares[2][2] == currentPlayer){
            btn02.setBackgroundColor(colorWinningBackground);
            btn12.setBackgroundColor(colorWinningBackground);
            btn22.setBackgroundColor(colorWinningBackground);
            gameCompleted = true;
            return;
        }

        if(squares[0][0] == currentPlayer && squares[1][1] == currentPlayer && squares[2][2] == currentPlayer){
            btn00.setBackgroundColor(colorWinningBackground);
            btn11.setBackgroundColor(colorWinningBackground);
            btn22.setBackgroundColor(colorWinningBackground);
            gameCompleted = true;
            return;
        }

        if(squares[0][2] == currentPlayer && squares[1][1] == currentPlayer && squares[2][0] == currentPlayer){
            btn02.setBackgroundColor(colorWinningBackground);
            btn11.setBackgroundColor(colorWinningBackground);
            btn20.setBackgroundColor(colorWinningBackground);
            gameCompleted = true;
            return;
        }


    }

    private void changePlayer(){
        currentPlayer = currentPlayer.equals(State.X)? State.O: State.X;
        colorCurrentPlayer=  colorCurrentPlayer == Color.argb(255,236,26,97)?  Color.argb(255,255,137,2):Color.argb(255,236,26,97);
    }

    private void initializeGame(){
        colorCurrentPlayer = Color.argb(255,236,26,97);
        colorDefaultBackground = Color.parseColor("#ffd6d7d7");
        colorWinningBackground = Color.parseColor("#FF34B5E5");

        squares = new State[3][3];
        gameCompleted = false;
        availableSquares = 9;
        currentPlayer = State.X;

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                squares[i][j] = State.Blank;

        btn00.setText("");
        btn01.setText("");
        btn02.setText("");
        btn10.setText("");
        btn11.setText("");
        btn12.setText("");
        btn20.setText("");
        btn21.setText("");
        btn22.setText("");

        btn00.setClickable(true);
        btn01.setClickable(true);
        btn02.setClickable(true);
        btn10.setClickable(true);
        btn11.setClickable(true);
        btn12.setClickable(true);
        btn20.setClickable(true);
        btn21.setClickable(true);
        btn22.setClickable(true);

        btn00.setBackgroundColor(colorDefaultBackground);
        btn01.setBackgroundColor(colorDefaultBackground);
        btn02.setBackgroundColor(colorDefaultBackground);
        btn10.setBackgroundColor(colorDefaultBackground);
        btn11.setBackgroundColor(colorDefaultBackground);
        btn12.setBackgroundColor(colorDefaultBackground);
        btn20.setBackgroundColor(colorDefaultBackground);
        btn21.setBackgroundColor(colorDefaultBackground);
        btn22.setBackgroundColor(colorDefaultBackground);


        textGameStatus.setText("Current Player: "+currentPlayer.name());

        vsComputer = getIntent().getStringExtra("GameType").equalsIgnoreCase("Computer")?true:false;

        if(vsComputer){
            setTitle("TicTacToe - Play Against Computer");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("You Vs. Computer")
                    .setMessage("You start the game. You're 'X'.\n\nGood Luck!")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setPositiveButton("Close", null)
                    .show();
        }
        else
            setTitle("TicTacToe - Play Against Human");

    }

    private enum State{
        X,O,Blank
    }

}
