package cn.usmaker.merchant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.usmaker.merchant.commons.ActivityUtils;
import cn.usmaker.merchant.components.Code;
import cn.usmaker.merchant.components.HMActionBar;

/**
 * 找回密码界面
 */
public class ForgetPassWordActivity extends AppCompatActivity {

    @Bind(R.id.actionBar)
    HMActionBar mActionBar;
    @Bind(R.id.edt_account_number)
    EditText    mEdtAccountNumber;
    @Bind(R.id.edt_auth_code)
    EditText    mEdtAuthCode;
    @Bind(R.id.iv_showCode)
    ImageView   mIvShowCode;
    @Bind(R.id.btn_next_move)
    Button      mBtnNextMove;
    //产生的验证码
    private String realCode;

    ActivityUtils mActivityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_forget_pass_word);
        ButterKnife.bind(this);
//下划线
//        mText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setActionBar();
        //将验证码用图片的形式显示出来
        mIvShowCode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }

    private void setActionBar() {
        mActionBar.setTitle("找回密码");
        mActionBar.setLeftImageResource(R.drawable.back);
        mActionBar.setLeftImageOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.iv_showCode, R.id.btn_next_move})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_showCode:
                mActivityUtils.showToast("更换验证码");
                mIvShowCode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
            case R.id.btn_next_move:
                String phoneCode = mEdtAuthCode.getText().toString().trim().toLowerCase();
                if (phoneCode.equals(realCode)) {
                    mActivityUtils.showToast("验证码正确");
                } else {
                    mActivityUtils.showToast("验证码错误");
                    mActivityUtils.startActivity(VerifyPhoneNumberActivity.class);
                }
                break;
        }
    }
}
