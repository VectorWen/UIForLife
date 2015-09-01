package com.vector.uiforlife.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.vector.uiforlife.R;
import com.vector.uiforlife.view.WheelDate;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class TimeDateActivity extends BaseActivity implements WheelDate.OnDateChangeListener{

    @InjectView(R.id.show_content) TextView mShowContent;
    private Time time = new Time();
    private PopupWindow mWheelDatePopupWindow;
    private WheelDate mWheelDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        time.parse3339("2555-02-02");
        time.set(2147443200000l);
        long s = Long.MAX_VALUE;
        long mills = time.toMillis(true);
        long millsFalse = time.toMillis(false);
        String date3339 = time.format3339(true);


        setContentView(R.layout.activity_time_date);
        ButterKnife.inject(this);
        mWheelDate = WheelDate.createWithContext(this);
        mWheelDate.setOnDateChangeListener(this);
        mWheelDatePopupWindow = new PopupWindow(mWheelDate.getContentView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mWheelDatePopupWindow.setFocusable(true);
        mWheelDatePopupWindow.setOutsideTouchable(true);
        mWheelDatePopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    @Override
    public void onDateChange(long date) {
        time.set(date);
        mShowContent.setText(time.format3339(true));
    }

    @OnClick(R.id.wheel_date)
    void wheelDateClick(View v){
        mWheelDate.refresh();
        mWheelDatePopupWindow.showAtLocation(mShowContent, Gravity.BOTTOM,0,0);
    }

    @OnClick(R.id.new_time)
    void newTimeClick(View v){
        time = new Time();
        mShowContent.setText(getStringForTime(time));
    }

    @OnClick(R.id.now_time)
    void nowTimeClick(View v){
        time = new Time();
        time.set(System.currentTimeMillis());
        mShowContent.setText(getStringForTime(time));
    }

    @OnClick(R.id.format)
    void formatClick(View v){
        time = new Time();
        time.set(System.currentTimeMillis());
        mShowContent.setText(time.format("%Y/%m/%d %H:%M:%S"));
    }
  @OnClick(R.id.ymd)
    void ymdClick(View v){
        time = new Time();
        time.set(System.currentTimeMillis());
        mShowContent.setText(time.year+" "+time.month+" "+time.monthDay);
    }
  @OnClick(R.id.time_zone_cct)
    void cctClick(View v){
      time = new Time();
      time.set(System.currentTimeMillis());
      time.switchTimezone("Asia/Taipei");
      mShowContent.setText(getStringForTime(time));
    }

  @OnClick(R.id.time_zone_utc)
    void utcClick(View v){
      time = new Time();
      time.switchTimezone("UTC");
      time.set(System.currentTimeMillis());
      mShowContent.setText(getStringForTime(time));
    }

    @OnClick(R.id.to_millis)
    void getMillisClick(){
        time = new Time();
        time.set(23,4,2015);
        long millis = time.toMillis(false);
        mShowContent.setText(millis+"");
    }
    @OnClick(R.id.parse)
    void parseClick(){
        time = new Time();
        time.parse("20140902T000000");
        time.set(time.toMillis(false));
        mShowContent.setText(getStringForTime(time));
    }

    private String getStringForTime(Time time){
        StringBuilder builder = new StringBuilder();
        builder.append("TimeZone:").append(time.timezone).append("\n");
        builder.append("默认的时间格式:\n2445:").append(time.format2445()).append("\n3339 true:").
                append(time.format3339(true)).append("\n3339 false:").append(time.format3339(false)).append("\n");
        builder.append("星期").append(time.weekDay).append("\n");
        return builder.toString();
    }
}
