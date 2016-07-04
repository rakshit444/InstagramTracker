package com.jain.rakshit.instagramtrackerfinal.Rest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RelationshipData extends RealmObject {

    @PrimaryKey
    private int id;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    @SerializedName("data")
    @Expose
    private RelationshipData1 data;

    /**
     * @return The meta
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    public RelationshipData1 getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(RelationshipData1 data) {
        this.data = data;
    }

}

