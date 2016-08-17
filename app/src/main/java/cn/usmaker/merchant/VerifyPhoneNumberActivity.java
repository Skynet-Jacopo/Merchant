package cn.usmaker.merchant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.usmaker.merchant.commons.ActivityUtils;
import cn.usmaker.merchant.components.HMActionBar;

public class VerifyPhoneNumberActivity extends AppCompatActivity {

    @Bind(R.id.actionBar)
    HMActionBar mActionBar;
    @Bind(R.id.edt_phone_number)
    EditText    mEdtPhoneNumber;
    @Bind(R.id.edt_auth_code)
    EditText    mEdtAuthCode;
    @Bind(R.id.btn_get_auth_code)
    Button      mBtnGetAuthCode;
    @Bind(R.id.btn_next_move)
    Button      mBtnNextMove;

    ActivityUtils mActivityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_verify_phone_number);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setActionBar();
    }
    private void setActionBar() {
        mActionBar.setTitle("验证手机号");
        mActionBar.setLeftImageResource(R.drawable.back);
        mActionBar.setLeftImageOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @OnClick({R.id.btn_get_auth_code, R.id.btn_next_move})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_auth_code:
                mActivityUtils.showToast("获取手机验证码");
                break;
            case R.id.btn_next_move:
                mActivityUtils.showToast("下一步");
                mActivityUtils.startActivity(ResetPassWordActivity.class);
                break;
        }
    }
}
