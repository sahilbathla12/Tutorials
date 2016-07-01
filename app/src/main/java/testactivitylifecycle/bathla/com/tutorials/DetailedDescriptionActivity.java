package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by bathla on 4/27/2016.
 */
public class DetailedDescriptionActivity extends AppCompatActivity
{

    TextView DescriptionTitle =null;
    TextView DescriptionDetail =null;
    WebView webView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_description);


        // Find the toolbar view and set as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// ...
// Display icon in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       // getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DescriptionTitle= (TextView) findViewById(R.id.descriptionTitle);
         //   DescriptionDetail = (TextView) findViewById(R.id.detailedDescription);
        webView =(WebView) findViewById(R.id.webview);
        //https://docs.google.com/document/d/1_d5LMTwWglTw9t2lXckz3OuFyCClX0OewWKXdJDjqYQ/edit
        //https://drive.google.com/drive/folders/0B7NZ1GptSkOCfmRRTG1XZzhOMzJjbEM2aHBuUFFkejB2U3ZkMHp5UjNpdUlKQlZqT3R3RUU
        //https://drive.google.com/drive/folders/0B7NZ1GptSkOCdFlOMVBERFRJazA
        Intent intent = getIntent();
        // DatabaseItem db =intent.getParcelableExtra("databaseItem");




        String descriptionPosition = intent.getStringExtra("databaseItemPosition");
        String topicName = intent.getStringExtra("databaseItemString");

        // Log.d("DetailedDesciption",db.toString());
        DatabaseHelper hp = new DatabaseHelper(this);

        int i=1;
        /* String docs="<iframe src='http://docs.google.com/viewer?url=https://drive.google.com/drive/folders/0B7NZ1GptSkOCdFlOMVBERFRJazA' width='100%' height='100%'     style='border: none;'></iframe>";
        //webView.loadUrl("http://tutorials.jenkov.com");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("http://www.google.com");
        webView.loadData( docs , "text/html", "UTF-8");*/
        //AssetManager assetManager = getAssets();
/*

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings .setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient());
        String pdf="https://drive.google.com/drive/folders/0B7NZ1GptSkOCdFlOMVBERFRJazA";
        webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
*/


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        //---you need this to prevent the webview from
        // launching another browser when a url
        // redirection occurs---
        webView.setWebViewClient(new Callback());

        String pdfURL = "http://www.expertagent.co.uk/asp/in4glestates/{16D968D6-198E-4E33-88F4-8A85731CE605}/{05c36123-4df0-4d7d-811c-8b6686fdd526}/external.pdf";
        webView.loadUrl(
                "http://docs.google.com/gview?embedded=true&url=" + pdfURL);

      //  setContentView(webView);
      //  File file = new File("android.resource://raw/abc.pdf");



     //   Uri path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.java);
        //Uri path = Uri.fromFile(pdfFile);

       /* Intent intents = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(path, "application/pdf");*/
        //https://drive.google.com/drive/folders/0B7NZ1GptSkOCdFlOMVBERFRJazA

/*

        String doc="<iframe src='https://drive.google.com/open?id=0B7NZ1GptSkOCUFZ0ZS1yQVBaVGM' width='100%' height='100%' style='border: none;'></iframe>";
        String docs="<iframe src='http://docs.google.com/viewer?url=https://drive.google.com/drive/folders/0B7NZ1GptSkOCdFlOMVBERFRJazA' width='100%' height='100%'     style='border: none;'></iframe>";
        // webView.getSettings().setJavaScriptEnabled(true);
    //    webView.getSettings().(true);
         WebSettings webSettings = webView.getSettings();
         webSettings.setJavaScriptEnabled(true);
         webView.getSettings().setAllowFileAccess(true);
         //webView.loadData( doc , "text/html", "UTF-8");
        Log.d("Description","Loading google.com");
       // webView.loadUrl("http://www.google.com");
        Log.d("Description","Loaded google.com");
        String pdf="https://drive.google.com/drive/folders/0B7NZ1GptSkOCdFlOMVBERFRJazA";
        //webView.loadData( docs , "text/html",  "UTF-8");
        //webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
        Log.d("Description","Loaded google.com");
        webView.loadUrl("www.google.com");
       // setContentView(webView);
//        webView.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return false;
//            }
//        });
       // startActivity(intents);
        */
/*Intent intent2 = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),"application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try
        {
            getApplicationContext().startActivity(intent);
        }
        catch (ActivityNotFoundException e)
        {
            Toast.makeText(Stack_dr.this, "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
        }
        Intent intent1 = new Intent(Intent.ACTION_VIEW);
        //intent1.setData(path);
        startActivity(intent1);*//*


      //  String pdf = "http://www.pc-hardware.hu/PDF/konfig.pdf";
       // String doc="<iframe src='Path of the Google Drive Where you have uploaded your documents in PDF' width='100%' height='100%' style='border: none;'></iframe>";

//        webView = (WebView) findViewById(R.id.webView1);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setPluginsEnabled(true);
//        webView.getSettings().setAllowFileAccess(true);
//        webView.loadData( doc , "text/html", "UTF-8");
       // String Pdf = "https://drive.google.com//drive//folders//0B7NZ1GptSkOCfmRRTG1XZzhOMzJjbEM2aHBuUFFkejB2U3ZkMHp5UjNpdUlKQlZqT3R3RUU";
        */
/*String doc="<iframe src='Path of the Google Drive Where you have uploaded your documents in PDF' width='100%' height='100%' style='border: none;'></iframe>";
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginsEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.loadData( doc , "text/html", "UTF-8");*//*


        Log.d("DESCRIPTION",""+topicName);
       // DescriptionDetail.setText(db.getDescription());
       */
/* DescriptionDetail.setText(topicName);
        //DescriptionTitle.setText(db.getTopic());
        DescriptionTitle.setText(descriptionPosition);*//*


*/

    }

/*
    private void CopyReadAssets()
    {
        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), "abc.pdf");
        try
        {
            in = assetManager.open("abc.pdf");
         //   out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e)
        {
            Log.e("tag", e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.parse("file://" + getFilesDir() + "/abc.pdf"),
                "application/pdf");

        startActivity(intent);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }*/
private class Callback extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(
            WebView view, String url) {
        return(false);
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_tutorials, menu);
        return true;

    }
}
