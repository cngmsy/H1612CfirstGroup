package com.jiyun.qcloud.dashixummoban.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class RegistActivity extends BaseActivity {
    @BindView(R.id.e_number)
    EditText eNumber;
    @BindView(R.id.e_code)
    EditText eCode;
    @BindView(R.id.gain)
    Button gain;
    @BindView(R.id.regist)
    Button regist;
    @BindView(R.id.land)
    Button land;
    private String str;
    private String psd;
    //private EventHandler eventHandler;

    @Override
    protected void initData() {
//        eventHandler = new EventHandler() {
//            public void afterEvent(int event, int result, Object data) {
//                Message message = myHandler.obtainMessage(0x00);
//                message.arg1 = event;
//                message.arg2 = result;
//                message.obj = data;
//                myHandler.sendMessage(message);
//            }
//        };
//
//        cn.smssdk.SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.regist_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.land, R.id.gain})
    public void onViewClicked(View view) {

//        switch (view.getId()) {
//            case R.id.gain:
//                str = eNumber.getText().toString();
//                if (null == str || "".equals(str) || str.length() != 11) {
//                    Toast.makeText(this, "电话号码输入有误", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                cn.smssdk.SMSSDK.getVerificationCode("86", str);
//                regist.setClickable(false);
//                new Thread() {
//                    @Override
//                    public void run() {
//                        int totalTime = 60;
//                        for (int i = 0; i < totalTime; i++) {
//                            Message message = myHandler.obtainMessage(0x01);
//                            message.arg1 = totalTime - i;
//                            myHandler.sendMessage(message);
//                            try {
//                                sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        myHandler.sendEmptyMessage(0x02);
//                    }
//                }.start();
//                break;
//            case R.id.land:
//                psd = eCode.getText().toString();
//                if (null != psd && psd.length() == 4) {
//                    cn.smssdk.SMSSDK.submitVerificationCode("86", str,eCode.getText().toString());
//                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "密码长度不正确", Toast.LENGTH_SHORT).show();
//                    Log.e("TAG","密码长度不正确");
//                }
//                break;
//        }
//    }
//    Handler myHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0x00:
//                    int event = msg.arg1;
//                    int result = msg.arg2;
//                    Object data = msg.obj;
//                    Log.e("TAG", "result : " + result + ", event: " + event + ", data : " + data);
//                    if (result == cn.smssdk.SMSSDK.RESULT_COMPLETE) {
//                        if (event == cn.smssdk.SMSSDK.EVENT_GET_VERIFICATION_CODE) {
//                            Toast.makeText(RegistActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
//                        } else if (event == cn.smssdk.SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                            Log.e("TAG", "提交验证码成功");
//                            Toast.makeText(RegistActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            Log.e("TAG", data.toString());
//                        }
//                    } else {
//                        try {
//                            Throwable throwable = (Throwable) data;
//                            throwable.printStackTrace();
//                            JSONObject object = new JSONObject(throwable.getMessage());
//                            String des = object.optString("detail");
//                            int status = object.optInt("status");
//                            Log.e("TAG", "status: " + status + ", detail: " + des);
//                            if (status > 0 && !TextUtils.isEmpty(des)) {
//                                Toast.makeText(RegistActivity.this, des, Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    break;
//                case 0x01:
//                    regist.setText("重新发送(" + msg.arg1 + ")");
//                    break;
//                case 0x02:
//                    regist.setText("获取验证码");
//                    regist.setClickable(true);
//                    break;
//            }
//        }
//    };
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //销毁
//        cn.smssdk.SMSSDK.unregisterEventHandler(eventHandler);
    }
}
