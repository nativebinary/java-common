package common.android.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import common.basic.logs.Logger;
import common.basic.utils.ListUtil;

public class ViewPagerUtil {
    public static abstract class ProviderRotate {
        public abstract Context getContext();
        public abstract FragmentManager getFragmentManager();
        public abstract Fragment getFragment(int virtualIndex);
        public abstract int getCount();

        public int getVirtualCount() {
            return getCount() + 2;
        }

        public int getVirtualIndexFromIndex(int index) {
            return ListUtil.getIndexByInfiniteIndexWithOffset(getCount(), index, 1);
        }

        public int getIndexFromVirtualIndex(int virtualIndex) {
            return ListUtil.getIndexByInfiniteIndexWithOffset(getCount(), virtualIndex, -1);
        }
    }

    public static void makeRotate(final ViewPager viewPager, final ProviderRotate provider) {
        viewPager.setAdapter(new FragmentStatePagerAdapter(provider.getFragmentManager()) {
            @Override
            public Fragment getItem(final int i) {
                return provider.getFragment(i);
            }

            @Override
            public int getCount() {
                return provider.getCount() + 2;
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Logger.e(position);
                if (viewPager.getCurrentItem() == provider.getVirtualCount() - 1) {
                    viewPager.setCurrentItem(1, false);
                }
                if (viewPager.getCurrentItem() == 0) {
                    viewPager.setCurrentItem(provider.getVirtualCount() - 2, false);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(1);
    }
}
