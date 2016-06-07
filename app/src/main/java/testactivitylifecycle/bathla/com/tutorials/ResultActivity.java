package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bathla on 5/29/2016.
 */
public class ResultActivity extends Activity{

    TextView ResulttextView = null;
    TextView CommenttextView = null;
    DatabaseHelper helper =null;
    Button resultButton = null;
    int topicId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ResulttextView = (TextView) findViewById(R.id.result);
        CommenttextView= (TextView) findViewById(R.id.resultcomment);
        resultButton = (Button) findViewById(R.id.showExplanation);
        helper = new DatabaseHelper(this);

        final Intent intent = getIntent();

        Map<Integer,String> result = (Map<Integer, String>) intent.getSerializableExtra("result");

        Log.d("Radio" ,result.toString());
       int correctAnswers =  intent.getIntExtra("correct_Answers",0);
        int IncorrectAnswers =  intent.getIntExtra("Incorrect_Answers",0);
        topicId = intent.getIntExtra("topicId",0);
        Map<Integer,String> answersHashMap = (Map<Integer, String>) intent.getSerializableExtra("result");


        Log.d("Radio","Result Correct Answers : "+correctAnswers);
        Log.d("Radio","Result InCorrect Answers : "+IncorrectAnswers);
        Log.d("Radio","Answers : "+answersHashMap.toString());
        Log.d("Radio","Topic Id : "+topicId);

        Map<Integer,String> resultanswersHashMap =  helper.getAnswers((topicId+1));
        correctAnswers =0;
        IncorrectAnswers =0;
        for (Map.Entry entry : answersHashMap.entrySet())
        {
                Log.d("key", ""+entry.getKey());
            //Log.d("result map ", ""+resultanswersHashMap.get(entry.getKey()));
           // Log.d("answer map ", ""+entry.getValue());
            String resultStr=(String) resultanswersHashMap.get(entry.getKey());
            String answer=(String) answersHashMap.get(entry.getKey());
            Log.d("result map STR", ""+resultStr);
            Log.d("answer map STR", ""+answer);

            Log.d("match STR", ""+(resultStr==(answer)));


                if(resultStr.equals(answer))
                {
                    Log.d("correct answer map STR", ""+correctAnswers);
                    ++correctAnswers;
                }
                else
                {

                    IncorrectAnswers++;
                }
                Log.d("Value", ""+entry.getValue());
        }

        ResulttextView.setText("Correct Answers Are :"+correctAnswers+"/"+(correctAnswers+IncorrectAnswers));
        if(correctAnswers>IncorrectAnswers)
        {
            CommenttextView.setText("Good Work");

        }
        else
        {
            CommenttextView.setText("Try Again");
        }

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getApplicationContext(), ResultAnswer.class);
                intent1.putExtra("topicId",topicId);
                startActivity(intent1);
            }
        });
    }
}
