package cn.usmaker.merchant.workerRegister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.usmaker.merchant.R;
import cn.usmaker.merchant.commons.ActivityUtils;
import cn.usmaker.merchant.commons.AssetsUtils;
import cn.usmaker.merchant.components.HMActionBar;

public class WorkerRegisterActivity2 extends AppCompatActivity {

    @Bind(R.id.action_bar)
    HMActionBar mActionBar;
    @Bind(R.id.edt_store_name)
    EditText    mEdtStoreName;
    @Bind(R.id.edt_manage_kinds)
    EditText    mEdtWorkType;
    @Bind(R.id.tv_manage_choice)
    TextView    mTvManageChoice;
    @Bind(R.id.edt_phone_number)
    EditText    mEdtPhoneNumber;
    @Bind(R.id.edt_telephone_number)
    EditText    mEdtTelephoneNumber;
    @Bind(R.id.tv_province)
    TextView    mTvProvince;
    @Bind(R.id.tv_city)
    TextView    mTvCity;
    @Bind(R.id.tv_district)
    TextView    mTvDistrict;
    @Bind(R.id.tv_address_choice)
    TextView    mTvAddressChoice;
    @Bind(R.id.edt_address_details)
    EditText    mEdtAddressDetails;
    @Bind(R.id.btn_next_move)
    Button      mBtnNextMove;

    ActivityUtils mActivityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_worker_register2);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setActionBar();
    }

    private void setActionBar() {
        mActionBar.setTitle("工人注册");
        mActionBar.setLeftImageResource(R.drawable.back);
        mActionBar.setLeftImageOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.tv_manage_choice, R.id.tv_address_choice, R.id.btn_next_move})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_manage_choice:
                mActivityUtils.showToast("选择工种");
                break;
            case R.id.tv_address_choice:
                mActivityUtils.showToast("选择地址");
                //选用第三方实现省市区三级联动
                ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
                String json = AssetsUtils.readText(this, "city.json");
                data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
                AddressPicker picker = new AddressPicker(this, data);
                picker.setSelectedItem("山东", "济南", "高新");
                //picker.setHideProvince(true);//加上此句举将只显示地级及县级
                //picker.setHideCounty(true);//加上此句举将只显示省级及地级
                picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                    @Override
                    public void onAddressPicked(String province, String city, String county) {
                        mActivityUtils.showToast(province + city + county);
                        mTvProvince.setText(province);
                        mTvCity.setText(city);
                        mTvDistrict.setText(county);
                    }
                });
                picker.show();
                break;
            case R.id.btn_next_move:
                mActivityUtils.showToast("下一步");
                mActivityUtils.startActivity(WorkerRegisterActivity3.class);
                break;
        }
    }
}