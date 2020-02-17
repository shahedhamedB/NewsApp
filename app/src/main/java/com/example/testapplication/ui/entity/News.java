package com.example.testapplication.ui.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "news")
public class News implements Parcelable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "news_id")
    @SerializedName("news_id")
    @Expose
    private String idNews;


    @ColumnInfo(name = "news_title")
    @SerializedName("news_title")
    @Expose
    private String strTitle;


    @ColumnInfo(name = "image")
    @SerializedName("imageLink")
    @Expose
    private String strImg;

    @ColumnInfo(name = "section_name")
    @SerializedName("section_name")
    @Expose
    private String strName;

    public News() {
    }

    public News(@NonNull String idNews, String strTitle, String strImg, String strName) {
        this.idNews = idNews;
        this.strTitle = strTitle;
        this.strImg = strImg;
        this.strName = strName;
    }

    protected News(Parcel in) {
        idNews = in.readString();
        strTitle = in.readString();
        strImg = in.readString();
        strName = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @NonNull
    public String getIdNews() {
        return idNews;
    }

    public void setIdNews(@NonNull String idNews) {
        this.idNews = idNews;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public String getStrImg() {
        return strImg;
    }

    public void setStrImg(String strImg) {
        this.strImg = strImg;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(idNews);
        dest.writeString(strTitle);
        dest.writeString(strImg);
        dest.writeString(strName);

    }
    @BindingAdapter("imgUrl")
    public static void loadimage(View view,String imgurl){
        ImageView imageView= (ImageView) view;
        Glide.with(imageView.getContext()).asBitmap().load(imgurl).into(imageView);
    }
}
