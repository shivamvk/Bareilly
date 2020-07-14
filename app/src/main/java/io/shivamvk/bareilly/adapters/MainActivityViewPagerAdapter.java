package io.shivamvk.bareilly.adapters;


import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import io.shivamvk.bareilly.fragments.HomeFragment;
import io.shivamvk.bareilly.fragments.ProfileFragment;
import io.shivamvk.bareilly.fragments.QueriesFragment;
import io.shivamvk.bareilly.fragments.SettingsFragment;

public class MainActivityViewPagerAdapter extends FragmentStatePagerAdapter {
    private int NUM_ITEMS = 4;
    private SparseArray<Fragment> mPageReferences;

    public MainActivityViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        mPageReferences = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = HomeFragment.Companion.newInstance();
                mPageReferences.put(position, fragment);
                return fragment;
            case 1:
                fragment = QueriesFragment.Companion.newInstance();
                mPageReferences.put(position, fragment);
                return fragment;
            case 2:
                fragment = ProfileFragment.Companion.newInstance();
                mPageReferences.put(position, fragment);
                return fragment;
            case 3:
                fragment = SettingsFragment.Companion.newInstance();
                mPageReferences.put(position, fragment);
                return fragment;
            default:
                fragment = HomeFragment.Companion.newInstance();
                return fragment;
        }
    }

    public Fragment getFragment(int position) {
        try {
            return mPageReferences.get(position);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            mPageReferences.remove(position);
            Fragment fragment = (Fragment) object;
            View view = fragment.getView();
            container.removeView(view);
        } catch (Exception e) {
        }
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}