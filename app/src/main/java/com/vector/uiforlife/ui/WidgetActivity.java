package com.vector.uiforlife.ui;

import android.os.Bundle;
import android.widget.RadioButton;

import com.vector.uiforlife.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class WidgetActivity extends BaseActivity {

    @InjectView(R.id.radioButton)
    RadioButton radioButton;
    @InjectView(R.id.radioButton2)
    RadioButton radioButton2;
    @InjectView(R.id.radioButton3)
    RadioButton radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);
        ButterKnife.inject(this);
    }
    @OnClick(R.id.click_false)
    void clickFalse(){
        radioButton.setClickable(false);
        radioButton.setTextColor(0xff00ff00);
        radioButton.setBackgroundColor(0xffffffff);
        radioButton2.setClickable(false);
        radioButton2.setTextColor(0xff00ff00);
        radioButton2.setBackgroundColor(0xffffffff);
        radioButton3.setClickable(false);
        radioButton3.setTextColor(0xff00ff00);
        radioButton3.setBackgroundColor(0xffffffff);
    }
    @OnClick(R.id.click_true)
    void clickTrue(){
        radioButton.setClickable(true);
        radioButton.setTextColor(getResources().getColorStateList(R.color.radio_button_color));
        radioButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_button_bg));
        radioButton2.setClickable(true);
        radioButton2.setTextColor(getResources().getColorStateList(R.color.radio_button_color));
        radioButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_button_bg));
        radioButton3.setClickable(true);
        radioButton3.setTextColor(getResources().getColorStateList(R.color.radio_button_color));
        radioButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.radio_button_bg));
    }
}
