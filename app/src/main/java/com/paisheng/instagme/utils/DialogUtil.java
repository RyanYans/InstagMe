package com.paisheng.instagme.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.paisheng.instagme.R;
import com.paisheng.instagme.widget.dialog.SecondTipDialog;
import com.paisheng.lib.grant.AbstractOnPermissionCallBack;
import com.paisheng.lib.grant.core.PermissionRequestFactory;
import com.paisheng.lib.view.ViewHolder;


/**
 * <br> ClassName:   DialogUtil
 * <br> Description: 对话框工具
 * <br>
 * <br> Author:      fangbignran
 * <br> Date:        2017/8/4 8:49
 */
public class DialogUtil {


    /**
     * <br> Description: 弹框
     * <br> Author:     fangbingran
     * <br> Date:        2017/8/4 8:45
     *
     * @param activity          Activity
     * @param title             标题
     * @param cont              内容
     * @param auto              确定按钮文字
     * @param cancel            取消按钮文字
     * @param onConfirmListener 回调接口
     * @return
     */
    public static AlertDialog showDialog(Activity activity, String title, String cont,
                                         String auto, String cancel,
                                         final OnConfirmListener onConfirmListener) {

        if (activity == null || activity.isFinishing()) {
            return null;
        }
        View view = LayoutInflater.from(activity).inflate(R.layout.confirm_apply_infor_dialog, null);
        final AlertDialog dialog = new AlertDialog.Builder(activity).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.setContentView(view);
        ViewHolder viewHolder = new ViewHolder(view, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    /***取消***/
                    case R.id.tv_confirm_apply_infor_dialog_cancel:
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        if (onConfirmListener != null) {
                            onConfirmListener.clickCancel();
                        }
                        break;
                    /***确认***/
                    case R.id.tv_confirm_apply_infor_dialog_auto:
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        if (onConfirmListener != null) {
                            onConfirmListener.clickConfirm();
                        }
                        break;
                    default:
                }
            }
        });
        viewHolder.setOnClickListener(R.id.tv_confirm_apply_infor_dialog_cancel);
        viewHolder.setOnClickListener(R.id.tv_confirm_apply_infor_dialog_auto);
        viewHolder.setText(R.id.tv_confirm_apply_infor_dialog_title, title);
        if (TextUtils.isEmpty(cont)) {
            viewHolder.setVisibility(R.id.tv_confirm_apply_infor_dialog_cont, View.GONE);
        } else {
            viewHolder.setText(R.id.tv_confirm_apply_infor_dialog_cont, cont);
        }
        viewHolder.setText(R.id.tv_confirm_apply_infor_dialog_auto, auto);
        viewHolder.setText(R.id.tv_confirm_apply_infor_dialog_cancel, cancel);
        return dialog;
    }

    public interface OnConfirmListener {
        void clickConfirm();

        void clickCancel();
    }

    /**
     * 弹出对话框
     */
    public static AlertDialog showTipsDialog(Activity activity, String title, CharSequence text, String comfirmText, String cancelText,
                                             final SecondTipDialog.OnConfirmListener listener) {
        return showTipsDialog(activity, title, text, comfirmText, cancelText, listener, Gravity.CENTER, true, 0);
    }

    /**
     * 弹出对话框
     */
    public static AlertDialog showTipsDialog(Activity activity, String title, final CharSequence text, final String comfirmText, String cancelText,
                                             final SecondTipDialog.OnConfirmListener listener, int gravity, boolean isCancel, final int confrimTime) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }

        return new SecondTipDialog(activity)
                .init(isCancel)
                .setContent(text, gravity)
                .setDialogTitle(title)
                .setConfirm(comfirmText, confrimTime)
                .setCancel(cancelText)
                .setOnClickListener(listener);
    }

    /**
     * <br> Description: 主动还款超过一次提示
     * <br> Author:      wujun
     * <br> Date:        2017/8/9 17:27
     */
    public static AlertDialog showOverdueRepayment(Activity activity, String title, CharSequence text,
                                                   String comfirmText, final OnConfirmListener listener) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }

        final AlertDialog dialog = new AlertDialog.Builder(activity).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        View view = (View) LayoutInflater.from(activity).inflate(R.layout.view_dialog_repayment_overdue_layout, null);
        dialog.setContentView(view);
        if (TextUtils.isEmpty(text)) {
            view.findViewById(R.id.svMessage).setVisibility(View.GONE);
        }
        TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        if (TextUtils.isEmpty(title)) {
            txtTitle.setVisibility(View.GONE);
        } else {
            txtTitle.setVisibility(View.VISIBLE);
            txtTitle.setText(title);
        }
        TextView txtMessage = (TextView) view.findViewById(R.id.txtMessage);
        txtMessage.setText(text);

        Button comfirmBtn = (Button) view.findViewById(R.id.btnOk);
        comfirmBtn.setText(comfirmText);
        comfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    listener.clickConfirm();
                }
            }
        });
        return dialog;

    }


    /**
     * <br> Description: 拨打电话客服电话
     * <br> Author:     fangbingran
     * <br> Date:        2017/9/15 15:12
     *
     * @param activity Activity
     */
    public static void callPhone(final Activity activity) {
        /*** 客服电话*/
        final String phone = "4001690188";
        showTipsDialog(activity, "是否要打电话给客服？", "(服务时间 9:00-22:00；周末 9:00-18:00)", "确认", "取消", new SecondTipDialog.OnConfirmListener() {
            @Override
            public void clickConfirm() {

                PermissionRequestFactory.create(activity)
                        .addPermission(Manifest.permission.CALL_PHONE)
                        .request(new AbstractOnPermissionCallBack(activity) {
                            @Override
                            public void onRequestAllow(String... strings) {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:" + phone));
                                try {
                                    activity.startActivity(intent);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
            }

            @Override
            public void clickCancel() {
            }
        });
    }
}
