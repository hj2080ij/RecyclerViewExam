package com.example.recyclerviewexam;

import android.view.View;

public interface MyOnClickListener {
    void onClick(View v, int position, CardItem cardItem);

    void onShareButtonClick(CardItem cardItem);

    void onLearnMoreButtonClick(int position);
}
