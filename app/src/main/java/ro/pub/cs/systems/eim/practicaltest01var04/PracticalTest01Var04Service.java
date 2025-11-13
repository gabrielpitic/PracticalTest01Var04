package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var04Service extends Service {
    private ProcessingThread processingThread = null;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(ProcessingThread.isRunning) {
            ProcessingThread.isRunning = false;
        }
        Log.d("[BROADCAST]", "onDestroy was called");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getExtras() != null) {
            String topText = intent.getStringExtra("TOP_TEXT");
            String bottomText = intent.getStringExtra("BOTTOM_TEXT");
            if(!ProcessingThread.isRunning) {
                processingThread = new ProcessingThread(getApplicationContext(),
                        topText, bottomText);
                ProcessingThread.isRunning = true;
                processingThread.start();
            }
        }
        return START_REDELIVER_INTENT;
    }
}

class ProcessingThread extends Thread {
    static boolean isRunning = false;
    private Context context;
    private String topText, bottomText;
    public ProcessingThread(Context ctx, String topText, String bottomText) {
        this.context = ctx;
        this.topText = topText;
        this.bottomText = bottomText;
    }

    @Override
    public void run() {
        while(isRunning) {
            Intent intent = new Intent("TOP_TEXT_ACTION");
            intent.setPackage(context.getPackageName());
            intent.putExtra("TOP_TEXT", topText);

            Intent intent2 = new Intent("BOTTOM_TEXT_ACTION");
            intent2.setPackage(context.getPackageName());
            intent2.putExtra("BOTTOM_TEXT", bottomText);

            context.sendBroadcast(intent);
            context.sendBroadcast(intent2);

            Log.d("[SERVICE]", "TOP_TEXT_ACTION" + " " + topText);
            Log.d("[SERVICE]", "BOTTOM_TEXT_ACTION" + " " + bottomText);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class BroadcastRecv extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        assert action != null;
        if(action.equals("TOP_TEXT_ACTION")) {
            String text = intent.getStringExtra("TOP_TEXT");
            Log.d("[BROADCAST RECV]", "Action: " + action + " Text: " + text);
        } else if(action.equals("BOTTOM_TEXT_ACTION")) {
            String text = intent.getStringExtra("BOTTOM_TEXT");
            Log.d("[BROADCAST RECV]", "Action: " + action + " Text: " + text);

        }
    }
}