package wangjing.shareprefrenceutil.global;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public abstract class DefaultGlobal implements IGlobal {
    @Override
    public String getImgCacheDir() {
        return "";
    }

//    @Override
//    public DbUtils getDbUtils() {
//        return DbUtils.create(getContext());
//    }

    @Override
    public Map<String, String> getConfig() {
        return null;
    }

    //获取url配置的map
    public Map<String,String> getHttpUrlMap(){
        return new HashMap<>();
    }

    public String getClientId(){
        return null;
    }

    @Override
    public Map<String, Class> getActivitys() {
        return new HashMap<>();
    }

    @Override
    public Map<String, Integer> getDrawbles() {
        return new HashMap<>();
    }

    @Override
    public Context getCurrentActivity() {
        return null;
    }
}
