package com.example.quranonline.data.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.quranonline.view.activity.SohorAlarmCancelPage;

public class SohorAlarmReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.medo.sohoralarm")) {
            Intent i = new Intent(context, SohorAlarmCancelPage.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        }
    }
}
