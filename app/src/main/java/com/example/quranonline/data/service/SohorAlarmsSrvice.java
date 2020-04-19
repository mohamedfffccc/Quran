package com.example.quranonline.data.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import com.example.quranonline.R;

import static com.example.quranonline.data.local.HelperMethod.notifyAzkar;

public class SohorAlarmsSrvice extends Service {
    public SohorAlarmsSrvice() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext() , R.raw.sohor);
        mp.start();
        Intent intent1 = new Intent();
        intent1.setAction("com.medo.sohoralarm");
        getApplicationContext().sendBroadcast(intent1);


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
