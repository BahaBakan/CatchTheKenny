package com.bahabakan.kennyyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

     ImageView[] imageArray;

     Handler handler;
     Runnable runnable;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //başlatmak için:
        timeText=(TextView) findViewById(R.id.timeText);
        scoreText=(TextView) findViewById(R.id.scoreText);

        imageView=(ImageView) findViewById(R.id.imageView);
        imageView2=(ImageView) findViewById(R.id.imageView2);
        imageView3=(ImageView) findViewById(R.id.imageView3);
        imageView4=(ImageView) findViewById(R.id.imageView4);
        imageView5=(ImageView) findViewById(R.id.imageView5);
        imageView6=(ImageView) findViewById(R.id.imageView6);
        imageView7=(ImageView) findViewById(R.id.imageView7);
        imageView8=(ImageView) findViewById(R.id.imageView8);
        imageView9=(ImageView) findViewById(R.id.imageView9);

         imageArray=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideImage();

        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time: "+ l/1000);
            }

            @Override
            public void onFinish() {

                timeText.setText("Time off");
                handler.removeCallbacks(runnable);

                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart
                         Intent intent =getIntent();
                        finish();
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game over", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();



    }

    public void increaseScore(View view){
        score++;

        scoreText.setText("Score:"+score);

    }

    public void hideImage(){

        handler= new Handler();

        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random =new Random();
                int i= random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,1000);
            }
        };

        handler.post(runnable);
    }
}