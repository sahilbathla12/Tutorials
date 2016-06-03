package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bathla on 5/2/2016.
 */
public class QuestionActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_question);

        TextView Question= (TextView) findViewById(R.id.question);
        Intent intent = getIntent();
        List<DataBaseQuestion> dq =intent.getParcelableArrayListExtra("question");
        Log.d("Hello",dq.toString());
        Question.setText(dq.get(0).getQuestion());


    }
}
