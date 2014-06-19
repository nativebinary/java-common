package common.android.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import common.basic.utils.ListUtil;

public class ViewPagerUtil {
    public static abstract class ProviderRotate {
        public abstract Fragment getFragment(int virtualIndex);
        public abstract int getCount();

        public void onPageSelected(int position) { }
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
        public void onPageScrollStateChanged(int state) { }

        public final int getVirtualCount() {
            return getCount() + 2;
        }

        public final int getVirtualIndexFromIndex(int index) {
            return ListUtil.getIndexByInfiniteIndexWithOffset(getCount(), index, 1);
        }

        public final int getIndexFromVirtualIndex(int virtualIndex) {
            return ListUtil.getIndexByInfiniteIndexWithOffset(getCount(), virtualIndex, -1);
        }

    }

    public static void makeRotate(final ViewPager viewPager, final FragmentManager fragmentManager, final ProviderRotate provider) {
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(final int i) {
                return provider.getFragment(i);
            }

            @Override
            public int getCount() {
                return provider.getVirtualCount();
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (position == provider.getVirtualCount() - 1) {
                    viewPager.setCurrentItem(1, false);
                    return;
                }

                if (position == 0) {
                    viewPager.setCurrentItem(provider.getVirtualCount() - 2, false);
                    return;
                }

                provider.onPageSelected(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                provider.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                provider.onPageScrollStateChanged(state);
            }
        });
        viewPager.setCurrentItem(1);
    }
}
