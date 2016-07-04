package com.jain.rakshit.instagramtrackerfinal.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jain.rakshit.instagramtrackerfinal.R;
import com.jain.rakshit.instagramtrackerfinal.Rest.Model.Datum;
import com.squareup.picasso.Picasso;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by Rakshit on 6/24/2016.
 */

public class LostFollowersRecyclerViewAdapter extends RealmBasedRecyclerViewAdapter<Datum, LostFollowersRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private RealmResults<Datum> realmResults1;

    public LostFollowersRecyclerViewAdapter(Context context,
                                            RealmResults<Datum> realmResults1,
                                            boolean automaticUpdate,
                                            boolean animateIdType) {
        super(context, realmResults1, automaticUpdate, animateIdType);
        Log.i("Adapter", "Super called");
        this.context = context;
        this.realmResults1 = realmResults1;
    }


    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        Log.i("Adapter", "setting view");
        View v = inflater.inflate(R.layout.card_row, viewGroup, false);
        ViewHolder vh = new ViewHolder((LinearLayout) v);
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final Datum datum = realmResults1.get(position);
        viewHolder.tv_version.setText(datum.getFullName());
        Picasso.with(context).load(datum.getProfilePicture()).into(viewHolder.profile_picture);

    }

    @Override
    public int getItemCount() {
        Log.i("Adapter", "getting count");
        return realmResults1.size();
    }

    public class ViewHolder extends RealmViewHolder {
        private TextView tv_version;
        private ImageView profile_picture;

        public ViewHolder(LinearLayout container) {
            super(container);
            Log.i("Adapter", "Initializing");
            tv_version = (TextView) container.findViewById(R.id.user_name);
            profile_picture = (ImageView) container.findViewById(R.id.profile_picture);
        }

    }

}
