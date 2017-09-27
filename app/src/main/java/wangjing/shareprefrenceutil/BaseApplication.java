package wangjing.shareprefrenceutil;

import android.app.Application;
import android.content.Context;
import android.view.View;

import java.util.Map;

import wangjing.shareprefrenceutil.global.DefaultGlobal;
import wangjing.shareprefrenceutil.global.GB;

/**
 * BaseApplication
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化GB.CallBack
        GB.setCallBack(new DefaultGlobal() {
            @Override
            public Context getContext() {
                return BaseApplication.this;
            }

            @Override
            public String getUUID() {
                return null;
            }

            @Override
            public String getAccessToken() {
                return null;
            }

            @Override
            public String getServiceUrl(String configName) {
                return null;
            }

            @Override
            public void tokenInvalidReLogin() {

            }

            @Override
            public String getAccessSession() {
                return null;
            }

            @Override
            public String getToken(char cToken) {
                return null;
            }

            @Override
            public String getHttpCacheDir() {
                return null;
            }

            @Override
            public <T extends View> void addCommonParam(Map<String, String> params) {

            }

            @Override
            public String getApplicationId() {
                return null;
            }

            @Override
            public void reCommitTaskScore() {

            }
        });
    }
}
