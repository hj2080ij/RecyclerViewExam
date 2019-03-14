package com.example.recyclerviewexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyRecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL
        );
        mRecyclerView.setLayoutManager(layoutManager);

        List<CardItem> dataList = new ArrayList<>();
        dataList.add(new CardItem("히어로", "RPG"));
        dataList.add(new CardItem("닷지", "노잼게임"));
        dataList.add(new CardItem("도라", "도라\n도라"));
        dataList.add(new CardItem("젠틀", "카톡"));
        dataList.add(new CardItem("젠틀", "카톡"));
        dataList.add(new CardItem("젠틀", "카톡"));
        dataList.add(new CardItem("젠틀", "카톡"));
        dataList.add(new CardItem("젠틀", "카톡"));
        dataList.add(new CardItem("젠틀", "카톡"));
        dataList.add(new CardItem("젠틀", "카톡"));
        dataList.add(new CardItem("젠틀", "카톡"));
        dataList.add(new CardItem("젠틀", "카톡"));
        dataList.add(new CardItem("젠틀", "카톡"));
        dataList.add(new CardItem("젠틀", "카톡"));

        mAdapter = new MyRecyclerAdapter(dataList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new MyRecyclerAdapter.MyOnClickListener() {
            @Override
            public void onClick(View v, int position, CardItem cardItem) {
                mAdapter.setSelect(position);
                mAdapter.notifyItemChanged(position);
            }

            @Override
            public void onShareButtonClick(CardItem cardItem) {
                Toast.makeText(MainActivity.this, cardItem.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLearnMoreButtonClick(int position) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View v, CardItem item) {
                mAdapter.removeItem(item);

                int position = (int) v.getTag();

                mAdapter.notifyItemRemoved(position);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                mAdapter.addItem(new CardItem("추가한 거", "추가한 내용"), 3);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
