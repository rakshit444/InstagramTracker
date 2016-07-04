package com.jain.rakshit.instagramtrackerfinal.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * Created by Rakshit on 6/20/2016.
 */

public class InstagramSession {
    private static final String first_time_variable = "true";
    private static final String first_time_variable1 = "true";
    private static final String SHARED = "Instagram_Preferences";
    private static final String API_USERNAME = "username";
    private static final String API_ID = "id";
    private static final String API_NAME = "name";
    private static final String API_ACCESS_TOKEN = "access_token";
    private SharedPreferences mSharedPreferences;
    private Editor mEditor;
    private Context mContext;

    public InstagramSession(Context context) {
        this.mContext = context;
        mSharedPreferences = context.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public InstagramSession() {
        mSharedPreferences = mContext.getSharedPreferences(SHARED, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public void storeAccessToken(String accessToken, String id, String username, String name) {
        Log.i("Session", "setting first time variable to true");
        mEditor.putString(first_time_variable, "true");
        mEditor.putString(first_time_variable1, "true");
        mEditor.putString(API_ID, id);
        mEditor.putString(API_NAME, name);
        mEditor.putString(API_ACCESS_TOKEN, accessToken);
        mEditor.putString(API_USERNAME, username);
        mEditor.commit();
    }

    public void setFirst_time_variable() {
        Log.i("Session", "setting first time variable to false");
        mEditor.putString(first_time_variable, "false");
        mEditor.commit();
    }

    public void setFirst_time_variable1() {
        Log.i("Session", "setting first time variable to false");
        mEditor.putString(first_time_variable, "false");
        mEditor.commit();
    }

    public void resetAccessToken() {

        mEditor.putString(API_ID, null);
        mEditor.putString(API_NAME, null);
        mEditor.putString(API_ACCESS_TOKEN, null);
        mEditor.putString(API_USERNAME, null);
        Log.i("Session", "Reset setting first time variable to true");
        mEditor.putString(first_time_variable, "true");
        mEditor.putString(first_time_variable1, "true");
        mEditor.commit();
    }

    /**
     * Get user name
     *
     * @return User name
     */
    public String getFirst_time_variable() {
        Log.i("Session", first_time_variable + " getting first time variable");
        return mSharedPreferences.getString(first_time_variable, null);
    }

    public String getFirst_time_variable1() {
        Log.i("Session", first_time_variable + " getting first time variable1");
        return mSharedPreferences.getString(first_time_variable1, null);
    }

    public String getUsername() {
        return mSharedPreferences.getString(API_USERNAME, null);
    }

    /**
     * @return
     */
    public String getId() {
        return mSharedPreferences.getString(API_ID, null);
    }

    /**
     * @return
     */
    public String getName() {
        return mSharedPreferences.getString(API_NAME, null);
    }

    /**
     * Get access token
     *
     * @return Access token
     */
    public String getAccessToken() {
        return mSharedPreferences.getString(API_ACCESS_TOKEN, null);
    }
}
