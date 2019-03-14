package com.example.recyclerviewexam;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.BatchUpdateException;
import java.util.List;

public class MyRecyclerAdaptet extends RecyclerView.Adapter<MyRecyclerAdaptet.ViewHolder> {
    private List<CardItem>  mDataList;

    public interface MyRecyclerViewClickListener {
        void onItemClicked(int position);
        void onShareButtonClicked(int position);
        void onLearnMoreBttonClicked(int position);
    }
    private MyRecyclerViewClickListener mListener;

    public MyRecyclerAdaptet(MyRecyclerViewClickListener listener) {
        mListener = listener;
    }


    public MyRecyclerAdaptet(List<CardItem> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card,parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CardItem item = mDataList.get(position);
        holder.title.setText(item.getTitle());
        holder.contents.setText(item.getContents());

        if(mListener != null) {
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(pos);
                }
            });
            holder.more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onLearnMoreBttonClicked(pos);
                }
            });
            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onShareButtonClicked(pos);
                }
            });
        }

    }
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView contents;
        Button share;
        Button more;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_text);
            contents = itemView.findViewById(R.id.contents_text);
            share = itemView.findViewById(R.id.share_button);
            more = itemView.findViewById(R.id.more_button);
        }

    }
}
