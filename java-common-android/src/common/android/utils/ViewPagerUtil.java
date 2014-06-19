package common.android.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import common.basic.utils.ListUtil;

public class ViewPagerUtil {
    public static abstract class ProviderRotate {
        private FragmentStatePagerAdapter fragmentStatePagerAdapter;
        private ViewPager.OnPageChangeListener onPageChangeListener;

        public abstract int getCount();
        public abstract Fragment getItem(int virtualIndex);
        public void destroyItem(ViewGroup container, int position, Object object) { }

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

        public final void notifyDataSetChanged() {
            fragmentStatePagerAdapter.notifyDataSetChanged();
        }
    }

    public static void makeRotate(final ViewPager viewPager, final FragmentManager fragmentManager, final ProviderRotate provider) {
        viewPager.setAdapter(provider.fragmentStatePagerAdapter = new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public int getCount() {
                return provider.getVirtualCount();
            }

            @Override
            public Fragment getItem(final int i) {
                return provider.getItem(i);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);

                provider.destroyItem(container, position, object);
            }
        });
        viewPager.setOnPageChangeListener(provider.onPageChangeListener = new ViewPager.OnPageChangeListener() {

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
