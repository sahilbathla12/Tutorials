package testactivitylifecycle.bathla.com.tutorials;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bathla on 5/22/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    //Log Cat
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "tutorialsManager";

    // Table Names
    private static final String TABLE_TOPICS = "TOPICS_TABLE";
    private static final String TABLE_QUESTIONS = "QUESTIONS_TABLE";
    private static final String TABLE_DESCRIPTION= "DESCRIPTION_TABLE";

    // Common column names
    private static final String KEY_TOPICS_ID = "id";
    private static final String KEY_TOPICS_NAME = "name";
    private static final String KEY_TOPICS_DESCRIPTION_ID = "description_id";
    private static final String KEY_TOPICS_QUESTIONS_ID = "example_id";
    // private static final String KEY_TOPICS_ID = "id";

    private static final String KEY_QUESTIONS_ID= "id";
    private static final String KEY_QUESTIONS_QUESTION= "question";
    private static final String KEY_QUESTIONS_OPTION_A= "optiona";
    private static final String KEY_QUESTIONS_OPTION_B= "optionb";
    private static final String KEY_QUESTIONS_OPTION_C= "optionc";
    private static final String KEY_QUESTIONS_OPTION_D= "optiond";
    private static final String KEY_QUESTIONS_OPTION_E= "optione";
    private static final String KEY_QUESTIONS_ANSWER= "answer";
    private static final String KEY_QUESTIONS_TOPIC_ID= "topicid";
    private static final String KEY_QUESTIONS_TYPE= "type";
    private static final String KEY_QUESTIONS_EXPLANATION ="explanation";

    private static final String KEY_DESCRIPTION_ID= "id";
    private static final String KEY_DESCRIPTION_DESCRIPTION= "description";
    private static final String KEY_DESCRIPTION_TOPIC_ID= "topic_id";
    private static final String KEY_CREATED_AT = "created_at";


    private static final String CREATE_TABLE_TOPIC = "CREATE TABLE "
            + TABLE_TOPICS + "(" + KEY_TOPICS_ID + " INTEGER PRIMARY KEY," + KEY_TOPICS_NAME
            + " TEXT"  +  ")";


    private static final String CREATE_TABLE_DESCRIPTION = "CREATE TABLE "
            + TABLE_DESCRIPTION + "(" + KEY_DESCRIPTION_ID + " INTEGER PRIMARY KEY," + KEY_DESCRIPTION_DESCRIPTION
            + " TEXT,"  + KEY_DESCRIPTION_TOPIC_ID
            + " INTEGER"  +")";


    private static final String CREATE_TABLE_QUESTION = "CREATE TABLE "
            + TABLE_QUESTIONS + "(" + KEY_QUESTIONS_ID + " INTEGER PRIMARY KEY," + KEY_QUESTIONS_QUESTION
            + " TEXT,"   + KEY_QUESTIONS_ANSWER
            + " TEXT,"  + KEY_QUESTIONS_EXPLANATION
            + " TEXT,"  + KEY_QUESTIONS_TYPE
            + " TEXT,"  + KEY_QUESTIONS_OPTION_A
            + " TEXT,"  + KEY_QUESTIONS_OPTION_B
            + " TEXT,"  + KEY_QUESTIONS_OPTION_C
            + " TEXT,"  + KEY_QUESTIONS_OPTION_D
            + " TEXT,"  + KEY_QUESTIONS_OPTION_E
            + " TEXT," + KEY_QUESTIONS_TOPIC_ID
            + " INTEGER"  +")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_TOPIC);
        db.execSQL(CREATE_TABLE_QUESTION);
        db.execSQL(CREATE_TABLE_DESCRIPTION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);

        // create new tables
        onCreate(db);
    }



    /*
    * Inserting ROW
    */
    public void insertTopics(int id, String topicName) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
       // values.put(KEY_TOPICS_ID, id);
        values.put(KEY_TOPICS_NAME, topicName);
        // insert row
        long topic_id = db.insert(TABLE_TOPICS, null, values);

        db.close();;
        Log.d("insertTopic","TOPICS INSERTED : id "+id+"  TOPIC NAME : "+topicName);
    }

//    private static final String CREATE_TABLE_DESCRIPTION = "CREATE TABLE "
//            + TABLE_DESCRIPTION + "(" + KEY_DESCRIPTION_ID + " INTEGER PRIMARY KEY," + KEY_DESCRIPTION_DESCRIPTION
//            + " TEXT,"  + KEY_DESCRIPTION_TOPIC_ID
//            + " INTEGER,"  +")";


    public void insertDescription(int description_id, String description, int topic_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
       // values.put(KEY_DESCRIPTION_ID, description_id);
        values.put(KEY_DESCRIPTION_DESCRIPTION, description);
        values.put(KEY_DESCRIPTION_TOPIC_ID, topic_id);

        // insert row
        long descriptionID = db.insert(TABLE_DESCRIPTION, null, values);
        Log.d("insertDescription","TOPICS INSERTED : id "+topic_id+"  TOPIC NAME : "+description);
        db.close();;

    }



/*

    private static final String CREATE_TABLE_QUESTION = "CREATE TABLE "
            + TABLE_QUESTIONS + "(" + KEY_QUESTIONS_ID + " INTEGER PRIMARY KEY," + KEY_QUESTIONS_QUESTION
            + " TEXT,"   + KEY_QUESTIONS_ANSWER
            + " TEXT,"  + KEY_QUESTIONS_EXPLANATION
            + " TEXT,"  + KEY_QUESTIONS_TYPE
            + " TEXT,"  + KEY_QUESTIONS_OPTION_A
            + " TEXT,"  + KEY_QUESTIONS_OPTION_B
            + " TEXT,"  + KEY_QUESTIONS_OPTION_C
            + " TEXT,"  + KEY_QUESTIONS_OPTION_D
            + " TEXT," + KEY_QUESTIONS_TOPIC_ID
            + " INTEGER,"  +")";
*/

    static int test=0;

    public void insertQuestions(int question_id, String question, String answer,String explanation, String question_type,
                                String optionA, String optionB, String optionC, String optionD, String optionE, int topic_id ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_QUESTIONS_ID,++test);
        values.put(KEY_QUESTIONS_QUESTION, question);
        values.put(KEY_QUESTIONS_ANSWER, answer);
        values.put(KEY_QUESTIONS_EXPLANATION, explanation);
        values.put(KEY_QUESTIONS_TYPE, question_type);
        values.put(KEY_QUESTIONS_OPTION_A, optionA);
        values.put(KEY_QUESTIONS_OPTION_B, optionB);
        values.put(KEY_QUESTIONS_OPTION_C, optionC);
        values.put(KEY_QUESTIONS_OPTION_D, optionD);
        values.put(KEY_QUESTIONS_OPTION_E, optionE);
        values.put(KEY_QUESTIONS_TOPIC_ID, topic_id);

        // insert row
        long questionID = db.insert(TABLE_QUESTIONS, null, values);

        db.close();;

    }

    public Map<Integer,String> getTopics()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TOPICS ;

      //  Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

//        if (c != null)
//            c.moveToFirst();

        Map<Integer,String> topicsName = new LinkedHashMap<>();

        Log.d("TOPICS Sahil","Cusrsor Moved to First");

        if (c.moveToFirst()) {
            do {

                topicsName.put(c.getInt((c.getColumnIndex(KEY_TOPICS_ID))),c.getString((c.getColumnIndex(KEY_TOPICS_NAME))));

                Log.d("TOPICS Sahil",""+c.getInt(c.getColumnIndex(KEY_TOPICS_ID))+" "+c.getString(c.getColumnIndex(KEY_TOPICS_NAME)));

            } while (c.moveToNext());
        }
        Log.d("TOPICS", topicsName.toString());
        return topicsName ;
    }

    public String getTopicId(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TOPICS +" where "+KEY_TOPICS_ID+" = "+id;;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        String topicsname = "";
        if (c.moveToFirst()) {
            do {

                topicsname= c.getString((c.getColumnIndex(KEY_TOPICS_NAME)));


            } while (c.moveToNext());
        }
        return topicsname ;
    }
    public Map<Integer,String> getQuestion()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TOPICS ;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Map<Integer,String> topicsMap = new LinkedHashMap<>();
        if (c.moveToFirst()) {
            do {

                topicsMap.put(c.getInt((c.getColumnIndex(KEY_TOPICS_ID))),c.getString((c.getColumnIndex(KEY_TOPICS_NAME))));


            } while (c.moveToNext());
        }
        return topicsMap ;
    }

    public Map<Integer,String>  getDescription()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_DESCRIPTION ;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Map<Integer,String> descriptionMap = new LinkedHashMap<>();
        if (c.moveToFirst()) {
            do {

                descriptionMap.put(c.getInt((c.getColumnIndex(KEY_DESCRIPTION_TOPIC_ID))),c.getString((c.getColumnIndex(KEY_DESCRIPTION_DESCRIPTION))));

            } while (c.moveToNext());
        }
        return descriptionMap ;
    }

    public String  getDesctiption(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_DESCRIPTION +" where "+KEY_DESCRIPTION_ID+" = "+id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);


    String description="";
        Map<Integer,String> descriptionMap = new LinkedHashMap<>();
        if (c.moveToFirst()) {
            do {

                descriptionMap.put(c.getInt((c.getColumnIndex(KEY_DESCRIPTION_TOPIC_ID))),c.getString((c.getColumnIndex(KEY_DESCRIPTION_DESCRIPTION))));

                description=c.getString((c.getColumnIndex(KEY_DESCRIPTION_DESCRIPTION)));

            } while (c.moveToNext());
        }
        Log.d("Description",descriptionMap.toString());
        return description ;
    }

    public List<DataBaseQuestion>  getQuestion(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        List<DataBaseQuestion> dq = new ArrayList<>();
        //String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS +" where "+KEY_TOPICS_ID+" = "+id;
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS +" where "+KEY_QUESTIONS_TOPIC_ID+" = "+id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);


        String description="";
       // Map<Integer,String> descriptionMap = new LinkedHashMap<>();
        if (c.moveToFirst()) {
            do {

                DataBaseQuestion question = new DataBaseQuestion();
                question.setQuestionNo(c.getInt(c.getColumnIndex(KEY_QUESTIONS_ID))+"");
                question.setQuestion(c.getString(c.getColumnIndex(KEY_QUESTIONS_QUESTION)));
                question.setAnswer(c.getString(c.getColumnIndex(KEY_QUESTIONS_ANSWER)));
                question.setExplanation(c.getString(c.getColumnIndex(KEY_QUESTIONS_EXPLANATION)));
                question.setTypeOfQuestion(c.getString(c.getColumnIndex(KEY_QUESTIONS_TYPE)));
                Log.d("question", "Type of Question :"+c.getString(c.getColumnIndex(KEY_QUESTIONS_TYPE)));
                List optionList= new ArrayList();
                optionList.add(c.getString(c.getColumnIndex(KEY_QUESTIONS_OPTION_A)));
                optionList.add(c.getString(c.getColumnIndex(KEY_QUESTIONS_OPTION_B)));
                optionList.add(c.getString(c.getColumnIndex(KEY_QUESTIONS_OPTION_C)));
                optionList.add(c.getString(c.getColumnIndex(KEY_QUESTIONS_OPTION_D)));
                optionList.add(c.getString(c.getColumnIndex(KEY_QUESTIONS_OPTION_E)));
                question.setOptions(optionList);

                dq.add(question);
             //   descriptionMap.put(c.getInt((c.getColumnIndex(KEY_DESCRIPTION_TOPIC_ID))),c.getString((c.getColumnIndex(KEY_DESCRIPTION_DESCRIPTION))));

               // description=c.getString((c.getColumnIndex(KEY_DESCRIPTION_DESCRIPTION)));

            } while (c.moveToNext());
        }
        return dq ;
    }

    public int getTotalQuestionCountForOneTopic(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

       // List<DataBaseQuestion> dq = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTIONS +" where "+KEY_QUESTIONS_TOPIC_ID+" = "+id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        int questionCount=0;
        String description="";
        // Map<Integer,String> descriptionMap = new LinkedHashMap<>();
        if (c.moveToFirst()) {
            do {
              //  questionCount
                //   descriptionMap.put(c.getInt((c.getColumnIndex(KEY_DESCRIPTION_TOPIC_ID))),c.getString((c.getColumnIndex(KEY_DESCRIPTION_DESCRIPTION))));

                // description=c.getString((c.getColumnIndex(KEY_DESCRIPTION_DESCRIPTION)));

            } while (c.moveToNext());
        }
        Log.d("questionSize "," count  is :"+c.getCount());
        return c.getCount();
    }
    public Map<Integer,String> getAnswers(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        // List<DataBaseQuestion> dq = new ArrayList<>();
        String selectQuery = "SELECT "+KEY_QUESTIONS_ID+","+KEY_QUESTIONS_ANSWER +"   FROM " + TABLE_QUESTIONS +" where "+KEY_QUESTIONS_TOPIC_ID+" = "+id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Map<Integer,String> answersMap = new LinkedHashMap<>();
        int questionCount=0;
        String description="";
        // Map<Integer,String> descriptionMap = new LinkedHashMap<>();
        if (c.moveToFirst()) {
            do {

                Log.d("Answers From DataBAse", "Question Id : "+c.getInt(c.getColumnIndex(KEY_QUESTIONS_ID))+"Answers : "+c.getString(c.getColumnIndex(KEY_QUESTIONS_ANSWER)));
                answersMap.put(questionCount++,c.getString(c.getColumnIndex(KEY_QUESTIONS_ANSWER)));
                //  questionCount
                //   descriptionMap.put(c.getInt((c.getColumnIndex(KEY_DESCRIPTION_TOPIC_ID))),c.getString((c.getColumnIndex(KEY_DESCRIPTION_DESCRIPTION))));

                // description=c.getString((c.getColumnIndex(KEY_DESCRIPTION_DESCRIPTION)));

            } while (c.moveToNext());
        }
        Log.d("questionSize "," count  is :"+c.getCount());
        return answersMap;
    }
}
