package cn.usmaker.merchant.forgetPassword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.usmaker.merchant.LoginActivity;
import cn.usmaker.merchant.R;
import cn.usmaker.merchant.commons.ActivityUtils;
import cn.usmaker.merchant.components.HMActionBar;

public class ResetPassWordActivity extends AppCompatActivity {

    @Bind(R.id.actionBar)
    HMActionBar mActionBar;
    @Bind(R.id.edt_pass_word)
    EditText    mEdtPassWord;
    @Bind(R.id.edt_pass_word_again)
    EditText    mEdtPassWordAgain;
    @Bind(R.id.btn_reset_complete)
    Button      mBtnResetComplete;

    ActivityUtils mActivityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_reset_pass_word);
        setActionBar();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }
    private void setActionBar() {
        mActionBar.setTitle("重置密码");
        mActionBar.setLeftImageResource(R.drawable.back);
        mActionBar.setLeftImageOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @OnClick(R.id.btn_reset_complete)
    public void onClick() {
        mActivityUtils.showToast("重置完成");
        mActivityUtils.startActivity(LoginActivity.class);
    }
}
