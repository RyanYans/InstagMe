package com.paisheng.instagme.common.pic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.chrisbanes.photoview.PhotoView;
import com.paisheng.instagme.R;
import com.paisheng.instagme.common.arouter.PicExplorerRouterConstant;
import com.paisheng.instagme.utils.AnimationHelper;
import com.paisheng.lib.picture.loader.Monet;


/**
 * @author: yuanbaining
 * @Filename: PicExplorerActivity
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/2/1 9:32
 */

@Route(path = PicExplorerRouterConstant.PIC_EXPLORER_PAGE)
public class PicExplorerActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener {

    public static final String PICT_URL = "pict_url";
    private PhotoView mPhotoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picexplorer);
        overridePendingTransition(R.anim.pic_in, android.R.anim.fade_out);

        findViewById(R.id.root).setOnClickListener(this);
        mPhotoView = (PhotoView) findViewById(R.id.photo_view);
        mPhotoView.setOnClickListener(this);
        mPhotoView.setOnLongClickListener(this);

        loadPict();
    }

    private void loadPict() {
        String pictUrl = getIntent().getStringExtra(PICT_URL);
        if (!TextUtils.isEmpty(pictUrl)) {
            Monet.get(this).load(pictUrl).into(mPhotoView);
        }
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AnimationHelper.setFadeTransition(this);
    }

    @Override
    public boolean onLongClick(View view) {
        // 先预留，后续保存图片
        return false;
    }
}
