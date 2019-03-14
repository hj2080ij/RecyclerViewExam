package com.example.recyclerviewexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL
        );
        recyclerView.setLayoutManager(layoutManager);

        List<CardItem> dataList = new ArrayList<>();
        dataList.add(new CardItem("히어로", "RPG"));
        dataList.add(new CardItem("닷지", "노잼게임"));
        dataList.add(new CardItem("도라", "도라\n도라"));
        dataList.add(new CardItem("젠틀", "카톡"));

        MyRecyclerAdaptet adapter = new MyRecyclerAdaptet(dataList);
        recyclerView.setAdapter(adapter);

       adapter.

    }





}
