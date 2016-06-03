package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by bathla on 4/27/2016.
 */
public class DetailedDescriptionActivity extends Activity
{

    TextView DescriptionTitle =null;
    TextView DescriptionDetail =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_description);
        DescriptionTitle= (TextView) findViewById(R.id.descriptionTitle);
        DescriptionDetail = (TextView) findViewById(R.id.detailedDescription);


        Intent intent = getIntent();
       // DatabaseItem db =intent.getParcelableExtra("databaseItem");

        String descriptionPosition = intent.getStringExtra("databaseItemPosition");
        String topicName = intent.getStringExtra("databaseItemString");

        // Log.d("DetailedDesciption",db.toString());
        DatabaseHelper hp = new DatabaseHelper(this);
        int i=1;

        Log.d("DESCRIPTIOn",""+topicName);
       // DescriptionDetail.setText(db.getDescription());
        DescriptionDetail.setText(topicName);
        //DescriptionTitle.setText(db.getTopic());
        DescriptionTitle.setText(descriptionPosition);

    }
}
