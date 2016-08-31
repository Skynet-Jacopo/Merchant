package cn.usmaker.merchant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.usmaker.merchant.merchantRegister.MerchantRegisterActivity1;
import cn.usmaker.merchant.commons.ActivityUtils;
import cn.usmaker.merchant.companyRegister.CompanyRegisterActivity1;
import cn.usmaker.merchant.components.CircleImageView;
import cn.usmaker.merchant.workerRegister.WorkerRegisterActivity1;

/**
 * 公司,工人,商家注册界面
 */
public class RegisterLoginActivity extends AppCompatActivity {

    @Bind(R.id.iv_logo)
    CircleImageView mIvLogo;
    @Bind(R.id.llayout_merchant_register)
    LinearLayout    mLlayoutMerchantRegister;
    @Bind(R.id.llayout_company_register)
    LinearLayout    mLlayoutCompanyRegister;
    @Bind(R.id.llayout_worker_register)
    LinearLayout    mLlayoutWorkerRegister;

    ActivityUtils mActivityUtils ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_register_login);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_logo, R.id.llayout_merchant_register, R.id.llayout_company_register, R.id.llayout_worker_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_logo:
                mActivityUtils.showToast("logo");
                break;
            case R.id.llayout_merchant_register:
                mActivityUtils.showToast("商家注册");
                mActivityUtils.startActivity(MerchantRegisterActivity1.class);
                break;
            case R.id.llayout_company_register:
                mActivityUtils.showToast("公司注册");
                mActivityUtils.startActivity(CompanyRegisterActivity1.class);
                break;
            case R.id.llayout_worker_register:
                mActivityUtils.showToast("工人注册");
                mActivityUtils.startActivity(WorkerRegisterActivity1.class);
                break;
        }
    }
}
