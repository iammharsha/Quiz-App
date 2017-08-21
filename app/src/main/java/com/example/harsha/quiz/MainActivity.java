package com.example.harsha.quiz;


import android.content.Intent;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {

    private int flag=0;
    private int score=0;
    private String a;
    private TextView question;
    private TextView option1;
    private TextView option2;
    private TextView option3;
    private TextView option4;
    private String file;
    private JSONArray ques1;
    private String answer;
    public static int count=0;
    private static Intent intent;
    private JSONObject c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try{
            InputStream is = getAssets().open("question.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            file = new String(buffer, "UTF-8");

        }
        catch (IOException ex) {
            ex.printStackTrace();

        }

        question=(TextView) findViewById(R.id.question);
        option1=(TextView) findViewById(R.id.option1);
        option2=(TextView) findViewById(R.id.option2);
        option3=(TextView) findViewById(R.id.option3);
        option4=(TextView) findViewById(R.id.option4);

        JSONObject ques;
        try{
            ques = new JSONObject(file);
            ques1=ques.getJSONArray("qn");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        int qn[]=new int[5];
        Arrays.fill(qn,-1);

        for(int i=0;i<2;i++)
        {
            qn[i]=i;
        }


        System.out.println(score);
        setData();



        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0) {
                    flag=1;
                    answer = option1.getText().toString();
                    if (answer.equals(a)) {
                        score = score + 1;
                        hello(option1, true);
                    } else {
                        hello(option1, false);
                    }
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0) {
                    flag=1;
                    answer = option2.getText().toString();
                    if (answer.equals(a)) {
                        score = score + 1;
                        hello(option2, true);
                    } else {
                        hello(option2, false);
                    }
                }

            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0) {
                    flag=1;
                    answer = option3.getText().toString();
                    if (answer.equals(a)) {
                        score = score + 1;
                        hello(option3, true);
                    } else {
                        hello(option3, false);
                    }
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0) {
                    flag=1;
                    answer = option4.getText().toString();
                    if (answer.equals(a)) {
                        score = score + 1;
                        hello(option4, true);
                    } else {
                        hello(option4, false);
                    }
                }

            }
        });






    }

    public void hello(final TextView text,final boolean op)
    {
        CountDownTimer countDown=new CountDownTimer(3*1000,50) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(op)
                    text.setBackgroundColor(getResources().getColor(R.color.right));
                else
                    text.setBackgroundColor(getResources().getColor(R.color.wrong));
            }

            @Override
            public void onFinish() {
                flag=0;
                if(count!=2)
                {
                    setData();
                }else{
                    result();
                }
            }
        };

        countDown.start();

    }

    public void setData()
    {
        option1.setBackgroundColor(NULL);
        option2.setBackgroundColor(NULL);
        option3.setBackgroundColor(NULL);
        option4.setBackgroundColor(NULL);
        try {
            c = ques1.getJSONObject(count);
            a=c.getString("answer");
            count++;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try {
            a = c.getString("question");
            question.setText(a);
            a = c.getString("option1");
            option1.setText(a);
            a = c.getString("option2");
            option2.setText(a);
            a = c.getString("option3");
            option3.setText(a);
            a = c.getString("option4");
            option4.setText(a);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void result()
    {
        intent=new Intent(MainActivity.this,ScoreActivity.class);
        intent.putExtra("score",score);
        startActivity(intent);
    }
}

