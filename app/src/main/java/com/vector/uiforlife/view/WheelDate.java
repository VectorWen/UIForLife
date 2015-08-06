package com.vector.uiforlife.view;

import android.content.Context;

import com.vector.uiforlife.R;

import butterknife.InjectView;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/8/6.
 */
public class WheelDate {


    @InjectView(R.id.entry_time_year)
    WheelView mYear;
    @InjectView(R.id.entry_time_month)
    WheelView mMonth;
    @InjectView(R.id.entry_time_day)
    WheelView mDay;

    private Context mContext;

    private WheelDate(){}

    public static WheelDate createWithContext(Context context){
        return null;
    }



}
