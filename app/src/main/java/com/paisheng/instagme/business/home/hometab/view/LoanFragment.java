package com.paisheng.instagme.business.home.hometab.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paisheng.instagme.R;
import com.paisheng.instagme.business.home.hometab.LoginRegViewPagerIndicator;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author: yuanbaining
 * @Filename: LoanFragment
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/26 10:06
 */


public class LoanFragment extends Fragment {

    private static LoanFragment mFragment;
    @BindView(R.id.vpi_loan)
    LoginRegViewPagerIndicator mVpiLoan;
    @BindView(R.id.vp_loan)
    ViewPager mVpLoan;

    private List<String> mTitles = Arrays.asList("帖子", "涂片");
    Unbinder unbinder;

    public static LoanFragment getInstance() {
        if (mFragment == null) {
            mFragment = new LoanFragment();
        }
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater(savedInstanceState).inflate(R.layout.fragment_loan, null);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager();
    }

    private void initViewPager() {
        mVpiLoan.setTabItemTitles(mTitles);
        mVpiLoan.setViewPager(mVpLoan, 0);

        final RegisterFragment registerFragment = new RegisterFragment();
        final LoginFragment loginFragment = new LoginFragment();
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.size();
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return registerFragment;
                } else {
                    return loginFragment;
                }
            }
        };
        mVpLoan.setAdapter(adapter);
        mVpLoan.setCurrentItem(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
