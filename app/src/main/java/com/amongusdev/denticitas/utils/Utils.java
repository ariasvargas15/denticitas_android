package com.amongusdev.denticitas.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
}
