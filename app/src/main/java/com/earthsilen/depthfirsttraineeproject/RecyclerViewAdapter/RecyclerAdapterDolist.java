package com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.earthsilen.depthfirsttraineeproject.DetailFromList.NewsDetails;
import com.earthsilen.depthfirsttraineeproject.ItemClickListener;
import com.earthsilen.depthfirsttraineeproject.Models.DepthfirstNewsModels;
import com.earthsilen.depthfirsttraineeproject.Models.DoListModels.DoListModel;
import com.earthsilen.depthfirsttraineeproject.Models.DoListModels.Row;
import com.earthsilen.depthfirsttraineeproject.Models.DolistModels;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Data;
import com.earthsilen.depthfirsttraineeproject.OnLoadMoreListener;
import com.earthsilen.depthfirsttraineeproject.R;

import java.util.List;


public class RecyclerAdapterDolist extends RecyclerView.Adapter<RecyclerAdapterDolist.ViewHolder> {

    private List<Row> mNewsDepth;
    public Context mContext;
    private RecyclerView mRecyclerView;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;


    // The minimum amount of items to have below your current scroll position
// before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
        public TextView mTitle;
        public TextView mDate;
        private ItemClickListener itemClickListener;

        public ViewHolder(View view) {
            super(view);


            mTitle = (TextView) view.findViewById(R.id.txt_title_dolist);
            mDate = (TextView) view.findViewById(R.id.txt_date_dolist);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);


//            mImg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent zoom = new Intent()
//                }
//            });

        }

        public void setOnClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false, null);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true, null);
            return true;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            itemClickListener.onClick(v, getAdapterPosition(), false, event);
            return true;
        }
    }

    public RecyclerAdapterDolist(Context context, List<Row> dataset, RecyclerView recyclerView) {
        mNewsDepth = dataset;
        mContext = context;
        mRecyclerView = recyclerView;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();


            recyclerView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);

                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                                // End has been reached
                                // Do something
                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore();
                                }
                                loading = true;
                            }
                        }
                    });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mNewsDepth.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.recycler_list_dolist, parent, false);

            vh = new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progressbar, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Row row = mNewsDepth.get(position);

        viewHolder.mTitle.setText(row.getNAME());
        viewHolder.mDate.setText(row.getORGNAME());


        viewHolder.setOnClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, final int position, boolean isLongClick, MotionEvent motionEvent) {
//                if (isLongClick) {
////                    Toast.makeText(mContext, depthfirstNewsModels.getDesc(), Toast.LENGTH_SHORT).show();
////
////                    Bundle imgzoom = new Bundle();
////                    imgzoom.putInt("imgPathPos", position);
////                    Intent zoom = new Intent(mContext, ImageZoom.class);
////                    zoom.putExtras(imgzoom);
////                    mContext.startActivity(zoom);
//
//
//                } else {
//                    //Bundle bundle = new Bundle();
//                    //bundle.putInt("arraypos", position);
//
//                    Intent details = new Intent(mContext, NewsDetails.class);
//                    //details.putExtras(bundle);
//                    details.putExtra("test", mNewsDepth.get(position));
//                    mContext.startActivity(details);
//
//                    //Toast.makeText(mContext, depthfirstNewsModels.getDesc(), Toast.LENGTH_SHORT).show();
//                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return (null != mNewsDepth ? mNewsDepth.size() : 0);
    }

    public void setNewData(List<Row> newData) {
        mNewsDepth.addAll(newData);
    }

    public static class ProgressViewHolder extends ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }


    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoaded() {
        loading = false;
    }
}