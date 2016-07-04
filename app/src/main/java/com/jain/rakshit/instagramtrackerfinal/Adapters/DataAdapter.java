package com.jain.rakshit.instagramtrackerfinal.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jain.rakshit.instagramtrackerfinal.R;
import com.jain.rakshit.instagramtrackerfinal.Rest.Model.Datum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context mcontext;
    private ArrayList<Datum> android;

    public DataAdapter(ArrayList<Datum> android, Context mcontext) {

        this.android = android;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.tv_version.setText(android.get(i).getUsername());
        Picasso.with(mcontext).load(android.get(i).getProfilePicture()).into(viewHolder.profile_picture);
        //viewHolder.tv_api_level.setText(android.get(i).getTruncated());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_version;
        private ImageView profile_picture;

        public ViewHolder(View view) {
            super(view);

            tv_version = (TextView) view.findViewById(R.id.user_name);
            profile_picture = (ImageView) view.findViewById(R.id.profile_picture);

        }
    }
}
