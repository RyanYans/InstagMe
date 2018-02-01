package com.paisheng.instagme.business.home.main.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.paisheng.instagme.R;
import com.paisheng.instagme.base.AbstractIMActivity;
import com.paisheng.instagme.business.home.hometab.view.HomeFragment;
import com.paisheng.instagme.business.home.main.contract.IMainActivityContract;
import com.paisheng.instagme.business.home.main.presenter.MainPresenter;
import com.paisheng.instagme.business.home.mytab.view.MyFragment;
import com.paisheng.instagme.common.arouter.MainRouterConstant;
import com.paisheng.lib.widget.tabviewpager.TabViewPagerManager;
import java.util.ArrayList;
import java.util.List;

/**
 * <br> ClassName:   MainActivity
 * <br> Description: 主页面
 * <br>
 * <br> Author:      yuanbaining
 * <br> Date:        2018/1/26 10:34
 */
@Route(path = MainRouterConstant.MAIN_PAGE, name = "主界面")
public class MainActivity extends AbstractIMActivity<MainPresenter>
        implements IMainActivityContract.IView{
    private Bundle mSavedInstanceState;
    private TabViewPagerManager mPagerManager;

    /*** TAB文本 ***/
    private String[] mTextViewArray = {"主页", "个人中心"};
    /*** TAB默认ICON ***/
    private int[] mIconNormals = {R.drawable.ic_home_normal, R.drawable.ic_me_normal};
    /*** TAB激活ICON ***/
    private int[] mIconActivateds = {R.drawable.ic_home_select, R.drawable.ic_me_select};
    /*** 主页面Fragment ***/
    private HomeFragment mHomeFragment;
    /*** 个人中心Fragment ***/
    private MyFragment mMyFragment;
    private MainPresenter mMainPresenter;

    @Override
    protected MainPresenter createPresenter() {
        mMainPresenter = new MainPresenter();
        return mMainPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
        initView();
    }

    private void initView() {
        initViewPager();
    }

    private void initViewPager() {
        mPagerManager = new TabViewPagerManager(getMainLayout(), getSupportFragmentManager());
        mPagerManager.initView(initFragment(), mTextViewArray, mIconNormals, mIconActivateds, null, null, null);
    }

    /**
     *<br> Description: 初始化主界面Tab的Fragment
     *<br> Author:      yuanbaining
     *<br> Date:        2018/2/1 18:53
     */
    private List<Fragment> initFragment() {
        mHomeFragment = getSavedInstanceFragment(HomeFragment.class);
        mMyFragment = getSavedInstanceFragment(MyFragment.class);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(mHomeFragment);
        fragments.add(mMyFragment);
        // 添加屏幕翻转先detach fragment 处理        ???待理解
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        boolean flag = false;
        for (Fragment frag: fragments) {
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

    /**
     * <br> Description: 获取fragment
     * <br> Author:      谢文良
     * <br> Date:        2017/8/1 11:22
     *
     * @param mClass Class
     * @return Fragment
     */
    private <T extends Fragment> T getSavedInstanceFragment(Class<T> mClass) {
        Fragment mFragment = null;
        if (mSavedInstanceState != null) {
            mFragment = getSupportFragmentManager().getFragment(mSavedInstanceState, mClass.getName());
        }
        if (mFragment == null) {
            try {
                mFragment = mClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            mFragment.onAttach((Context) this);
        }
        return (T) mFragment;
    }

    @Override
    public void onBackPressed() {
        if (mMainPresenter != null) {
            mMainPresenter.exit();
        }
    }
}
