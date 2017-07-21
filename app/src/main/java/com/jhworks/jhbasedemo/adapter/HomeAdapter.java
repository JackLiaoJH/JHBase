package com.jhworks.jhbasedemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jhworks.jhbase.base.BaseAdapter;
import com.jhworks.jhbasedemo.R;
import com.jhworks.jhbasedemo.holder.HomeHolder;
import com.jhworks.jhbasedemo.module.Post;

/**
 * @apiNote
 * @since 2017/7/21
 * <p>
 * author: jacksonliao
 */
public class HomeAdapter extends BaseAdapter<Post> {
    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public WrapperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeHolder(mInflater.inflate(R.layout.home_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(WrapperHolder holder, final int position) {
        if (holder instanceof HomeHolder) {
            HomeHolder homeHolder = (HomeHolder) holder;
            Post post = mDataList.get(position);
            homeHolder.bindData(post);
            homeHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
