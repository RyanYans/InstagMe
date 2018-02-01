package com.paisheng.instagme.business.home.hometab.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.paisheng.instagme.R;
import com.paisheng.instagme.base.AbstractIMFragment;
import com.paisheng.instagme.business.home.hometab.LoginRegViewPagerIndicator;
import com.paisheng.instagme.business.home.hometab.contract.IHomeFragmentContract;
import com.paisheng.instagme.business.home.hometab.presenter.HomeFragmentPresenter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


/**
 * @author: yuanbaining
 * @Filename: HomeFragment
 * @Description:    主页Fragment
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/26 15:06
 */

public class HomeFragment extends AbstractIMFragment<HomeFragmentPresenter> implements IHomeFragmentContract.IView {
    private HomeFragmentPresenter mHomeFragmentPresenter;

    @BindView(R.id.vpi_loan)
    LoginRegViewPagerIndicator mVpiHome;
    @BindView(R.id.vp_loan)
    ViewPager mVpHome;
    private List<String> mTitles = Arrays.asList("帖子", "涂片");

    @Override
    protected HomeFragmentPresenter createPresenter() {
        mHomeFragmentPresenter = new HomeFragmentPresenter();
        return mHomeFragmentPresenter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_loan;
    }

    @Override
    protected void lazyLoad() {
        initViewPager();
    }

    private void initViewPager() {
        mVpiHome.setTabItemTitles(mTitles);
        mVpiHome.setViewPager(mVpHome, 0);

        final PosterFragment posterFragment = new PosterFragment();
        final PictFragment pictFragment = new PictFragment();
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.size();
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return posterFragment;
                } else {
                    return pictFragment;
                }
            }
        };
        mVpHome.setAdapter(adapter);
        mVpHome.setCurrentItem(0);
    }
}
