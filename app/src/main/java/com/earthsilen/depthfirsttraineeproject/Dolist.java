package com.earthsilen.depthfirsttraineeproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.earthsilen.depthfirsttraineeproject.HTTPManager.HttpManager;
import com.earthsilen.depthfirsttraineeproject.HTTPManager.HttpManagerDolist;
import com.earthsilen.depthfirsttraineeproject.Models.DepthfirstNewsModels;
import com.earthsilen.depthfirsttraineeproject.Models.DoListModels.DoListModel;
import com.earthsilen.depthfirsttraineeproject.Models.DoListModels.Row;
import com.earthsilen.depthfirsttraineeproject.Models.DolistModels;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Example;
import com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter.RecyclerAdapterDolist;
import com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter.RecyclerAdapterNewsDepthfirst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Dolist extends Fragment {

    View v;
    FloatingActionButton addlist;
    ProgressDialog dialogLoadData, dialoagLoadDataMore;
//    String url = "http://eservice.dla.go.th/mobile?serviceName=WEBWS002&ORG_CODE=178";
    RecyclerView mRecyclerView;
    private int apiSize;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapterDolist mAdapter;

    @Override
    public void onResume() {
        super.onResume();
        if(feedsList != null) {
            feedsList.clear();
            loadData();
            //Toast.makeText(getActivity(), "123", Toast.LENGTH_SHORT).show();
        }
    }

    private List<Row> feedsList;
    private List<Row> feedsListMore;
    private ImageView nodataImg;
    private TextView textNotiNoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_dolist, container, false);

        initFab();
        initView();
        loadData();
        //GET API from server

//        dialog.setMessage("Loading....");
//        dialog.show();
//
//        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String string) {
//                parseJsonData(string);
//                initView();
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(getActivity(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });
//
//        RequestQueue rQueue = Volley.newRequestQueue(getActivity());
//        rQueue.add(request);

        return v;

    }

    public void initFab() {
        addlist = (FloatingActionButton) v.findViewById(R.id.fabBtn);
        addlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getActivity(), CreateList.class);
                startActivity(a);
            }
        });
    }


    public void initView() {

        nodataImg = (ImageView) v.findViewById(R.id.no_data_icon);
        textNotiNoData = (TextView)v.findViewById(R.id.txt_sorry);
        nodataImg.setVisibility(View.GONE);
        textNotiNoData.setVisibility(View.GONE);
        //init Recycler and Adapter
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_dolist);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


    }

//    public void parseJsonData(String jsonString) {
//        try {
//            Log.d("SD", jsonString);
//            JSONObject response = new JSONObject(jsonString);
//
//            JSONArray posts = response.optJSONArray("rows");
//
//            feedsList = new ArrayList<>();
//            for (int i = 0; i < posts.length(); i++) {
//                JSONObject post = posts.optJSONObject(i);
//                DolistModels item = new DolistModels();
//                item.setTitle(post.optString("NAME"));
//                item.setNote(post.optString("ORG_NAME"));
//                item.setDate(post.optString("POSITION"));
//                Log.d("SD", post.getString("NAME"));
//                Log.d("TestIMG", post.getString("ORG_NAME"));
//
//                feedsList.add(item);
//
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        dialog.dismiss();
//
//    }

    private void loadData() {
        String serviceName = "WEBWS002";
        int orgCode = 178;
        dialogLoadData = new ProgressDialog(getActivity());
        dialogLoadData.setMessage("Loading....");
        dialogLoadData.setCanceledOnTouchOutside(false);
        dialogLoadData.show();

        Call<DoListModel> call = HttpManagerDolist.getInstance().getService().resposDolist(serviceName, orgCode);
        call.enqueue(new Callback<DoListModel>() {
            @Override
            public void onResponse(Call<DoListModel> call, retrofit2.Response<DoListModel> response) {
                if (response.isSuccessful()) {
                    DoListModel dao = response.body();
                    if (dao == null) {
                        Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
                    }
                    //apiSize = dao.getSize();
                    feedsList = dao.getRows();
                    mAdapter = new RecyclerAdapterDolist(getActivity(), feedsList, mRecyclerView);
                    mRecyclerView.setAdapter(mAdapter);

//                    onLoadDataMore();


                    //feedsListMore = dao.getData();
                    //feedsListInit.clear();
//                    onSendInitDataToRecyclerView(feedsList);

                    dialogLoadData.dismiss();


                } else {
//                    textView.setText(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<DoListModel> call, Throwable t) {
                //Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                dialogLoadData.dismiss();
                nodataImg.setVisibility(View.VISIBLE);
                textNotiNoData.setTextColor(Color.BLACK);
                textNotiNoData.setText("Sorry, Data is not available. \nIt might be happened from the internet or server");
                textNotiNoData.setVisibility(View.VISIBLE);
            }
        });


    }

//    private void onLoadDataMore() {
////        mAdapter = new RecyclerAdapterNewsDepthfirst(getActivity(), feedsList, mRecyclerView);
////        mRecyclerView.setAdapter(mAdapter);
//
//
//        mAdapter.setOnLoadMoreListener(new RecyclerAdapterNewsDepthfirst.OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                //add null , so the adapter will check view_type and show progress bar at bottom
////                feedsList.add(null);
////                    mAdapter.setShowProgressBar();
////                mAdapter.notifyItemInserted(feedsList.size() - 1);
//
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //   remove progress item
////                        feedsList.remove(feedsList.size() - 1);
////                        mAdapter.notifyItemRemoved(feedsList.size() - 1);
//                        //add items one by one
////                       int start = studentList.size();
////                       int end = start + 20;
////
////                       for (int i = start + 1; i <= end; i++) {
////                           studentList.add(new Student("Student " + i, "AndroidStudent" + i + "@gmail.com"));
////                            mAdapter.notifyItemInserted(studentList.size());
////                        }
////                        mAdapter.notifyItemInserted(feedsList.size());
////                        mAdapter.notifyDataSetChanged();
////                        Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();
////                        feedsListMore.clear();
//
//                        int position = feedsList.size();
//
//                        if (position != apiSize) {
//                            loadDataMore(position);
//
//                        } else {
//                            Toast.makeText(getActivity(), "End of lists", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
//                    }
//                }, 2000);
//
//            }
//        });
//    }

    public void loadDataMore(int loadPos) {
        String cmd = "listNews";
//        String LOGIN = "1160100149372";
//        String PASSWORD = "0877509800";
        dialoagLoadDataMore = new ProgressDialog(getActivity());
        dialoagLoadDataMore.setMessage("Loading....");
        dialoagLoadDataMore.setCanceledOnTouchOutside(false);
        dialoagLoadDataMore.show();
        Call<DoListModel> call = HttpManagerDolist.getInstance().getService().resposDolist(cmd, loadPos);
//        dialog = new ProgressDialog(getActivity());
//        dialog.setMessage("Loading....");
//        dialog.show();
        call.enqueue(new Callback<DoListModel>() {
            @Override
            public void onResponse(Call<DoListModel> call, retrofit2.Response<DoListModel> response) {
                if (response.isSuccessful()) {
                    DoListModel dao = response.body();
                    feedsListMore.clear();
                    feedsListMore = dao.getRows();
                    mAdapter.setNewData(feedsListMore);
                    mAdapter.notifyItemInserted(feedsList.size() - 1);
                    mAdapter.notifyDataSetChanged();
//
                    mAdapter.setLoaded();


                } else {
//                    textView.setText(response.errorBody().toString());
                }
                dialoagLoadDataMore.dismiss();
            }

            @Override
            public void onFailure(Call<DoListModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                dialoagLoadDataMore.dismiss();
            }
        });


    }

}
