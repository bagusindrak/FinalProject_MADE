
package com.thinking.submission5.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@Entity
public class Tv implements Parcelable {

    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("first_air_date")
    private String mFirstAirDate;
//    @SerializedName("genre_ids")
//    private List<Long> mGenreIds;
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Long mId;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String mName;
//    @SerializedName("origin_country")
//    private List<String> mOriginCountry;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_name")
    private String mOriginalName;
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private Double mPopularity;
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @SerializedName("vote_count")
    private Long mVoteCount;

    public Tv(Parcel in) {
        mBackdropPath = in.readString();
        mFirstAirDate = in.readString();
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mName = in.readString();
        mOriginalLanguage = in.readString();
        mOriginalName = in.readString();
        mOverview = in.readString();
        if (in.readByte() == 0) {
            mPopularity = null;
        } else {
            mPopularity = in.readDouble();
        }
        mPosterPath = in.readString();
        if (in.readByte() == 0) {
            mVoteAverage = null;
        } else {
            mVoteAverage = in.readDouble();
        }
        if (in.readByte() == 0) {
            mVoteCount = null;
        } else {
            mVoteCount = in.readLong();
        }
    }

    public Tv() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mBackdropPath);
        dest.writeString(mFirstAirDate);
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mName);
        dest.writeString(mOriginalLanguage);
        dest.writeString(mOriginalName);
        dest.writeString(mOverview);
        if (mPopularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mPopularity);
        }
        dest.writeString(mPosterPath);
        if (mVoteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mVoteAverage);
        }
        if (mVoteCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mVoteCount);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Tv> CREATOR = new Creator<Tv>() {
        @Override
        public Tv createFromParcel(Parcel in) {
            return new Tv(in);
        }

        @Override
        public Tv[] newArray(int size) {
            return new Tv[size];
        }
    };

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getFirstAirDate() {
        return mFirstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        mFirstAirDate = firstAirDate;
    }

//    public List<Long> getGenreIds() {
//        return mGenreIds;
//    }
//
//    public void setGenreIds(List<Long> genreIds) {
//        mGenreIds = genreIds;
//    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

//    public List<String> getOriginCountry() {
//        return mOriginCountry;
//    }
//
//    public void setOriginCountry(List<String> originCountry) {
//        mOriginCountry = originCountry;
//    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return mOriginalName;
    }

    public void setOriginalName(String originalName) {
        mOriginalName = originalName;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Long voteCount) {
        mVoteCount = voteCount;
    }

}
