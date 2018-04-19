package com.yochio.copy_conference2018;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import javax.annotation.Nullable;

/**
 * Created by yochio on 2018/03/05.
 */

public abstract class FragmentStateNullablePagerAdapter extends FragmentStatePagerAdapter {

    public FragmentStateNullablePagerAdapter(FragmentManager fm) { super(fm); }

    public void setPrimaryItem(ViewGroup container, int position, @Nullable Object object) {
        super.setPrimaryItem(container, position, object);
    }

}
