package com.example.steven.flickrgallery;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by Steven on 30/12/14.
 */
public class FlickrRestClient {

    private static final String BASE_URL = "https://api.flickr.com/services/rest";
    private FlickrApiService apiService;

    private static final String TAG = "FlickrRestClient";
    private static Map<String, String> options = new HashMap<String, String>();

    static{
        options.put("method","flickr.photos.search");
        options.put("api_key", "fcc77d5e1f86d5e8893b7c382855144d");
        options.put("format", "json");
        options.put("nojsoncallback", "1");
    }


    public FlickrRestClient(){
        Gson gson = new GsonBuilder().create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(FlickrApiService.class);
    }

    public FlickrApiService getApiService()
    {
        return this.apiService;
    }

    public void doFlickrSearch(String searchTerm,int aantal, final FlickrGalleryActivity activity)
    {
        String encodedSearch = null;

        try {
            encodedSearch = StringUtitities.makeUrlEncoded(searchTerm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Callback<PhotosResponse> callback = new Callback<PhotosResponse>() {
            @Override
            public void success(PhotosResponse photosResponse, Response response) {
                //to do onsuccess
                Log.d(TAG,"On success : "+photosResponse.getPhotos().getTotal());
                activity.fillGridView(photosResponse.getPhotos().getPhoto());
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e(TAG,"Failure : "+retrofitError.getMessage());
            }
        };

        options.put("text",encodedSearch);
        options.put("per_page",""+aantal);

        apiService.getFlickrContent(options,callback);

    }






}
