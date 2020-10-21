package il.co.androidthread18;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
// Ruben Rudov
public class CustomAsync extends AsyncTask<Integer,Integer,String> {
    @SuppressLint("StaticFieldLeak")
    public TextView timeLeftTv;
    @SuppressLint("StaticFieldLeak")
    public ProgressBar progressBar;
    public boolean isRunning = true;
    int num;
    CustomAsync(TextView timeLeftTv, ProgressBar progressBar)
    {
        this.timeLeftTv = timeLeftTv;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... params) {
        num = params[0];
        while (num > 0)
        {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isRunning)
            {
                num--;
                publishProgress(num);
            }
            if(num == 0)
            {
                isRunning = false;
            }

        }
        return "בוצע";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        timeLeftTv.setText(String.valueOf(values[0]));
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        timeLeftTv.setText(s);
    }
}
