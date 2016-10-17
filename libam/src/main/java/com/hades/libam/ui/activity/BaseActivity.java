package com.hades.libam.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hades.libam.ui.interf.IRootView;
import com.hades.libam.ui.presenter.IRootPresenter;


/**
 * Created by Hades on 16/10/8.
 */

public abstract class BaseActivity<P extends IRootPresenter> extends AppCompatActivity implements IRootView {

    protected P mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView(savedInstanceState);
        mPresenter = onLoadPresenter();
        getPresenter().attachView(this);
        initData();
        initEvent();
        getPresenter().start();
    }

    protected abstract int getLayoutId();

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
        super.onDestroy();
    }

    protected abstract P onLoadPresenter();
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initData();
    protected abstract void initEvent();
}
