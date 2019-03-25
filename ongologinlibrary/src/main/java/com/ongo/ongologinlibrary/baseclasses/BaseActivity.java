package com.ongo.ongologinlibrary.baseclasses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected abstract void initializePresenter();
    protected BasePresenter presenter;
    protected abstract void onCreateView(Bundle saveInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(getLayoutId());
        initializePresenter();
        onCreateView(savedInstanceState);
    }
    public void setBasePresenter(BasePresenter basePresenter) {
        presenter = basePresenter;
        if (presenter != null) {
            this.presenter.initialize(null);
        }
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void handleException(Pair<Integer, String> error) {

    }
}
