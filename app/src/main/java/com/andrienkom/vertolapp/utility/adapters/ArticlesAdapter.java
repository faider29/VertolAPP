package com.andrienkom.vertolapp.utility.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Articles;

import java.util.ArrayList;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleHolder> {

    private Context mContext;
    private List<Articles> mArticlesList;
    private List<OnItemClickListener> mOnItemClickListeners;

    public ArticlesAdapter(Context context, List<Articles> articlesList){
        mContext = context;
        mArticlesList = articlesList;
        mOnItemClickListeners = new ArrayList<>();

    }


    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_newspaper_articles,viewGroup,false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder articleHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mArticlesList == null ? 0 : mArticlesList.size();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {


        public ArticleHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(int position, Articles articles);
    }
}
