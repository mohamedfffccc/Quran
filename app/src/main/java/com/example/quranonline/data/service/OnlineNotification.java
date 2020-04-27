package com.example.quranonline.data.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.quranonline.R;

/**
 * Helper class for showing and canceling online
 * notifications.
 * <p>
 * This class makes heavy use of the {@link NotificationCompat.Builder} helper
 * class to create notifications in a backward-compatible way.
 */
public class OnlineNotification {
    /**
     * The unique identifier for this type of notification.
     */
    private static final String NOTIFICATION_TAG = "Online";
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
            "واعبد ربك حتي ياتيك اليقين","اللهم صلي علي سيدنا محمد وعلي اله وصحبه وسلم" ,
            "اغتنم خمسا قبل خمس فراغك قبل شغلك , صحتك قبل سقمك , غناك قبل فقرك  , حياتك قبل موتك , شبابك قبل هرمك"};
    /**
     * Shows the notification, or updates a previously shown notification of
     * this type, with the given parameters.
     * <p>
     * TODO: Customize this method's arguments to present relevant content in
     * the notification.
     * <p>
     * TODO: Customize the contents of this method to tweak the behavior and
     * presentation of online notifications. Make
     * sure to follow the
     * <a href="https://developer.android.com/design/patterns/notifications.html">
     * Notification design guidelines</a> when doing so.
     *
     * @see #cancel(Context)
     */
    public static void notify(final Context context,
                              final String exampleString, final int number) {
        int rand = (int) (Math.random() * AZKARS.length);

        final Resources res = context.getResources();

        // This image is used as the notification's large icon (thumbnail).
        // TODO: Remove this if your notification has no relevant thumbnail.
        final Bitmap picture = BitmapFactory.decodeResource(res, R.drawable.quran);


        final String ticker = exampleString;
        final String title = "الا بـذكر الـله تطمـئن القـلوب";
        final String text = AZKARS[rand];

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

                // Set appropriate defaults for the notification light, sound,
                // and vibration.
                .setDefaults(Notification.DEFAULT_ALL)

                // Set required fields, including the small icon, the
                // notification title, and text.
                .setSmallIcon(R.drawable.quran)
                .setContentTitle(title)
                .setContentText(text)
                .setLights(Color.GREEN, 3000 , 300)
                .setVibrate(new long[] {100,200,300,400})

                // All fields below this line are optional.

                // Use a default priority (recognized on devices running Android
                // 4.1 or later)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                // Provide a large icon, shown with the notification in the
                // notification drawer on devices running Android 3.0 or later.
                .setLargeIcon(picture)

                // Set ticker text (preview) information for this notification.
                .setTicker(ticker)

                // Show a number. This is useful when stacking notifications of
                // a single type.
                .setNumber(number)

                // If this notification relates to a past or upcoming event, you
                // should set the relevant time information using the setWhen
                // method below. If this call is omitted, the notification's
                // timestamp will by set to the time at which it was shown.
                // TODO: Call setWhen if this notification relates to a past or
                // upcoming event. The sole argument to this method should be
                // the notification timestamp in milliseconds.
                //.setWhen(...)

                // Set the pending intent to be initiated when the user touches
                // the notification.
                .setContentIntent(
                        PendingIntent.getActivity(
                                context,
                                0,
                                new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")),
                                PendingIntent.FLAG_UPDATE_CURRENT))

                // Show expanded text content on devices running Android 4.1 or
                // later.
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text)
                        .setBigContentTitle(title)
                        .setSummaryText("اذكر الله"))

                // Example additional actions for this notification. These will
                // only show on devices running Android 4.1 or later, so you
                // should ensure that the activity in this notification's
                // content intent provides access to the same actions in
                // another way.
                .addAction(
                        R.drawable.ic_action_stat_share,
                        res.getString(R.string.action_share),
                        PendingIntent.getActivity(
                                context,
                                0,
                                Intent.createChooser(new Intent(Intent.ACTION_SEND)
                                        .setType("text/plain")
                                        .putExtra(Intent.EXTRA_TEXT,  text), "اذكر الله"),
                                PendingIntent.FLAG_UPDATE_CURRENT))
                .addAction(
                        R.drawable.ic_action_stat_reply,
                        res.getString(R.string.action_reply),
                        null)

                // Automatically dismiss the notification when it is touched.
                .setAutoCancel(true);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            String channel_id = "Online_id";
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(channel_id,
                    "channel human readable title"
                    ,NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[] {100,200,300,400});
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);

            builder.setChannelId(channel_id);

        }

        notify(context, builder.build());
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Context context, final Notification notification) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFICATION_TAG, 0, notification);
        } else {
            nm.notify(NOTIFICATION_TAG.hashCode(), notification);
        }
    }

    /**
     * Cancels any notifications of this type previously shown using
     * {@link #notify(Context, String, int)}.
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static void cancel(final Context context) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, 0);
        } else {
            nm.cancel(NOTIFICATION_TAG.hashCode());
        }
    }
}
