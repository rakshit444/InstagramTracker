package com.jain.rakshit.instagramtrackerfinal.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jain.rakshit.instagramtrackerfinal.Activites.MainActivity;
import com.jain.rakshit.instagramtrackerfinal.Adapters.DataAdapter;
import com.jain.rakshit.instagramtrackerfinal.EndPoints.RelationshipService;
import com.jain.rakshit.instagramtrackerfinal.Model.InstagramSession;
import com.jain.rakshit.instagramtrackerfinal.R;
import com.jain.rakshit.instagramtrackerfinal.Rest.Model.Datum;
import com.jain.rakshit.instagramtrackerfinal.Rest.Model.User;
import com.jain.rakshit.instagramtrackerfinal.Rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Following.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Following#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Following extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Following";
    public ArrayList<Datum> mList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DataAdapter adapter;
    private RecyclerView recyclerView;
    private View view;
    private InstagramSession mInstagramSession;
    private RestClient mRestClient;
    private Context mContext;
    private int i;
    private ProgressBar spinner;

    private OnFragmentInteractionListener mListener;

    public Following() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Following.
     */
    // TODO: Rename and change types and number of parameters
    public static Following newInstance(String param1, String param2) {
        Following fragment = new Following();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Log.i(TAG, mParam1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.following, container, false);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        MainActivity mainActivity = new MainActivity();
        mList = new ArrayList<Datum>();
        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        Log.i("TAG", "recycleview layout is set");

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        spinner = (ProgressBar) view.findViewById(R.id.progressBar1);

        spinner.setVisibility(View.VISIBLE);

        mInstagramSession = new InstagramSession(getActivity().getApplicationContext());
        mRestClient = new RestClient();
        RelationshipService apiService = mRestClient.getRelationshipService();

        Log.i(TAG, mInstagramSession.getAccessToken());
        Log.i(TAG, mInstagramSession.getId());
        Call<User> call1 = apiService.getList(mInstagramSession.getId(), mInstagramSession.getAccessToken());

        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                int statusCode = response.code();
                User users = response.body();
                List<Datum> users1 = users.getData();

                for (i = 0; i < users1.size(); i++) {
                    Log.i("TAG", users1.get(i).getId());
                    mList.add(new Datum(users1.get(i).getFullName(), users1.get(i).getId(), users1.get(i).getProfilePicture()));
                }

                onFragmentMessage(mList);
                Log.d("sssssssssssssssss", users1.size() + "");
                adapter = new DataAdapter(mList, getActivity().getApplicationContext());

                spinner.setVisibility(View.GONE);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Log.i("onFailure", t.getMessage());
            }
        });

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onFragmentMessage(ArrayList<Datum> mList) {
        mListener.onFragmentMessage(mList);
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
        public void onFragmentMessage(ArrayList<Datum> mList);
    }
}
