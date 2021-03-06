
package com.thinking.submission5.entity;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TvRespone {

    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<Tv> mTvs;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<Tv> getResults() {
        return mTvs;
    }

    public void setResults(List<Tv> tvs) {
        mTvs = tvs;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

}
