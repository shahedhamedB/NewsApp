package com.example.testapplication.ui.adapter;

import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class Pagination extends RecyclerView.OnScrollListener{
    LinearLayoutManager layoutManager;

    public Pagination(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visableItem= layoutManager.getChildCount();
        int totalItemCount= layoutManager.getItemCount();
        int firstVisableItemPosition= layoutManager.findFirstVisibleItemPosition();
        if(!isLoading() && !isLastPage()){
            if(visableItem+firstVisableItemPosition>=totalItemCount
                    && firstVisableItemPosition>=0
                    && totalItemCount>=getTotalPages()){
                loadMoreItem();

            }

        }
    }

    protected abstract void loadMoreItem();

    protected abstract int getTotalPages();

    protected abstract boolean isLastPage();

    protected abstract boolean isLoading();
}
