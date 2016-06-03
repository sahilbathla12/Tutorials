package testactivitylifecycle.bathla.com.tutorials;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;

/**
 * Created by bathla on 4/25/2016.
 */
public class Downloader {

    private static String tag="Downloader";

    //Handler msg that represents we are posting a progress update.

    static final int POST_PROGRESS = 1;

    public static void DownloadFromURL(String urlString, FileOutputStream fos) throws MalformedURLException {

        //keep the start time so we can display how long it took to the Log.
        long startTime = System.currentTimeMillis();
        Log.d(tag, "download begining");


        /*
        Open a Connection to URL
         */
        URL url = new URL(urlString);

        URLConnection conn=null;
        try {
             conn= url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // this will be useful so that you can show a tipical 0-100% progress bar
        //int lenghtOfFile = ucon.getContentLength();

        Log.i(tag, "Opened Connection");


        //From connection get INbut Stream

        InputStream is = null;

        try {
             is = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        BufferedInputStream bis = new BufferedInputStream(is);


        // Now Buffered OutPutStream and Write to a File;
        BufferedOutputStream bos = new BufferedOutputStream(fos);


        //write the downloaded InputStreas to Bos anthen file

        byte[] data = new byte[1024];
        int count =0;
        try {
            while((count=bis.read(data)) !=-1)
            {
                //keep track of size for progress.
                    //total += count;
                //write this chunk
                bos.write(data, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(tag, "download ready in "
                + ((System.currentTimeMillis() - startTime))
                + " milisec");
        ;


    }
}
