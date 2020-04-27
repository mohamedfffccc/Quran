package com.example.quranonline.data.local;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.quranonline.data.model.salatlist.Datum;
import com.example.quranonline.data.model.salatlist.SalatList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.quranonline.data.local.Constants.SALAT;

public class SharedPreferencesManger {

    public static SharedPreferences sharedPreferences = null;
    public static String USER_DATA = "USER_DATA";
    public static String USER_PASSWORD = "USER_PASSWORD";
    public static String REMEMBER = "REMEMBER";
    public static String USER_TYPE = "USER_TYPE";
    public static String USER_TYPE_CLIENT = "client";
    public static String USER_TYPE_RESTAURANT = "restaurant";
    public static String CLIENT_DATA = "CLIENT_DATA";
    public static String RESTAURANT_DATA = "RESTAURANT_DATA";



    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "Blood", activity.MODE_PRIVATE);
        }
    }

    public static void SaveData(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void SaveData(Activity activity, String data_Key, boolean data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static String LoadData(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }

        return sharedPreferences.getString(data_Key, null);
    }


    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }
    public static void SaveData(Activity activity, String data_Key, Object data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String StringData = gson.toJson(data_Value);
            editor.putString(data_Key, StringData);
            editor.commit();
        }
    }

    public static void save(Activity activity, SalatList prayer) {
        SaveData(activity, SALAT, prayer);
    }

    public static SalatList loadPrayings(Activity activity) {
        SalatList salatlist = null;

        Gson gson = new Gson();
        salatlist = gson.fromJson(LoadData(activity, SALAT), SalatList.class);

        return salatlist;
    }
    public static void saveList(Activity activity, String datakey, List<Datum> data) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            HashSet<Datum> set = new HashSet<Datum>();

            set.addAll(data);
//            editor.putStringSet("categories", set);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }

    }

    public static Set<String> LoadList(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }

        return sharedPreferences.getStringSet(data_Key, null);
    }
    public static <Datum>   void setList(Activity activity , String key , List<Datum> data)
    {
        setSharedPreferences(activity);
        Gson gson = new Gson();
        String json = gson.toJson(data);
        set(key,json);
        Toast.makeText(activity,"saved" , Toast.LENGTH_LONG).show();
    }

    private static void set(String key, String value ) {
        if (sharedPreferences != null) {


            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key , value);
            editor.commit();
        }



    }
    public static List<Datum> getTimings(Activity activity , String key)
    {
        setSharedPreferences(activity);

        if (sharedPreferences != null) {
            Gson gson = new Gson();
            List<Datum> data;
            String v = sharedPreferences.getString(key,"0");
            Type type = new TypeToken<List<Datum>>()
            {

            }.getType();
            data=gson.fromJson(v , type);
            return data;
        }
        return  null;
    }

}
