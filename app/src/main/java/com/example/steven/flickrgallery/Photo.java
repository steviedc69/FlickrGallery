package com.example.steven.flickrgallery;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Created by Steven on 31/12/14.
 */
public class Photo implements Parcelable {

    @Expose
    private String id;
    @Expose
    private String owner;
    @Expose
    private String secret;
    @Expose
    private String server;
    @Expose
    private Integer farm;
    @Expose
    private String title;
    @Expose
    private Integer ispublic;
    @Expose
    private Integer isfriend;
    @Expose
    private Integer isfamily;



    /*public String getImageUrl()
    {
        return "nog aan vullen";
    }
*/
    public Photo(){}

    public Photo(Parcel in)
    {
        id = in.readString();
        owner = in.readString();
        secret = in.readString();
        server = in.readString();
        farm = in.readInt();
        title = in.readString();
        ispublic = in.readInt();
        isfriend = in.readInt();
        isfamily = in.readInt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer isIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    public Integer isIsfriend() {
        return isfriend;
    }

    public void setIsfriend(int isfriend) {
        this.isfriend = isfriend;
    }

    public Integer isIsfamily() {
        return isfamily;
    }

    public void setIsfamily(int isfamily) {
        this.isfamily = isfamily;
    }


    public String getPhotoUrl()
    {
        return "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+"_c.jpg";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(owner);
        dest.writeString(secret);
        dest.writeString(server);
        dest.writeInt(farm);
        dest.writeString(title);
        dest.writeInt(ispublic);
        dest.writeInt(isfriend);
        dest.writeInt(isfamily);

    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
