package il.co.androidthread18;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView timeLeft;
    ProgressBar progressBar;
    Button startBtn, stopBtn;
    CustomAsync asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeLeft = findViewById(R.id.tv);
        startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);
        stopBtn = findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(this);
        stopBtn.setEnabled(false);
        progressBar = findViewById(R.id.progress);
        progressBar.setMax(100);
        progressBar.setProgress(0);
    }

    @Override
    public void onClick(View v)
    {
        if(startBtn == v)
        {
            if(asyncTask == null)
            {
                asyncTask = new CustomAsync(timeLeft, progressBar);
                asyncTask.execute(100);
            }
            else  if(asyncTask.num == 0)
            {
                asyncTask = new CustomAsync(timeLeft, progressBar);
                asyncTask.execute(100);
            }
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
            asyncTask.isRun = true;
        }
        else if(stopBtn == v)
        {
            asyncTask.isRun = false;
            startBtn.setEnabled(true);
            stopBtn.setEnabled(false);
        }
    }
}
