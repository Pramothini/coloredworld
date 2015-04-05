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
    String[] normal_vision = {"12","8","5","6","5","No Number","29","3","15","74","45","7","16","73","No Number","8","7","26","42"};
    String[] red_green_vision = {"1","3","2","No Number","No Number","5","70","5","17","21","No Number","No Number","No Number","No Number","45","3","5","6, faint 2","2, faint 4"};
    String[] other_option = {"2","9","3","4","22","2","12","7","6","8","11","23","54","9","15","9","1","2, faint 6","4, faint 2"};
    int normal_score = 0;
    int red_green_score = 0;
    int total_cvd_score = 0;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        iactivityno = i.getIntExtra("activitynumber", 0);
        normal_score = i.getIntExtra("normal_score", 0);
        red_green_score = i.getIntExtra("red_green_score", 0);
        total_cvd_score = i.getIntExtra("total_cvd_score", 0);
        result += i.getStringExtra("result");
        if(iactivityno == 0){
            setContentView(R.layout.test_instructions);
        }
//        else if((iactivityno == 8 && (result.contains("Tritanomaly")))||(iactivityno == 18 && result.contains("Normal"))){
        else if(iactivityno == 18 && (result.contains("Normal") || result.contains("Tritanomaly"))){
            //case 1: if all answers are correct for all the first 6 plates, user does not have any red-green deficiency
            //case 2: if 13 or more answers are correct for the first 15 plates, user does not have any red-green deficiency
            setContentView(R.layout.test_result);
            TextView tv = (TextView) findViewById(R.id.test_result);
//            result += "You do not have any color vision deficiency";
            tv.setText(result +" red_green_score = "+red_green_score+" normal_score = "+normal_score+
                    " total_cvd_score = "+total_cvd_score);
        }
//        else if((iactivityno == 7 && normal_score == 6) || (iactivityno == 18 && normal_score>=15)){
//            //case 1: if all answers are correct for all the first 6 plates, user does not have any red-green deficiency
//            //case 2: if 13 or more answers are correct for the first 15 plates, user does not have any red-green deficiency
//            setContentView(R.layout.test_result);
//            TextView tv = (TextView) findViewById(R.id.test_result);
//            String result = "You do not have any color vision deficiency";
//            tv.setText(result + " red_green_score = "+red_green_score+" normal_score = "+normal_score+
//                    " total_cvd_score = "+total_cvd_score);
//        }
        else if(iactivityno <= 19){
            setContentView(R.layout.activity_main);
            if(iactivityno == 7 && normal_score == 6){
                iactivityno = 16;
                result = "Normal Vision - no red green blindness";
            }
            TextView tv = (TextView) findViewById(R.id.plate_number);

//            tv.setText("result = "+result);
//            tv.setText("result = "+result+" ns = "+normal_score+" #"+(iactivityno));
            tv.setText("#"+(iactivityno));
            changeplate(iactivityno-1);
        }
        else{
            setContentView(R.layout.test_result);
            TextView tv = (TextView) findViewById(R.id.test_result);
//            if(normal_score >= 13){
//                result += "You do not have any color vision deficiency";
//            }
            if(total_cvd_score >= 10){
                result += "You have total color vision deficiency";
            }
//            else if(normal_score <=9 ){
//                result += "You have red-green deficiency";
//            }
            tv.setText(result + " red_green_score = "+red_green_score+" normal_score = "+normal_score+
                    " total_cvd_score = "+total_cvd_score);
        }

    }

    public void SubmitAnswer(View v){
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        Button btn = (Button)v;
        String cvd = "";
//        if(iactivityno == 7 && normal_score == 6){
//            if(btn.getText().equals("8")){
//                result+="No Tritanomaly. Normal CV";
//                normal_score++;
//            }
//            else{
//                result+="Tritanomaly detected";
//            }
//        }
//        if(iactivityno == 16){
//            if(normal_score >= 13){
//                result += "Normal CV - no red green blindness";
//            }
//        }
     if (iactivityno == 18 || iactivityno == 19){
            if((red_green_vision[iactivityno-1]).equalsIgnoreCase(btn.getText() + "")){
                    cvd = "Protanomaly";
            }
            else if((other_option[iactivityno-1].equalsIgnoreCase(btn.getText()+""))){
                cvd = "Deuteranomaly";
            }
            else if(((red_green_vision[iactivityno-1]).charAt(0)+"").equals(btn.getText())){
                cvd = "Protanopia";
            }
            else if(((other_option[iactivityno-1]).charAt(0)+"").equals(btn.getText())){
                cvd = "Deuteranopia";
            }
            else if((normal_vision[iactivityno-1]).equals(btn.getText())){
                cvd = "Normal";
            }
            if(iactivityno == 18)
                result = cvd;
            else if(iactivityno == 19){
//                result += cvd;
                if(!result.contains(cvd)){
                    result = "This color test could not detect your cvd";
                }
            }
        }
        else if((normal_vision[iactivityno-1]).equals(btn.getText())){
         if(iactivityno == 16){
             if(normal_score >= 13){
                 result += "Normal CV - no red green blindness";
             }
         }
            normal_score++;

        }
        else if(iactivityno == 16)
            result = "You have Tritanomaly";
        else if(iactivityno == 17){
         if (!result.contains("Tritanomaly")){
             result = "You have Tritanomaly";
         }

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
        i.putExtra("result", result);
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
        if(normal_vision[iactivityno].equals("No Number")){
            b[0].setText("20");
        }
        else{
            b[0].setText(normal_vision[iactivityno]);
        }
        if(red_green_vision[iactivityno].equals("No Number")){
            b[1].setText("20");
        }
        else{
            b[1].setText(red_green_vision[iactivityno]);
        }
        b[2].setText(other_option[iactivityno]);

        switch(iactivityno){
            case 1:
                image.setImageResource(R.drawable.plate2);
                break;
            case 2:
                image.setImageResource(R.drawable.plate4);
                break;
            case 3:
                image.setImageResource(R.drawable.plate8);
                break;
            case 4:
                image.setImageResource(R.drawable.plate10);
                break;
            case 5:
                image.setImageResource(R.drawable.plate14);
                break;
            case 6:
//                if(normal_score == 6){
//                    image.setImageResource(R.drawable.tritaplate2);
//                    b[0].setText("8");
//                    b[1].setText("3");
//                    b[2].setText("9");
//                }
//                else{
                    image.setImageResource(R.drawable.plate3);
//                }

                break;
            case 7:
                image.setImageResource(R.drawable.plate5);
                break;
            case 8:
                image.setImageResource(R.drawable.plate6);
                break;
            case 9:
                image.setImageResource(R.drawable.plate7);
                break;
            case 10:
                image.setImageResource(R.drawable.plate9);
                break;
            case 11:
                image.setImageResource(R.drawable.plate11);
                break;
            case 12:
                image.setImageResource(R.drawable.plate12);
                break;
            case 13:
                image.setImageResource(R.drawable.plate13);
                break;
            case 14:
                image.setImageResource(R.drawable.plate15);
                break;
            case 15:
                image.setImageResource(R.drawable.tritaplate2);
                break;
            case 16:
                image.setImageResource(R.drawable.tritaplate3);
                break;
            case 17:
                image.setImageResource(R.drawable.plate16_v1);
                Button ans4 = (Button) findViewById(R.id.nothing);
                ans4.setText("2");
                Button ans5 = (Button) findViewById(R.id.unsure);
                ans5.setText("6");
                break;
            case 18:
                image.setImageResource(R.drawable.plate17_v1);
                Button answer4 =(Button) findViewById(R.id.nothing);
                answer4.setText("4");
                Button answer5 = (Button) findViewById(R.id.unsure);
                answer5.setText("2");
                break;
            default:
                break;

        }


    }
}
