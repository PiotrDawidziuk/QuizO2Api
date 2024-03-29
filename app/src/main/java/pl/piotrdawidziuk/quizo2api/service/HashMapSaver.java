package pl.piotrdawidziuk.quizo2api.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import pl.piotrdawidziuk.quizo2api.model.Quiz;
import pl.piotrdawidziuk.quizo2api.model.QuizList;

public class HashMapSaver {

    public static void saveHashMap(String key, Object obj, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(key, json);
        editor.apply();
    }

    public static HashMap<String, Integer> getHashMap(String key, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, Integer>>() {
        }.getType();
        HashMap<String, Integer> obj = gson.fromJson(json, type);
        return obj;
    }

    public static HashMap<String, QuizList> getQuizListHashMap(String key, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, QuizList>>() {
        }.getType();
        HashMap<String, QuizList> obj = gson.fromJson(json, type);
        return obj;
    }

    public static HashMap<String, Quiz> getQuizHashMap(String key, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, Quiz>>() {
        }.getType();
        HashMap<String, Quiz> obj = gson.fromJson(json, type);
        return obj;
    }

}
