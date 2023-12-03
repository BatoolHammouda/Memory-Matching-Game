package edu.birzeit.a1202874_todo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView ask4Names=findViewById(R.id.asking4Names);
        EditText name1=findViewById(R.id.player1);
        EditText name2=findViewById(R.id.player2);
        Button start=findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,playingActivity.class);
                String fPlayer=name1.getText().toString();
                String sPlayer=name2.getText().toString();
                if((fPlayer.isEmpty())==false && (sPlayer.isEmpty())==false){
                    intent1.putExtra("fPlayer",fPlayer);
                    intent1.putExtra("sPlayer",sPlayer);
                    startActivity(intent1);
                }


            }
        });
    }
}