package com.jhworks.jhbasedemo.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jhworks.jhbase.base.BaseAdapter;
import com.jhworks.jhbasedemo.R;
import com.jhworks.jhbasedemo.module.Post;
//import com.jhworks.jhbasedemo.utils.GlideApp;

/**
 * @apiNote
 * @since 2017/7/21
 * <p>
 * author: jacksonliao
 */
public class HomeHolder extends BaseAdapter.WrapperHolder<Post> {
    private ImageView mItemIv;
    private LinearLayout.LayoutParams mParams;

    public HomeHolder(View itemView) {
        super(itemView);
        mItemIv = (ImageView) itemView.findViewById(R.id.item_iv);
        mParams = (LinearLayout.LayoutParams) mItemIv.getLayoutParams();
    }

    @Override
    public void bindData(Post data) {
        super.bindData(data);
        if (data == null) return;

        mParams.width = data.width;
        mParams.height = data.height;


//        GlideApp.with(mContext)
//                .load(data.imageUrl)
//                .into(mItemIv);
    }
}
