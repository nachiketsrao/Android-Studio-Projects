package com.company.myinformationbook.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.company.myinformationbook.fragments.fragmentFrance;
import com.company.myinformationbook.fragments.fragmentItaly;
import com.company.myinformationbook.fragments.fragmentUnitedKingdom;

import static com.company.myinformationbook.fragments.fragmentUnitedKingdom.newInstance;

public class ViewPagerAdapterCountries extends FragmentStateAdapter {

    public ViewPagerAdapterCountries(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public Fragment createFragment(int position) {

        Fragment fragment;

        switch (position)
        {
            case 0:
                fragment = fragmentUnitedKingdom.newInstance();
                break;
            case 1:
                fragment = fragmentFrance.newInstance();
                break;
            case 2:
                fragment = fragmentItaly.newInstance();
                break;
            default:
                return null;
        }

        return fragment;
    }

    @Override
    public int getItemCount() {

        return 3;

    }
}
