package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * Created by bathla on 4/25/2016.
 */

public class SpashScreen extends Activity {


    List<DatabaseItem> dataBaseItems= null;
    DatabaseHelper  helper;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    private GoogleApiClient client;
    public static SharedPreferences sh;
    public static SharedPreferences.Editor editor;
    boolean startActivity= true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_splash_screen);

        Thread th = new Thread(new SplashScreenThread());
        th.start();




        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    class SplashScreenThread implements Runnable {
        @Override


        public void run() {

            sh = getSharedPreferences("myprefe", 0);
            editor = sh.edit();

            String  loadXML=null;
    //        Log.d("SAhil",sh.getString("LoadXML",null));
            loadXML = sh.getString("LoadXML",null);
          //  if(loadXML!=null)
          // Toast.makeText(getApplicationContext(), loadXML, Toast.LENGTH_SHORT).show();
           // LoadXMLTasks loadXMLTaks = new LoadXMLTasks();
           // loadXMLTaks.execute(dataBaseItems);

            if((loadXML == null))
            {
                //Toast.makeText(getApplicationContext(),"Load",Toast.LENGTH_SHORT).show();;
                LoadXMLTasks  loadXMLTaks = new LoadXMLTasks();
                loadXMLTaks.execute(dataBaseItems);
                editor.putString("LoadXML","Loaded");
                editor.commit();

            }
            else
            {
              //  Toast.makeText(getApplicationContext(),"Already Load",Toast.LENGTH_SHORT).show();;

            }

            if(loadXML != null) {
                //Intent intent = new Intent(getApplicationContext(),TopicsListActivity.class);
                Intent intent = new Intent(getApplicationContext(), TopicsListActivity.class);

                startActivity(intent);
                finish();
            }

            try {
                Log.d("SplashScreenThread", "We are in Splash Screen Thread");
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }
    }




    class LoadXMLTasks extends AsyncTask<Object, Object, Object> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object aVoid) {
            super.onPostExecute(aVoid);

            if(startActivity= true) {
                //Intent intent = new Intent(getApplicationContext(),TopicsListActivity.class);
                Intent intent = new Intent(getApplicationContext(), TopicsListActivity.class);

                startActivity(intent);
                finish();
            }
        }

        @Override
        protected Object doInBackground(Object... params)
        {
            loadDataBaseXML();
            return  null;
        }


        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }
    }
    static  int a=0;
    public void loadDataBaseXML()
    {
        startActivity= false;
        try {

            InputStream ins = getResources().openRawResource(R.raw.database);
            dataBaseItems = DatabaseXMLParser.ReadFile(this,ins);

            helper = new DatabaseHelper(getApplicationContext());

            for (int i=0;i<dataBaseItems.size();i++)
            {
                int questions=0;
                helper.insertTopics((i+1),dataBaseItems.get(i).getTopic());
                helper.insertDescription((i+1),dataBaseItems.get(i).getDescription(),(i+1));
                Log.d("loadXMLQUESTION","TOPICS Created");
                Log.d("loadXMLQUESTION","DESCRIPTION Created");
                for(int j=0;j<dataBaseItems.get(i).getPracticeQuestionList().size();j++)
                {
                    Log.d("loadXMLQUESTION",
                            ++a+ dataBaseItems.get(i).getPracticeQuestionList().get(j).getQuestion()+
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getAnswer()+
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getExplanation()+
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getTypeOfQuestion()+
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getOptions().get(0).toString()+
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getOptions().get(1).toString()+
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getOptions().get(2).toString()+
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getOptions().get(3).toString()+
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getOptions().get(4).toString()+
                            (i+1));
                    helper.insertQuestions((i+1),dataBaseItems.get(i).getPracticeQuestionList().get(j).getQuestion(),
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getAnswer(),
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getExplanation(),
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getTypeOfQuestion(),
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getOptions().get(0).toString(),
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getOptions().get(1).toString(),
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getOptions().get(2).toString(),
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getOptions().get(3).toString(),
                            dataBaseItems.get(i).getPracticeQuestionList().get(j).getOptions().get(4).toString(),
                            (i+1)
                    );
                }

            }
            helper.getTopics();
            helper.getDescription();
            startActivity= true;
            Log.d("TOPIC","TOPICS TABLE CREATED");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
