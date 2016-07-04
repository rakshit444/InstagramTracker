package com.jain.rakshit.instagramtrackerfinal.Rest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Rakshit on 6/22/2016.
 */

public class RelationshipData1 extends RealmObject {


    @PrimaryKey
    private String id;
    @SerializedName("outgoing_status")
    @Expose
    private String outgoingStatus;
    @SerializedName("target_user_is_private")
    @Expose
    private Boolean targetUserIsPrivate;
    @SerializedName("incoming_status")
    @Expose
    private String incomingStatus;

    /**
     * @return The outgoingStatus
     */
    public String getOutgoingStatus() {
        return outgoingStatus;
    }

    /**
     * @param outgoingStatus The outgoing_status
     */
    public void setOutgoingStatus(String outgoingStatus) {
        this.outgoingStatus = outgoingStatus;
    }

    /**
     * @return The targetUserIsPrivate
     */
    public Boolean getTargetUserIsPrivate() {
        return targetUserIsPrivate;
    }

    /**
     * @param targetUserIsPrivate The target_user_is_private
     */
    public void setTargetUserIsPrivate(Boolean targetUserIsPrivate) {
        this.targetUserIsPrivate = targetUserIsPrivate;
    }

    /**
     * @return The incomingStatus
     */
    public String getIncomingStatus() {
        return incomingStatus;
    }

    /**
     * @param incomingStatus The incoming_status
     */
    public void setIncomingStatus(String incomingStatus) {
        this.incomingStatus = incomingStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}