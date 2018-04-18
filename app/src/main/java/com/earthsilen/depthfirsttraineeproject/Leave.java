package com.earthsilen.depthfirsttraineeproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.earthsilen.depthfirsttraineeproject.Models.DolistModels;
import com.earthsilen.depthfirsttraineeproject.Models.LeaveModels;
import com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter.RecyclerAdapterDolist;
import com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter.RecyclerAdapterLeave;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Leave extends Fragment {

    String url = "http://eservice.dla.go.th/mobile?serviceName=WEBWS002&ORG_CODE=178";
    FloatingActionButton createleave;
    ProgressDialog dialog;
    RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<LeaveModels> feedsList;

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_leave, container, false);
            initFAB();
        //GET API from server
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading....");
        dialog.show();

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
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


    //Init Method below

    private void initFAB(){

        createleave = (FloatingActionButton)v.findViewById(R.id.fabBtnLeave);

        createleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getActivity(), LeaveCreate.class);
                startActivity(j);
            }
        });
    }

    //Initial view for creating list view
    public void initView() {


        //init Recycler and Adapter
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_leave);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerAdapterLeave(getActivity(), feedsList);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void parseJsonData(String jsonString) {
        try {
            Log.d("SD", jsonString);
            JSONObject response = new JSONObject(jsonString);

            JSONArray posts = response.optJSONArray("rows");

            feedsList = new ArrayList<>();
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                LeaveModels item = new LeaveModels();
                item.setReason(post.optString("NAME"));
                item.setDetails(post.optString("ORG_NAME"));
                item.setFromDate(post.optString("POSITION"));
                item.setToDate(post.optString("POSITION"));
                Log.d("SD", post.getString("NAME"));
                Log.d("TestIMG", post.getString("ORG_NAME"));

                feedsList.add(item);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        dialog.dismiss();

    }


}
