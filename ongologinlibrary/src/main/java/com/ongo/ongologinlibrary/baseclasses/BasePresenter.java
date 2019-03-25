package com.ongo.ongologinlibrary.baseclasses;

import android.os.Bundle;

import java.lang.ref.WeakReference;


public abstract class BasePresenter<T extends BaseView> {


    private WeakReference<T> view;

    public void setView(T view) {
        this.view = new WeakReference<>(view);
    }

    public T getView() {
        if (view != null)
            return view.get();
        return null;
    }

    public void initialize(Bundle extras) {
    }

    public BasePresenter() {


    }



    public void disposeAll() {

    }

}
