package com.example.jessie.rjt_cw_async_task_9_11;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    ProgressDialog pd;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                MyAsyncTask mat = new MyAsyncTask();
                mat.execute("COMPLETE");

            }


        });
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(MainActivity.this, "Progress Dialog ", "Please wait...");

        }

        @Override
        protected String doInBackground(String... strings) {

            for (int i = 0; i < 10; i++)
            {
                int value = i;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                publishProgress(value);
            }
            String result = strings[0];

            return result;


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText("%Downloaded: "+values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pd.cancel();
            tv.setText(s);
        }

    }
}
