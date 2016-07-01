package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bathla on 4/27/2016.
 */
public class TopicsListActivity extends AppCompatActivity {

    List<DatabaseItem> dataBaseItems= null;
    DatabaseHelper  helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        // Find the toolbar view and set as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// ...
// Display icon in the toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new DatabaseHelper(this);


       // loadDataBaseXML();
//        loadXMLTaks loadXMLTaks = new loadXMLTaks();
//        loadXMLTaks.execute(dataBaseItems);


        final ListView list = (ListView) findViewById(R.id.listViews);


        final RowAdapter adapter = new RowAdapter();

         list.setAdapter(adapter);

         list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"Item Clicked : "+id,Toast.LENGTH_SHORT).show();

                /*DatabaseItem db = dataBaseItems.get((int) id);
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                // intent.putExtra("databaseItem",db);

                intent.putExtra("databaseItemPosition",list.getSelectedItem().toString());
                intent.putExtra("databaseItemString",helper.getDesctiption(position));
                startActivity(intent);*/

                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                intent.putExtra("questionNo",position);
                startActivity(intent);
            }
        });


    }


//    class LoadXMLTask extends AsyncTask<>
//    {
//
//    }


    class RowAdapter extends BaseAdapter
    {

    //    List<DatabaseItem> dataBaseItems= null;

        TextView Heading;
       /* Button examples,practice;*/

//        RowAdapter(List<DatabaseItem> dataBaseItems)
//        {
//            this.dataBaseItems=dataBaseItems;
//        }

        @Override
        public int getCount() {

            return helper.getTopics().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            View row = inflater.inflate(R.layout.layout_row, parent, false);
            this.Heading = (TextView) row.findViewById(R.id.list_headings);
           /* this.examples = (Button) row.findViewById(R.id.list_examples);
            this.practice = (Button) row.findViewById(R.id.list_Practice);
*/
            Log.d("ListViewActivity","Topic is  : "+helper.getTopicId(position+1));
           // Heading.setText(this.dataBaseItems.get(position).getTopic());
            Heading.setText(helper.getTopicId(position+1));
           /* examples.setText("Examples");
            practice.setText("practice");
*/
            this.Heading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(), "Clicked On " + position, Toast.LENGTH_SHORT).show();
                    //DatabaseItem db = dataBaseItems.get(position);
                   /* Intent intent = new Intent(getApplicationContext(), DetailedDescriptionActivity.class);
                //    intent.putExtra("databaseItem",db);
                    intent.putExtra("databaseItemPosition",helper.getTopicId(position+1));
                    intent.putExtra("databaseItemString",helper.getDesctiption(position+1));
                    startActivity(intent);*/
                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    intent.putExtra("questionNo",position);
                    //intent.putParcelableArrayListExtra("databasequestion", (ArrayList<? extends Parcelable>) dq);
                    startActivity(intent);
                }
            });

         /*   this.practice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

            this.examples.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(),"Clicked On "+position,Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Clicked On " + position, Toast.LENGTH_SHORT).show();
               //     DatabaseItem db = dataBaseItems.get(position);
               //     List<DataBaseQuestion> dq = db.getPracticeQuestionList();
                   // Log.d("Qustions is ",dq.toString());
                    // Log.d("Hello",dq.get(0).getQuestion());
                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    intent.putExtra("questionNo",position);

                    //intent.putParcelableArrayListExtra("databasequestion", (ArrayList<? extends Parcelable>) dq);
                    startActivity(intent);
                }
            });
*/
            return row;
        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_tutorials, menu);

        return true;

    }

}
