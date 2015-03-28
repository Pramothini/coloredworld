package mpcs.coloredworld;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Collections;


public class MainActivity extends ActionBarActivity {

    int iactivityno = 0;
    String[] normal_vision = {"12","8","29","5","3","15","74","6"};
    String[] red_green_vision = {"12","3","70","2","5","17","21","Nothing"};
    int normal_score = 0;
    int red_green_score = 0;
    int total_cvd_score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        iactivityno = i.getIntExtra("activitynumber", 0);
        normal_score = i.getIntExtra("normal_score", 0);
        red_green_score = i.getIntExtra("red_green_score", 0);
        total_cvd_score = i.getIntExtra("total_cvd_score", 0);
        if(iactivityno == 0){
            setContentView(R.layout.test_instructions);
        }
        else if(iactivityno <= 8){
            setContentView(R.layout.activity_main);
            TextView tv = (TextView) findViewById(R.id.plate_number);

//            tv.setText(i.getStringExtra("button_txt"));
            tv.setText("#"+(iactivityno));
            changeplate(iactivityno-1);
        }
        else{
            setContentView(R.layout.test_result);
            TextView tv = (TextView) findViewById(R.id.test_result);
            tv.setText("red_green_score = "+red_green_score+" normal_score = "+normal_score+
                    " total_cvd_score = "+total_cvd_score);
        }

    }

    public void SubmitAnswer(View v){
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        Button btn = (Button)v;
        if((normal_vision[iactivityno-1]).equals(btn.getText())){
            normal_score++;
        }
        else if((red_green_vision[iactivityno-1]).equals(btn.getText())){
            red_green_score++;
        }
        else{
            total_cvd_score++;
        }
        i.putExtra("normal_score",normal_score);
        i.putExtra("red_green_score",red_green_score);
        i.putExtra("total_cvd_score",total_cvd_score);
        i.putExtra("activitynumber", ++iactivityno);
        startActivity(i);

    }

    public void reset(View v){
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        i.putExtra("activitynumber", 1);
        startActivity(i);
    }

    public void changeplate(int iactivityno){
        ImageView image = (ImageView) findViewById(R.id.plate);
        Button[] b = new Button[3];
        b[0] =(Button) findViewById(R.id.ans1);
        b[1] =(Button) findViewById(R.id.ans2);
        b[2] =(Button) findViewById(R.id.ans3);
        Collections.shuffle(Arrays.asList(b));
        b[0].setText(normal_vision[iactivityno]);
        b[1].setText(red_green_vision[iactivityno]);
        b[2].setText("10");

        switch(iactivityno){
            case 1:
                image.setImageResource(R.drawable.plate2);
                break;
            case 2:
                image.setImageResource(R.drawable.plate4);
                break;
            case 3:
                image.setImageResource(R.drawable.plate6);
                break;
            case 4:
                image.setImageResource(R.drawable.plate7);
                break;
            case 5:
                image.setImageResource(R.drawable.plate8);
                break;
            case 6:
                image.setImageResource(R.drawable.plate9);
                break;
            case 7:
                image.setImageResource(R.drawable.plate11);
                break;
            default:
                break;

        }


    }
}
