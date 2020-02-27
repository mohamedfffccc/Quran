package com.example.quranonline.data.local;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class HelperMethod {

    private static ProgressDialog checkDialog;
    public static AlertDialog alertDialog;
//    private static Snackbar snackbar;


//    public static MultipartBody.Part convertFileToMultipart(String pathImageFile, String Key) {
//        if (pathImageFile != null) {
//            File file = new File(pathImageFile);
//
//            RequestBody reqFileselect = RequestBody.create(MediaType.parse("image/*"), file);
//
//            MultipartBody.Part Imagebody = MultipartBody.Part.createFormData(Key, file.getName(), reqFileselect);
//
//            return Imagebody;
//        } else {
//            return null;
//        }
//    }
//

//    public static RequestBody convertToRequestBody(String part) {
//        try {
//            if (!part.equals("")) {
//                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), part);
//                return requestBody;
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public static void onLoadImageFromUrl(ImageView imageView, String URl, Context context, int drId) {
//        Glide.with(context)
//                .load(URl)
//                .into(imageView);
//    }
//
//    public static void createSnackBar(View view, String message, Context context) {
//        final Snackbar snackbar = Snackbar.make(view, message, 50000);
//        snackbar.setAction(R.string.dismiss, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                snackbar.dismiss();
//            }
//        })
//                .setActionTextColor(context.getResources().getColor(android.R.color.holo_red_light))
//
//                .show();
//    }

//    public static void createSnackBar(View view, String message, Context context, View.OnClickListener action, String Title) {
//        snackbar = Snackbar.make(view, message, 50000);
//        snackbar.setAction(Title, action)
//                .setActionTextColor(context.getResources().getColor(android.R.color.holo_red_light))
//                .show();
//    }

    //Calender
//    public static void showCalender(Context context, String title, final TextView text_view_data, final DateModel data1) {
//
//        DatePickerDialog mDatePicker = new DatePickerDialog(context, AlertDialog.THEME_HOLO_DARK, new DatePickerDialog.OnDateSetListener() {
//            public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
//
//                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
//                DecimalFormat mFormat = new DecimalFormat("00", symbols);
//                String data = selectedYear + "-" + String.format(new Locale("en"), mFormat.format(Double.valueOf((selectedMonth + 1)))) + "-"
//                        + mFormat.format(Double.valueOf(selectedDay));
//                data1.setDate_txt(data);
//                data1.setDay(mFormat.format(Double.valueOf(selectedDay)));
//                data1.setMonth(mFormat.format(Double.valueOf(selectedMonth + 1)));
//                data1.setYear(String.valueOf(selectedYear));
//                if (text_view_data != null) {
//                    text_view_data.setText(data);
//                }
//            }
//        }, /*Integer.parseInt(data1.getYear())*/3, /*Integer.parseInt(data1.getMonth()) - 1*/4, /*Integer.parseInt(data1.getDay())*/8);
//        mDatePicker.setTitle(title);
//        mDatePicker.show();
//    }

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
    public static void shareVia(Context context , String name , String url)
    {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(name);
        if (intent != null) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, url);
            share.setPackage(name);
            context.startActivity(share);

        }
        else
        {
            Toast.makeText(context , "you don,t have"+name , Toast.LENGTH_SHORT).show();
        }
    }
//
}
//
//}
//
