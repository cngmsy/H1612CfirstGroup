package com.jiyun.qcloud.dashixummoban.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017/8/25.
 */

public class InfoActivity extends BaseActivity {
    @BindView(R.id.iv_info_back)
    ImageView ivInfoBack;
    @BindView(R.id.rl_info_head)
    RelativeLayout rlInfoHead;
    @BindView(R.id.et_info_name)
    EditText etInfoName;
    @BindView(R.id.rbtn_man)
    RadioButton rbtnMan;
    @BindView(R.id.rbtn_women)
    RadioButton rbtnWomen;
    @BindView(R.id.rg_info_sex)
    RadioGroup rgInfoSex;
    @BindView(R.id.et_info_phonenum)
    EditText etInfoPhonenum;
    @BindView(R.id.et_info_address)
    TextView etInfoAddress;
    @BindView(R.id.et_info_street)
    EditText etInfoStreet;
    @BindView(R.id.iv_arrow_down)
    ImageView ivArrowDown;
    @BindView(R.id.tv_biaoqian)
    TextView tvBiaoqian;
    @BindView(R.id.activity_info)
    LinearLayout activityInfo;
    @BindView(R.id.btn_info_submit333)
    Button btnInfoSubmit333;
    private PopupWindow popupWindow;
    private RadioGroup rgPopup;
    private String sex = "";

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        rgInfoSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_man:
                        sex = "男";
                        break;
                    case R.id.rbtn_women:
                        sex = "女";
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_info;
    }


    @OnClick({R.id.iv_info_back, R.id.et_info_address, R.id.iv_arrow_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_info_back:
                finish();
                break;
            case R.id.et_info_address:
                startActivity(new Intent(this, MapActivity.class));
                break;
            case R.id.iv_arrow_down:
                View pop = View.inflate(this, R.layout.popup_biaoqian, null);
                rgPopup = (RadioGroup) pop.findViewById(R.id.rg_popup);
                popupWindow = new PopupWindow(pop, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(view, 0, -100);
                rgPopup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.wu:
                                tvBiaoqian.setVisibility(View.VISIBLE);
                                tvBiaoqian.setText("无");
                                popupWindow.dismiss();
                                break;
                            case R.id.home:
                                tvBiaoqian.setVisibility(View.VISIBLE);
                                tvBiaoqian.setText("家");
                                popupWindow.dismiss();
                                break;
                            case R.id.company:
                                tvBiaoqian.setVisibility(View.VISIBLE);
                                tvBiaoqian.setText("公司");
                                popupWindow.dismiss();
                                break;
                            case R.id.shcoll:
                                tvBiaoqian.setVisibility(View.VISIBLE);
                                tvBiaoqian.setText("学校");
                                popupWindow.dismiss();
                                break;
                        }
                    }
                });
                break;
        }
    }

    private void submit() {
        String name = etInfoName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(sex)) {
            Toast.makeText(this, "请选项性别", Toast.LENGTH_SHORT).show();
        }
        String number = etInfoPhonenum.getText().toString();
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(this, "请输入您的电话号码", Toast.LENGTH_SHORT).show();
        }
        String street = etInfoStreet.getText().toString();
        if (TextUtils.isEmpty(street)) {
            Toast.makeText(this, "请选择填入详细地址", Toast.LENGTH_SHORT).show();
        }

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sex) && !TextUtils.isEmpty(number) && !TextUtils.isEmpty(street)) {
            Toast.makeText(this, "添加地址成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    @OnClick(R.id.btn_info_submit333)
    public void onClick() {
        submit();
    }
}
