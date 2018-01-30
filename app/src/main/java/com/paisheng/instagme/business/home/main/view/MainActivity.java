package com.paisheng.instagme.business.home.main.view;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.paisheng.instagme.R;
import com.paisheng.instagme.base.AbstractIMActivity;
import com.paisheng.instagme.business.home.hometab.view.LoanFragment;
import com.paisheng.instagme.business.home.main.presenter.MainPresenter;
import com.paisheng.instagme.business.home.mytab.view.MyFragment;
import com.paisheng.instagme.common.arouter.MainRouterConstant;
import com.paisheng.instagme.common.grant.CommonPermissionCallBack;
import com.paisheng.lib.grant.core.PermissionRequestFactory;
import com.paisheng.lib.widget.tabviewpager.TabViewPagerManager;

import java.util.ArrayList;
import java.util.List;

@Route(path = MainRouterConstant.MAIN_PAGE, name = "主界面")
public class MainActivity extends AbstractIMActivity<MainPresenter> {
    private Bundle mSavedInstanceState;
    private TabViewPagerManager mPagerManager;

    /***TAB文本***/
    private String[] mTextViewArray = {"主页", "个人中心"};
    /***TAB默认ICON***/
    private int[] mIconNormals = {R.drawable.ic_home_normal, R.drawable.ic_me_normal};
    /***TAB激活ICON***/
    private int[] mIconActivateds = {R.drawable.ic_home_select, R.drawable.ic_me_select};
    private LoanFragment mLoanFragment;
    private MyFragment mMyFragment;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSavedInstanceState = savedInstanceState;

        initView();

        PermissionRequestFactory.create(this)
                .addPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .request(new CommonPermissionCallBack(this));

    }

    private void initView() {
        initViewPager();
    }

    private void initViewPager() {
        mPagerManager = new TabViewPagerManager(getMainLayout(), getSupportFragmentManager());
        mPagerManager.initView(initFragment(), mTextViewArray, mIconNormals, mIconActivateds, null, null, null);
    }

    private List<Fragment> initFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        mLoanFragment = LoanFragment.getInstance();
        mMyFragment = MyFragment.getInstance();

        fragments.add(mLoanFragment);
        fragments.add(mMyFragment);

        // 添加屏幕翻转先detach fragment 处理
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        boolean flag = false;
        for (Fragment frag : fragments) {
            if (frag.isAdded()) {
                flag = true;
                transaction.detach(frag);
            }
        }
        if (flag) {
            transaction.commitAllowingStateLoss();
        }

        return fragments;
    }
}
