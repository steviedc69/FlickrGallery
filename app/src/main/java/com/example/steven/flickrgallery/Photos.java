package com.example.steven.flickrgallery;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by Steven on 30/12/14.
 */
public class Photos {

    @Expose
    private Integer page;
    @Expose
    private Integer pages;
    @Expose
    private Integer perpage;
    @Expose
    private String total;
    @Expose
    private List<Photo> photo;
    @Expose
    private String stat;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}


