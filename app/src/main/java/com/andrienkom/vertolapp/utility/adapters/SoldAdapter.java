package com.andrienkom.vertolapp.utility.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Events;
import com.andrienkom.vertolapp.entities.Sold;
import com.andrienkom.vertolapp.utility.Consts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SoldAdapter extends RecyclerView.Adapter<SoldAdapter.SoldHolder> {

    private Context mContext;
    private List<Sold> mSoldList;

    private List<OnItemClickListener> mItemClickListeners;

    public SoldAdapter(Context context, List<Sold> soldList) {
        mContext = context;
        mSoldList = soldList;
        mItemClickListeners = new ArrayList<>();

    }



    @NonNull
    @Override
    public SoldHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sold, viewGroup, false);
        return new SoldHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoldHolder soldHolder, int i) {
        soldHolder.setHeader(mSoldList.get(i).getTitle());
        Picasso.get()
                .load(Consts.BASE_URL + mSoldList.get(i).getImg())
                .error(R.drawable.error)
                .into(soldHolder.getSoldImage());

        soldHolder.setDate(mSoldList.get(i).getDate());
        soldHolder.setClickListener(view -> {
            if (mItemClickListeners.size() != 0 && mSoldList != null) {
                for (OnItemClickListener l : mItemClickListeners) {
                    l.onItemClick(i, mSoldList.get(i));
                }
            }
        });
    }

    /**
     * Возвращает количесвто Акций
     * @return
     */
    @Override
    public int getItemCount() {
        return mSoldList == null ? 0 : mSoldList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListeners.add(listener);
    }

    public class SoldHolder extends RecyclerView.ViewHolder {

        private ImageView mSoldImage;
        private TextView mHeader;
        private TextView mDate;
        private View mView;

        public SoldHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mHeader = itemView.findViewById(R.id.item_sold_header);
            mDate = itemView.findViewById(R.id.item_sold_date);
            mSoldImage = itemView.findViewById(R.id.item_sold_image);
        }

        public void setHeader(String header) {
            mHeader.setText(header);
        }

        public void setSoldImage(Drawable drawable) {
            mSoldImage.setImageDrawable(drawable);
        }

        public void setDate(String date) {
            mDate.setText(date);
        }

        public void setClickListener(View.OnClickListener listener) {
            mView.setOnClickListener(listener);
        }



        public ImageView getSoldImage() {
            return mSoldImage;
        }
    }

    public interface OnItemClickListener  {
        void onItemClick(int position, Sold sold);


    }
}
