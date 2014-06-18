package common.android;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import common.android.extensions.FragmentActivityBase;
import common.android.utils.ViewPagerUtil;
import common.android.utils.ViewUtil;
import common.basic.utils.ListUtil;

import java.util.List;

public class ActivityViewPagerTest extends FragmentActivityBase {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager_test);

        final ViewPager viewPager = ViewUtil.findViewPager(this, R.id.viewPager);
        final List<String> list = ListUtil.create("A", "B", "C", "D", "E");

        ViewPagerUtil.makeRotate(viewPager, new ViewPagerUtil.ProviderRotate() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Context getContext() {
                return ActivityViewPagerTest.this;
            }

            @Override
            public FragmentManager getFragmentManager() {
                return getSupportFragmentManager();
            }


            @Override
            public Fragment getFragment(final int virtualIndex) {
                return new Fragment() {
                    @Override
                    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                        final Button button = new Button(getContext());
                        button.setText(list.get(getIndexFromVirtualIndex(virtualIndex)));
                        return button;
                    }
                };
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}