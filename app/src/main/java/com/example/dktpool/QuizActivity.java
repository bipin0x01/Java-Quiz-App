package com.example.dktpool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView txtQuestionNumber, txtScore, txtQuestion;
    private RadioGroup radioGrp;
    private RadioButton rb1, rb2,rb3,rb4;
    private Button btnNxt;
    private List<QuestionModel> qnList;

    int totalQuestions = 10;
    int questionCounter = 0 ;
    int score = 0;


    ColorStateList dfRbColor;
    boolean answered;

    private QuestionModel currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

//        Create a new empty array to save questions to
        qnList = new ArrayList<>();

//        Link each item from the layout to a variable
        txtQuestionNumber = findViewById(R.id.textQuestionNumber);
        txtScore = findViewById(R.id.textScore);
        txtQuestion = findViewById(R.id.textQuestion);
        radioGrp = findViewById(R.id.radioGroup);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        btnNxt = findViewById(R.id.btnNext);


        dfRbColor = rb1.getTextColors();
         addQuestion();

//         shuffle the questions
        Collections.shuffle(qnList);

//        Store the size of question array into total questions
//        totalQuestions = qnList.size();
        gotoNextQuestion();

        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answered==false){
//                    check if any answer is selected or not
                    if (rb1.isChecked() || rb2.isChecked() ||rb3.isChecked() || rb4.isChecked()){
                        checkAns();
                    }else {
                        Toast.makeText(QuizActivity.this, "Please select an option to continue to next question", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    gotoNextQuestion();
                }
            }
        });
    }




//    Check if the submitted answer is correct
    private void checkAns(){
    answered = true ;
    RadioButton rbSelected = findViewById(radioGrp.getCheckedRadioButtonId());
    int correctAns = radioGrp.indexOfChild(rbSelected) + 1;
//    check the answer and add the score
    if (correctAns == currentQuestion.getCorrectAns()){
        score++;
        txtScore.setText("Score:" + score);
    }

//    set all radio button to red on answer submit
    rb1.setTextColor(Color.RED);
    rb2.setTextColor(Color.RED);
    rb3.setTextColor(Color.RED);
    rb4.setTextColor(Color.RED);

//    Change the correct option to green
    switch (currentQuestion.getCorrectAns()){
        case 1:
            rb1.setTextColor(Color.GREEN);
            break;
        case 2:
            rb2.setTextColor(Color.GREEN);
            break;
        case 3:
            rb3.setTextColor(Color.GREEN);
            break;
        case 4:
            rb4.setTextColor(Color.GREEN);
            break;
    }
        if (questionCounter < totalQuestions){
            btnNxt.setText("Next");
        }else {
            btnNxt.setText("Get Result");
            btnNxt.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent showResult = new Intent(QuizActivity.this, ResultActivity.class);
                            // creating a bundle object
                            Bundle bundle = new Bundle();

                            // storing the string value in the bundle
                            // which is mapped to key
                            bundle.putString("totalScore", Integer.toString(score));

                            // passing the bundle into the intent
                            showResult.putExtras(bundle);
                            startActivity(showResult);
                        }
                    }
            );
        }
    }


    private void addQuestion(){
        qnList.add(new QuestionModel("What should you do when approaching School Zone?","Slow Down","Speed Up", "Drive as usual", "Honk for Pedestrians",1));
        qnList.add(new QuestionModel("What should you do when you see Red Light?","Speed up","Prepare to stop","Doesn't matter, Keep Driving","Check for the camera, and Speed up",2));
        qnList.add(new QuestionModel("What should you do if you see an Ambulance?","Give Way", "Drive as Usual","Stop your vehicle","Speed up",1));
        qnList.add(new QuestionModel("You are about to make a right-hand turn at this intersection. You have the green light. You hear a siren from behind and then see that a fire truck will soon overtake you. You should -\n", "Continue and make the turn because you have the right of way","Speed up to beat the fire truck","Stop and let the fire truck overtake you","Turn off your Vehicles engine.",3));
        qnList.add(new QuestionModel("Where there are double dividing lines, you may park -","At least two metres from the dividing lines.","One metre from the dividing lines.","I don’t care, I can park anywhere.","At least three metres from the dividing lines.",4));
        qnList.add(new QuestionModel("You should use your right-hand indicator when -","You intend to slow down.","You intend to move to the right, at any time.","You are about to stop.","You intend to move to the left",2));
        qnList.add(new QuestionModel("Are you required to carry your driver's licence with you every time you drive?","Yes","No, being licensed is enough","No, I don’t care about my Driving License, I can Drive, that’s it.","No, it is only needed on long trips.",1));
        qnList.add(new QuestionModel("Is it an offence to obstruct the clear vision of your number plates?","Yes, but it is legal for a towbar or bicycle rack to cover the rear number plate.","No, you are allowed to cover your number plates if you want to.","I don’t care, My vehicle, My rule","Yes, at any time.",4));
        qnList.add(new QuestionModel("What must you do if you miss your exit on a freeway?","Stop immediately and turn around.","Continue until you reach the next appropriate exit.","Stop, and reverse back along the freeway to the exit you missed.","Call 000 for Help.",2));
        qnList.add(new QuestionModel("Do you have any responsibilities when opening a vehicle door on a roadway?","No, there is no regulation to cover this situation.","Yes, you must not open a door if you are likely to cause danger to road users or impede traffic.","No, any following traffic must stop if the door interferes with its progress.","No, My vehicle, My rule",2));
        qnList.add(new QuestionModel("Stop signs or flashing lights at railway crossings should always be obeyed, because -","Car brakes often fail",
                "Your eyes could be damaged due to flashing lights","Trains are fast, heavy and cannot stop quickly.","Pedestrians might be crossing.",3));
        qnList.add(new QuestionModel("Your car registration expires today. You have your car inspected for registration renewal but it fails the inspection. What should you do?","After today you must not drive the car until it has been repaired, passed another inspection and registered.","Go to the RTA and ask them to renew your registration and tell them you will soon get the car fixed.","You have seven days to have the car repaired, after which the RTA considers it to be unregistered.","Do literally Nothing, Just Netflix and chill",1));
        qnList.add(new QuestionModel("You are driving at night with your headlights on a high beam. When should you dip your headlights?","When within 200 metres of an oncoming vehicle only.","Never, you are allowed to drive with your lights on high beam at all times.","When within 200 metres of the vehicle ahead or an oncoming one.","Don’t worry about it, It’s just a light",3));
    }

    private void gotoNextQuestion(){
//        check the color of radio btn back to default once previous qn is submitted
        radioGrp.clearCheck();
        rb1.setTextColor((dfRbColor));
        rb2.setTextColor((dfRbColor));
        rb3.setTextColor((dfRbColor));
        rb4.setTextColor((dfRbColor));
        if (questionCounter < totalQuestions){
            currentQuestion = qnList.get(questionCounter);
            txtQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            questionCounter++;
            txtQuestionNumber.setText("Question:" + questionCounter + "/" + totalQuestions);
            answered = false;
            btnNxt.setText("Check");
        }else{
            finish();
        }
    }
}