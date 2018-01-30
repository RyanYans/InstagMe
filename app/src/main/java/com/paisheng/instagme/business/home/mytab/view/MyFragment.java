package com.paisheng.instagme.business.home.mytab.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paisheng.instagme.R;
import com.paisheng.lib.widget.dialog.ProgressHUD;


/**
 * @author: yuanbaining
 * @Filename: MyFragment
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/26 9:33
 */


public class MyFragment extends Fragment {

    private static MyFragment mFragment;

    public static MyFragment getInstance() {
        if (mFragment == null) {
            mFragment = new MyFragment();
        }
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater(savedInstanceState).inflate(R.layout.fragment_my, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProgressHUD.show(getContext(), "加载中...");
    }

}
