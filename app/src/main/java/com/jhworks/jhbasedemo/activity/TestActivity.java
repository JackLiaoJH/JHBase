package com.jhworks.jhbasedemo.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jhworks.jhbase.base.BaseActivity;
import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbase.base.BaseFragmentActivity;
import com.jhworks.jhbase.utils.ToastUtils;
import com.jhworks.jhbasedemo.BaseChildActivity;
import com.jhworks.jhbasedemo.R;
import com.jhworks.jhbasedemo.fragment.TestFragment;

/**
 * @apiNote
 * @since 2017/7/17
 * <p>
 * author: jacksonliao
 */
public class TestActivity extends BaseChildActivity {

//    @Override
//    protected void initToolBar(Toolbar toolbar) {
//        super.initToolBar(toolbar);
//        toolbar.setTitle(TestActivity.class.getSimpleName());
//    }

    @Override
    protected BaseFragment getFragment() {
        return new TestFragment();
    }
/*
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fragment, new TestFragment(), TestFragment.class.getSimpleName())
//                .commit();


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ToastUtils.show(this, "请求权限开始....");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        }
    }*/


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
