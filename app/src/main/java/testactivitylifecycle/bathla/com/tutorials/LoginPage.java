package testactivitylifecycle.bathla.com.tutorials;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by bathla on 4/8/2016.
 */
public class LoginPage extends Activity
{
    EditText userName_et=null;
    Button submit_btn=null;
    ScrollView registration_sv=null;
    TextView Welcomename_tv=null;
    ScrollView welcome_sv=null;
    String welcomeName= null;
    Button start_btn = null;


    public static SharedPreferences sh;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        sh = getSharedPreferences("myprefe", 0);
        editor = sh.edit();


        userName_et= (EditText) findViewById(R.id.userName_et);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        Welcomename_tv = (TextView) findViewById(R.id.welcomeName_tv);
        registration_sv = (ScrollView) findViewById(R.id.registration_sv);
        welcome_sv = (ScrollView) findViewById(R.id.welcome_sv);
         welcomeName= userName_et.getText().toString();
        start_btn = (Button) findViewById(R.id.start_btn);


        registration_sv.setVisibility(View.VISIBLE);
        welcome_sv.setVisibility(View.GONE);

        String userName = sh.getString("userName",null);
        Toast.makeText(getApplicationContext(),"Hello"+userName,Toast.LENGTH_SHORT).show();;
        if(!( userName == null))
        {
            welcomeName = userName;
            Welcomename_tv.setText("Hi " + welcomeName);

            registration_sv.setVisibility(View.GONE);
            welcome_sv.setVisibility(View.VISIBLE);
        }
        else
        {
            registration_sv.setVisibility(View.VISIBLE);
            welcome_sv.setVisibility(View.GONE);
        }
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                welcomeName = userName_et.getText().toString();
                Welcomename_tv.setText("Hi " + welcomeName);
                editor.putString("userName",welcomeName);
                editor.commit();
                registration_sv.setVisibility(View.GONE);
                welcome_sv.setVisibility(View.VISIBLE);
            }
        });



        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(LoginPage.this,TopicsListActivity.class);
            //    Intent intent = new Intent(LoginPage.this,TopicsListActivity.class);
                startActivity(intent);
            }
        });
    }
}
