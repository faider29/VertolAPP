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
import com.andrienkom.vertolapp.entities.News;
import com.andrienkom.vertolapp.utility.Consts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private Context mContext;
    private List<News> mNewsList;

    private List<OnItemClickListener> mItemClickListeners;

    public NewsAdapter(Context context, List<News> newsList) {
        mContext = context;
        mNewsList = newsList;
        mItemClickListeners = new ArrayList<>();

    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false);
        return new NewsHolder(view);
    }


@Override
public void onBindViewHolder(@NonNull NewsHolder newsHolder, int i) {
    newsHolder.setHeader(mNewsList.get(i).getTitle());
    Picasso.get()
            .load(Consts.BASE_URL + mNewsList.get(i).getImg())
            .error(R.drawable.error)
            .into(newsHolder.getNewsImage());

    newsHolder.setDate(mNewsList.get(i).getDate());
    newsHolder.setClickListener(view -> {
        if (mItemClickListeners.size() != 0 && mNewsList != null){
            for (OnItemClickListener l: mItemClickListeners){
                l.onItemClick(i, mNewsList.get(i));
            }
        }
    });
    }

    @Override
    public int getItemCount() {
        return mNewsList == null ? 0 : mNewsList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mItemClickListeners.add(listener);
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        private ImageView mNewsImage;
        private TextView mHeader;
        private TextView mDate;
        private View mView;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mHeader = itemView.findViewById(R.id.item_news_header);
            mDate = itemView.findViewById(R.id.item_news_date);
            mNewsImage = itemView.findViewById(R.id.item_news_image);
        }

        public void setHeader(String header){
            mHeader.setText(header);
        }

        public void setNewsImage(Drawable drawable){
            mNewsImage.setImageDrawable(drawable);
        }

        public void setDate(String date) {
            mDate.setText(date);
        }

        public void setClickListener(View.OnClickListener listener){
            mView.setOnClickListener(listener);
        }

        public ImageView getNewsImage() {
            return mNewsImage;
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position, News news);
    }

}