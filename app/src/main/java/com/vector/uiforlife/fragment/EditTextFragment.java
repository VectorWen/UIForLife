package com.vector.uiforlife.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.SuggestionSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.vector.uiforlife.R;
import com.vector.uiforlife.logger.Logger;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/9/1.
 */
public class EditTextFragment extends Fragment{

    private Logger logger = Logger.getLogger(EditTextFragment.class);

    private View mParent;
    private EditText mEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParent = inflater.inflate(R.layout.fragment_edit_text,null,false);
        mEditText = (EditText) mParent.findViewById(R.id.edit_text);
        mEditText.addTextChangedListener(mTextWatcher);
//        mEditText.setFilters(new InputFilter[]{new InputFilter() {
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                logger.d("filter()-- %s -- %d -- %d -- %s -- %d -- %d",source,start,end,dest,dstart,dend);
//                source = source.toString().trim();
//                String[] strs = dest.toString().split("  ");
//                if(strs.length>0&&strs[strs.length-1].length() == 4){
//                    return "  "+source;
//                }
//                return source;
//            }
//        }});
        return mParent;
    }

    private SpannableString mContentText;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            logger.d("beforeTextChanged() -- %s -- %d -- %d -- %d",s,start,count,after);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            logger.d("onTextChanged() -- %s -- %d -- %d", s, start, count);
            mContentText = addSpace(s);
        }

        @Override
        public void afterTextChanged(Editable s) {
            logger.d("afterTextChanged() -- %s", s);
            mEditText.removeTextChangedListener(this);
            mEditText.setText(mContentText);
            mEditText.addTextChangedListener(this);
        }
    };

    public static SpannableString addSpace(CharSequence text){
        SpannableString spannable = new SpannableString(text);
        int indexs = text.length() - 1; //[0...n]  [1]   [4]  [9]
        for(int i=indexs/4;i > 0;i--){ //[0...n/4] [0]   [1]  [2,1]
            SpannableString spaceSpan = new SpannableString(" "+text.subSequence(i*4,i*4+1));
            ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
            SuggestionSpan suggestionSpan = new SuggestionSpan(this,"  ",)
            spannable.setSpan(spaceSpan,i*4,i*4+1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }



}
