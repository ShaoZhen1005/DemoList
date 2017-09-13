package com.p4u.android;

import android.app.Application;
import android.content.pm.PackageManager;
import android.provider.Settings;

import com.p4u.android.utils.LogUtils;

/**
 * Created by ShaoZhen-PC on 2017/9/13.
 */

public class App extends Application{
    private String TAG = this.getClass().getName();

    public static App mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }


    /**
     * 获取Application
     */
    public static App getApp() {
        return mApplication;
    }

    /**
     * Android ID
     *
     * @return
     */
    public String getAndroidID() {
        return Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * SN
     */

    public static String getSN() {
        return android.os.Build.SERIAL;
    }

    /**
     * 系统版本号
     *
     * @return
     */
    public static String getSoftwareVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 软件版本号
     *
     * @return
     */
    public String getAppVersion() {

        try {
            String string = String.valueOf(getPackageManager().getPackageInfo(
                    getPackageName(), 0).versionCode);
            String versionName = String.valueOf(getPackageManager()
                    .getPackageInfo(getPackageName(), 0).versionName);
            LogUtils.i(TAG, "当前版本号:" + string + ",当前版本名称:" + versionName);
            return string;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
