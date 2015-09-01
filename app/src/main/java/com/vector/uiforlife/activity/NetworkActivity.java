package com.vector.uiforlife.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vector.uiforlife.R;
import com.vector.uiforlife.entity.Login;
import com.vector.uiforlife.network.Bloodsucker;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class NetworkActivity extends BaseActivity implements Bloodsucker.BloodsuckerListener,Bloodsucker.BloodsuckerErrorListener{

    public static final String OK =  "{'code':200,'msg':'ok','token':'token'}";
    public static final String not_login =  "{'code':201,'请登录':'error','token':'token'}";
    public static final String error_404 =  "{'code':404,'msg':'都不知道你要找什么','token':'token'}";
    public static final String error_3000 =  "{'code':30000,'msg':'参数错误呀','token':'token'}";
    public static String  test= OK; //测试数据

    @InjectView(R.id.txt)
    TextView txt;


    @OnClick(R.id.ok)
    void ok(View view){
        test = OK;
        mBloodsucker.get(0,"http://www.baidu.com",this,this);
    }
    @OnClick(R.id.not_login)
    void notLogin(View view){
        test = not_login;
        mBloodsucker.get(0,"http://www.baidu.com",this,this);
    }
    @OnClick(R.id.error_404)
    void error404(View view){
        test = error_404;
        mBloodsucker.get(0,"http://www.baidu.com",this,this);
    }
    @OnClick(R.id.error_30000)
    void error300000(View view){
        test = error_3000;
        mBloodsucker.get(0,"http://www.baidu.com",this,this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ButterKnife.inject(this);
        mBloodsucker.get(0,"http://www.baidu.com",this,this);
    }

    @Override
    public void ok(int requestCode, String json) {
        Login login = Login.createInstanceByJson(json);
        txt.setText(login.getToken());
    }

    @Override
    public void error(int requestCode, int errorCode) {
        txt.setText("error"+ errorCode);
    }
}
