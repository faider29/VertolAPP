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
import com.andrienkom.vertolapp.utility.Consts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsHolder> {

    private Context mContext;
    private List<Events> mEventsList;
    private List<OnItemClickListener> mItemClickListeners;

    public EventsAdapter(Context context, List<Events> eventsList){
        mContext = context;
        mEventsList = eventsList;
        mItemClickListeners = new ArrayList<>();

    }



    @NonNull
    @Override
    public EventsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_calendar,viewGroup,false);
        return new EventsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsHolder eventsHolder, int i) {
        eventsHolder.setHeader(mEventsList.get(i).getTitle());
        Picasso.get()
                .load(Consts.BASE_URL + mEventsList.get(i).getImg())
                .error(R.drawable.error)
                .into(eventsHolder.getEventsImage());

        eventsHolder.setDate(mEventsList.get(i).getDate());
        eventsHolder.setClickListener(view -> {
            if (mItemClickListeners.size() != 0 && mEventsList != null) {
                for (OnItemClickListener l : mItemClickListeners) {
                    l.onItemClick(i, mEventsList.get(i));
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mEventsList == null ? 0 : mEventsList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListeners.add(listener);
    }

    public class EventsHolder extends RecyclerView.ViewHolder {

        private ImageView mEventsImage;
        private TextView mHeader;
        private TextView mDate;
        private View mView;

        public EventsHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mHeader = itemView.findViewById(R.id.item_events_header);
            mDate = itemView.findViewById(R.id.item_events_date);
            mEventsImage = itemView.findViewById(R.id.item_events_image);
        }

        public void setHeader(String header) {
            mHeader.setText(header);
        }

        public void setEventsImage(Drawable drawable) {
            mEventsImage.setImageDrawable(drawable);
        }

        public void setDate(String date) {
            mDate.setText(date);
        }

        public void setClickListener(View.OnClickListener listener) {
            mView.setOnClickListener(listener);
        }



        public ImageView getEventsImage() {
            return mEventsImage;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, Events events);
    }
}
