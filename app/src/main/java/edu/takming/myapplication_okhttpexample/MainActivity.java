package edu.takming.myapplication_okhttpexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
TextView ans;
EditText hurl,url2;
Button b,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        ans=(TextView)findViewById(R.id.txt);
        hurl=(EditText)findViewById(R.id.editText);
        url2=(EditText)findViewById(R.id.editText2);
        b=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            final String w=url2.getText().toString();
            final okhttp_util okhttpw=new okhttp_util();
            @Override
            public void onClick(View view) {
                Thread t=new Thread(){
                    public void run()
                    {
                        final String res=okhttpw.urlget(w);
                        Log.d(" TAG ", res);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               try{
                                    ans.setText(res);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                };
                t.start();
            }
        });
        //
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String w=hurl.getText().toString();
                final okhttp_util okhttpw=new okhttp_util();
                Thread t=new Thread(){
                    public void run()
                        {
                            final String res=okhttpw.urlget(w);
                            Log.d(" TAG ", res);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    JSONObject j1= null;
                                    try {
                                        j1 = new JSONObject(res);
                                        JSONObject j2=j1.getJSONObject("retVal");
                                        JSONObject j3=j2.getJSONObject("0001");
                                        String sna=j3.getString("sna");
                                        String tot=j3.getString("tot");
                                        ans.setText(sna+",tot="+tot);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                };
                t.start();


                //


            }
        });
    }
}
