package com.vector.uiforlife.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author vector.huang
 * @version V1.0
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date 2015/7/29.
 */
public class Tips {

    public static void toast(Context context,String msg){
        toast(context,msg,Toast.LENGTH_SHORT);
    }
    public static void toast(Context context,String msg,int length){
        Toast.makeText(context,msg,length).show();
    }
}
