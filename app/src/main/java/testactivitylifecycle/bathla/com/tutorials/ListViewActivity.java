package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by bathla on 4/25/2016.
 */
public class ListViewActivity extends Activity {

    String[] Topics =null;
    String[] Descriptions =null;
    String[] Examples =null;
    String[] Practice_Questions =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);


        Topics =getResources().getStringArray(R.array.topics);
        Descriptions =getResources().getStringArray(R.array.description);
        Examples =getResources().getStringArray(R.array.Examples);
        Practice_Questions =getResources().getStringArray(R.array.PracticeQuestion);

        ListView list = (ListView) findViewById(R.id.listViews);
        // list.setAdapter();
       RowAdapter adapter = new RowAdapter(this.Topics,this.Descriptions,this.Examples,this.Practice_Questions);
        list.setAdapter(adapter);
    }

    class RowAdapter extends BaseAdapter
    {

        String[] list_Topics=null;
        String[] list_Descriptions=null;
        String[] list_Examples =null;
        String[] list_Practice_Questions =null;
        TextView Heading;
        Button examples,practice;

        RowAdapter(String[] topics,String[] descriptions,String[] examples,String[] practice_Questions)
        {
            this.list_Descriptions=descriptions;
            this.list_Examples=examples;
            this.list_Topics=topics;
            this.list_Practice_Questions=practice_Questions;
        }

        @Override
        public int getCount() {

            return this.list_Topics.length;
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
          /*  this.examples = (Button) row.findViewById(R.id.list_examples);
            this.practice = (Button) row.findViewById(R.id.list_Practice);
*/
            Log.d("ListViewActivity","Topic is  : "+this.list_Topics[position]);
            Heading.setText(this.list_Topics[position]);
           /* examples.setText("EXAMPLES");
            practice.setText("Practice");
*/
            this.Heading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(), "Clicked On " + position, Toast.LENGTH_LONG).show();
                }
            });

           /* this.practice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(),"Clicked On "+position,Toast.LENGTH_LONG).show();
                }
            });
            this.examples.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getApplicationContext(),"Clicked On "+position,Toast.LENGTH_LONG).show();
                }
            });*/

            return row;
        }



    }


}
