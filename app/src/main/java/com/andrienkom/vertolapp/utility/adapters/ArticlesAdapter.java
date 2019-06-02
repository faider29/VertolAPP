package com.andrienkom.vertolapp.utility.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        articleHolder.setTextView(mArticlesList.get(i).getTitle());
      //  articleHolder.setTextView(mArticlesList.get(i).getId());
        articleHolder.setClickListener(view ->{
            if (mOnItemClickListeners.size() != 0 && mArticlesList != null){
                for (OnItemClickListener l: mOnItemClickListeners){
                    l.OnItemClick(i, mArticlesList.get(i));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArticlesList == null ? 0 : mArticlesList.size();
    }

    public void setOnItemClickListeners(OnItemClickListener listeners){
        mOnItemClickListeners.add(listeners);
    }



    public class ArticleHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private View mView;

        public ArticleHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mTextView = itemView.findViewById(R.id.tv_newspaper_news_header);
        }

        public void setTextView(String textView) {
            mTextView.setText(textView);
        }

        public void setClickListener(View.OnClickListener listener){
            mView.setOnClickListener(listener);

        }

    }

    public interface OnItemClickListener{
        void OnItemClick(int position, Articles articles);
    }
}
