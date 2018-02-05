package com.jhworks.jhbasedemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbasedemo.R;
import com.jhworks.jhbasedemo.adapter.HomeAdapter;
import com.jhworks.jhbasedemo.module.Post;

import java.util.ArrayList;
import java.util.List;

import cn.jhworks.utilscore.utils.RandomUtils;

/**
 * @apiNoteØ
 * @since 2017/7/21
 * <p>
 * author: jacksonliao
 */
public class HomeFragment extends BaseFragment {

    private RecyclerView mRecyclerView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);


        String[] array = getResources().getStringArray(R.array.home_array);
        List<Post> list = new ArrayList<>();

        Post post;
        for (String url : array) {
            post = new Post();
            post.imageUrl = url;
            post.width = RandomUtils.getRandom(300, 500);
            post.height = RandomUtils.getRandom(300, 600);
            list.add(post);
        }

        HomeAdapter homeAdapter = new HomeAdapter(mContext);
        mRecyclerView.setAdapter(homeAdapter);
        homeAdapter.setDataList(list);
    }
}
