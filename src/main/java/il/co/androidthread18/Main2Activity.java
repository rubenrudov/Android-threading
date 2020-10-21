package il.co.androidthread18;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
// Ruben Rudov
public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    TextView timeLeft, title;
    ProgressBar progressBar;
    Button startBtn, stopBtn, switchActivity;
    CustomThread customThread;
    Handler handler;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                timeLeft.setText(String.valueOf(msg.arg1));
                progressBar.setProgress(msg.arg1);
                return true;
            }
        });
        setContentView(R.layout.activity_main);
        timeLeft = findViewById(R.id.timeLeft);
        startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);
        stopBtn = findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(this);
        stopBtn.setEnabled(false);
        switchActivity = findViewById(R.id.switchBtn);
        switchActivity.setOnClickListener(this);
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
            if(customThread == null)
            {
                customThread = new CustomThread(handler, 100);
                customThread.start();
            }
            else if (customThread.num == 0)
            {
                customThread = new CustomThread(handler, 100);
                customThread.start();
            }
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
            customThread.isRunning = true;
        } else if(stopBtn == v) {
            startBtn.setEnabled(true);
            stopBtn.setEnabled(false);
            customThread.isRunning = false;
        } else if(v == switchActivity) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
