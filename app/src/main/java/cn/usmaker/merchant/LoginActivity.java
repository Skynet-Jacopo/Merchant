package cn.usmaker.merchant;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.usmaker.merchant.commons.ActivityUtils;
import cn.usmaker.merchant.components.CircleImageView;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity implements TextWatcher {

    @Bind(R.id.iv_head)
    CircleImageView mIvHead;
    @Bind(R.id.edt_user_name)
    EditText        mEdtUserName;
    @Bind(R.id.edt_pass_word)
    EditText        mEdtPassWord;
    @Bind(R.id.tv_forget_pass_word)
    TextView        mTvForgetPassWord;
    @Bind(R.id.btn_login)
    Button          mBtnLogin;
    @Bind(R.id.llayout_register)
    LinearLayout    mLlayoutRegister;

    private ActivityUtils mActivityUtils;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_login);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
//        mIvHead.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.rest));
        verifyEdtContent();
    }

    @OnClick({R.id.iv_head, R.id.tv_forget_pass_word, R.id.btn_login, R.id.llayout_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                mActivityUtils.showToast("头像");
                break;
            case R.id.tv_forget_pass_word:
                mActivityUtils.showToast("忘记密码");
                mActivityUtils.startActivity(ForgetPassWordActivity.class);
                break;
//            case R.id.btn_login:
//                mActivityUtils.showToast("登录");
//
//                break;
            case R.id.llayout_register:
                mActivityUtils.showToast("注册");
                mActivityUtils.startActivity(RegisterLoginActivity.class);
                break;
        }
    }

    private void verifyEdtContent() {
        mEdtUserName.addTextChangedListener(this);
        mEdtPassWord.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEdtUserName.getText().toString().trim())
                        ||TextUtils.isEmpty(mEdtPassWord.getText().toString().trim())){
                    mActivityUtils.showToast("请输入正确的用户名和密码");
                }else {
                    mActivityUtils.startActivity(MerchantHomePagerActivity.class);
                }
            }
        });
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
