package lk.peruma.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button btnHuman, btnComputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnComputer = (Button) findViewById(R.id.buttonComputer);
        btnHuman = (Button) findViewById(R.id.buttonHuman);

        setTitle("TicTacToe - Select Game Type");
    }

    public void onClick(View v){
        Intent gameIntent = new Intent("lk.peruma.tictactoe.GameActivity");

        if (v.getId() == btnComputer.getId())
            gameIntent.putExtra("GameType","Computer");
        if (v.getId() == btnHuman.getId())
            gameIntent.putExtra("GameType","Human");

        startActivity(gameIntent);
    }

    public void onAboutClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("About")
                .setMessage("A simple TicTacToe game.\n\nFeedback: axp6201@rit.edu")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("Close", null)
                .show();
    }
}
