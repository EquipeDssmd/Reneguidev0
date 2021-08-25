package com.example.reneguidev0;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Fragment_Controller extends FragmentStateAdapter {
    public Fragment_Controller(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new intro2();

            case 2 :
                return new intro3();

        }
        return new intro1();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
