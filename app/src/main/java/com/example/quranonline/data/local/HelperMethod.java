package com.example.quranonline.data.local;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.quranonline.R;
import com.example.quranonline.data.service.AdanService;
import com.example.quranonline.data.service.AzkarService;
import com.example.quranonline.view.activity.SettingActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.ALARM_SERVICE;

public class HelperMethod {

    private static ProgressDialog checkDialog;
    public static AlertDialog alertDialog;
    public static ProgressDialog dialog;
    public  static  String [] AZKARS =  {"لا اله الا الله وحده لا شريك له له الملك وله الحمد وهو علي كل شيئ قدير"
            ,"اعوذ بكلمات الله التامات من شر ما خلق"
            ,"سبحان الله وبحمده سبحان الله العظيم",
            "اللهم انت ربي لا اله الا انت خلقتني وانا عبدك وانا علي عهدك ووعدك ما استطعت " +
                    "اعوذ بك من شر ما صنعت ابؤء لك بنعمتك علي وابوء بذنبي فاغفر لي فانه لا يغفر الذنوب الا انت"
            ,"رضيت بالله ربا وبالاسلام دينا" +
            " وبمحمد صلي الله عليه وسلم نبيا ورسولا"
            ,"اللهم ما اصبح بي من نعمة او باحد من خلقك فمنك وحدك لا شريك لك فلك الحمد ولك الشكر"
            ,"حسبي الله لا اله الا هو عليه توكلت وهو رب العرش العظيم",
            "سبحان الله وبحمده عدد خلقه ورضا نفسه وزنة عرشه ومداد كلماته",
            "اللهم اني اعوذ بك من الكفر والفقر واعوذ بك من عذاب القبر لا اله الا انت",
            "يا حي يا قيوم برحمتك استغيث اصلح لي شاني كله ولا تكلني الي نفسي طرفة عين"
            ,"اللهم انا نعوذ بك من ان نشرك بك شيئا نعلمه ونستغفرك لما لا نعلمه",
            "استغفر الله الذي لا اله الا هو الحي القيوم واتوب اليه",
            "سبحان الله وبحمده","استغفر الله واتوب اليه","الهم انت السلام و منك السلام تباركت ياذا الجلال والاكرام"
            ,"اللهم اني اسالك علما نافعا ورزقا طيبا وعملا متقبلا"
            ,"الهم اجرني من نار جهنم",
            "اللهم اعني علي ذكرك و شكرك وحسن عبادتك"
            ,"قل هو الله احد الله الصمد لم يلد ولم يولد ولم يكن لع كفوا احد"
            , "قل اعوذ برب الناس ملك الناس اله الناس ممن شر الوسواس الخناس الذي يوسوس في صدور الناس من الجنه والناس",
            "واعبد ربك حتي ياتيك اليقين","اللهم صلي علي سيدنا محمد وعلي اله وصحبه وسلم" +
            "اغتنم خمسا قبل خمس فراغك قبل شغلك , صحتك قبل سقمك , غناك قبل فقرك  , حياتك قبل موتك , شبابك قبل هرمك"};
    private static NetworkInfo nInfo;
    private static Intent adan_i;


    public static void showProgressDialog(Activity activity, String title) {
        try {
            checkDialog = new ProgressDialog(activity);
            checkDialog.setMessage(title);
            checkDialog.setIndeterminate(false);
            checkDialog.setCancelable(false);
            checkDialog.show();

        } catch (Exception e) {

        }
    }

    public static void dismissProgressDialog() {
        try {
            if (checkDialog != null && checkDialog.isShowing()) {
                checkDialog.dismiss();
            }
        } catch (Exception e) {

        }
    }

    public static void ReplaceFragment(FragmentManager supportFragmentManager, Fragment fragment, int container_id
            , TextView toolbarTitle, String title) {

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

        transaction.replace(container_id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }

    }


    public static void disappearKeypad(Activity activity, View v) {
        try {
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }

    public static void shareVia(Context context, String name, String url) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(name);
        if (intent != null) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, url);
            share.setPackage(name);
            context.startActivity(share);

        } else {
            showAlert("app not found" , name , context);
        }
    }

    public static void showDownloadProgress(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("downloading...");
        dialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();
    }
    public static void showAlert(String message , String url , Context con) {
        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        builder.setTitle("Alert")
                .setIcon(R.drawable.logo_app)
                .setMessage(message)

                .setPositiveButton("Download", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + url));
                        con.startActivity(i);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
    public static void notifyAzkar(Context context )
    {
        int id = 0;
        int rand = (int) (Math.random() * AZKARS.length);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.quran)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources() , R.drawable.quran))
                .setContentTitle("الا بذكر الله تطمئن القلوب")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(AZKARS[rand]))
                .setContentText(AZKARS[rand]);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            String channel_id = "Online_id";
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(channel_id,
                    "channel human readable title"
            ,NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            builder.setChannelId(channel_id);


        }
//        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        builder.setSound(uri);
        manager.notify(id,builder.build());
        id++;

    }
    public static void startAlarmService(Context context ,String time) {
        adan_i = new Intent(context, AdanService.class);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, adan_i, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String t = simpleDateFormat.format(new Date());
        String[] times = time.split(":");
        // T

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[1]));
        calendar.set(Calendar.SECOND, 00);
        Log.d("adan" , "الساعة " + Integer.parseInt(times[0]) +"والدقيقة "+Integer.parseInt(times[1]));
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }





}

