package com.jhworks.jhbasedemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.jhworks.jhbasedemo.R;

/**
 * @apiNote
 * @since 2017/7/25
 * <p>
 * author: jacksonliao
 */
public class ErrorView extends FrameLayout {
    public ErrorView(@NonNull Context context) {
        this(context, null);
    }

    public ErrorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ErrorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.error_view_layout,this);
    }
}
