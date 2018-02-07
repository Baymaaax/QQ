package com.example.nyt.qq;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by NYT on 2018/2/6.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<GridView> gridViewList;

    public ViewPagerAdapter(ArrayList<GridView> gridViewList) {
        super();
        this.gridViewList = gridViewList;
    }


    @Override
    public int getCount() {
        return gridViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(gridViewList.get(position));
        return gridViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(gridViewList.get(position));
    }
}
