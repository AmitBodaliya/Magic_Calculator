package com.samsung.calculator;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;

public class Prefs {
    Context context;
    String projectName = "project";

    public Prefs(Context context){
        this.context = context;
    }

    public void setPrefs(String id , int data){
        SharedPreferences sharedPreferences = context.getSharedPreferences(projectName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("" + id, data);
        editor.apply();
    }

    public int getPrefs(String id , int defaultValue){
        SharedPreferences getShared = context.getSharedPreferences(projectName, MODE_PRIVATE);
        return getShared.getInt("" + id, defaultValue);
    }

}
