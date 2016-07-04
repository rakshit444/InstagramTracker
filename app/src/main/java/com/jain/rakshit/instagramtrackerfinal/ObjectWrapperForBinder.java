package com.jain.rakshit.instagramtrackerfinal;

import android.os.Binder;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rakshit on 6/22/2016.
 */
public class ObjectWrapperForBinder extends Binder implements Parcelable {

    public static final Parcelable.Creator<ObjectWrapperForBinder> CREATOR = new Parcelable.Creator<ObjectWrapperForBinder>() {
        public ObjectWrapperForBinder createFromParcel(Parcel in) {
            return (ObjectWrapperForBinder) in.readStrongBinder();
        }

        public ObjectWrapperForBinder[] newArray(int size) {
            return new ObjectWrapperForBinder[size];
        }
    };
    private final Object mData;

    public ObjectWrapperForBinder(Object data) {
        mData = data;
    }

    public Object getData() {
        return mData;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeStrongBinder(this);
    }
}
