package com.nyw.wanandroid.module.main.data.repository.datastore;

import com.bakerj.rxretrohttp.RxRetroHttp;
import com.nyw.domain.common.api.WanApiResult;
import com.nyw.domain.common.loadmore.PageLoadMoreResponse;
import com.nyw.domain.domain.bean.request.home.HomeReq;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.domain.domain.bean.response.home.BannerBean;
import com.nyw.wanandroid.module.main.data.api.mainApiService;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author nyw
 * @date 2019/06/27
 *
 * Generated by MVPGenerator
 */
public class mainRemoteDataStore {
    private mainApiService mService = RxRetroHttp.create(mainApiService.class);



    public Observable<PageLoadMoreResponse<ArticleBean>> getArticleList(HomeReq req) {
        return mService.getArticleList(req.getPage());
    }

    public Observable<WanApiResult<List<BannerBean>>> getBannerList() {
        return mService.getBanner();
    }

}
