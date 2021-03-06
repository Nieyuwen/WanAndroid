package com.nyw.wanandroid.module.web.presentation.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.nyw.domain.common.util.cache.UserCacheUtil;
import com.nyw.domain.domain.event.home.ToUserCenterEvent;
import com.nyw.domain.domain.router.PathConstants;
import com.nyw.libproject.common.activity.WanBaseActivity;
import com.nyw.libwidgets.dialog.WebMenuDialog;
import com.nyw.wanandroid.R;
import com.nyw.wanandroid.module.web.mvp.webContract;
import com.nyw.wanandroid.module.web.mvp.webPresenter;
import com.nyw.wanandroid.utils.IntentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.State;

@Route(path = PathConstants.PATH_WEB_COMMON)
public class WebActivity extends WanBaseActivity implements webContract.View {
    @State
    @Autowired
    String url;
    @State
    @Autowired
    int id;
    @BindView(R.id.main_view)
    LinearLayout mainViewGroup;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.common_iv_back)
    ImageView commonIvBack;
    private AgentWeb mAgentWeb;
    private webPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        ButterKnife.bind(this);
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        mPresenter = new webPresenter(this);
        initView();
    }

    private void initView() {
        commonIvBack.setOnClickListener(v -> finish());
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mainViewGroup, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator(getResources().getColor(com.nyw.libproject.R.color.accent), 1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setMainFrameErrorView(com.nyw.libproject.R.layout.layout_agent_web_error, com.nyw.libproject.R.id.iv_404)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                // .interceptUnkownUrl()
                .setWebChromeClient(new WebChromeClient() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                    }
                })
                .setWebViewClient(new WebViewClient())
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        BarUtils.setStatusBarLightMode(this, false);
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.more})
    public void onViewClicked() {
        WebMenuDialog.show(more, new WebMenuDialog.OnMenuClickListener() {
            @Override
            public void onCollect() {
                if (UserCacheUtil.checkIsLoginWithJump(new ToUserCenterEvent())) {
                    mPresenter.Collect(id);
                }
            }

            @Override
            public void onShare() {
                IntentUtil.openShare(getContext(), url);
            }

            @Override
            public void onBrowser() {
                IntentUtil.openBrowser(getContext(), url);
            }
        });
    }

    @Override
    public void CollectSuccess() {
        ToastUtils.showShort("收藏成功");
    }

}
