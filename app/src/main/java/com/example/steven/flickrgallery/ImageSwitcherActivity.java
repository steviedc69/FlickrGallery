package com.example.steven.flickrgallery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ImageSwitcherActivity extends Activity {

    private static final String TAG = "ImageSwitcherActivity";
    private ImageSwitcher switcher;
    private ArrayList<String>photosUrls;
    private String beginPhoto;
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher);

        if(savedInstanceState != null)
        {
            photosUrls = savedInstanceState.getStringArrayList("list");
            beginPhoto = savedInstanceState.getString("current");
        }
        else
        {
            photosUrls = getIntent().getExtras().getStringArrayList("list");
            beginPhoto = getIntent().getExtras().getString("current");
        }

        setCurrent();

        Log.d(TAG,"beginPhoto "+beginPhoto);


        switcher = (ImageSwitcher)findViewById(R.id.switcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView view = new ImageView(getApplicationContext());
                view.setScaleType(ImageView.ScaleType.CENTER);
                view.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return view;
            }
        });

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);

        switcher.setAnimation(in);
        switcher.setOutAnimation(out);

        ImageSwitcherPicasso mImageSPicasso = new ImageSwitcherPicasso(this,switcher);
        Picasso.with(this).load(beginPhoto).into(mImageSPicasso);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("list",photosUrls);
        outState.putString("current",photosUrls.get(current));
    }

    public void vorige(View view)
    {
        if(current != 0)
        {
            current --;
            ImageSwitcherPicasso mImageSPicasso = new ImageSwitcherPicasso(this,switcher);
            Picasso.with(this).load(photosUrls.get(current)).into(mImageSPicasso);
        }

    }
    public void volgende(View view)
    {
        if(current != photosUrls.size()-1)
        {
            current++;
            ImageSwitcherPicasso mImageSPicasso = new ImageSwitcherPicasso(this,switcher);
            Picasso.with(this).load(photosUrls.get(current)).into(mImageSPicasso);
        }
    }

    private void setCurrent()
    {
        if(this.photosUrls!=null && !this.beginPhoto.isEmpty())
        {
            this.current = photosUrls.indexOf(beginPhoto);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_switcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
