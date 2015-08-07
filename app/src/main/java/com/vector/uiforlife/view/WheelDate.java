package com.vector.uiforlife.view;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.vector.uiforlife.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 1,初始化的时间是今天
 *
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/8/6.
 */
public class WheelDate {

    public static int defaultMinYear = 1990;
    private final int[] DAYS_PER_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    @InjectView(R.id.entry_time_year)
    WheelView mWheelViewYear;
    @InjectView(R.id.entry_time_month)
    WheelView mWheelViewMonth;
    @InjectView(R.id.entry_time_day)
    WheelView mWheelViewDay;

    private Context mContext;
    private View mContentView;
    private Time time = new Time();

    private OnDateChangeListener mOnDateChangeListener;

    private long mSelectedDate;//现在看到的时间，保证是选择看到的日期的时间戳，ms 为单位
    private int mMaxYear;//最大的年
    private int mMinYear = defaultMinYear;//最小的年
    private List<String> mYearData;
    private List<String> mMonthData;
    private List<String> mDayData;
    private int mYear;
    private int mMonth;//[0-11]
    private int mDay;//[1-31]

    private WheelDate(Context context){
        time.setToNow();
        mMaxYear = time.year;
        mContext = context;
        mContentView = LayoutInflater.from(context).inflate(R.layout.view_wheel_date,null,false);
        ButterKnife.inject(this, mContentView);
        initData();
        mWheelViewYear.setItems(mYearData);
        mWheelViewYear.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) { //这些回调方法只有在手动滚动的时候才会回调，使用setSelection() 不会触发
                mYear = Integer.parseInt(item);
                resetDayData();
                dateChange();
            }
        });
        mWheelViewMonth.setItems(mMonthData);
        mWheelViewMonth.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                mMonth = Integer.parseInt(item) - 1;
                resetDayData();
                dateChange();
            }
        });
        mWheelViewDay.setItems(mDayData);
        mWheelViewDay.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                mDay = Integer.parseInt(item);
                dateChange();
            }
        });
        refresh();
    }

    /**
     * 初始化三个Wheel 的数据，初始化时间为今天
     */
    private void initData(){
        mMonthData = Arrays.asList(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
        mYearData = new ArrayList<>();
        for (int i = mMaxYear; i >= mMinYear; i--) {
            mYearData.add(i + "");
        }
        time.setToNow();
        mDayData = new ArrayList<>();
        for (int i = 1; i <= getMaxDayNumForMonthAndYear(time.year, time.month); i++) {
            mDayData.add(i + "");
        }
        mYear = time.year;
        mMonth = time.month;
        mDay = time.monthDay;
        mSelectedDate = time.toMillis(false);
    }

    /**
     * 因为不同的年月对应的天数会不同，当年月改变后，需要重设天数
     */
    private void resetDayData() {
        mDayData = new ArrayList<>();
        int maxDay = getMaxDayNumForMonthAndYear(mYear, mMonth);
        for (int i = 1; i <= maxDay; i++) {
            mDayData.add(i + "");
        }
        mWheelViewDay.setItems(mDayData);
        mDay = Math.min(mDay, maxDay);
        mWheelViewDay.setSelection(mDay - 1);
    }

    private int getMaxDayNumForMonthAndYear(int year, int month) {
        if (month != 2) {
            return DAYS_PER_MONTH[month];
        }
        if (isBissextile(year)) {
            return 29;
        }
        return 28;
    }

    private boolean isBissextile(int year) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return true;
            }
        } else {
            if (year % 4 == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 日期改变了
     */
    private void dateChange(){
        time.set(mDay,mMonth,mYear);
        time.normalize(false);
        mSelectedDate = time.toMillis(true);
        Log.d("WheelDate",mSelectedDate+"");
        if(mOnDateChangeListener!=null){
            mOnDateChangeListener.onDateChange(mSelectedDate);
        }
    }

    public static WheelDate createWithContext(Context context){
        return new WheelDate(context);
    }

    public View getContentView(){
        return mContentView;
    }

    public void refresh(){
        mWheelViewYear.setSelection(mMaxYear - mYear);
        mWheelViewMonth.setSelection(mMonth);
        mWheelViewDay.setSelection(mDay -1);
    }


    /**
     * 显示提前到指定时间戳
     * @param date
     */
    public void showDateTo(long date){
        time.set(date);
        mSelectedDate = date;
        mYear = time.year;
        mMonth = time.month;
        mDay = time.monthDay;
        refresh();
    }

    /**
     * get 当前选择的日期
     * @return
     */
    public long getSelectedDate(){
        return mSelectedDate;
    }

    public void setOnDateChangeListener(OnDateChangeListener onDateChangeListener){
        mOnDateChangeListener = onDateChangeListener;
    }

    public interface OnDateChangeListener{
        void onDateChange(long date);
    }

}
