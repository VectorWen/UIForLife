package com.vector.uiforlife.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.vector.uiforlife.R;
import com.vector.uiforlife.adapter.PinnedHeaderAdapter;
import com.vector.uiforlife.view.BladeView;
import com.vector.uiforlife.view.PinnedHeaderListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/6/2.
 */
public class PinnedHeaderActivity extends Activity {

    private ViewHolder mViewHolder;
    private String[] mHeaderContent;
    private List<String> mHeaderSections;
    private List<Integer> mHeaderPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinned_header);
        mViewHolder = new ViewHolder(getWindow().getDecorView());
        initData();
    }

    private void initData() {
        mHeaderContent = new String[]{"1","2","3","4","5","6","7","8","9","10"
        ,"1","2","3","4","5","6","7","8","9","10"
        ,"1","2","3","4","5","6","7","8","9","10"
        ,"1","2","3","4","5","6","7","8","9","10"
        ,"1","2","3","4","5","6","7","8","9","10"};
        mHeaderSections = new ArrayList<>();
        mHeaderSections.add("1");
        mHeaderSections.add("2");
        mHeaderSections.add("3");
        mHeaderSections.add("4");
        mHeaderSections.add("5");
        mHeaderPosition = new ArrayList<>();
        mHeaderPosition.add(0);
        mHeaderPosition.add(10);
        mHeaderPosition.add(20);
        mHeaderPosition.add(30);
        mHeaderPosition.add(40);
        mViewHolder.setData(mHeaderContent,mHeaderSections,mHeaderPosition);
    }

    class ViewHolder implements AdapterView.OnItemClickListener{
        @InjectView(R.id.friends_display)
        PinnedHeaderListView friendsDisplay;
        @InjectView(R.id.friends_myletterlistview)
        BladeView friendsMyletterlistview;
        private PinnedHeaderAdapter mAdapter;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
            friendsDisplay.setOnItemClickListener(this);
        }

        private void setData( String[] headerContent, List<String> headerSections,
                              List<Integer> headerPosition){
            if(mAdapter == null){
                mAdapter = new PinnedHeaderAdapter(PinnedHeaderActivity.this,headerContent,headerSections,headerPosition);
            }
            friendsDisplay.setAdapter(mAdapter);
            friendsDisplay.setOnScrollListener(mAdapter);
            friendsDisplay.setPinnedHeaderView(getLayoutInflater().inflate(
                    R.layout.activity_listview_head, friendsDisplay, false));
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(PinnedHeaderActivity.this,"position = "+position,Toast.LENGTH_LONG).show();
        }
    }
}


