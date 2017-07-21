package com.jhworks.jhbasedemo.holder;

import android.view.View;
import android.widget.TextView;

import com.jhworks.jhbase.base.BaseAdapter;
import com.jhworks.jhbasedemo.R;

/**
 * @apiNote
 * @since 2017/7/21
 * <p>
 * author: jacksonliao
 */
public class Main2Holder extends BaseAdapter.WrapperHolder<String> {
    private TextView mItemTv;

    public Main2Holder(View itemView) {
        super(itemView);
        mItemTv = (TextView) itemView.findViewById(R.id.item_tv);
    }

    @Override
    public void bindData(String data) {
        super.bindData(data);
        if (data == null) return;

        mItemTv.setText(data);
    }
}
