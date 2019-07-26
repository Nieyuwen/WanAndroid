package com.nyw.wanandroid.module.project.mvp;

import com.bakerj.base.loadmore.mvp.LoadMoreContract;
import com.nyw.domain.domain.bean.request.project.ProjectReq;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.libproject.loadmore.mvp.PageLoadMorePresenter;

/**
 * @author nyw
 * @date 2019/07/10
 *
 * Generated by MVPGenerator
 */
public interface projectListContract {
    //泛型为返回的数据类型，即Adapter中展示的数据类型
    interface View extends LoadMoreContract.View<ArticleBean> {
        void CollectSuccess();
        void UnCollectSuccess();
    }

    //具体泛型注释可查阅 {父基类LoadMorePresenter}
    abstract class Presenter extends PageLoadMorePresenter<projectListContract.View, ProjectReq, ArticleBean,
            ArticleBean> {
        public Presenter(projectListContract.View view) {
            super(view);
        }
        public abstract void Collect(int id);
        public abstract void UnCollect(int id);
    }
}