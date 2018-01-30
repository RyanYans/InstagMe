package com.paisheng.instagme.common.grant;

import android.app.Activity;
import android.text.Html;

import com.paisheng.instagme.widget.dialog.SecondTipDialog;
import com.paisheng.lib.grant.AbstractOnPermissionCallBack;
import com.paisheng.lib.grant.PermissionUtils;
import com.paisheng.lib.util.ToastUtil;

import java.util.Arrays;

/**
 * <br> ClassName:   CommonPermissionCallBack
 * <br> Description: 团贷网定制权限CallBack
 * <br>
 * <br> Author:      wujun
 * <br> Date:        2017/9/13 18:04
 */
public class CommonPermissionCallBack extends AbstractOnPermissionCallBack {

    private Activity mActivity;
    public CommonPermissionCallBack(Activity activity) {
        super(activity);
        this.mActivity = activity;
    }

    public CommonPermissionCallBack(Activity activity, boolean showRefuseDialog) {
        super(activity, showRefuseDialog);
        this.mActivity = activity;
    }

    @Override
    public void onRequestAllow(String... permissionName) {

    }

    @Override
    public void onRequestRefuse(String... permissionName) {
        if (mShowRefuseDialog){
            String message = PermissionUtils.getStringBuilder(Arrays.asList(permissionName))+"权限已被拒绝,去设置中打开";
            showDialog(message);
        } else {
            ToastUtil.showResultToast(mActivity, PermissionUtils.getStringBuilder(Arrays
                    .asList(permissionName)) + "权限申请失败!");
        }
    }

    @Override
    public void onRequestNoAsk(String... permissionName) {
        String message = PermissionUtils.getStringBuilder(Arrays.asList(permissionName))
                + "权限已关闭,去设置中打开";
        showDialog(message);

    }
    /**
     *<br> Description: 弹出Dialog
     *<br> Author:      wujun
     *<br> Date:        2017/9/13 18:37
     */
    private void  showDialog(String message) {
        new SecondTipDialog(mActivity)
                .init(false)
                .setContent(message)
                .setDialogTitle("")
                .setConfirm("确定")
                .setCancel(Html.fromHtml("<font color=\"#999999\">取消</font>"))
                .setOnClickListener(new SecondTipDialog.OnConfirmListener(){
                    @Override
                    public void clickConfirm() {
                        PermissionUtils.openPermissionSettings(mActivity,mActivity.getPackageName());
                    }

                    @Override
                    public void clickCancel() {
                        onSettingDialogCancel();
                    }
                });
    }


}
