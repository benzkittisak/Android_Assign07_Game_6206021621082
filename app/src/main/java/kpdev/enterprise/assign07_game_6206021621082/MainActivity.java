package kpdev.enterprise.assign07_game_6206021621082;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int res_iv[] = {
            R.id.imageView1,
            R.id.imageView2,
            R.id.imageView3,
            R.id.imageView4,
            R.id.imageView5,
            R.id.imageView6,
            R.id.imageView7,
            R.id.imageView8,
            R.id.imageView9,
    };

    int res_images[] = {
            R.drawable.apple,
            R.drawable.avocado,
            R.drawable.banana,
            R.drawable.blueberry,
            R.drawable.cherry,
            R.drawable.dragonfruit,
            R.drawable.grapefruit,
            R.drawable.guava,
            R.drawable.kiwi,
            R.drawable.lemon,
            R.drawable.mango,
            R.drawable.orange,
            R.drawable.papaya,
            R.drawable.passionfruit,
            R.drawable.peach,
            R.drawable.pear,
            R.drawable.pineapple,
            R.drawable.pomegranate,
            R.drawable.strawberry,
            R.drawable.watermelon,
    };

    private int hidden_icon = R.drawable.format_icon;
    private ImageView fruits_icon []  = new ImageView[res_iv.length];
    private Button btnStart;
    private TextView scoreTV , timeTV , timeUpTV , resultTV ;
    int iNum = -1 , oldNum = -1, iImage = -1;
    CountDownTimer timer;
    Random rnd = new Random();

    int max = res_images.length;
    int max_hidden = res_iv.length;
    int score = 0 ;

    // Time countdown
    private final int interval = 1000 , timeLimit = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initial Variables
        btnStart = findViewById(R.id.btnStart);
        scoreTV = findViewById(R.id.showScore);
        timeTV = findViewById(R.id.item_countdown);
        timeUpTV = findViewById(R.id.timeupTV);
        resultTV = findViewById(R.id.resultScore);

        // Initial Hidden Images
        for (int i = 0; i < fruits_icon.length; i++) {
            fruits_icon[i] = findViewById(res_iv[i]);
            fruits_icon[i].setImageResource(hidden_icon);
        }

        timer = new CountDownTimer(timeLimit,interval){
            @Override
            public void onTick(long millisUntilFinished) {
                if(oldNum != -1){
                    fruits_icon[oldNum].setImageResource(hidden_icon);
                    fruits_icon[oldNum].setOnClickListener(null);
                }

                iNum = rnd.nextInt(max_hidden);
                iImage = rnd.nextInt(max);
                fruits_icon[iNum].setImageResource(res_images[iImage]);
                fruits_icon[iNum].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        score++;
                        scoreTV.setText("คะแนน : " + score);
                    }
                });
                timeTV.setText("นับถอยหลัง " +( millisUntilFinished / interval));
                oldNum = iNum;
            }

            @Override
            public void onFinish() {
                fruits_icon[iNum].setImageResource(hidden_icon);
                fruits_icon[iNum].setOnClickListener(null);
                timeUpTV.setText("หมดเวลา !!");
                resultTV.setText("คุณได้คะแนน : " +score);
                btnStart.setEnabled(true);
            }
        };

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = 0 ;
                scoreTV.setText("คะแนน : 0");
                timeUpTV.setText("");
                resultTV.setText("");
                timer.start();
                btnStart.setEnabled(false);
            }
        });
    }
}