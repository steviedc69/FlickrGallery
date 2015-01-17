package com.example.steven.flickrgallery;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by Steven on 30/12/14.
 */
public interface FlickrApiService {

    @GET("/")
    public void getFlickrContent(@QueryMap Map<String,String> options, Callback<PhotosResponse> response);
}
