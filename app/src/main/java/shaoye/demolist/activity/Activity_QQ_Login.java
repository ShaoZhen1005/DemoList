package shaoye.demolist.activity;

/**
 * Created by ShaoZhen-PC on 2017/9/1.
 */

public class Activity_QQ_Login {


    /*************************** --↓↓↓-- QQ登陆 --↓↓↓-- ***************************/
    /**
     * QQ请求登陆接口
     */

    // QQ登陆
//        if (mTencent == null) {
//            mTencent = Tencent.createInstance(URLs.AppId, getActivity());
//        }

//    public void touristLogin() {
//        if (!mTencent.isSessionValid()) {
//            mTencent.login(this, "all", loginListener);
//            isServerSideLogin = false;
//            LogUtils.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
//        } else {
//            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
//                mTencent.logout(getContext());
//                mTencent.login(this, "all", loginListener);
//                isServerSideLogin = false;
//                LogUtils.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
//                return;
//            }
//            mTencent.logout(getContext());
//            updateUserInfo();
//        }
//    }

//
//    @Override
//        public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        LogUtils.d(TAG, "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
//        if (requestCode == Constants.REQUEST_LOGIN ||
//                requestCode == Constants.REQUEST_APPBAR) {
//            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    IUiListener loginListener = new BaseUiListener() {
//        @Override
//        protected void doComplete(JSONObject values) {
//            LogUtils.d(TAG, "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
//            initOpenidAndToken(values);
//            updateUserInfo();
//        }
//    };
//
//    Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0) {
//                JSONObject response = (JSONObject) msg.obj;
//                if (response.has("nickname")) {
//                    try {
//                        // 用户名
////                        mUserInfo.setVisibility(android.view.View.VISIBLE);
////                        mUserInfo.setText(response.getString("nickname"));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            } else if (msg.what == 1) {
//                Bitmap bitmap = (Bitmap) msg.obj;
//                // 头像
////                mUserLogo.setImageBitmap(bitmap);
////                mUserLogo.setVisibility(android.view.View.VISIBLE);
//            }
//        }
//    };
//
//    /**
//     * 获取用户信息
//     */
//    private void updateUserInfo() {
//        if (mTencent != null && mTencent.isSessionValid()) {
//            IUiListener listener = new IUiListener() {
//
//                @Override
//                public void onError(UiError e) {
//
//                }
//
//                @Override
//                public void onComplete(final Object response) {
//                    Message msg = new Message();
//                    msg.obj = response;
//                    msg.what = 0;
//                    mHandler.sendMessage(msg);
//                    new Thread() {
//
//                        @Override
//                        public void run() {
//                            JSONObject json = (JSONObject) response;
//                            if (json.has("figureurl")) {
//                                Bitmap bitmap = null;
//                                try {
//                                    bitmap = QQLoginUtil.getbitmap(json.getString("figureurl_qq_2"));
//                                } catch (JSONException e) {
//
//                                }
//                                Message msg = new Message();
//                                msg.obj = bitmap;
//                                msg.what = 1;
//                                mHandler.sendMessage(msg);
//                            }
//                        }
//
//                    }.start();
//                }
//
//                @Override
//                public void onCancel() {
//
//                }
//            };
//            mInfo = new UserInfo(getContext(), mTencent.getQQToken());
//            mInfo.getUserInfo(listener);
//        }
//    }
//
//    public static void initOpenidAndToken(JSONObject jsonObject) {
//        try {
//            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
//            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
//            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
//            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
//                    && !TextUtils.isEmpty(openId)) {
//                mTencent.setAccessToken(token, expires);
//                mTencent.setOpenId(openId);
//            }
//        } catch(Exception e) {
//        }
//    }
//
//    private class BaseUiListener implements IUiListener {
//
//        @Override
//        public void onComplete(Object response) {
//            if (null == response) {
//                LogUtils.d(TAG,"response.toString() : " + response.toString());
//                new AlertDialog.Builder(getActivity()).setTitle("登录").setMessage(response.toString())
//                        .setNegativeButton("知道了", null).create().show();
//                return;
//            }
//            doComplete((JSONObject)response);
//        }
//
//        protected void doComplete(JSONObject values) {
//
//        }
//
//        @Override
//        public void onError(UiError e) {
//
//            LogUtils.d(TAG,"onError: " + e.errorDetail);
//            QQLoginUtil.dismissDialog();
//        }
//
//        @Override
//        public void onCancel() {
//            LogUtils.d(TAG,"onCancel: ");
//            QQLoginUtil.dismissDialog();
//            if (isServerSideLogin) {
//                isServerSideLogin = false;
//            }
//        }
//    }

    /*************************** --↑↑↑-- QQ登陆 --↑↑↑-- ***************************/
}
