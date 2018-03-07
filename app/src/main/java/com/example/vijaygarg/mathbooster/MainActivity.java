package com.example.vijaygarg.mathbooster;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    TextView question,time,score,wrongright;
    EditText answer;
    Button submit,startagain,start;
    String correctanswer;
    int timer=0;
    int correct=0,total=0;
    LinearLayout ly;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wrongright=findViewById(R.id.tvwrongright);
        question=findViewById(R.id.tvquestion);
        time=findViewById(R.id.tvtimer);
        score=findViewById(R.id.tvscore);
        answer=findViewById(R.id.etanswer);
        submit=findViewById(R.id.btnsubmit);
        startagain=findViewById(R.id.btnstartAgain);
        start=findViewById(R.id.btnStart);
        ly=findViewById(R.id.linearlayout);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myanswer=answer.getText().toString();
                if(myanswer.equals(correctanswer)){
                    correct++;
                    wrongright.setText("CORRECT ANSWER");

                }else{

                    wrongright.setText("WRONG ANSWER!!");
                }
                createnextquestion();
                total++;
                score.setText(correct+"/"+total);
                answer.setText("");
            }
        });
        startagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetgame();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            start.setVisibility(View.GONE);
            ly.setVisibility(View.VISIBLE);
            resetgame();

            }
        });
    }

    private void createnextquestion() {
        Random r=new Random();
        int a=r.nextInt(50);
        int b=r.nextInt(50);

        question.setText(a+""+"+" + b+"");
        correctanswer=a+b+"";
    }

    private void resetgame() {
       createnextquestion();
        timer=60*1000;
        score.setText("0/0");
        correct=0;
        total=0;
        wrongright.setText("Game Start");
        answer.setText("");
        submit.setEnabled(true);
        startagain.setVisibility(View.GONE);
        new CountDownTimer(timer+100,1000){

            @Override
            public void onTick(long l) {
                int min= (int) (l/60000);
                int sec= (int) (l-min*60000);
                sec=sec%100;
                if(min<10&&sec<10){
                    time.setText("0"+min+":"+"0"+sec);
                }else{
                    time.setText(min+":"+sec);
                 }
            }

            @Override
            public void onFinish() {
                submit.setEnabled(false);
                startagain.setVisibility(View.VISIBLE);
                time.setText("00:00");
                score.setText("Your Score"+ correct+"/"+total);
            }
        }.start();

    }
}
