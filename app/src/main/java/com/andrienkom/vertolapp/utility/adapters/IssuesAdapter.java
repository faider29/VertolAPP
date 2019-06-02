package com.andrienkom.vertolapp.utility.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrienkom.vertolapp.R;
import com.andrienkom.vertolapp.entities.Issues;

import java.util.ArrayList;
import java.util.List;

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssuesHolder> {

    private Context mContext;
    private List<Issues> mIssuesList;
    private List<IssuesHolder> mHolderList;
    private static String TAG = IssuesAdapter.class.getSimpleName();


    private List<OnItemClickListener> mOnItemClickListeners;


    public IssuesAdapter(Context context, List<Issues> issuesList){
        mContext = context;
        mIssuesList = issuesList;
        mOnItemClickListeners = new ArrayList<>();
    }

    @NonNull
    @Override
    public IssuesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_newspaper_issue,viewGroup,false);
        IssuesHolder holder = new IssuesHolder(view);
        mHolderList.add(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IssuesHolder issuesHolder, int i) {
        issuesHolder.setTextView(mIssuesList.get(i).getName());
       // issuesHolder.setTextView(mIssuesList.get(i).getId());
        issuesHolder.setClickListener(view ->{
            if (mOnItemClickListeners.size() != 0 && mIssuesList != null){
                for (OnItemClickListener l : mOnItemClickListeners){
                    l.OnItemClick(i, mIssuesList.get(i));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mIssuesList == null ? 0 : mIssuesList.size();
    }

    public void setOnItemClickListeners(OnItemClickListener listeners){
        mOnItemClickListeners.add(listeners);
    }



    public class IssuesHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private View mView;

        public IssuesHolder(@NonNull View itemView) {
            super(itemView);
            mView =itemView;
            mTextView = itemView.findViewById(R.id.tv_newspaper_issue_header);
        }

        public void setTextView(String textView) {
            mTextView.setText(textView);
        }

        private void disableView() {
            mView.setBackgroundColor(1);
        }





        public void setClickListener(View.OnClickListener listener){
            mView.setOnClickListener(listener);
            for (IssuesHolder holder: mHolderList) {

            }

        }
    }



    public interface OnItemClickListener{
        void OnItemClick(int position, Issues issues);
    }
}
