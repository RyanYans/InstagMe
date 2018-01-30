package com.paisheng.instagme.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.paisheng.instagme.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * <br> ClassName:   SecondTipDialog
 * <br> Description: 带有确定与取消按钮的弹窗
 * <br>
 * <br> Author:      yexiaochuan
 * <br> Date:        2017/8/21 16:12
 */
public class SecondTipDialog extends AlertDialog{
    private boolean confirmFlag;
    private boolean cancelFlag;

    public SecondTipDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_dialog_tips_layout);
    }

    /**
     *<br> Description: 初始化
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/21 17:20
     * @param isCanCancel
     *                  是否可取消
     * @return
     */
    public SecondTipDialog init(boolean isCanCancel) {
        setCancelable(isCanCancel);
        setCanceledOnTouchOutside(isCanCancel);
        show();
        setOnClickListener(null);
        return this;
    }

    /**
     *<br> Description: 设置标题
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/21 17:21
     * @param title
     *                  标题
     * @return
     */
    public SecondTipDialog setDialogTitle(CharSequence title) {
        if (TextUtils.isEmpty(title)) {
            return this;
        }
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(title);
        return this;
    }

    /**
     *<br> Description: 设置内容
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/21 17:21
     * @param content
     *                  内容
     * @return
     */
    public SecondTipDialog setContent(CharSequence content) {
        setContent(content,Gravity.CENTER);
        return this;
    }

    /**
     *<br> Description: 设置内容
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/21 17:58
     * @param content
     *                  内容
     * @param gravity
     *                  内容布局方向
     * @return
     */
    public SecondTipDialog setContent(CharSequence content, int gravity) {
        if (TextUtils.isEmpty(content)) {
            return this;
        }
        TextView txtMessage = (TextView) findViewById(R.id.txtMessage);
        txtMessage.setGravity(gravity);
        txtMessage.setTextColor(0xFF212121);
        txtMessage.setText(content);
        txtMessage.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     *<br> Description: 设置确定按钮
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/21 17:58
     * @param text
     *                  按钮文本
     * @return
     */
    public SecondTipDialog setConfirm(CharSequence text) {
        setConfirm(text,0);
        return this;
    }

    /**
     *<br> Description: 设置确定按钮,倒计时
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/21 17:59
     * @param text
     *                  按钮文本
     * @param time
     *                  倒计时时间
     * @return
     */
    public SecondTipDialog setConfirm(CharSequence text, int time) {
        if (TextUtils.isEmpty(text)) {
            confirmFlag = false;
            return this;
        }

        confirmFlag = true;
        Button confirmBtn = (Button) findViewById(R.id.btnOk);
        confirmBtn.setVisibility(View.VISIBLE);
        confirmBtn.setText(text);

        if (time > 0) {
            setCutDownFunction(confirmBtn, text, time);
        }

        setBtnBackground();
        return this;
    }

    /**
     *<br> Description: 设置倒计时功能
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/21 17:59
     */
    private SecondTipDialog setCutDownFunction(final Button confirmBtn, final CharSequence text, final int time) {

        confirmBtn.setText(text + "(" + time + ")");
        confirmBtn.setEnabled(false);

        Observable.interval(1, 1, TimeUnit.SECONDS)
                .take(time)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        int count = (int) (time - aLong - 1);
                        if (count > 0) {
                            confirmBtn.setText(text + "(" + count + ")");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        confirmBtn.setText(text);
                        confirmBtn.setEnabled(true);
                    }
                });
        return this;
    }

    /**
     *<br> Description: 设置取消按钮
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/21 17:59
     * @param text
     *                  按钮文本
     * @return
     */
    public SecondTipDialog setCancel(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            cancelFlag = false;
            return this;
        }

        cancelFlag = true;
        TextView cancelBtn = (TextView) findViewById(R.id.btnCancel);
        cancelBtn.setText(text);
        cancelBtn.setVisibility(View.VISIBLE);
        cancelBtn.setTextColor(0xFF212121);
        setBtnBackground();
        return this;
    }

    /**
     *<br> Description: 设置按钮背景，适配窗口圆角
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/21 18:00
     */
    private void setBtnBackground() {
        if (cancelFlag && confirmFlag) {
            findViewById(R.id.btnCancel).setBackgroundResource(R.drawable.bg_bottom_left_rounded_box_selector);
            findViewById(R.id.btnOk).setBackgroundResource(R.drawable.bg_bottom_right_rounded_box_selector);
            return;
        }

        if (cancelFlag) {
            findViewById(R.id.btnCancel).setBackgroundResource(R.drawable.bg_bottom_rounded_box_selector);
        }

        if (confirmFlag) {
            findViewById(R.id.btnOk).setBackgroundResource(R.drawable.bg_bottom_rounded_box_selector);
        }
    }

    /**
     *<br> Description: 设置按钮点击事件
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/21 18:00
     */
    public SecondTipDialog setOnClickListener(final OnConfirmListener listener) {
        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.clickCancel();
                }
            }
        });

        findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.clickConfirm();
                }
            }
        });
        return this;
    }

    public interface OnConfirmListener {
        void clickConfirm();

        void clickCancel();
    }

    /**
     * <br> ClassName:   DialogUtil
     * <br> Description: OnConfirmListener简单实例
     * <br>
     * <br> Author:      谢文良
     * <br> Date:        2017/8/22 10:09
     */
    public static class SimpleOnConfirmListener implements OnConfirmListener {

        @Override
        public void clickConfirm() {

        }

        @Override
        public void clickCancel() {

        }
    }
}
