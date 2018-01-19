package com.example.altaris.memlionere.Activities;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.altaris.memlionere.classes.Questions;
import com.example.altaris.memlionere.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class PlayActivity extends AppCompatActivity {

    TextView questionText;
    Button answerAbtn;
    Button answerBbtn;
    Button answerCbtn;
    Button answerDbtn;

    public int actualQuestion = 0;

    public ArrayList<Questions> questionsList = new ArrayList<>();
    public ArrayList<Integer> usedQuestions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        questionText = (TextView) findViewById(R.id.questionText);
        answerAbtn = (Button) findViewById(R.id.ans_aBtn);
        answerBbtn = (Button) findViewById(R.id.ans_bBtn);
        answerCbtn = (Button) findViewById(R.id.ans_cBtn);
        answerDbtn = (Button) findViewById(R.id.ans_dBtn);

        questionsList = getQuestionsArrayList();

        actualQuestion = newRandomQuestionId();

        //button actions
        answerAbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    answerAbtn.setBackgroundDrawable( getResources().getDrawable(R.drawable.selected_answer) );
//
                } else {
                    answerAbtn.setBackground(getResources().getDrawable(R.drawable.selected_answer));
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                changetext();
            }
        });

        answerBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    answerBbtn.setBackgroundDrawable( getResources().getDrawable(R.drawable.selected_answer) );
                } else {
                    answerBbtn.setBackground(getResources().getDrawable(R.drawable.selected_answer));
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                changetext();
            }
        });

        answerCbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    answerCbtn.setBackgroundDrawable( getResources().getDrawable(R.drawable.selected_answer) );
                } else {
                    answerCbtn.setBackground(getResources().getDrawable(R.drawable.selected_answer));
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                changetext();
            }
        });

        answerDbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    answerDbtn.setBackgroundDrawable( getResources().getDrawable(R.drawable.selected_answer) );
                } else {
                    answerDbtn.setBackground(getResources().getDrawable(R.drawable.selected_answer));
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                changetext();
            }
        });

        changetext();
    }

    public ArrayList<Questions> getQuestionsArrayList()
    {
        ArrayList<Questions> questionsList = new ArrayList<>();
        String json = null;
        try {
            InputStream is = getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray q_jArray = obj.getJSONArray("questions");

            for (int i = 0; i < q_jArray.length(); i++) {
                JSONObject jo_inside = q_jArray.getJSONObject(i);
                Questions question = new Questions();

                question.text = (String)jo_inside.get("question");
                question.correct = (String)jo_inside.get("correct");
                question.answer1 = (String)jo_inside.get("answer1");
                question.answer2 = (String)jo_inside.get("answer2");
                question.answer3 = (String)jo_inside.get("answer3");

                questionsList.add(question);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questionsList;
    }

    public void changetext()
    {

        questionText.setText(questionsList.get(actualQuestion).text);
        ArrayList<Integer> answersArray = answersShuffle();

        answerAbtn.setText(switchAnswer(answersArray.get(0)));
        answerBbtn.setText(switchAnswer(answersArray.get(1)));
        answerCbtn.setText(switchAnswer(answersArray.get(2)));
        answerDbtn.setText(switchAnswer(answersArray.get(3)));
    }

    public String switchAnswer(int a)
    {
        switch (a)
        {
            case 0: return questionsList.get(actualQuestion).correct;
            case 1: return questionsList.get(actualQuestion).answer1;
            case 2: return questionsList.get(actualQuestion).answer2;
            case 3: return questionsList.get(actualQuestion).answer3;
            default: return "error";
        }
    }

    public int newRandomQuestionId(){
        Random rand = new Random();
//        int b = 0;
        if((usedQuestions != null)&&(questionsList != null)) {

            int n = rand.nextInt(questionsList.size()==0 ? 1 : questionsList.size());//random from 1 to nr. of questions

            if (usedQuestions.size() == questionsList.size()) {
                usedQuestions.clear();
            }

            if (usedQuestions.contains(n)) {
                newRandomQuestionId();
            } else {
                usedQuestions.add(n);
                return n;
            }
        }
        return 0;
    }

    public ArrayList<Integer> answersShuffle() {
        ArrayList<Integer> shuffleArr = new ArrayList<>();
        int i = 0;

        while(shuffleArr.size() < 4){
                Random rand = new Random();
                int n = rand.nextInt(4);

                if (shuffleArr.contains(n)) {
                    continue;
                }
                shuffleArr.add(n);
                i++;
        }

        return shuffleArr;
    }
}
