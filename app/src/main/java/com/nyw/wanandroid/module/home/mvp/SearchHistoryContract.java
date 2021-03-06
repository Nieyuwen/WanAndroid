package com.nyw.wanandroid.module.home.mvp;

import com.bakerj.base.BasePresenter;
import com.bakerj.base.BaseView;
import com.nyw.domain.domain.bean.response.home.HotkeyBean;

import java.util.List;

/**
 * @author nyw
 * @date 2019/07/10
 *
 * Generated by MVPGenerator
 */
public interface SearchHistoryContract {
    interface View extends BaseView {
        void HotkeyBeanGet(List<HotkeyBean> data);
    }

    abstract class Presenter extends BasePresenter<View> {
        public Presenter(View view) {
            super(view);
        }
        public abstract void getHotkeyBean();
    }
}
