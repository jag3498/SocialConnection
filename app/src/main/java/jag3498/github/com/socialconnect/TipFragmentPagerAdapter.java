package jag3498.github.com.socialconnect;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TipFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public TipFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FriendTipsFragment();
        } else if (position == 1){
            return new WorkTipsFragment();
        } else {
            return new RomanticTipsFragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }


    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Friends";
            case 1:
                return "Work";
            case 2:
                return "Romantic";
            default:
                return null;
        }
    }


}
