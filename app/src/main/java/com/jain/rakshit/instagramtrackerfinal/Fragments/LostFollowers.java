package com.jain.rakshit.instagramtrackerfinal.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jain.rakshit.instagramtrackerfinal.Activites.MainActivity;
import com.jain.rakshit.instagramtrackerfinal.Adapters.DataAdapter;
import com.jain.rakshit.instagramtrackerfinal.Adapters.LostFollowersRecyclerViewAdapter;
import com.jain.rakshit.instagramtrackerfinal.EndPoints.RelationshipService;
import com.jain.rakshit.instagramtrackerfinal.Model.InstagramSession;
import com.jain.rakshit.instagramtrackerfinal.R;
import com.jain.rakshit.instagramtrackerfinal.Rest.Model.Datum;
import com.jain.rakshit.instagramtrackerfinal.Rest.Model.RelationshipData;
import com.jain.rakshit.instagramtrackerfinal.Rest.Model.RelationshipData1;
import com.jain.rakshit.instagramtrackerfinal.Rest.RestClient;

import java.io.IOException;
import java.util.ArrayList;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LostFollowers.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LostFollowers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LostFollowers extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    private static final String TAG = "LostFollowers";
    public ArrayList<Datum> mList;
    private View view;
    private InstagramSession mInstagramSession;

    private RealmResults<Datum> mRealm;

    private RestClient mRestClient;
    private Realm realm;

    private String outgoingStatus;
    private String oldIncommingStatus;
    private String newIncommingStatus;
    private OnFragmentInteractionListener mListener;
    private RelationshipService apiService;
    private String user_id;
    private String full_name;
    private String profile_picture;
    private DataAdapter adapter;
    private RealmConfiguration realmConfiguration;
    private RealmRecyclerView realmRecyclerView;
    private LostFollowersRecyclerViewAdapter lostFollowersRecyclerViewAdapter;
    private ProgressBar spinner;

    private ArrayList<Datum> realm11;

    public LostFollowers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param mList Parameter 1.
     * @return A new instance of fragment Following.
     */
    // TODO: Rename and change types and number of parameters
    public static LostFollowers newInstance(ArrayList<Datum> mList) {
        LostFollowers fragment = new LostFollowers();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, mList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            mList = (ArrayList<Datum>) getArguments().getSerializable(ARG_PARAM1);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.lost_followers, container, false);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onStart() {
        super.onStart();
        MainActivity mainActivity = new MainActivity();
        realm11 = new ArrayList<Datum>();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        realmConfiguration = new RealmConfiguration.Builder(getContext())
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        realm = Realm.getDefaultInstance();

        spinner = (ProgressBar) view.findViewById(R.id.progressBar1);

        spinner.setVisibility(View.VISIBLE);

        realmRecyclerView = (RealmRecyclerView) view.findViewById(R.id.realm_recycler_view);
        mInstagramSession = new InstagramSession(getActivity().getApplicationContext());

        mRestClient = new RestClient();
        apiService = mRestClient.getRelationshipService();

        Log.i(TAG, mInstagramSession.getAccessToken());
        Log.i(TAG, mInstagramSession.getId());


        final RealmResults<Datum> ss = realm.where(Datum.class).findAll();
        ss.addChangeListener(new RealmChangeListener<RealmResults<Datum>>() {
            @Override
            public void onChange(RealmResults<Datum> element) {
                Log.i(TAG, ss.toString() + "");
            }
        });


        new DownloadFilesTask().execute(mList, null, null);


    }

    public void recycleView() {

        realm = Realm.getDefaultInstance();
        mRealm = realm.where(Datum.class).findAll();
        Log.i(TAG, mRealm + " OnPostExecute");
        lostFollowersRecyclerViewAdapter = new LostFollowersRecyclerViewAdapter(getActivity().getApplicationContext(), mRealm, true, true);

        realmRecyclerView.setAdapter(lostFollowersRecyclerViewAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Remove the listener.

        // Close the realm instance.
        realm.close();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class DownloadFilesTask extends AsyncTask<ArrayList<Datum>, Integer, Long> {
        @Override
        protected Long doInBackground(ArrayList<Datum>... params) {

            realm = Realm.getDefaultInstance();
            for (int i = 0; i < mList.size(); i++) {
                user_id = mList.get(i).getId();
                profile_picture = mList.get(i).getProfilePicture();
                full_name = mList.get(i).getUsername();
                Call<RelationshipData> call1 = apiService.getRelation(user_id, mInstagramSession.getAccessToken());

                Log.i(TAG, "SETTING ID BEFORE CALLING " + user_id);

                try {
                    RelationshipData mRelationshipData = call1.execute().body();

                    Log.i(TAG, "SETTING ID " + user_id);

                    RelationshipData1 users1 = mRelationshipData.getData();

                    users1.setId(user_id);

                    realm.beginTransaction();

                    if (!mInstagramSession.getFirst_time_variable().equals("true")) {

                        Log.i(TAG, mInstagramSession.getFirst_time_variable() + "First time initialization");

                        RealmResults<RelationshipData1> query = realm.where(RelationshipData1.class)
                                .equalTo("id", user_id).findAll();

                        oldIncommingStatus = query.get(0).getIncomingStatus();

                        Log.i(TAG, "QUERY INITIATED " + oldIncommingStatus);

                        outgoingStatus = query.get(0).getOutgoingStatus();

                    }
                    realm.copyToRealmOrUpdate(users1);

                    realm.commitTransaction();

                    RealmResults<RelationshipData1> query1 = realm.where(RelationshipData1.class)
                            .equalTo("id", user_id).findAll();


                    newIncommingStatus = query1.get(0).getIncomingStatus();
                    Log.i(TAG, "2nd QUERY " + newIncommingStatus);

                    Log.i(TAG, users1.getOutgoingStatus() + "");
                    Log.i(TAG, users1.getIncomingStatus() + "");
                    Log.i(TAG, users1.getTargetUserIsPrivate() + "");

                    if (!mInstagramSession.getFirst_time_variable().equals("true")) {
                        Log.i(TAG, mInstagramSession.getFirst_time_variable() + "First time initialization");

                        if ((outgoingStatus.equals("follows")) && (!oldIncommingStatus.equals(newIncommingStatus))
                                && (oldIncommingStatus.equals("followed_by"))) {
                            Log.i(TAG, "Un Follow");
                            final Datum datum = new Datum();
                            datum.setId(user_id);
                            datum.setProfilePicture(profile_picture);
                            datum.setFullName(full_name);
                            realm.executeTransaction(new Realm.Transaction() {

                                @Override
                                public void execute(Realm realm) {

                                    realm.copyToRealmOrUpdate(datum);

                                }
                            });


                        } else if (outgoingStatus.equals("follows") && (!oldIncommingStatus.equals(newIncommingStatus))
                                && newIncommingStatus.equals("followed_by")) {
                            Log.i(TAG, "Followed Back");
                            realm.beginTransaction();
                            realm.where(Datum.class).beginsWith("id", user_id).findAll().deleteFirstFromRealm();
                            realm.commitTransaction();

                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {

        }


        protected void onPostExecute(Long result) {
            if (mInstagramSession.getFirst_time_variable().equals("true")) {
                mInstagramSession.setFirst_time_variable();
            }
            spinner.setVisibility(View.GONE);
            recycleView();
        }
    }
}
