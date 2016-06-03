package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ResulttextView = (TextView) findViewById(R.id.result);
        CommenttextView= (TextView) findViewById(R.id.resultcomment);


        Intent intent = getIntent();

        Map<Integer,String> result = (Map<Integer, String>) intent.getSerializableExtra("result");

        Log.d("Radio" ,result.toString());
       int correctAnswers =  intent.getIntExtra("correct_Answers",0);
        int IncorrectAnswers =  intent.getIntExtra("Incorrect_Answers",0);
        Log.d("Radio","Result Correct Answers : "+correctAnswers);
        Log.d("Radio","Result InCorrect Answers : "+IncorrectAnswers);

        ResulttextView.setText("Correct Answers Are :"+correctAnswers+"/"+(correctAnswers+IncorrectAnswers));
        if(correctAnswers>IncorrectAnswers)
        {
            CommenttextView.setText("Good Work");

        }
        else
        {
            CommenttextView.setText("Try Again");
        }
    }
}
