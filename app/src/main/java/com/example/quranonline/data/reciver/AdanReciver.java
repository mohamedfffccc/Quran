package com.example.quranonline.data.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.quranonline.view.activity.AdanCancelPage;
import com.example.quranonline.view.activity.SohorAlarmCancelPage;

public class AdanReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.medo.adan.salat")) {
            Intent i = new Intent(context, AdanCancelPage.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        }
    }
    }

