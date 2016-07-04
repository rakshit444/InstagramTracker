package com.jain.rakshit.instagramtrackerfinal.Model;

/**
 * Created by Rakshit on 6/22/2016.
 */

public class DBRelations {
    int user_id;
    int code;
    String outgoing_status;

    public DBRelations() {

    }

    public DBRelations(int user_id, int code, String outgoing_status) {
        this.user_id = user_id;
        this.code = code;
        this.outgoing_status = outgoing_status;
    }

    public DBRelations(int code, String outgoing_status) {
        this.code = code;
        this.outgoing_status = outgoing_status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getOutgoing_status() {
        return outgoing_status;
    }

    public void setOutgoing_status(String outgoing_status) {
        this.outgoing_status = outgoing_status;
    }
}
