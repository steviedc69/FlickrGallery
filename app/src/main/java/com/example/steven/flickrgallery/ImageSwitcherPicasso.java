package com.example.steven.flickrgallery;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageSwitcher;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Steven on 17/01/15.
 */
public class ImageSwitcherPicasso implements Target {
   private ImageSwitcher switcher;
    private Context context;


    public ImageSwitcherPicasso(Context context, ImageSwitcher img)
    {
        this.context = context;
        this.switcher = img;

    }


    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {

        switcher.setImageDrawable(new BitmapDrawable(context.getResources(),bitmap));

    }

    @Override
    public void onBitmapFailed(Drawable drawable) {

    }

    @Override
    public void onPrepareLoad(Drawable drawable) {

    }
}
