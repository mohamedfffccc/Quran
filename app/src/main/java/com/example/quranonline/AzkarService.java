package com.example.quranonline;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.net.URI;

public class AzkarService extends Service {
    public AzkarService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Wake Up" , Toast.LENGTH_SHORT).show();
        new OnlineNotification().notify(getApplicationContext() , "" , 0);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext() , R.raw.sound);
        mp.start();

//        try{
//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//            Ringtone r = RingtoneManager.getRingtone(getApplicationContext() , notification);
//            r.play();
//           new OnlineNotification().notify(getApplicationContext() , "" , 0);
//
//        }
//        catch (Exception e)
//        {
//            Log.d("alarm" , e.getMessage());
//
//            Toast.makeText(this,e.getMessage() , Toast.LENGTH_SHORT).show();
//
//        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"alarm stopped" , Toast.LENGTH_SHORT).show();


    }
}
