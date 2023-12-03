package edu.birzeit.a1202874_todo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class playingActivity extends AppCompatActivity {

    public int counter1 = 0;
    public int counter2 = 0;
    public int totalMatchedPairs = 0;
    public ArrayList<Button> choosenCards = new ArrayList<>();
    public int currentPlayer = 0;

    private String player1Name;
    private String player2Name;

    private void showTheTurns() { // to display the turns
        TextView turn = findViewById(R.id.turn);
        if (currentPlayer == 0) turn.setText(player1Name);
        else turn.setText(player2Name);
    }

    private void playersTurn() { // to manage the turns
        if (currentPlayer == 0) currentPlayer = 1;
        else currentPlayer = 0;
        showTheTurns();
    }

    public void processOfTheGame(Button selected) {
        if (choosenCards == null) {
            choosenCards = new ArrayList<>();
        }

        if (selected != null) {
            choosenCards.add(selected);

            if (choosenCards.size() == 2) {
                Button firstCard = choosenCards.get(0);
                Button secondCard = choosenCards.get(1);

                if (firstCard != null && secondCard != null) {
                    if (firstCard.getTag().toString().equals(secondCard.getTag().toString())) {//in case of matched
                        firstCard.setEnabled(false);
                        secondCard.setEnabled(false);
                        totalMatchedPairs++;
                        if (currentPlayer == 0) {
                            counter1++;
                        } else {
                            counter2++;
                        }
                        playersTurn();
                        checkForWinner(); // Check for the winner after each turn
                    } else {//in case of unmatched
                        firstCard.setEnabled(true);
                        secondCard.setEnabled(true);
                    }
                    choosenCards.clear();
                }
            }
        }

        if (totalMatchedPairs == 3) {
            checkForWinner();
        }
    }

    Button firstChoice = null;
    Button secondChoice = null;

    public void handleClickedButtons(Button chosenCard, TextView t1, TextView t2) {
        if (firstChoice == null) {
            firstChoice = chosenCard;
            t1.setText(firstChoice.getTag().toString());
        } else if (secondChoice == null && chosenCard != firstChoice) {
            secondChoice = chosenCard;
            t2.setText(secondChoice.getTag().toString());
            processOfTheGame(secondChoice);
        }

        // Check for winner and reset choices
        if (firstChoice != null && secondChoice != null) {
            processOfTheGame(firstChoice);
            firstChoice = null;
            secondChoice = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        Button A = findViewById(R.id.A);
        Button B = findViewById(R.id.B);
        Button C = findViewById(R.id.C);
        Button D = findViewById(R.id.D);
        Button E = findViewById(R.id.E);
        Button F = findViewById(R.id.F);
        Button restart = findViewById(R.id.restart);
        TextView choice1 = findViewById(R.id.card1);
        TextView choice2 = findViewById(R.id.card2);
        TextView winner = findViewById(R.id.win);

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickedButtons(A, choice1, choice2);
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickedButtons(B, choice1, choice2);
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickedButtons(C, choice1, choice2);
            }
        });

        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickedButtons(D, choice1, choice2);
            }
        });

        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickedButtons(E, choice1, choice2);
            }
        });

        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClickedButtons(F, choice1, choice2);
            }
        });

        // Restart the game
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter1 = 0;
                counter2 = 0;
                currentPlayer = 0;
                totalMatchedPairs = 0;
                choosenCards.clear();
                showTheTurns();

                A.setEnabled(true);
                B.setEnabled(true);
                C.setEnabled(true);
                D.setEnabled(true);
                E.setEnabled(true);
                F.setEnabled(true);

                choice1.setText("");
                choice2.setText("");
                winner.setText("");
                Intent intent2=new Intent(playingActivity.this,MainActivity.class);
                startActivity(intent2);

            }
        });

        Intent intent1 = getIntent();
        player1Name = intent1.getStringExtra("fPlayer");
        player2Name = intent1.getStringExtra("sPlayer");
        showTheTurns(); // Show initial player's turn
    }

    private void checkForWinner() {
        if (totalMatchedPairs == 3) {
            TextView winnerTextView = findViewById(R.id.win);
            String winnerText;

            if (counter1 > counter2) {
                winnerText = player1Name + " wins the game with a score of " + counter1;
            } else if (counter2 > counter1) {
                winnerText = player2Name + " wins the game with a score of " + counter2;
            } else {
                winnerText = "TIE with a score of " + counter1;
            }

            winnerTextView.setText(winnerText);
        }
    }
}



