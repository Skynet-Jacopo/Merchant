package cn.usmaker.merchant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.usmaker.merchant.commons.ActivityUtils;
import cn.usmaker.merchant.components.HMActionBar;

public class MerchantRegisterActivity3 extends AutoLayoutActivity {

    @Bind(R.id.action_bar)
    HMActionBar mActionBar;
    @Bind(R.id.edt_register_number)
    EditText    mEdtRegisterNumber;
    @Bind(R.id.edt_license_name)
    EditText    mEdtLicenseName;
    @Bind(R.id.iv_business_license_pic)
    ImageView   mIvBusinessLicensePic;
    @Bind(R.id.iv_identity_card_front)
    ImageView   mIvIdentityCardFront;
    @Bind(R.id.iv_identity_card_back)
    ImageView   mIvIdentityCardBack;
    @Bind(R.id.tv_agreement)
    TextView    mTvAgreement;
    @Bind(R.id.btn_register_completed)
    Button      mBtnRegisterCompleted;

    ActivityUtils mActivityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_merchant_register3);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_business_license_pic, R.id.iv_identity_card_front, R.id.iv_identity_card_back, R.id.tv_agreement, R.id.btn_register_completed})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_business_license_pic:
                mActivityUtils.showToast("营业执照");
                break;
            case R.id.iv_identity_card_front:
                mActivityUtils.showToast("身份证正面");
                break;
            case R.id.iv_identity_card_back:
                mActivityUtils.showToast("身份证反面");
                break;
            case R.id.tv_agreement:
                mActivityUtils.showToast("协议");
                break;
            case R.id.btn_register_completed:
                mActivityUtils.showToast("注册完成");
                break;
        }
    }
}
