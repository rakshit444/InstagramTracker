package com.jain.rakshit.instagramtrackerfinal.Rest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class User {

    @SerializedName("pagination")
    @Expose
    private Pagination pagination;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    /**
     * @return The pagination
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * @param pagination The pagination
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    /**
     * @return The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * @param meta The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * @return The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }

}