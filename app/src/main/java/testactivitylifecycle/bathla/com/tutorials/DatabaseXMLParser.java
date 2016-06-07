package testactivitylifecycle.bathla.com.tutorials;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bathla on 4/26/2016.
 */
public class DatabaseXMLParser {

    static final String KEY_ITEM = "item";
    static final String KEY_TOPIC_NAME = "topicName";
    static final String KEY_DESCRIPTION = "Description";
    static final String KEY_EXAMPLES = "Examples";
    static final String KEY_EXAMPLE = "Example";
    static final String KEY_PRACTICEQUESTIONS = "PracticeQuestions";
    static final String KEY_PRACTICEQUESTION = "PraacticeQuestion";
    static final String KEY_QUESTION_No= "QuestionNo";
    static final String KEY_QUESTION= "Question";
    static final String KEY_OPTIONS= "options";
    static final String KEY_OPTION= "option";
    static final String KEY_TYPEOFQUESTION= "TypeofQuestion";
    static final String KEY_ANSWER= "Answer";
    static final String KEY_EXPLANATION= "Explanation";
    static final String KEY_TYPE_OF_QUESTION= "TypeofQuestion";





    private  static String myTag="DataBaseXMLPullParser";
    public static  List<DatabaseItem> ReadFile(Context ctx, InputStream fisinput)
    {

        List<DatabaseItem> databaseItemsList = new ArrayList<>();
        //temp holder for current DatabaseItem while parsing


        DatabaseItem currItem= null;
        currItem = new DatabaseItem();

        // For Practice Question
        DataBaseQuestion currDataBaseQuestion=null;


        // temp holder for current text value while parsing
        String curText = "";



        try
        {

             XmlPullParserFactory xmlPullParserFactory= XmlPullParserFactory.newInstance();
             XmlPullParser xmlPullParser=xmlPullParserFactory.newPullParser();
            Log.d(myTag,"xmlPullParser Created");

            // OPen InputStream and Reader of our File
            // Open up InputStream and Reader of our file.
           // FileInputStream fis = ctx.openFileInput("database.xml");
            Log.d(myTag,"Reading File");

            BufferedReader reader = new BufferedReader(new InputStreamReader(fisinput));
            Log.d(myTag,"Buffer Reader File");

            //point parser to the file
            xmlPullParser.setInput(reader);

            Log.d(myTag, "XML PULLPARSER Input Set");

            //getIntial event Type
            int eventType=   xmlPullParser.getEventType();


            while(eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xmlPullParser.getName();
                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                            // If we are starting a new <site> block we need
                            //a new StackSite object to represent it
                            currItem = new DatabaseItem();
                        }
                       else if (tagname.equalsIgnoreCase(KEY_EXAMPLES)) {
                            // If we are starting a new <site> block we need
                            //a new StackSite object to represent it
                                currItem.exampleList= new ArrayList<>();
                        }
                        else if (tagname.equalsIgnoreCase(KEY_PRACTICEQUESTIONS)) {
                            // If we are starting a new <site> block we need
                            //a new StackSite object to represent it
                            currItem.practiceQuestionList= new ArrayList<>();
                        }
                        else if (tagname.equalsIgnoreCase(KEY_PRACTICEQUESTION)) {
                            // If we are starting a new <site> block we need
                            //a new StackSite object to represent it
                            currDataBaseQuestion = new DataBaseQuestion();
                        }
                        else if (tagname.equalsIgnoreCase(KEY_OPTIONS)) {
                            // If we are starting a new <site> block we need
                            //a new StackSite object to represent it
                            currDataBaseQuestion.optionList = new ArrayList<>();
                        }
                        else if (tagname.equalsIgnoreCase(KEY_ANSWER)) {
                            // If we are starting a new <site> block we need
                            //a new StackSite object to represent it

                        }


                        break;


                   case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        curText = xmlPullParser.getText();
                  //      Log.d(myTag,"TAG TEXT IS :  "+curText );
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_ITEM)) {
                            // if </site> then we are done with current Site
                            // add it to the list.
                            databaseItemsList.add(currItem);
                     //       Log.d(myTag, "Item  is " + curText);
                        } else if (tagname.equalsIgnoreCase(KEY_DESCRIPTION)) {
                            // if </name> use setName() on curSite
                            currItem.setDescription(curText);
                       //     Log.d(myTag, "Desscription  is " + curText);
                        } else if (tagname.equalsIgnoreCase(KEY_TOPIC_NAME)) {
                            // if </link> use setLink() on curSite
                            currItem.setTopic(curText);
                       //     Log.d(myTag, "Topic NAme is "+curText);
                        } else if (tagname.equalsIgnoreCase(KEY_EXAMPLES)) {
                            // if </about> use setAbout() on curSite

               //          Log.d(myTag,"EXAMPLES : "+xmlPullParser.getAttributeName(0));
                        }
                        else if (tagname.equalsIgnoreCase(KEY_EXAMPLE)) {
                            // if </about> use setAbout() on curSite
                                currItem.exampleList.add(curText);
                          //  Log.d(myTag,"EXAMPLES : "+xmlPullParser.getAttributeName(0));
                        }
                        else if (tagname.equalsIgnoreCase(KEY_PRACTICEQUESTIONS)) {
                                currItem.practiceQuestionList.add(currDataBaseQuestion);
                        }
                        else if (tagname.equalsIgnoreCase(KEY_PRACTICEQUESTION)) {
                       //     Log.d(myTag,"KEY_PRACTICEQUESTION : "+curText);
                            currItem.getPracticeQuestionList().add(currDataBaseQuestion);
                        }
                        else if (tagname.equalsIgnoreCase(KEY_QUESTION)) {
                       //     Log.d(myTag,"KEY_QUESTION : "+curText);
                            currDataBaseQuestion.setQuestion(curText);

                        }else if (tagname.equalsIgnoreCase(KEY_QUESTION_No)) {
                       //     Log.d(myTag,"KEY_QUESTION_No : "+curText);
                            currDataBaseQuestion.setQuestionNo(curText);
                        }
                        else if (tagname.equalsIgnoreCase(KEY_QUESTION_No)) {
                //            Log.d(myTag,"KEY_QUESTION_No : "+curText);
                            currDataBaseQuestion.setQuestionNo(curText);
                        }
                        else if (tagname.equalsIgnoreCase(KEY_OPTION)) {
              //              Log.d(myTag,"KEY_OPTION : "+curText);
                            currDataBaseQuestion.optionList.add(curText);
                        }
                        else if (tagname.equalsIgnoreCase(KEY_ANSWER)) {
                            //              Log.d(myTag,"KEY_OPTION : "+curText);
                                currDataBaseQuestion.setAnswer(curText);
                        }

                        else if (tagname.equalsIgnoreCase(KEY_TYPE_OF_QUESTION)) {
                            //              Log.d(myTag,"KEY_OPTION : "+curText);
                            currDataBaseQuestion.setTypeOfQuestion(curText);
                        }
                        else if (tagname.equalsIgnoreCase(KEY_EXPLANATION)) {
                            //              Log.d(myTag,"KEY_OPTION : "+curText);
                            currDataBaseQuestion.setExplanation(curText);
                        }

                        break;

                    default:
                        break;
                }
                //move on to next iteration
                Log.d(myTag,"EXAMPLES List : "+currItem.exampleList);
                Log.d(myTag,"DataBase Question List : "+currDataBaseQuestion);
                Log.d(myTag,"PracticeQuestions List : "+currItem);
                eventType = xmlPullParser.next();
            }





            }
        catch (XmlPullParserException ex)
        {
            ex.printStackTrace();;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return  databaseItemsList;

    }
}
