package com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
import com.earthsilen.depthfirsttraineeproject.DetailFromList.NewsDetailsTrainee;
import com.earthsilen.depthfirsttraineeproject.ImageZoomFromList.ImageZoom;
import com.earthsilen.depthfirsttraineeproject.ImageZoomFromList.ImageZoomNewsTrainee;
import com.earthsilen.depthfirsttraineeproject.ItemClickListener;
import com.earthsilen.depthfirsttraineeproject.Models.DepthfirstNewsModels;
import com.earthsilen.depthfirsttraineeproject.Models.NewsModels.Data;
import com.earthsilen.depthfirsttraineeproject.Models.TraineeNewsModels;
import com.earthsilen.depthfirsttraineeproject.Models.TraineeTeamModels;
import com.earthsilen.depthfirsttraineeproject.Models.WebTeamModels;
import com.earthsilen.depthfirsttraineeproject.News;
import com.earthsilen.depthfirsttraineeproject.R;

import java.util.List;

public class RecyclerAdapterNewsTrainee extends RecyclerView.Adapter<RecyclerAdapterNewsTrainee.ViewHolder> {

    private List<Data> mNewsDepth;
    private Context mContext;
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
        public TextView mDesc, mTime;
        public ImageView mImg;
        private ItemClickListener itemClickListener;

        public ViewHolder(View view) {
            super(view);


            mDesc = (TextView) view.findViewById(R.id.newsdesc);
            mTime = (TextView) view.findViewById(R.id.news_datetime);
            mImg = (ImageView) view.findViewById(R.id.imgshow1);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

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

    public RecyclerAdapterNewsTrainee(Context context, List<Data> dataset, RecyclerView recyclerView) {
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
                            visibleThreshold = linearLayoutManager.getChildCount();
                            totalItemCount = linearLayoutManager.getItemCount();
                            lastVisibleItem = linearLayoutManager
                                    .findLastVisibleItemPosition();
                            if (!loading
                                    && visibleThreshold + lastVisibleItem >= totalItemCount) {
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
                    R.layout.recycler_list_news, parent, false);

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
        final Data data = mNewsDepth.get(position);

        Glide.with(mContext).load("http://122.155.13.198/MOT_DK/download/"+data.getImageList().get(0).getAttachFileName()).into(viewHolder.mImg);
        viewHolder.mDesc.setTextSize(17);
        viewHolder.mDesc.setTextColor(Color.BLACK);
        viewHolder.mDesc.setText(data.getTopic());

        viewHolder.mTime.setTypeface(viewHolder.mTime.getTypeface(), Typeface.ITALIC);
        viewHolder.mTime.setText(data.getNewsDateLabel());


        //Click on image for zooming
        viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgZoom = new Intent(mContext, ImageZoomNewsTrainee.class);
                imgZoom.putExtra("imgPathZ",mNewsDepth.get(position).getImageList().get(0));
                mContext.startActivity(imgZoom);
            }
        });

        viewHolder.setOnClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick, MotionEvent motionEvent) {
                if (isLongClick) {
                    //Toast.makeText(mContext, traineeNewsModels.getDesc(), Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(mContext, traineeNewsModels.getDesc(), Toast.LENGTH_SHORT).show();

//                    Bundle bundle = new Bundle();
//                    bundle.putInt("arraypos", position);

                    Intent details = new Intent(mContext, NewsDetailsTrainee.class);
                    //details.putExtras(bundle);
                    details.putExtra("test", mNewsDepth.get(position));
                    mContext.startActivity(details);

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mNewsDepth ? mNewsDepth.size() : 0);
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

    public static class ProgressViewHolder extends RecyclerAdapterNewsTrainee.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }

    public void setNewData(List<Data> newData) {
        mNewsDepth.addAll(newData);
    }
}