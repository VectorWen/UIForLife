package com.vector.uiforlife.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.vector.uiforlife.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/7/29.
 */
public class LeftSlideFragment extends Fragment {

    @InjectView(R.id.list_view)
    SwipeMenuListView mListView;

    private CustomAdapter mAdapter;
    private List<String> mItems;

    private View mParent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mParent == null) {
            mParent = inflater.inflate(R.layout.fragment_list_view_left_slide, null, false);
            ButterKnife.inject(this, mParent);
            mAdapter = new CustomAdapter();
            mListView.setAdapter(mAdapter);
            mListView.setMenuCreator(mCreator);
        }
        if (mParent != null) {
            ViewGroup parentContainer = (ViewGroup) mParent.getParent();
            if (parentContainer != null) {
                parentContainer.removeView(mParent);
            }
        }

        mItems = new ArrayList<>(10);
        mItems.add("天才1");
        mItems.add("天才2");
        mItems.add("天才3");
        mItems.add("天才4");
        mItems.add("天才5");
        mItems.add("天才6");
        mItems.add("天才7");
        mItems.add("天才8");
        mItems.add("天才9");
        mItems.add("天才10");
        return mParent;
    }


    public void setItems(List<String> items) {
        mItems = items;
        mAdapter.notifyDataSetInvalidated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private SwipeMenuCreator mCreator = new SwipeMenuCreator() {

        @Override
        public void create(SwipeMenu menu) {
            // create "open" item
            SwipeMenuItem openItem = new SwipeMenuItem(
                    getActivity());
            // set item background
            openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                    0xCE)));
            // set item width
            openItem.setWidth(160);
            // set item title
            openItem.setTitle("Open");
            // set item title fontsize
            openItem.setTitleSize(18);
            // set item title font color
            openItem.setTitleColor(Color.WHITE);
            // add to menu
            menu.addMenuItem(openItem);

            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(
                    getActivity());
            // set item background
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                    0x3F, 0x25)));
            // set item width
            deleteItem.setWidth(160);
            openItem.setTitle("Delete");
            openItem.setTitleSize(18);
            deleteItem.setTitleColor(Color.RED);
            // add to menu
            menu.addMenuItem(deleteItem);
        }
    };

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mItems == null ? 0 : mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_left_slide_drawer_layout, null, false);
                viewHolder = new ViewHolder(convertView, position, mItems.get(position));
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.setData(mItems.get(position));
            return convertView;
        }

        class ViewHolder {

            int mPosition;
            String mItem;
            TextView mItemTxt;

            ViewHolder(View view, int position, String item) {
                mPosition = position;
                mItem = item;
                mItemTxt = (TextView) view.findViewById(R.id.item);
            }

            void setData(String item) {
                mItemTxt.setText(item);
            }
        }
    }
}
