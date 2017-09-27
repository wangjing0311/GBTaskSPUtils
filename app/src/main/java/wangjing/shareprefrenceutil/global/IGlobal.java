package wangjing.shareprefrenceutil.global;

import android.content.Context;
import android.view.View;

import java.util.Map;

public interface IGlobal {
    /**
     * @return
     *
     * @description 获取Context对象
     */
    Context getContext();

    /**
     * @return
     *
     * @description 获取用户的uuid
     */
    String getUUID();

    /**
     * @return
     *
     * @description 获取accesstoken
     */
    String getAccessToken();

    /**
     * @param configName
     *
     * @return
     *
     * @description 根据配置获取url地址
     */
    String getServiceUrl(String configName);

    /**
     * @description token失效，重新登录
     */
    void tokenInvalidReLogin();

    /**
     * @return
     *
     * @description 获取图片本地缓存地址，bitmapUtils使用到
     */
    String getImgCacheDir();

    /**
     * @return
     *
     * @description 获取数据库, 目前只有base中的下载模块使用
     */
//    DbUtils getDbUtils();

    /**
     * @return
     *
     * @description V10接口验证参数:通过登录获得的sessionid
     */
    String getAccessSession();

    /**
     * V10接口验证参数
     *
     * @param cToken 验证类型 <br/>‘A’: A类验证<br/>‘B’: B类验证
     *
     * @return
     */
    String getToken(char cToken);

    String getHttpCacheDir();

    <T extends View> void addCommonParam(Map<String, String> params);

    // 给图片url加签名
//    String addSign(View container, String uri, BitmapDisplayConfig displayConfig);

    //获取gradle中的一些配置信息
    Map<String,String>  getConfig();

    //获取url配置的map
    Map<String,String> getHttpUrlMap();

    //获取个推所需的clientid
    String getClientId();

    //获取页面层的一些activity类
    Map<String,Class>  getActivitys();

    //获取界面层图片资源
    Map<String,Integer> getDrawbles();

    String getApplicationId();

    void reCommitTaskScore();

    //获取当前Activity对象
    Context getCurrentActivity();

}
