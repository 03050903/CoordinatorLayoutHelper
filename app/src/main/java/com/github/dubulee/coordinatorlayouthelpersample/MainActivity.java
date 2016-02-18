package com.github.dubulee.coordinatorlayouthelpersample;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.github.dubulee.coordinatorlayouthelper.CoordinatorLayoutHelperViewPager;
import com.github.dubulee.coordinatorlayouthelper.HeaderLayout;
import com.github.dubulee.coordinatorlayouthelper.HeaderLayoutBehavior;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private final int CONTENTS_VIEW_POSITION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        
        HeaderLayout headerLayout = (HeaderLayout) findViewById(R.id.header_layout);

        CoordinatorLayoutHelperViewPager viewPager = (CoordinatorLayoutHelperViewPager) findViewById(R.id.viewpager);

        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        headerLayout.setFixedScrollRange((int) getResources().getDimension(R.dimen.tab_height));

        HeaderLayoutBehavior headerLayoutBehavior = (HeaderLayoutBehavior) ((CoordinatorLayout.LayoutParams) headerLayout.getLayoutParams()).getBehavior();

        headerLayoutBehavior.setScrollRootViewPosition(CONTENTS_VIEW_POSITION);
//        headerLayoutBehavior.setScrollViewPosition(SCROLL_VIEW_POSITION);
    }

    private void setupViewPager(ViewPager viewPager) {
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(ListViewPageFragment.createInstance(), "List View");
        pagerAdapter.addFragment(GridViewPageFragment.createInstance(), "Grid View");
        pagerAdapter.addFragment(ListViewPageFragment.createInstance(), "List View");
        pagerAdapter.addFragment(GridViewPageFragment.createInstance(), "Grid View");
        pagerAdapter.addFragment(ListViewPageFragment.createInstance(), "List View");
        pagerAdapter.addFragment(GridViewPageFragment.createInstance(), "Grid View");

        viewPager.setAdapter(pagerAdapter);
    }

    static class PagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}
