package com.example.steven.flickrgallery;

import com.google.gson.annotations.Expose;

/**
 * Created by Steven on 31/12/14.
 */
public class PhotosResponse {
    @Expose
    private Photos photos;


    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

}
