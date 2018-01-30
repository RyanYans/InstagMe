package com.paisheng.instagme.business.home.hometab.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paisheng.instagme.R;


/**
 * @author: yuanbaining
 * @Filename: RegisterFragment
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/26 12:44
 */

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater(savedInstanceState).inflate(R.layout.fragment_my, null);

        return view;
    }

}
