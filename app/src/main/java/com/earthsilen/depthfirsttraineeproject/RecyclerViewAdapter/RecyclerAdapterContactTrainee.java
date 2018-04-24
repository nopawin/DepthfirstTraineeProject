package com.earthsilen.depthfirsttraineeproject.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.earthsilen.depthfirsttraineeproject.DetailFromContact.ContactDetail;
import com.earthsilen.depthfirsttraineeproject.ItemClickListener;
import com.earthsilen.depthfirsttraineeproject.Models.TraineeTeamModels;
import com.earthsilen.depthfirsttraineeproject.R;

import java.util.List;

public class RecyclerAdapterContactTrainee extends RecyclerView.Adapter<RecyclerAdapterContactTrainee.ViewHolder> {

    private List<TraineeTeamModels> mTraineeTeam;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
        public TextView mName;
        public TextView mRank;
        public ImageView mImg;
        private ItemClickListener itemClickListener;

        public ViewHolder(View view) {
            super(view);

            mName = (TextView) view.findViewById(R.id.name1);
            mRank = (TextView) view.findViewById(R.id.rank1);
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

    public RecyclerAdapterContactTrainee(Context context, List<TraineeTeamModels> dataset) {
        mTraineeTeam = dataset;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.recycler_list_contacts, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final TraineeTeamModels traineeTeamModels = mTraineeTeam.get(position);

        viewHolder.mName.setText("Name: "+traineeTeamModels.getName());
        viewHolder.mRank.setText("Rank: "+traineeTeamModels.getRank());
        Glide.with(mContext).load(traineeTeamModels.getImgurl()).into(viewHolder.mImg);
        viewHolder.setOnClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick, MotionEvent motionEvent) {
                if (isLongClick) {
//                    Toast.makeText(mContext, traineeTeamModels.getRank(), Toast.LENGTH_SHORT).show();
                } else {
                    Intent details = new Intent(mContext, ContactDetail.class);
                    //details.putExtras(bundle);
                    details.putExtra("contactdetailstrainee", mTraineeTeam.get(position));
                    mContext.startActivity(details);

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mTraineeTeam.size();
    }
}