package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bathla on 5/22/2016.
 */
public class QuizActivity extends Activity{

    int questionSize;
    Button prevBtn;
    Button nextBtn;
    TextView questionNoTV;
    TextView questionTV;
    RadioGroup questionRG;
    RadioButton questionbtnA;
    RadioButton questionbtnB;
    RadioButton questionbtnC;
    RadioButton questionbtnD;
    RadioButton questionbtnE;
    CheckBox checkBoxButtonA;
    CheckBox checkBoxButtonB;
    CheckBox checkBoxButtonC;
    CheckBox checkBoxButtonD;
    CheckBox checkBoxButtonE;


    TextView questionAnswersTV;
    Button answerBtn;
    public  int questionNo=0;
    List<DataBaseQuestion> dq;

    DatabaseHelper helper;
    String options = "";
    Map<Integer,String> answers=new LinkedHashMap<>();
    int correctAnswers=0;
    int IncorrectAnswers=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_design);



        helper = new DatabaseHelper(this);
        prevBtn = (Button) findViewById(R.id.prev);
        nextBtn = (Button) findViewById(R.id.next);
        questionNoTV = (TextView) findViewById(R.id.questionNo);
        questionTV = (TextView) findViewById(R.id.question);
        questionRG = (RadioGroup) findViewById(R.id.radioGroupId);
        questionbtnA = (RadioButton) findViewById(R.id.optionA);
        questionbtnB = (RadioButton)findViewById(R.id.optionB);
        questionbtnC = (RadioButton)findViewById(R.id.optionC);
        questionbtnD = (RadioButton)findViewById(R.id.optionD);
        questionbtnE = (RadioButton)findViewById(R.id.optionE);
        questionAnswersTV = (TextView) findViewById(R.id.answers);
        answerBtn = (Button) findViewById(R.id.showAnswerbtn);


        checkBoxButtonA = (CheckBox) findViewById(R.id.checkBoxOptionA);
        checkBoxButtonB = (CheckBox) findViewById(R.id.checkBoxOptionB);
        checkBoxButtonC = (CheckBox) findViewById(R.id.checkBoxOptionC);
        checkBoxButtonD = (CheckBox) findViewById(R.id.checkBoxOptionD);
        checkBoxButtonE = (CheckBox) findViewById(R.id.checkBoxOptionE);




        questionAnswersTV.setVisibility(View.GONE);
        questionbtnA.setVisibility(View.GONE);
        questionbtnB.setVisibility(View.GONE);
        questionbtnC.setVisibility(View.GONE);
        questionbtnD.setVisibility(View.GONE);


        questionTV.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        //  DatabaseItem db =intent.getParcelableExtra("databaseItem");
        //   dq =intent.getParcelableExtra("databasequestion");


        int position =intent.getIntExtra("questionNo",1);
        Log.d("questionSize "," position is :"+position);

        //dq=intent.getParcelableArrayListExtra("databasequestion");

        dq=helper.getQuestion(position+1);
        boolean nextquestionAvailable=false;

        questionSize =helper.getTotalQuestionCountForOneTopic(position+1);
        Log.d("questionSize",""+questionSize);
        Log.d("question",""+questionNo);

        prepareQuestions(questionNo, nextquestionAvailable);


        if(questionNo==0)
        {
            prevBtn.setVisibility(View.GONE);
        }
        else if(questionNo >= questionSize-1)
        {
            nextBtn.setText("Submit");
            // nextBtn.setVisibility(View.GONE);
        }
        else
        {
            prevBtn.setVisibility(View.VISIBLE);
        }

        Log.d("Radio",dq.get(0).getTypeOfQuestion().toString());



        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prepareQuestions(--questionNo,false);
                questionAnswersTV.setVisibility(View.GONE);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   prepareQuestions(++questionNo,false);
                questionAnswersTV.setVisibility(View.GONE);
                Log.d("Radio","Test "+options);
//
//                questionbtnA.;
//                questionbtnB.setChecked(false);
//                questionbtnC.setChecked(false);
//                questionbtnD.setChecked(false);
//

                questionRG.clearCheck();

                if(checkBoxButtonA.isChecked())
                {
                    checkBoxButtonA.toggle();
                }

                if(checkBoxButtonB.isChecked())
                {
                    checkBoxButtonB.toggle();
                }
                if(checkBoxButtonC.isChecked())
                {
                    checkBoxButtonC.toggle();
                }
                if(checkBoxButtonD.isChecked())
                {
                    checkBoxButtonD.toggle();
                }




                answers.put(questionNo,options);
                Log.d("Radio",dq.get(questionNo).getAnswer());

                if(dq.get(questionNo).getAnswer().equals(options))
                {
                    ++correctAnswers;
                }
                else
                {
                    ++IncorrectAnswers;
                }

                if(questionNo==questionSize-1)
                {
                    Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                    ArrayList<String> result = new ArrayList<String>();
                    Log.d("ArrayList",answers.values().toString());

                    intent.putExtra("result", (Serializable) answers);
                    intent.putExtra("correct_Answers",correctAnswers);
                    intent.putExtra("Incorrect_Answers",IncorrectAnswers);
                    //   intent.putStringArrayListExtra("result",result);
                    Log.d("Radio","Correct Answers : "+correctAnswers);
                    Log.d("Radio","InCorrect Answers : "+IncorrectAnswers);
                    startActivity(intent);
                    finish();

                }else
                {
                    prepareQuestions(++questionNo,false);
                }

                //    findViewById(questionRG.getCheckedRadioButtonId())
            }
        });

        answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Button Clicked : "+dq.get(questionNo).getAnswer(),Toast.LENGTH_LONG).show();
                questionAnswersTV.setVisibility(View.VISIBLE);
                questionAnswersTV.setText(dq.get(questionNo).getAnswer());

            }
        });


        questionRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.optionA:
                        // do operations specific to this selection
                        Log.d("Radio","Option A clicked ");
                        options="A";
                        break;

                    case R.id.optionB:
                        // do operations specific to this selection
                        Log.d("Radio","Option B clicked ");
                        options="B";
                        break;

                    case R.id.optionC:
                        // do operations specific to this selection
                        Log.d("Radio","Option C clicked ");
                        options="C";
                        break;
                    case R.id.optionD:
                        // do operations specific to this selection
                        Log.d("Radio","Option D clicked ");
                        options="D";
                        break;

                    case R.id.optionE:
                        // do operations specific to this selection
                        Log.d("Radio","Option E clicked ");
                        options="E";
                        break;
                }


            }


        });


    }



    public void prepareQuestions(int id,boolean nextquestionAvailable)
    {

        Log.d("question","id : "+id);
        if(questionNo==0)
        {
            prevBtn.setVisibility(View.GONE);
        }
        else if(questionNo>= questionSize-1)
        {
            nextBtn.setText("Submit");
            //nextBtn.setVisibility(View.GONE);

        }
        else
        {
            prevBtn.setVisibility(View.VISIBLE);
        }

        Log.d("Questions",dq.toString());
        questionNoTV.setText(""+id+").");
        questionTV.setText(Html.fromHtml(dq.get(id).getQuestion()));
        Log.d("question",dq.get(id).getOptions().toString()+"");
        Log.d("question",dq.get(id).getOptions().get(0).toString()+"");

        Log.d("question",dq.get(id).getOptions().get(1).toString()+"");

        Log.d("question",dq.get(id).getOptions().get(2).toString()+"");

        Log.d("question",dq.get(id).getOptions().get(3).toString()+"");



        if(dq.get(questionNo).getTypeOfQuestion().equals("Single"))
        {



            if(!dq.get(id).getOptions().get(0).toString().trim().equals("") || dq.get(id).getOptions().get(0).toString() !=null)
            {
                Log.d("VISIBLE","questionbtnAis Visible");
                questionbtnA.setVisibility(View.VISIBLE);

                questionbtnA.setText(dq.get(id).getOptions().get(0).toString());
            }

            if(!dq.get(id).getOptions().get(1).toString().trim().equals(""))
            {
                Log.d("VISIBLE","questionbtnB is Visible");
                questionbtnB.setVisibility(View.VISIBLE);

                questionbtnB.setText(dq.get(id).getOptions().get(1).toString());
            }
            Log.d("question",""+(dq.get(id).getOptions().get(2).toString().trim().equals("")));
            Log.d("question",""+(dq.get(id).getOptions().get(2).toString() !=null));

            if(!(dq.get(id).getOptions().get(2).toString().trim().equals("")) )
            {
                Log.d("VISIBLE","questionbtnC is Visible");
                questionbtnC.setVisibility(View.VISIBLE);

                questionbtnC.setText(dq.get(id).getOptions().get(2).toString());
            }

            if(!(dq.get(id).getOptions().get(3).toString().trim().equals("")) )
            {
                Log.d("VISIBLE","questionbtnD is Visible");
                questionbtnD.setVisibility(View.VISIBLE);

                questionbtnD.setText(dq.get(id).getOptions().get(3).toString());
            }

            questionbtnE.setVisibility(View.GONE);
            checkBoxButtonA.setVisibility(View.GONE);
            checkBoxButtonB.setVisibility(View.GONE);
            checkBoxButtonC.setVisibility(View.GONE);
            checkBoxButtonD.setVisibility(View.GONE);
            checkBoxButtonE.setVisibility(View.GONE);
        }
        else
        {
            checkBoxButtonA.setVisibility(View.VISIBLE);
            if(!(dq.get(id).getOptions().get(0).toString().trim().equals("")))
            {
                checkBoxButtonA.setVisibility(View.VISIBLE);

                checkBoxButtonA.setText(dq.get(id).getOptions().get(0).toString());
            }

            if(!(dq.get(id).getOptions().get(1).toString().trim().equals("")))
            {
                checkBoxButtonB.setVisibility(View.VISIBLE);

                checkBoxButtonB.setText(dq.get(id).getOptions().get(1).toString());
            }

            if(!(dq.get(id).getOptions().get(2).toString().trim().equals("")) )
            {
                checkBoxButtonC.setVisibility(View.VISIBLE);

                checkBoxButtonC.setText(dq.get(id).getOptions().get(2).toString());
            }

            if(!(dq.get(id).getOptions().get(3).toString().trim().equals("")))
            {
                checkBoxButtonD.setVisibility(View.VISIBLE);

                checkBoxButtonD.setText(dq.get(id).getOptions().get(3).toString());
            }

            checkBoxButtonE.setVisibility(View.GONE);
            questionbtnA.setVisibility(View.GONE);
            questionbtnB.setVisibility(View.GONE);
            questionbtnC.setVisibility(View.GONE);
            questionbtnD.setVisibility(View.GONE);
            questionbtnE.setVisibility(View.GONE);
        }
        // questionAnswersTV.setText(dq.get(id).getAnswer().toString());
       /* questionTV.setText(dq.getQuestion());
        questionbtnA.setText(dq.getOptions().get(0));
        questionbtnB.setText(dq.getOptions().get(1));
        questionbtnC.setText(dq.getOptions().get(2));
        questionbtnD.setText(dq.getOptions().get(3));
        questionAnswersTV.setText(dq.getAnswer());
*/

    }
}

