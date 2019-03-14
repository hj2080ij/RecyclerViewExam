package com.example.recyclerviewexam;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    public interface MyOnClickListener {
        void onClick(View v, int position, CardItem cardItem);

        void onShareButtonClick(CardItem cardItem);

        void onLearnMoreButtonClick(int position);

        void onLongClick(View v, CardItem item);
    }



    private List<CardItem>  mDataList;
    private MyOnClickListener mListener;

    private Set<Integer> mSelectedPositionSet = new HashSet<>();

    public void removeItem(int position) {
        mDataList.remove(position);
    }

    public void removeItem(CardItem item) {
        mDataList.remove(item);
    }

    public void setSelect(int position) {
        if (mSelectedPositionSet.contains(position)) {
            mSelectedPositionSet.remove(position);
        } else {
            mSelectedPositionSet.add(position);
        }
    }

    public void setOnClickListener(MyOnClickListener listener) {
        mListener = listener;
    }

    public MyRecyclerAdapter(List<CardItem> dataList) {
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
        final CardItem item = mDataList.get(position);
        holder.title.setText(item.getTitle());
        holder.contents.setText(item.getContents());

        if (mSelectedPositionSet.contains(position)) {
            holder.itemView.setBackgroundColor(Color.RED);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        if(mListener != null) {
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(v, pos, item);
                }
            });
            holder.more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onLearnMoreButtonClick(pos);
                }
            });
            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onShareButtonClick(item);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    v.setTag(pos);
                    mListener.onLongClick(v, item);
                    return true;
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
