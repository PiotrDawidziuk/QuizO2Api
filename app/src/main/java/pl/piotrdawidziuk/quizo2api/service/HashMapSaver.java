package pl.piotrdawidziuk.quizo2api.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class HashMapSaver {

    public static void saveHashMap(String key , Object obj, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(key,json);
        editor.apply();     // This line is IMPORTANT !!!
    }


    public static HashMap<String,Integer> getHashMap(String key,Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key,"");
        java.lang.reflect.Type type = new TypeToken<HashMap<String,Integer>>(){}.getType();
        HashMap<String,Integer> obj = gson.fromJson(json, type);
        return obj;
    }
}
