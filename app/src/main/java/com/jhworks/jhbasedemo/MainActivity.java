package com.jhworks.jhbasedemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jhworks.jhbase.base.BaseActivity;
import com.jhworks.jhbase.utils.LogUtils;
import com.jhworks.jhbase.utils.ToastUtils;
import com.jhworks.jhbasedemo.fragment.HomeFragment;
import com.jhworks.jhbasedemo.fragment.Main2Fragment;
import com.jhworks.jhbasedemo.fragment.Main3Fragment;

import java.util.List;

public class MainActivity extends BaseActivity {

    private BottomNavigationView mBottomNav;


    private BottomNavigationView.OnNavigationItemSelectedListener mItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.bottom_nav_1:
                            showFragment(HomeFragment.class.getSimpleName());
                            break;
                        case R.id.bottom_nav_2:
                            showFragment(Main2Fragment.class.getSimpleName());
                            break;
                        case R.id.bottom_nav_3:
                            showFragment(Main3Fragment.class.getSimpleName());
                            break;
                    }
                    return true;
                }
            };


    private void showFragment(String tag) {
        if (TextUtils.isEmpty(tag)) return;

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        Fragment targetFragment = fm.findFragmentByTag(tag);
        if (targetFragment == null) {
            if (HomeFragment.class.getSimpleName().equals(tag)) {
                targetFragment = new HomeFragment();
            } else if (Main2Fragment.class.getSimpleName().equals(tag)) {
                targetFragment = new Main2Fragment();
            } else if (Main3Fragment.class.getSimpleName().equals(tag)) {
                targetFragment = new Main3Fragment();
            }
            transaction.add(R.id.fragment, targetFragment, tag);
        }

        List<Fragment> fragments = fm.getFragments();
        for (Fragment temp : fragments) {
            if (targetFragment == null) continue;
            LogUtils.i(" =" + temp + "---fragment=" + targetFragment);
            if (temp == targetFragment && temp.getTag().equals(tag)) {
                transaction.show(temp);
            } else {
                transaction.hide(temp);
            }
        }
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtils.debug(BuildConfig.DEBUG);

        mBottomNav = (BottomNavigationView) findViewById(R.id.bottom_nav);

        mBottomNav.setOnNavigationItemSelectedListener(mItemSelectedListener);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("标题");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_reorder_while);
//        toolbar.setLogo(R.drawable.ic_reorder_while);

        showFragment(HomeFragment.class.getSimpleName());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_search) {
            ToastUtils.show(this, "搜索....");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
