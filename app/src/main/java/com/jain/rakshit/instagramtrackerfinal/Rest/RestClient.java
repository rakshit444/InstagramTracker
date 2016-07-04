package com.jain.rakshit.instagramtrackerfinal.Rest;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jain.rakshit.instagramtrackerfinal.EndPoints.RelationshipService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rakshit on 6/22/2016.
 */

public class RestClient {

    private static final String TAG = "Relationship Service";
    private RelationshipService mRelationshipService;

    public RestClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Log.i(TAG, "setting retrofit service");
        mRelationshipService =
                retrofit.create(RelationshipService.class);
        Log.i(TAG, "Done");
    }

    public RelationshipService getRelationshipService() {
        return mRelationshipService;
    }


}

