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
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bathla on 4/25/2016.
 */
public class SitesXMLPullParser {


    static final String KEY_SITE = "site";
    static final String KEY_NAME = "name";
    static final String KEY_LINK = "link";
    static final String KEY_ABOUT = "about";
    static final String KEY_IMAGE_URL = "image";


    public static List<StackSites> getStackFromFiles(Context ctx)
    {
        List<StackSites> stacksSitesList=null;

        stacksSitesList = new ArrayList<>();
        //temp holder for current StackSite while parsing
        StackSites curStackSite = null;
        // temp holder for current text value while parsing
        String curText = "";

        try {

            // Get our factory and PullParser
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser =xmlPullParserFactory.newPullParser();


            // OPen InputStream and Reader of our File
            // Open up InputStream and Reader of our file.
            FileInputStream fis = ctx.openFileInput("stacksites.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

//            FileInputStream fileInputStream = new FileInputStream(new File("stacksites.xml"));
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

          //point parser to the file
            xmlPullParser.setInput(reader);

            //getIntial event Type
             int eventType=   xmlPullParser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT)
            {
                // Get the current tag
                String tagname = xmlPullParser.getName();



                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_SITE)) {
                            // If we are starting a new <site> block we need
                            //a new StackSite object to represent it
                            curStackSite = new StackSites();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        curText = xmlPullParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_SITE)) {
                            // if </site> then we are done with current Site
                            // add it to the list.
                            stacksSitesList.add(curStackSite);
                        } else if (tagname.equalsIgnoreCase(KEY_NAME)) {
                            // if </name> use setName() on curSite
                            curStackSite.setName(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_LINK)) {
                            // if </link> use setLink() on curSite
                            curStackSite.setLink(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_ABOUT)) {
                            // if </about> use setAbout() on curSite
                            curStackSite.setAbout(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_IMAGE_URL)) {
                            // if </image> use setImgUrl() on curSite
                            curStackSite.setImgUrl(curText);
                        }
                        break;

                    default:
                        break;
                }
                //move on to next iteration
                eventType = xmlPullParser.next();

            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  stacksSitesList;
    }


}
