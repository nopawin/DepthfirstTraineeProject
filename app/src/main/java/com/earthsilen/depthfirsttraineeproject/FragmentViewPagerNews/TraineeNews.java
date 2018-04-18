package com.earthsilen.depthfirsttraineeproject.FragmentViewPagerNews;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.earthsilen.depthfirsttraineeproject.HTTPManager.HttpManager;
import com.earthsilen.depthfirsttraineeproject.Models.DepthfirstNewsModels;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Data;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Example;
import com.earthsilen.depthfirsttraineeproject.Models.TraineeNewsModels;
import com.earthsilen.depthfirsttraineeproject.R;
import com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter.RecyclerAdapterNewsDepthfirst;
import com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter.RecyclerAdapterNewsTrainee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;

public class TraineeNews extends Fragment {

    //    String url = "http://122.155.13.198/MOT_DK/mobile?cmd=listNews&startDate=26-01-2561&endDate=26-01-2561";
    String url1 = "http://122.155.13.198/MOT_DK/mobile?cmd=listNews";
    ProgressDialog dialog;
    private DepthfirstNewsModels depthfirstNewsModels;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterNewsTrainee mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String topicde;
    private List<Data> feedsList;
    private List<Data> feedsListMore;
    private int apiSize;
    SwipeRefreshLayout swipeRefreshLayout;

    public static TraineeNews newInstance() {
        TraineeNews fragment = new TraineeNews();

        return fragment;
    }

    public TraineeNews() {
    }

    View v;
    private static final String TAG = "TraineeNewsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_trainee_news, container, false);
        initView();
        loadData();

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_view);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        feedsList.clear();
                        loadData();


                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1500);
            }
        });

//GET API
        //GET API from server
//        dialog = new ProgressDialog(getActivity());
//        dialog.setMessage("Loading....");
//        dialog.show();
//
//        StringRequest request = new StringRequest(url1, new Response.Listener<String>() {
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

//        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_dfnews);
//
//        mRecyclerView.setHasFixedSize(true);
//
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        mAdapter = new RecyclerAdapterNewsDepthfirst(getActivity(), feedsList);
//        mRecyclerView.setAdapter(mAdapter);


        return v;

    }


    /**
     * private List<DepthfirstNewsModels> initPlayer() {
     * <p>
     * DepthfirstNewsModels messi = new DepthfirstNewsModels("Mr.ABC Haha", topicde);
     * DepthfirstNewsModels ronaldo = new DepthfirstNewsModels("Mr.ABC sadas", "Junior Mobile Developer");
     * DepthfirstNewsModels suarez = new DepthfirstNewsModels("Mr.ABC asdasdasdcxv", "Senior Mobile Developer");
     * <p>
     * <p>
     * List<DepthfirstNewsModels> dataset = new ArrayList<DepthfirstNewsModels>();
     * <p>
     * dataset.add(messi);
     * dataset.add(ronaldo);
     * dataset.add(suarez);
     * <p>
     * <p>
     * return dataset;
     * }
     **/

    public void initView() {
        feedsListMore = new ArrayList<>();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_traineenews);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


    }

//    public void parseJsonData(String jsonString) {
//        TraineeNewsModels item;
//        try {
//            Log.d("SD", jsonString);
//            JSONObject response = new JSONObject(jsonString);
//
//            JSONArray posts = response.optJSONArray("data");
//
//            feedsList = new ArrayList<>();
//            for (int i = 0; i < posts.length(); i++) {
//                JSONObject post = posts.optJSONObject(i);
//                item = new TraineeNewsModels();
//                item.setDesc(post.optString("topic"));
//
//                JSONArray images = post.optJSONArray("imageList");
//                for (int a = 0; a < images.length(); a++) {
//                    JSONObject getimg = images.optJSONObject(a);
//                    item.addToArray(getimg.optString("attachFileName"));
//                    Log.d("showpawa",getimg.optString("attachFileName"));
//                }
////                Log.d("SD", post.getString("title"));
////                Log.d("TestIMG", post.getString("thumbnail"));
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


    //Load API with Retrofit 2
    private void loadData() {
        String cmd = "listNews";
//        String LOGIN = "1160100149372";
//        String PASSWORD = "0877509800";

        Call<Example> call = HttpManager.getInstance().getService().repos(cmd);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {
                if (response.isSuccessful()) {
                    Example dao = response.body();
                    feedsList = dao.getData();

                    mAdapter = new RecyclerAdapterNewsTrainee(getActivity(), feedsList, mRecyclerView);
                    mRecyclerView.setAdapter(mAdapter);

                    onLoadDataMore();


                } else {
//                    textView.setText(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadDataMore(int loadPos) {
        String cmd = "listNews";
//        String LOGIN = "1160100149372";
//        String PASSWORD = "0877509800";

        Call<Example> call = HttpManager.getInstance().getService().reposLoadMore(cmd, loadPos);
//        dialog = new ProgressDialog(getActivity());
//        dialog.setMessage("Loading....");
//        dialog.show();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {
                if (response.isSuccessful()) {
                    Example dao = response.body();
                    feedsListMore.clear();
                    feedsListMore = dao.getData();
                    mAdapter.setNewData(feedsListMore);
                    mAdapter.notifyItemInserted(feedsList.size() - 1);
                    mAdapter.notifyDataSetChanged();
//
                    mAdapter.setLoaded();


                } else {
//                    textView.setText(response.errorBody().toString());
                }
//                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void onLoadDataMore() {
//        mAdapter = new RecyclerAdapterNewsDepthfirst(getActivity(), feedsList, mRecyclerView);
//        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnLoadMoreListener(new RecyclerAdapterNewsTrainee.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //add null , so the adapter will check view_type and show progress bar at bottom
//                feedsList.add(null);
//                    mAdapter.setShowProgressBar();
//                mAdapter.notifyItemInserted(feedsList.size() - 1);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //   remove progress item
//                        feedsList.remove(feedsList.size() - 1);
//                        mAdapter.notifyItemRemoved(feedsList.size() - 1);
                        //add items one by one
//                       int start = studentList.size();
//                       int end = start + 20;
//
//                       for (int i = start + 1; i <= end; i++) {
//                           studentList.add(new Student("Student " + i, "AndroidStudent" + i + "@gmail.com"));
//                            mAdapter.notifyItemInserted(studentList.size());
//                        }
//                        mAdapter.notifyItemInserted(feedsList.size());
//                        mAdapter.notifyDataSetChanged();
//                        Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();
//                        feedsListMore.clear();

                        int position = feedsList.size();

                        if (position != apiSize) {
                            loadDataMore(position);

                        } else {
                            Toast.makeText(getActivity(), "End of lists", Toast.LENGTH_SHORT).show();
                        }


                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
                    }
                }, 2000);

            }
        });
    }


}
