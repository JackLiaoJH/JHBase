package com.jhworks.jhbasedemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jhworks.jhbase.base.BaseAdapter;
import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbasedemo.R;
import com.jhworks.jhbasedemo.adapter.Main2Adapter;

import java.util.Arrays;
import java.util.List;

/**
 * @apiNote
 * @since 2017/7/21
 * <p>
 * author: jacksonliao
 */
public class Main2Fragment extends BaseFragment implements BaseAdapter.OnItemClickListener<String> {
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_main2;
    }

    @Override
    protected void initView(View rootView, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        Main2Adapter main2Adapter = new Main2Adapter(mContext);
        mRecyclerView.setAdapter(main2Adapter);

        String[] array = getResources().getStringArray(R.array.main2_array);
        List<String> list = Arrays.asList(array);
        main2Adapter.setDataList(list);
        main2Adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, String data, int position) {

    }
}
