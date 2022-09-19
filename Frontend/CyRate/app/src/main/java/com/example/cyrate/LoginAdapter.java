package com.example.cyrate;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {
    final Context ctx;
    int totalTabs;

    public LoginAdapter(FragmentManager fm, Context ctx, int totalTabs) {
        super(fm);
        this.ctx = ctx;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new LoginTabFragment();
            case 1:
                return new SignUpTabFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
