package com.amongusdev.denticitas.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.amongusdev.denticitas.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class Utils {

    public static void goToNextActivityCleanStack(Activity activity, Class clase, boolean finaliza, Bundle params) {
        Intent intent = new Intent(activity, clase).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (params != null) {
            intent.putExtras(params);
        }
        activity.startActivity(intent);
        if (finaliza) {
            activity.finish();
        }
    }

    public static String timeToString(Date date) {
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm", Locale.US);
        return hour.format(date);
    }

    public static String dateToString(Calendar instance) {
        //TODO
        return "Jue\n1 NOV";
    }

    public static String getValuePreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public static void saveValuePreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void saveValuePreferenceInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getValuePreferenceInt(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }
}
