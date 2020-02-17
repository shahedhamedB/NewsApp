package com.example.testapplication.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.testapplication.R;
import com.example.testapplication.databinding.NewsItemBinding;
import com.example.testapplication.ui.entity.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>  {
    private List<News> response;
    private Context context;


    public NewsAdapter(List<News> response , Context context ) {
        this.response = response;
        this.context = context;

    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemBinding newsItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.news_item, parent, false) ;

        return new NewsViewHolder(newsItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.NewsViewHolder holder, int position) {

       final News category = response.get(position) ;

        holder.newsItemBinding.setModel(category);





    }

    @Override
    public int getItemCount() {
        if(response != null){
            return response.size();
        }
        return 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {


   NewsItemBinding newsItemBinding;

        public NewsViewHolder(@NonNull NewsItemBinding itemView) {
            super(itemView.getRoot());
            newsItemBinding=itemView;




        }
    }
    public void add(News news){
        if(response == null){
            response=new ArrayList<>();
        }
        response.add(news);
        notifyItemInserted(response.size()-1);

    }
    public void addAll(List<News> news){
        if(news == null){
            news=new ArrayList<>();
        }
        for (News n:news){
            add(n);
        }
    }
    public void addBottomItem(){
        add(new News());
    }
    public void removeLastEmptyItem(){
        int position=response.size()-1;
        News item=getItem(position);
        if(item != null){
            response.remove(position);
            notifyItemRemoved(position);
        }
        
    }

    private News getItem(int position) {
        return response.get(position);
    }


}
