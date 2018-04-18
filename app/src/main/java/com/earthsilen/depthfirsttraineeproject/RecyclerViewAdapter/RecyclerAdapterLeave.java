package com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.earthsilen.depthfirsttraineeproject.ItemClickListener;
import com.earthsilen.depthfirsttraineeproject.Models.LeaveModels;
import com.earthsilen.depthfirsttraineeproject.R;

import java.util.List;



public class RecyclerAdapterLeave extends RecyclerView.Adapter<RecyclerAdapterLeave.ViewHolder> {

    private List<LeaveModels> mNewsDepth;
    public Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
        public TextView mFromDate;
        public TextView mToDate;
        public TextView mDetails;
        public ImageView mReasonImg;
        private ItemClickListener itemClickListener;

        public ViewHolder(View view) {
            super(view);


            mFromDate = (TextView) view.findViewById(R.id.txt_fromdate_leave);
            mToDate = (TextView) view.findViewById(R.id.txt_todate_leave);
            mDetails = (TextView) view.findViewById(R.id.txt_details_leave);
            mReasonImg = (ImageView) view.findViewById(R.id.img_reason_show);
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

    public RecyclerAdapterLeave(Context context, List<LeaveModels> dataset) {
        mNewsDepth = dataset;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.recycler_list_leave, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final LeaveModels leaveModels = mNewsDepth.get(position);

        viewHolder.mDetails.setText(leaveModels.getDetails());
        viewHolder.mFromDate.setText("From: "+leaveModels.getFromDate());
        viewHolder.mToDate.setText("To: "+leaveModels.getToDate());
        Glide.with(mContext).load(R.drawable.leavebusi).asBitmap().centerCrop().into(new BitmapImageViewTarget(viewHolder.mReasonImg) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                viewHolder.mReasonImg.setImageDrawable(circularBitmapDrawable);
            }
        });



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
}