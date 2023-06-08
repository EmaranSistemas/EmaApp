package com.emransac.emaapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.emransac.emaapp.Fragments.DetailFragment;
import com.emransac.emaapp.Fragments.InvFragment;
import com.emransac.emaapp.Fragments.StoreFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StoreFragment();
            case 1:
                return new DetailFragment();
            case 2:
                return new InvFragment();
            default:
                return new StoreFragment();
        }
    }

    @Override
    public int getCount() { // Trả về số lượng tab
        return 4;
    }

    // Set title cho fragment
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Home";
                break;
            case 1:
                title = "Favorite";
                break;
            case 2:
                title = "History";
                break;
            default:
                title = "Home";
                break;
        }
        return title;
    }
}
