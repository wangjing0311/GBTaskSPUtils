package wangjing.shareprefrenceutil.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import wangjing.shareprefrenceutil.global.GB;
import wangjing.shareprefrenceutil.task.Task;
import wangjing.shareprefrenceutil.task.TaskExecutor;
import wangjing.shareprefrenceutil.task.TaskUtils;

public class SharePrefrenceUtils {
    private static final String GLOBLE_SETTING = "app_settings";
    private static final String GLOBLE_TEXT = "app_text";

    private static final TaskExecutor executor = TaskUtils.createSerialExecutor("SharePrefrenceUtils");
    private static final String UPLOAD_LIST_SECTION = "UPLOAD_LIST_SECTION";

    public static void putString(final String key, final String value) {
        executor.post(new Task() {

            @Override
            public void run() throws Exception {
                getShare().edit().putString(key, value).commit();
            }
        });
    }

    public static String getString(String key, String defValue) {
        return getShare().getString(key, defValue);
    }

    public static void putText(final String key, final String value) {
        final String es = ZipStringUtils.zip(value);
        getShareText().edit().putString(key, es).commit();
    }

    public static void putTextSync(final String key, final String value) {
        final String es = ZipStringUtils.zip(value);
        getShareText().edit().putString(key, es).commit();
    }

    public static String getText(String key, String defValue) {
        String es = getShareText().getString(key, defValue);
        if (StringUtils.isEmptyOrNull(es) || StringUtils.isEqualCaseInsensitive(defValue, es))
            return defValue;
        String str = null;
        try {
            str = ZipStringUtils.unzip(es);
        } catch (Exception e) {
            Log.e("SharePrefrenceUtils ", e.getMessage());
            return defValue;
        }
        return str;
    }

    public static void putInt(final String key, final int value) {
        executor.post(new Task() {

            @Override
            public void run() throws Exception {
                getShare().edit().putInt(key, value).commit();
            }
        });
    }

    public static int getInt(String key, int defValue) {
        return getShare().getInt(key, defValue);
    }

    public static void putLong(final String key, final long value) {
        executor.post(new Task() {

            @Override
            public void run() throws Exception {
                getShare().edit().putLong(key, value).commit();
            }
        });
    }

    public static long getLong(String key, long defValue) {
        return getShare().getLong(key, defValue);
    }

    public static void putBoolean(final String key, final Boolean value) {
        executor.post(new Task() {

            @Override
            public void run() throws Exception {
                getShare().edit().putBoolean(key, value).commit();
            }
        });
    }

    public static boolean getBoolean(String key, Boolean defValue) {
        return getShare().getBoolean(key, defValue);
    }

    private static synchronized SharedPreferences getShare() {
        return GB.getCallBack().getContext().getSharedPreferences(GLOBLE_SETTING, Context.MODE_PRIVATE);
    }

    private static synchronized SharedPreferences getShareText() {
        return GB.getCallBack().getContext().getSharedPreferences(GLOBLE_TEXT, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferences getUpdataListSection() {
        return GB.getCallBack().getContext().getSharedPreferences(UPLOAD_LIST_SECTION, Context.MODE_PRIVATE);
    }

}
