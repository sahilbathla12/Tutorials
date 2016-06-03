package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Tutorials extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorials_activity);
        String string = "hello world!";

       try {
            InputStream ins = getResources().openRawResource(R.raw.database);
            //FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            DatabaseXMLParser.ReadFile(this,ins);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
