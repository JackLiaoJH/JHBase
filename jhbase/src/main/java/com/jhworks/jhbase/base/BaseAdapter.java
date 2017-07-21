package com.jhworks.jhbase.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * desc:
 * author: jacksonliao
 * email: 583125288@qq.com
 * date: 17/5/8
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.WrapperHolder> {

    protected LayoutInflater mInflater;
    protected List<T> mDataList;
    protected Context mContext;

    protected OnItemClickListener<T> mOnItemClickListener;

    public BaseAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setDataList(List<T> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    @Override
    public WrapperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WrapperHolder(new View(mContext));
    }

    @Override
    public abstract void onBindViewHolder(WrapperHolder holder, int position);

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    protected T getItem(int position) {
        if (mDataList == null || position < 0 || position >= mDataList.size()) return null;
        return mDataList.get(position);
    }

    public static class WrapperHolder<T> extends RecyclerView.ViewHolder {
        protected Context mContext;
        public WrapperHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
        }


        public void bindData(T data) {
        }
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, T data, int position);
    }
}
