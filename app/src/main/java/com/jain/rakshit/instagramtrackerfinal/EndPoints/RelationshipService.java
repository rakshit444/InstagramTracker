package com.jain.rakshit.instagramtrackerfinal.EndPoints;

import com.jain.rakshit.instagramtrackerfinal.Rest.Model.RelationshipData;
import com.jain.rakshit.instagramtrackerfinal.Rest.Model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Rakshit on 6/20/2016.
 */

public interface RelationshipService {

    @GET("v1/users/{user_id}/follows/")
    Call<User> getList(
            @Path("user_id") String userId,
            @Query("access_token") String accessToken);

    @GET("v1/users/{user_id}/relationship")
    Call<RelationshipData> getRelation(
            @Path("user_id") String userId,
            @Query("access_token") String accessToken);
}

