package com.vector.uiforlife.fragment;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.vector.uiforlife.R;
import com.vector.uiforlife.view.GradualCircleView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/8/13.
 */
public class NavigationFragment extends Fragment implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.view_pager)
    ViewPager mViewPager;
    @InjectView(R.id.btn)
    Button mStartBtn;
    private View mParent;
    @InjectViews({R.id.circle_1, R.id.circle_2, R.id.circle_3, R.id.circle_4})
    GradualCircleView[] mCircles;

    private ArrayList<View> mViews;
    private CustomPagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mParent = inflater.inflate(R.layout.fragment_navigation, null, false);
        ButterKnife.inject(this, mParent);
        mCircles[0].setAlpha(255);
        mViews = new ArrayList<>(4);
        int[] imgs = new int[]{R.drawable.ic_navigation0, R.drawable.ic_navigation1, R.drawable.ic_navigation2, R.drawable.ic_navigation3};
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgs[i]);
            mViews.add(imageView);

        }
        mAdapter = new CustomPagerAdapter();
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
        return mParent;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 3) return;
        int alpha = (int) Math.floor(positionOffset * 255);
        mCircles[position + 1].setAlpha(alpha);
        mCircles[position].setAlpha(255 - alpha);

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 3) {
            Animation alphaAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_enter);
            mStartBtn.startAnimation(alphaAnimation);
            mStartBtn.setVisibility(View.VISIBLE);
        }else{
            if (mStartBtn.getVisibility() == View.VISIBLE) {
                Animation alphaAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_exit);
                mStartBtn.startAnimation(alphaAnimation);
                mStartBtn.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class CustomPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViews == null ? 0 : mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(mViews.get(position));
            return mViews.get(position);
        }

    }
}
