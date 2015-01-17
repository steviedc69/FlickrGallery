package com.example.steven.flickrgallery;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Steven on 17/01/15.
 */
public class GridViewAdapter extends ArrayAdapter {
    private static final String TAG = "GridViewAdapter";

    private Context context;
    private int layoutResource;
    private ArrayList<Photo> data;
    private FlickrGalleryActivity activity;

    public GridViewAdapter(Context context, int resource, ArrayList<Photo> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutResource = resource;
        this.data = data;
        activity = (FlickrGalleryActivity)context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResource,parent,false);
            holder = new ViewHolder();
            holder.imageView = (ImageView)row.findViewById(R.id.image);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }
        final String url = data.get(position).getPhotoUrl();
        Log.d(TAG,url);
        Picasso.with(context).load(url).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onClickImage(data,url);
            }
        });
        return row;
    }

    static class ViewHolder
    {
        ImageView imageView;
    }
}
