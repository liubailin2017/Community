package com.sto.asportclient;

import android.content.Context;

public interface BaseView {
    public void setPresenter(BasePresenter presenter);
    public Context getContext();
}
