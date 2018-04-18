package com.earthsilen.depthfirsttraineeproject.FragmentViewPagerContacts;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.earthsilen.depthfirsttraineeproject.Models.TraineeTeamModels;
import com.earthsilen.depthfirsttraineeproject.Models.WebTeamModels;
import com.earthsilen.depthfirsttraineeproject.R;
import com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter.RecyclerAdapterContactTrainee;
import com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter.RecyclerAdapterContactWeb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TraineeContacts extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog dialog;
    private List<TraineeTeamModels> feedsList;
    String url = String.format("http://eservice.dla.go.th/mobile?serviceName=WEBWS002&ORG_CODE=178");

    public static TraineeContacts newInstance() {
        TraineeContacts fragment = new TraineeContacts();

        return fragment;
    }

    public TraineeContacts() { }

    View v;
    private static final String TAG = "TraineeNewsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_trainee_contacts, container, false);

        //GET API
        //GET API from server
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading....");
        dialog.show();

        StringRequest request = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                parseJsonData(string);
                initView();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(getActivity());
        rQueue.add(request);


        return v;

    }

    private void initView(){
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerAdapterContactTrainee(getActivity(), feedsList);
        mRecyclerView.setAdapter(mAdapter);
    }


    //Parse JSON data
    public void parseJsonData(String jsonString) {
        try {
            Log.d("SD",jsonString);
            JSONObject response = new JSONObject(jsonString);

            JSONArray posts = response.optJSONArray("rows");

            feedsList = new ArrayList<>();
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                TraineeTeamModels item = new TraineeTeamModels();
                item.setName(post.optString("NAME"));
                item.setRank(post.optString("POSITION"));
                item.setImgurl(post.optString("IMAGE"));
                Log.d("SD", post.getString("NAME"));
                Log.d("TestIMG", post.getString("IMAGE"));

                feedsList.add(item);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        dialog.dismiss();

    }

//    private List<TraineeTeamModels> initPlayer() {
//
//        TraineeTeamModels messi = new TraineeTeamModels("1","Mr.Test", "Trainee");
//        TraineeTeamModels ronaldo = new TraineeTeamModels("1","Mr.ABC", "Trainee");
//        TraineeTeamModels suarez = new TraineeTeamModels("1","Mr.AB", "Trainee");
//
//        List<TraineeTeamModels> dataset = new ArrayList<TraineeTeamModels>();
//
//        dataset.add(messi);
//        dataset.add(ronaldo);
//        dataset.add(suarez);
//
//        return dataset;
//    }


}