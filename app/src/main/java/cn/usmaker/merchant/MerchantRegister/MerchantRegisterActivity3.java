package cn.usmaker.merchant.merchantRegister;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.usmaker.merchant.LoginActivity;
import cn.usmaker.merchant.R;
import cn.usmaker.merchant.commons.ActivityUtils;
import cn.usmaker.merchant.components.HMActionBar;

/**
 * 商家注册第三个界面
 */
public class MerchantRegisterActivity3 extends AutoLayoutActivity {

    private final int REQUEST_CODE_CAMERA  = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
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
        setActionBar();
        initImageLoader(getApplicationContext());
    }
    private void setActionBar() {
        mActionBar.setTitle("门店资质认证");
        mActionBar.setLeftImageResource(R.drawable.back);
        mActionBar.setLeftImageOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    @OnClick({R.id.iv_business_license_pic, R.id.iv_identity_card_front, R.id.iv_identity_card_back, R.id.tv_agreement, R.id.btn_register_completed})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_business_license_pic:
                mActivityUtils.showToast("营业执照");
                getBusinessLicensePic();
                break;
            case R.id.iv_identity_card_front:
                mActivityUtils.showToast("身份证正面");
                getIdentityCardFront();
                break;
            case R.id.iv_identity_card_back:
                mActivityUtils.showToast("身份证反面");
                getIdentityCarBack();
                break;
            case R.id.tv_agreement:
                mActivityUtils.showToast("协议");
                break;
            case R.id.btn_register_completed:
                mActivityUtils.showToast("注册完成");
                mActivityUtils.startActivity(LoginActivity.class);
                break;
        }
    }

    private void getBusinessLicensePic() {
        ActionSheet.createBuilder(getApplicationContext(), getSupportFragmentManager())
                .setCancelButtonTitle("取消(Cancel)")
                .setOtherButtonTitles("打开相册(Open Gallery)", "拍照(Camera)")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }
                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            //相册
                            case 0:
                                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY,
                                        getBusinessLiensePicCallBack);
                                break;
                            //相机
                            case 1:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA,
                                        getBusinessLiensePicCallBack);
                                break;
                            default:
                                break;
                        }
                    }
                })
                .show();
    }

    private void getIdentityCardFront() {
        ActionSheet.createBuilder(getApplicationContext(), getSupportFragmentManager())
                .setCancelButtonTitle("取消(Cancel)")
                .setOtherButtonTitles("打开相册(Open Gallery)", "拍照(Camera)")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }
                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            //相册
                            case 0:
                                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY,
                                        getIdentityCardFrontCallBack);
                                break;
                            //相机
                            case 1:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, getIdentityCardFrontCallBack);
                                break;
                            default:
                                break;
                        }
                    }
                })
                .show();
    }

    private void getIdentityCarBack() {
        ActionSheet.createBuilder(getApplicationContext(), getSupportFragmentManager())
                .setCancelButtonTitle("取消(Cancel)")
                .setOtherButtonTitles("打开相册(Open Gallery)", "拍照(Camera)")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }
                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            //相册
                            case 0:
                                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY,
                                        getIdentityCarBackCallBack);
                                break;
                            //相机
                            case 1:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, getIdentityCarBackCallBack);
                                break;
                            default:
                                break;
                        }
                    }
                })
                .show();
    }

    private GalleryFinal.OnHanlderResultCallback getBusinessLiensePicCallBack =new GalleryFinal
            .OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            Log.d("reqeustCode", "onHanlderSuccess: "+reqeustCode);
            Log.d("reqeustCode", "onHanlderSuccess: "+resultList.get(0).getPhotoPath());
//            ImageLoader.getInstance().displayImage(resultList.get(0).getPhotoPath(),
//                    mIvBusinessLicensePic);
            mIvBusinessLicensePic.setBackgroundColor(Color.red(R.color.red));
            mIvBusinessLicensePic.setImageBitmap(BitmapFactory.decodeFile(resultList.get(0).getPhotoPath()));
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            mActivityUtils.showToast("获取照片失败");
        }
    };
    private GalleryFinal.OnHanlderResultCallback getIdentityCardFrontCallBack =new GalleryFinal
            .OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            Log.d("reqeustCode", "onHanlderSuccess: "+reqeustCode);
            Log.d("reqeustCode", "onHanlderSuccess: "+resultList.get(0).getPhotoPath());
//            ImageLoader.getInstance().displayImage(resultList.get(0).getPhotoPath(),
//                    mIvBusinessLicensePic);
            mIvIdentityCardFront.setBackgroundColor(Color.red(R.color.white));
            mIvIdentityCardFront.setImageBitmap(BitmapFactory.decodeFile(resultList.get(0).getPhotoPath()));
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            mActivityUtils.showToast("获取照片失败");
        }
    };
    private GalleryFinal.OnHanlderResultCallback getIdentityCarBackCallBack =new GalleryFinal
            .OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            Log.d("reqeustCode", "onHanlderSuccess: "+reqeustCode);
            Log.d("reqeustCode", "onHanlderSuccess: "+resultList.get(0).getPhotoPath());
//            ImageLoader.getInstance().displayImage(resultList.get(0).getPhotoPath(),
//                    mIvBusinessLicensePic);
            mIvIdentityCardBack.setBackgroundColor(Color.red(R.color.white));
            mIvIdentityCardBack.setImageBitmap(BitmapFactory.decodeFile(resultList.get(0).getPhotoPath()));
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            mActivityUtils.showToast("获取照片失败");
        }
    };
}
