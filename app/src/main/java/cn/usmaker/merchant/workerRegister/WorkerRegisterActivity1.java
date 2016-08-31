package cn.usmaker.merchant.workerRegister;

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
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.usmaker.merchant.R;
import cn.usmaker.merchant.commons.ActivityUtils;

public class WorkerRegisterActivity1 extends AppCompatActivity implements TextWatcher {

    @Bind(R.id.edt_phone_number)
    EditText  mEdtPhoneNumber;
    @Bind(R.id.edt_auth_code)
    EditText  mEdtAuthCode;
    @Bind(R.id.btn_get_auth_code)
    TextView  mBtnGetAuthCode;
    @Bind(R.id.edt_pass_word)
    EditText  mEdtPassWord;
    @Bind(R.id.edt_pass_word_again)
    EditText  mEdtPassWordAgain;
    @Bind(R.id.btn_next_move)
    Button    mBtnNextMove;
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @BindColor(R.color.red)
    int       colorBtnFocus;
    @BindColor(R.color.btn_not_focus)
    int       colorBtnNotFocus;

    ActivityUtils mActivityUtils;
//    private String mPassWord;
//    private String mPassWordAgain;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_worker_register1);
        ButterKnife.bind(this);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        //验证内容是否正确
        VerifyEditContent();

    }

    private void VerifyEditContent() {
        mEdtPhoneNumber.addTextChangedListener(this);
        mEdtAuthCode.addTextChangedListener(this);
        mEdtPassWord.addTextChangedListener(this);
        mEdtPassWordAgain.addTextChangedListener(this);
        mBtnNextMove.setBackgroundColor(colorBtnNotFocus);
    }

    @OnClick({R.id.btn_get_auth_code, R.id.btn_next_move, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_get_auth_code:
                mActivityUtils.showToast("获取验证码");
                break;
//            case R.id.btn_next_move:
//
//                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String mPhoneNumber   = mEdtPhoneNumber.getText().toString().trim();
        String mAuthCode      = mEdtAuthCode.getText().toString().trim();
        String mPassWord = mEdtPassWord.getText().toString().trim();
        String mPassWordAgain = mEdtPassWordAgain.getText().toString().trim();
        if (TextUtils.isEmpty(mPhoneNumber) || TextUtils.isEmpty(mAuthCode) || TextUtils.isEmpty
                (mPassWord) || TextUtils.isEmpty(mPassWordAgain)) {
            mBtnNextMove.setBackgroundColor(colorBtnNotFocus);
            mBtnNextMove.setEnabled(false);
        } else {
            mBtnNextMove.setBackgroundColor(colorBtnFocus);
            mBtnNextMove.setEnabled(true);
            mBtnNextMove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mEdtPassWord.getText().toString().trim().equals(mEdtPassWordAgain.getText()
                            .toString().trim())){
                        mActivityUtils.startActivity(WorkerRegisterActivity2.class);
                    }else {
                        mActivityUtils.showToast("两次密码不一致,请重新输入");
                    }
                }
            });
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
