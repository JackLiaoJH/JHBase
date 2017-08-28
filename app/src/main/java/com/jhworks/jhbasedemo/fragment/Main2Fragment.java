package com.jhworks.jhbasedemo.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jhworks.jhbase.base.BaseAdapter;
import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbasedemo.R;
import com.jhworks.jhbasedemo.activity.AVCloundActivity;
import com.jhworks.jhbasedemo.activity.DialogActivity;
import com.jhworks.jhbasedemo.activity.KeyBoardActivity;
import com.jhworks.jhbasedemo.activity.QiNiuActivity;
import com.jhworks.jhbasedemo.adapter.Main2Adapter;
import com.jhworks.jhbasedemo.mvp.MvpDemoActivity;

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

    private static final String EXTRA_CUSTOM_TABS_SESSION = "android.support.customtabs.extra.SESSION";

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
        switch (position) {
            case 0:
                openActivity(QiNiuActivity.class);
                break;
            case 1:
                openActivity(AVCloundActivity.class);
                break;
            case 2:
                openActivity(DialogActivity.class);
                break;
            case 3:
                String url = "https://paul.kinlan.me/";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(getActivity(), Uri.parse(url));

                break;
            case 4:
                openActivity(KeyBoardActivity.class);
                break;
            case 5:
                openActivity(MvpDemoActivity.class);
                break;
        }
    }
}
