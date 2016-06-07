package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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
    LinearLayout checkBoxLinearLayout;

    TextView questionAnswersTV;
    Button answerBtn;
    // Acc to list start from 0
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


        checkBoxLinearLayout = (LinearLayout) findViewById(R.id.linearLayout_checkBoxes);
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

        //ListView Clicked position
        final int position =intent.getIntExtra("questionNo",1);
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



        checkBoxLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(questionNo < questionSize-1)
                {
                    nextBtn.setText("Next");
                    // nextBtn.setVisibility(View.GONE);
                }
                prepareQuestions(--questionNo,false);
                questionAnswersTV.setVisibility(View.GONE);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   prepareQuestions(++questionNo,false);

                options="";
                Log.d("questionNo","question is : "+questionNo+" question size"+questionSize);

                questionTV.setFocusable(true);
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
                    options+="A";
                }

                if(checkBoxButtonB.isChecked())
                {
                    checkBoxButtonB.toggle();
                    options+="B";
                }
                if(checkBoxButtonC.isChecked())
                {
                    checkBoxButtonC.toggle();
                    options+="C";
                }
                if(checkBoxButtonD.isChecked())
                {
                    checkBoxButtonD.toggle();
                    options+="D";
                }
                if(checkBoxButtonE.isChecked())
                {
                    checkBoxButtonE.toggle();
                    options+="E";
                }



                    Log.d("options","options is :"+options);

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
                    intent.putExtra("topicId",position);
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

        Log.d("QuesionNo","Question no : "+id);

      //  Log.d("question No is ","id : "+id);
        questionTV.setFocusable(true);
        if(id==0)
        {
            prevBtn.setVisibility(View.GONE);
        }
        else if(id==questionSize-1)
        {
            nextBtn.setText("Submit");
            //nextBtn.setVisibility(View.GONE);
            prevBtn.setVisibility(View.VISIBLE);
        }
        else
        {
            prevBtn.setVisibility(View.VISIBLE);
        }


        Log.d("Questions",dq.toString());
        questionNoTV.setText(""+(id+1)+".");

        questionTV.setText(Html.fromHtml(dq.get(id).getQuestion()));

        Log.d("question",dq.get(id).getOptions().toString()+"");

        Log.d("question",dq.get(id).getOptions().get(0).toString()+"");

        Log.d("question",dq.get(id).getOptions().get(1).toString()+"");

        Log.d("question",dq.get(id).getOptions().get(2).toString()+"");

        Log.d("question",dq.get(id).getOptions().get(3).toString()+"");

        Log.d("question",dq.get(questionNo).getOptions().get(4).toString()+"");




        if(dq.get(id).getTypeOfQuestion().equals("Single"))
        {



            if(!dq.get(id).getOptions().get(0).toString().trim().equals("") || dq.get(id).getOptions().get(0).toString() !=null)
            {
                Log.d("VISIBLE","questionbtnAis Visible");
                questionbtnA.setVisibility(View.VISIBLE);

                questionbtnA.setText(Html.fromHtml(dq.get(id).getOptions().get(0).toString()));
                questionbtnA.setMovementMethod(new ScrollingMovementMethod());
            }
            else
            {
                Log.d("INVISIBLE","questionbtn A is InVisible");
            }


            if(!dq.get(id).getOptions().get(1).toString().trim().equals(""))
            {
                Log.d("VISIBLE","questionbtnB is Visible");
                questionbtnB.setVisibility(View.VISIBLE);

                questionbtnB.setText(Html.fromHtml(dq.get(id).getOptions().get(1).toString().trim()));
            }
            Log.d("question",""+(dq.get(id).getOptions().get(2).toString().trim().equals("")));
            Log.d("question",""+(dq.get(id).getOptions().get(2).toString() !=null));

            if(!(dq.get(id).getOptions().get(2).toString().trim().equals("")) )
            {
                Log.d("VISIBLE","questionbtnC is Visible");
                questionbtnC.setVisibility(View.VISIBLE);

                questionbtnC.setText(Html.fromHtml(dq.get(id).getOptions().get(2).toString().trim()));
            }

            if(!(dq.get(id).getOptions().get(3).toString().trim().equals("")) )
            {
                Log.d("VISIBLE","questionbtnD is Visible");
                questionbtnD.setVisibility(View.VISIBLE);

                questionbtnD.setText(Html.fromHtml(dq.get(id).getOptions().get(3).toString().trim()));
            }
            if(!(dq.get(questionNo).getOptions().get(4).toString().trim().equals("")) )
            {
                Log.d("VISIBLE","questionbtnE is Visible");
                questionbtnE.setVisibility(View.VISIBLE);

                questionbtnE.setText(Html.fromHtml(dq.get(id).getOptions().get(4).toString().trim()));
            }

           // questionbtnE.setVisibility(View.GONE);
            checkBoxButtonA.setVisibility(View.GONE);
            checkBoxButtonB.setVisibility(View.GONE);
            checkBoxButtonC.setVisibility(View.GONE);
            checkBoxButtonD.setVisibility(View.GONE);
            checkBoxButtonE.setVisibility(View.GONE);
        }
        else
        {
          //  checkBoxButtonA.setVisibility(View.VISIBLE);
            if(!(dq.get(id).getOptions().get(0).toString().trim().equals("")))
            {
                checkBoxButtonA.setVisibility(View.VISIBLE);

                checkBoxButtonA.setText(Html.fromHtml(dq.get(id).getOptions().get(0).toString().trim()));
            }

            if(!(dq.get(id).getOptions().get(1).toString().trim().equals("")))
            {
                checkBoxButtonB.setVisibility(View.VISIBLE);

                checkBoxButtonB.setText(Html.fromHtml(dq.get(id).getOptions().get(1).toString().trim()));
            }

            if(!(dq.get(id).getOptions().get(2).toString().trim().equals("")) )
            {
                checkBoxButtonC.setVisibility(View.VISIBLE);

                checkBoxButtonC.setText(Html.fromHtml(dq.get(id).getOptions().get(2).toString().trim()));
            }

            if(!(dq.get(questionNo).getOptions().get(3).toString().trim().equals("")))
            {
                checkBoxButtonD.setVisibility(View.VISIBLE);

                checkBoxButtonD.setText(Html.fromHtml(dq.get(id).getOptions().get(3).toString().trim()));
            }
            if(!(dq.get(id).getOptions().get(4).toString().trim().equals("")))
            {
                checkBoxButtonE.setVisibility(View.VISIBLE);

                checkBoxButtonE.setText(Html.fromHtml(dq.get(id).getOptions().get(4).toString().trim()));
            }
           // checkBoxButtonE.setVisibility(View.GONE);
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

    /*public void checkBoxClicked(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId())
        {
            case R.id.checkBoxOptionA:
                // do operations specific to this selection
                Log.d("Checked","Option A clicked ");
                options+="A";
                break;

            case R.id.checkBoxOptionB:
                // do operations specific to this selection
                Log.d("Checked","Option B clicked ");
                options+="B";
                break;

            case R.id.checkBoxOptionC:
                // do operations specific to this selection
                Log.d("Checked","Option C clicked ");
                options+="C";
                break;
            case R.id.checkBoxOptionD:
                // do operations specific to this selection
                Log.d("Checked","Option D clicked ");
                options+="D";
                break;

            case R.id.checkBoxOptionE:
                // do operations specific to this selection
                Log.d("Checked","Option E clicked ");
                options+="E";
                break;
        }
        Log.d("Options","options : "+options);

    }*/
}

