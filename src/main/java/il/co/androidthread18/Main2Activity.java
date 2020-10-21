package il.co.androidthread18;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    TextView timeLeft, title;
    ProgressBar progressBar;
    Button startBtn, stopBtn;
    CustomThread customThread;
    Handler handler;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                return false;
            }
        });
        setContentView(R.layout.activity_main);
        timeLeft = findViewById(R.id.timeLeft);
        startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);
        stopBtn = findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(this);
        stopBtn.setEnabled(false);
        progressBar = findViewById(R.id.progress);
        progressBar.setMax(100);
        progressBar.setProgress(100);
        title = findViewById(R.id.activityTitle);
        title.setText("Thread handler");
    }

    @Override
    public void onClick(View v)
    {
        if(startBtn == v) {
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
            if (customThread == null) {

                customThread = new CustomThread(handler, 100);

                customThread.start();

            } else if (customThread.num == 0) {
                customThread = new CustomThread(handler, 100);
                customThread.start();
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
            customThread.isRunning = true;
            } else {
                customThread = new CustomThread(handler, 100);
                customThread.start();
            }
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
            customThread.isRunning = true;
        } else if(stopBtn == v)
        {
            customThread.isRunning = false;
            startBtn.setEnabled(true);
            stopBtn.setEnabled(false);
        }
    }
}
