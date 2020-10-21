package il.co.androidthread18;
import android.os.Handler;
import android.os.Message;

public class CustomThread extends  Thread {
    int num;
    boolean
            isRunning = true,
            isGoing = true;
    Handler handler;

    public CustomThread(Handler handler,int num)
    {
        this.handler = handler;
        this.num = num;
    }

    @Override
    public void run() {
        super.run();
        while(isGoing)
        {
            if(isRunning && num >= 0)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    isGoing = false;
                }
                Message msg = new Message();
                msg.arg1 = num;
                num--;
                handler.sendMessage(msg);
            }
        }
    }
}
