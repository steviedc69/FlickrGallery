package com.example.steven.flickrgallery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FlickrGalleryActivity extends Activity {

    private GridView gridView;
    private Button button;
    private EditText search;
    private static final String TAG = "FlickrGalleryActivity";
    private ArrayList<Photo> photolist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_gallery);

        gridView = (GridView)findViewById(R.id.image_grid);
        button = (Button)findViewById(R.id.search_button);
        search = (EditText)findViewById(R.id.search);

        if(savedInstanceState != null && savedInstanceState.containsKey("fotos"))
        {
            photolist = savedInstanceState.getParcelableArrayList("fotos");
            GridViewAdapter adapter = new GridViewAdapter(this,R.layout.flick_img,photolist);
            gridView.setAdapter(adapter);

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!search.getText().toString().isEmpty())
                {
                  FlickrRestClient flickrRestClient = new FlickrRestClient();
                    flickrRestClient.doFlickrSearch(search.getText().toString(),5,FlickrGalleryActivity.this);
                }
            }
        });

    }

    public void fillGridView(List<Photo> flickrPhotos)
    {
        this.photolist = (ArrayList)flickrPhotos;
        Log.d(TAG,"fillGrid size : "+flickrPhotos.size());
      GridViewAdapter adapter = new GridViewAdapter(this,R.layout.flick_img,(ArrayList)flickrPhotos);
      gridView.setAdapter(adapter);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(photolist != null)
        {
            outState.putParcelableArrayList("fotos",photolist);
        }
    }

    public void onClickImage(ArrayList<Photo>fotos,String huidigeUrl)
    {
        ArrayList<String> fotoStrings = new ArrayList<String>();
        for (Photo p : fotos)
        {
            fotoStrings.add(p.getPhotoUrl());
        }
        Intent intent = new Intent(this,ImageSwitcherActivity.class);
        intent.putExtra("list",fotoStrings);
        intent.putExtra("current",huidigeUrl);
        this.startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.flickr_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
