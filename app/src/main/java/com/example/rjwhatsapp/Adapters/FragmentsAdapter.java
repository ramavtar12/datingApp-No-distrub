package com.example.rjwhatsapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rjwhatsapp.Fragments.ChatsFragment;

public class FragmentsAdapter extends FragmentPagerAdapter {
    public FragmentsAdapter(@NonNull FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position){
        switch (position) {
            case 0:
                return new ChatsFragment();

            default:
                return new ChatsFragment();
        }
    }

    @Override
    public int getCount(){
        return 3;
    }



}
