package com.jhworks.jhbasedemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jhworks.jhbase.base.BaseAdapter;
import com.jhworks.jhbasedemo.R;
import com.jhworks.jhbasedemo.holder.Main2Holder;

/**
 * @apiNote
 * @since 2017/7/21
 * <p>
 * author: jacksonliao
 */
public class Main2Adapter extends BaseAdapter<String> {
    public Main2Adapter(Context context) {
        super(context);
    }

    @Override
    public WrapperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Main2Holder(mInflater.inflate(R.layout.main2_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(WrapperHolder holder, final int position) {
        if (holder instanceof Main2Holder) {
            Main2Holder main2Holder = (Main2Holder) holder;
            main2Holder.bindData(mDataList.get(position));
            main2Holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(v, mDataList.get(position), position);
                    }
                }
            });
        }
    }
}
